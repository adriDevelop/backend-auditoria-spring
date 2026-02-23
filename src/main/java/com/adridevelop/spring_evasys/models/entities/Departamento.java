package com.adridevelop.spring_evasys.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name="departamentos")
public class Departamento implements Serializable{
	
	private static final long serialVersionUID = -4113555534461642824L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_departamento;
	
	@Column(unique = true)
	private String nombre;
	
	@Column
	private Integer activo = 1;
	
	// Solo tiene un departamento
	@JsonIgnore
	@OneToMany(mappedBy="departamento")
	private List<Empleado> empleados;
	
	@JsonIgnore
	@OneToMany(mappedBy="departamento")
	private List<Audicion> audiciones;
	
	@JsonIgnore
	@OneToMany(mappedBy="departamento")
	private List<Coordinador> coordinadores;
	
	@ManyToMany
	@JoinTable(
			name = "departamento_colectivo",
			joinColumns = @JoinColumn(name = "id_departamento"),
			inverseJoinColumns = @JoinColumn(name = "id_colectivo")
			)
	private Set<Colectivo> colectivos = new HashSet<>();
	
	// Puede tener mas de un centro
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "departamento_centros", // Tabla intermedia
			joinColumns = @JoinColumn(name = "id_departamento"),
			inverseJoinColumns = @JoinColumn(name = "id_centro")
			)
	private List<Centro> centros = new ArrayList<>();
}
