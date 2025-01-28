package com.portfolio.sarvech.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SkillResponseDto {

    private List<Skill> languages;
    private List<Skill> frameworks;
    private List<Skill> tools;
    private List<Skill> others;


    public SkillResponseDto(List<Skill> languages, List<Skill> frameworks, List<Skill> tools, List<Skill> others) {
        this.languages = languages;
        this.frameworks = frameworks;
        this.tools = tools;
        this.others = others;
    }
}
