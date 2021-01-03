package com.xslgy.common.service.impl;

import com.xslgy.common.entity.Member;
import com.xslgy.common.repository.MemberRepository;
import com.xslgy.common.service.MemberService;
import com.xslgy.common.utils.BeanUtils;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.utils.PrivacyUtils;
import com.xslgy.common.vo.MemberVO;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.modules.admin.utils.PasswordUtils;
import com.xslgy.modules.api.vo.MemberLoginVO;
import com.xslgy.modules.api.vo.MemberRegistVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    MemberRepository memberRepository;
    @Resource
    PrivacyUtils privacyUtils;

    @Override
    public PageUtils findAll(String name, String mobile, Pageable pageable) {
        return new PageUtils(memberRepository.findAll(new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                if (!StringUtils.isEmpty(mobile)) {
                    predicates.add(criteriaBuilder.like(root.get("mobile"), "%" + mobile + "%"));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable));
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberVO login(MemberLoginVO memberLoginVO, HttpServletRequest request) {
        MemberVO result = null;
        // 先校验空，然后，校验验证码是否正确
        Member member = getByUsername(memberLoginVO.getUsername());
        if (member != null) {
            if (PasswordUtils.encode(memberLoginVO.getPassword(), member.getSalt()).equals(member.getPassword())) {
                // 密码正确
                result = new MemberVO();
                BeanUtils.copyProperties(member, result);
                // 生成token
                String userAgent = request.getHeader("user-agent");
                String remoteIp = request.getRemoteHost();
                String token = MD5Encoder.encode((memberLoginVO.getUsername() + "|" + memberLoginVO.getPassword() + "|" + userAgent + "|" + remoteIp + "|" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
                request.getSession(true).setAttribute("frontToken", token);
                result.setToken(token);
            } else {
                // 密码不正确

            }
        }
        return result;
    }

    @Override
    public MemberVO regist(MemberRegistVO memberRegistVO) {
        MemberVO result = null;
        // 校验必填项是否为空，如果不为空，则注册，生成用户数据
        Member member = getByUsername(memberRegistVO.getUsername());
        if (member == null) {
            member = new Member();
            BeanUtils.copyProperties(memberRegistVO, member);
            member = save(member);
            result = new MemberVO();
            BeanUtils.copyProperties(member, result);
        } else {
            throw new XSLGYException("该用户名已经存在");
        }
        return result;
    }

    @Override
    public Member getByUsername(String username) {
        return memberRepository.getByUsername(username);
    }

    @Override
    public Member getByMobile(String mobile) {
        return memberRepository.getByMobile(mobile);
    }
}
