package com.adridevelop.spring_evasys.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="centros")
public class Centro implements Serializable{

	private static final long serialVersionUID = 2091556458462308822L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_centro;
	
	@Column
	private String localizacion;
	
	@Column
	private String nombre;
	
	@Column
	private Integer activo = 1;
	
	@JsonIgnore
	@OneToMany(mappedBy = "centro")
	private List<Empleado> empleados;
	
	@JsonIgnore
	@OneToMany(mappedBy = "centro")
	private List<Coordinador> coordinadores;
	
	// Tiene muchos departamentos
	@ManyToMany(mappedBy = "centros")
	private Set<Departamento> departamentos = new HashSet<>();
}
