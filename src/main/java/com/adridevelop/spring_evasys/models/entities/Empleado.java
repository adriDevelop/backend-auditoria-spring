package com.adridevelop.spring_evasys.models.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
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
@Table(name="empledos")
public class Empleado implements Serializable{

	private static final long serialVersionUID = 8480799030470078485L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_empleado;
	
	@Column(unique = true)
	private String id_rrhh;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidos;
	
	@Column(unique=true)
	private String email;
	
	@Column
	private String direccion;
	
	@Column
	private Date fecha_alta = new Date(Calendar.getInstance().getTimeInMillis());
	
	@Column
	private Date fecha_baja = null;
	
	@Column
	private Integer activo = 1;
	
	@Column
	private String actividad;
	
	// Solo tiene un departamento
	@ManyToOne
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	// Solo tiene un coordinador
	@ManyToOne
	@JoinColumn(name="id_coordinador")
	private Coordinador coordinador;
	
	@ManyToOne
	@JoinColumn(name="id_centro")
	private Centro centro;
	
	// Tiene muchas Auditorias
	@JsonIgnore
	@OneToMany(mappedBy = "empleado")
	private List<Audicion> auditorias;
	
	
}
