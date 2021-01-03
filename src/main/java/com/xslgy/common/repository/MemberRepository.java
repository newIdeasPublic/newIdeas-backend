package com.xslgy.common.repository;

import com.xslgy.common.entity.Member;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends BaseRepository<Member, Long> {

    Member getByUsername(String username);

    Member getByMobile(String mobile);
}
