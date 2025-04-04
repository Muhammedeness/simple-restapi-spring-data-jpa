package com.enesselvi.entites;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student") //tablomun adı
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
	
	@Id  // VERİTABANINA PRimary key olarak ayarlayacak
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)//her insert te id yi otomatik olarak arttır
	private Integer  id;
	
	
	@Column(name = "first_name" , nullable = false)
	private String firstName;
	
	@Column(name = "last_name" , nullable = false)
	private String lastName;
	
	
	@Column(name = "birth_of_date" , nullable = true)
	private Date birthOfDate;
	
	@Column(name="number" , nullable = false , unique = true)
	private Integer stuNumber;
	
	
	
	@OneToMany(mappedBy = "student" , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private List<Grade> grades = new ArrayList<>();
	

	
}
