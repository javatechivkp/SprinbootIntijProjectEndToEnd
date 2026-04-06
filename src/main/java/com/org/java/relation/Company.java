package com.org.java.relation;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Company {
    @Id
    @GeneratedValue
    private Long companyId;
    private String companyName;
    private String companyAddress;
    private String companyEmail;
    private String companyPhone;
    private String industry;
    private int foundedYear;
    @OneToMany(targetEntity = Project.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "cm_pk",referencedColumnName = "companyId")
    private List<Project> projects;
    
}
