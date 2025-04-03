package com.enesselvi.StudentDto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudentSave {
	
	@NotEmpty(message = "İsim alanı boş bırakılamaz")
	@Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
	private String firstName;
	
	@NotEmpty(message = "İsim alanı boş bırakılamaz")
	private String lastName;
	
	private String birthOfDate;
	
	@Min(value = 1000 , message = "En az 4 haneli numara girilmeli")
	@Max(value = 999999 , message = "En fazla 6 haneli sayı girilmeli")
	private Integer stuNumber;
	
}
