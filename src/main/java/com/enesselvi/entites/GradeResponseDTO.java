package com.enesselvi.entites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDTO {
    private Integer id;
    private Double midTermGrade;
    private Double finalGrade;
    private Double makeupGrade;
    private Double average;
    private String letterGrade;
}
