package com.adridevelop.spring_evasys.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="colectivos")
public class Colectivo implements Serializable{
	
	private static final long serialVersionUID = 4911084707474729665L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id_colectivo;
	
	@Column
	private String nombre;
	
	@Column
	private Integer activo = 1;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "colectivos")
	private List<Departamento> departamentos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "colectivo")
	private List<Audicion> audiciones;
	
}
