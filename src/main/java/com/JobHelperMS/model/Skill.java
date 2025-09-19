package com.JobHelperMS.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @Column(name = "skillId", nullable = false, unique = true)
    private String id;

    private String description;
    private String code;

}
