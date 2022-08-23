package com.ai.lms.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="resource")
public class Resource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false, unique = true)
    private String resource;
    @ManyToOne
    private Module module;
    
    public Resource(){
    	this.module = new Module();
    }
}
