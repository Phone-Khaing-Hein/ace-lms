package com.ai.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="module")
@Data
public class Module implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false, unique = true)
    @NotBlank(message = "Module name is required!")
    private String name;

    @ManyToOne
    private Course course;
    
    @Transient
    @NotNull(message = "Resource is required!(At least one)")
    private List<MultipartFile> resources;
    
    @Transient
    private int resourceCount;
}
