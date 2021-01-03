package com.xslgy.common.service.impl;

import com.xslgy.common.entity.Member;
import com.xslgy.common.repository.MemberRepository;
import com.xslgy.common.service.MemberService;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.MemberVO;
import com.xslgy.modules.api.vo.MemberLoginVO;
import com.xslgy.modules.api.vo.MemberRegistVO;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    MemberRepository memberRepository;

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
        // 先校验空，然后，校验验证码是否正确

        return null;
    }

    @Override
    public MemberVO regist(MemberRegistVO memberRegistVO) {
        // 校验必填项是否为空，如果不为空，则注册，生成用户数据
        return null;
    }
}
