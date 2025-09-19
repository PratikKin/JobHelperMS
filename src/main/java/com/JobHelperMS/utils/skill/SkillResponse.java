package com.JobHelperMS.utils.skill;

import com.JobHelperMS.model.Skill;
import com.JobHelperMS.constants.CreationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SkillResponse {
    private Skill skill;
    private CreationStatus status;
}
