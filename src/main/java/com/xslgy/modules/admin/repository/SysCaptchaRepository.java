package com.xslgy.modules.admin.repository;

import com.xslgy.core.repository.BaseRepository;
import com.xslgy.modules.admin.entity.SysCaptcha;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface SysCaptchaRepository extends BaseRepository<SysCaptcha, Long> {

    SysCaptcha getSysCaptchaByUuid(String uuid);

    @Modifying
    void deleteSysCaptchaByUuid(String uuid);
}
