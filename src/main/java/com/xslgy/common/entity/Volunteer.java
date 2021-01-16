package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author : 玄机
 * @Data : 2020/12/17  下午10:43
 * @Description :
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "volunteer")
@EqualsAndHashCode(callSuper = true)
public class Volunteer extends BaseEntity {
    private String name;
    @Column(name = "sex", length = 4)
    private String sex;
    private Date birthdate;
    @Column(name = "mobile", length = 20)
    private String mobile;
    private String professional;
    private String education;
    @Column(name = "id_card", length = 1024, unique = true)
    private String idCard;
    private String censusRegister;
    private String photoUrl;
    @Column(name = "nation", length = 40)
    private String nation;
    @Column(name = "blood_type", length = 10)
    private String bloodType;
    private String school;
    @Column(name = "politics_type", length = 20)
    private String politicsType;
    private String workUnit;
    private String position;
    private String workAddr;
    private String homeAddr;
    private String areaTeam;
    private String carInfo;
    private String carConfig;
    @Column(name = "skill", length = 255)
    private String skill;
    @Column(name = "reason", length = 255)
    private String reason;
    @Column(name = "free_date", length = 255)
    private String freeDate;
    @Column(name = "activity", length = 255)
    private String activity;
    @Column(name = "introduce_myself", length = 1024)
    private String introduceMyself;
    private Byte deleteFlag;
}
