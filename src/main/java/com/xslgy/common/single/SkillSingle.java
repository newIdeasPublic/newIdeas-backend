package com.xslgy.common.single;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/20  上午11:54
 * @Description : 技能分类单例
 */
public class SkillSingle {

    private static final Map<String, String> SKILL_MAP = new HashMap<>(32);

    static {
        SKILL_MAP.put("编程", "信息技术");
        SKILL_MAP.put("电脑维修", "信息技术");
        SKILL_MAP.put("软件设计", "信息技术");
        SKILL_MAP.put("主持人", "文宣类");
        SKILL_MAP.put("项目策划", "文宣类");
        SKILL_MAP.put("新闻写作", "文宣类");
        SKILL_MAP.put("摄影摄像", "文宣类");
        SKILL_MAP.put("微电影", "文宣类");
        SKILL_MAP.put("唱歌", "才艺类");
        SKILL_MAP.put("跳舞", "才艺类");
        SKILL_MAP.put("戏曲", "才艺类");
        SKILL_MAP.put("小品", "才艺类");
        SKILL_MAP.put("手语", "才艺类");
        SKILL_MAP.put("理发", "技艺类");
        SKILL_MAP.put("修电器", "技艺类");
        SKILL_MAP.put("水电工", "技艺类");
        SKILL_MAP.put("烹饪", "技艺类");
        SKILL_MAP.put("编织", "技艺类");
        SKILL_MAP.put("客服", "专业类");
        SKILL_MAP.put("语言", "专业类");
        SKILL_MAP.put("财务", "专业类");
        SKILL_MAP.put("行政", "专业类");
        SKILL_MAP.put("平面设计", "专业类");
        SKILL_MAP.put("课件制作", "专业类");
        SKILL_MAP.put("项目管理", "专业类");
        SKILL_MAP.put("师范", "专业类");
        SKILL_MAP.put("人事", "专业类");
        SKILL_MAP.put("法务", "专业类");
        SKILL_MAP.put("公共关系", "专业类");
        SKILL_MAP.put("护理", "专业类");
        SKILL_MAP.put("商务（市场、销售）", "专业类");
    }


    private SkillSingle() {
    }

    public static Map<String, String> getSkillMap() {
        return SKILL_MAP;
    }

}
