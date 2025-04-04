package com.enesselvi.StudentDto;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudentUpdate {
	
	@NotEmpty(message = "İsim alanı boş bırakılamaz")
	@Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
	private String firstName;
	
	@NotEmpty(message = "İsim alanı boş bırakılamaz")
	@Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
	private String lastName;
	
	@Past(message = "Doğum Tarihi Gelecekte ki bir tarih olarak girildi")
	private Date birthOfDate;
	

}
