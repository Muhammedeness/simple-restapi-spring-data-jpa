package com.enesselvi.GradeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDTO {
    private Double midTermGrade;
    private Double finalGrade;
    private Double makeupGrade;
    private Double average;
    private String letterGrade;
}
