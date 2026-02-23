package com.adridevelop.spring_evasys.models.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="coordinadores")
public class Coordinador implements Serializable{
	
	private static final long serialVersionUID = -6986924592401075378L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id_coordinador;
	
	@Column
	private Integer id_rrhh;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidos;
	
	@Column(unique = true)
	private String usuario;
	
	@Column
	private String password;
	
	@Column
	private Integer activo = 1;
	
	@Column
	private String rol = "ROLE_ADMIN";
	
	// Solo tiene un departamento
	@ManyToOne
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	// Tiene muchos empleados
	@JsonIgnore
	@OneToMany(mappedBy = "coordinador")
	private List<Empleado> empleados;
	
	// Solo puede tener un departamento
	@ManyToOne
	@JoinColumn(name="centro")
	private Centro centro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "coordinador")
	private List<Audicion> auditorias;

}
