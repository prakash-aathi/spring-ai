package com.practice.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private String name;
    private String email;
    private String contactNumber;
    private String city;
    private List<String> skills;
    private List<Employment> employment;
    private List<Project> projects;
}
