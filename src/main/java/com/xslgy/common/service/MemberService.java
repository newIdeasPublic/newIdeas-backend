package com.xslgy.common.service;

import com.xslgy.common.entity.Member;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.MemberLoginVO;
import com.xslgy.common.vo.MemberVO;
import com.xslgy.modules.api.vo.MemberRegistVO;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    PageUtils findAll(String name, String mobile, Pageable pageable);

    Member save(Member member);

    Member getById(Long id);

    void deleteById(Long id);

    MemberVO login(MemberLoginVO memberLoginVO);

    MemberVO regist(MemberRegistVO memberRegistVO);
}
