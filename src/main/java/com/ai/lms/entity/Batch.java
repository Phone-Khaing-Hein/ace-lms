package com.ai.lms.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="batch")
public class Batch implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(length = 50, nullable = false, unique = true)
	@NotBlank(message = "Batch name is required!")
    private String name;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
	
	@ManyToOne
	private Course course;
}
