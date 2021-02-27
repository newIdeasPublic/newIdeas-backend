package com.xslgy.common.service.impl;

import com.xslgy.common.dto.AddSocietyOrgDTO;
import com.xslgy.common.dto.SearchSocietyOrgDTO;
import com.xslgy.common.entity.SocietyOrg;
import com.xslgy.common.repository.SocietyOrgRepository;
import com.xslgy.common.service.SocietyOrgService;
import com.xslgy.common.utils.BeanUtils;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.SocietyOrgVO;
import com.xslgy.core.exception.XSLGYException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午8:32
 * @Description : 社会组织相关服务层实现
 */
@Slf4j
@Service
public class SocietyOrgServiceImpl implements SocietyOrgService {

    @Resource
    private SocietyOrgRepository societyOrgRepository;

    private static final Byte BYTE_ZERO = (byte) 0;
    private static final Byte BYTE_ONE = (byte) 1;

    @Override
    public String addSocietyOrg(AddSocietyOrgDTO addSocietyOrgDTO) {
        log.info("com.xslgy.common.service.impl.SocietyOrgServiceImpl.addSocietyOrg; params: {}", addSocietyOrgDTO);
        SocietyOrg societyOrg = new SocietyOrg();
        BeanUtils.copyProperties(addSocietyOrgDTO, societyOrg);
        societyOrg.setDeleteFlag(BYTE_ZERO);
        try {
            societyOrgRepository.save(societyOrg);
            return "添加成功";
        } catch (Exception e) {
            throw new XSLGYException("添加社会组织失败");
        }
    }

    @Override
    public String updateSocietyOrg(AddSocietyOrgDTO addSocietyOrgDTO) {
        log.info("com.xslgy.common.service.impl.SocietyOrgServiceImpl.updateSocietyOrg; params: {}", addSocietyOrgDTO);
        SocietyOrg societyOrg = societyOrgRepository.findById(addSocietyOrgDTO.getId()).orElseThrow(() -> new XSLGYException("组织不存在"));
        BeanUtils.copyPropertiesIgnoreNull(addSocietyOrgDTO, societyOrg);
        try {
            societyOrgRepository.save(societyOrg);
            return "更新成功";
        } catch (Exception e) {
            throw new XSLGYException("更新信息出错");
        }

    }

    @Override
    public PageUtils selectSocietyOrgPage(SearchSocietyOrgDTO searchSocietyOrgDTO, Integer pageNum, Integer pageSize) {
        log.info("com.xslgy.common.service.impl.SocietyOrgServiceImpl.selectSocietyOrgPage; params: {}，pageNum: {}；pageSize: {}", searchSocietyOrgDTO, pageNum, pageSize);

        Page<SocietyOrg> page = societyOrgRepository.findAll(getSpecification(searchSocietyOrgDTO), PageRequest.of(pageNum - 1, pageSize));
        List<SocietyOrgVO> societyOrgVoList = new ArrayList<>(pageSize);
        for (SocietyOrg societyOrg : page.getContent()) {
            societyOrgVoList.add(new SocietyOrgVO()
                    .setId(societyOrg.getId())
                    .setCity(societyOrg.getCity())
                    .setMobile(societyOrg.getMobile())
                    .setStatus(societyOrg.getStatus())
                    .setOrgName(societyOrg.getOrgName())
                    .setLinkman(societyOrg.getLinkman())
                    .setOrgMail(societyOrg.getOrgMail())
                    .setCategory(societyOrg.getCategory())
                    .setCommunity(societyOrg.getCommunity())
                    .setOrgLogoUrl(societyOrg.getOrgLogoUrl())
                    .setOrgAddress(societyOrg.getOrgAddress())
                    .setCreatedTime(societyOrg.getCreateTime())
                    .setUpdatedTime(societyOrg.getUpdateTime())
                    .setJuridicalPerson(societyOrg.getJuridicalPerson())
                    .setOrgCertificationUrl(societyOrg.getOrgCertificationUrl())

            );
        }
        return new PageUtils(societyOrgVoList, (int) page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public String deleteById(Long id) {
        SocietyOrg societyOrg = societyOrgRepository.findById(id).orElseThrow(() -> new XSLGYException("社会组织不存在"));
        societyOrg.setDeleteFlag(BYTE_ONE);
        try {
            societyOrgRepository.save(societyOrg);
            return "删除成功";
        } catch (Exception e) {
            throw new XSLGYException("删除失败");
        }
    }

    @Override
    public SocietyOrgVO getDetailById(Long id) {
        SocietyOrg societyOrg = societyOrgRepository.findById(id).orElseThrow(() -> new XSLGYException("社会组织不存在"));
        if (BYTE_ONE.equals(societyOrg.getDeleteFlag())) {
            throw new XSLGYException("社会组织已删除");
        }
        SocietyOrgVO societyOrgVO = new SocietyOrgVO();
        BeanUtils.copyProperties(societyOrg, societyOrgVO);
        return societyOrgVO;
    }

    private Specification<SocietyOrg> getSpecification(SearchSocietyOrgDTO searchSocietyOrgDTO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("deleteFlag"), BYTE_ZERO));
            if (Objects.nonNull(searchSocietyOrgDTO)) {
                if (Objects.nonNull(searchSocietyOrgDTO.getId())) {
                    predicateList.add(criteriaBuilder.equal(root.get("id"), searchSocietyOrgDTO.getId()));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getCategory())) {
                    predicateList.add(criteriaBuilder.like(root.get("category"), "%" + searchSocietyOrgDTO.getCategory() + "%"));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getCity())) {
                    predicateList.add(criteriaBuilder.like(root.get("city"), "%" + searchSocietyOrgDTO.getCity() + "%"));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getOrgName())) {
                    predicateList.add(criteriaBuilder.like(root.get("orgName"), "%" + searchSocietyOrgDTO.getOrgName() + "%"));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getOrgAddress())) {
                    predicateList.add(criteriaBuilder.like(root.get("orgAddress"), "%" + searchSocietyOrgDTO.getOrgAddress() + "%"));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getCommunity())) {
                    predicateList.add(criteriaBuilder.like(root.get("community"), "%" + searchSocietyOrgDTO.getCommunity() + "%"));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getJuridicalPerson())) {
                    predicateList.add(criteriaBuilder.like(root.get("juridicalPerson"), "%" + searchSocietyOrgDTO.getJuridicalPerson() + "%"));
                }
                if (Objects.nonNull(searchSocietyOrgDTO.getLinkman())) {
                    predicateList.add(criteriaBuilder.like(root.get("linkman"), "%" + searchSocietyOrgDTO.getLinkman() + "%"));
                }
                // 前台只查询审核通过的
                // TODO 管理后台要查询所有的数据
                // predicateList.add(criteriaBuilder.equal(root.get("status"), Constant.CHECK_STATUS.CHECK_PASS.getStatus()));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
        };
    }

}
