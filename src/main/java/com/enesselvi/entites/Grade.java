package com.enesselvi.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "Grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "Vize" , nullable = false)
	private Double midTermGrade;
	
	
	@Column(name = "Final" , nullable = false)
	private Double finalGrade;
	
	
	@Column(name = "Bütünleme" , nullable = true)
	private Double makeupGrade;
	
	@ManyToOne
	@JoinColumn(name = "student_id" , nullable = false)
	private Student student;
	
	
}
