package com.javahunter.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Education {

    private float x_percentage;
    private float xii_percentage;
    private float ug_percentage;
    private float pg_percentage;
    private List<String> skills;
}
