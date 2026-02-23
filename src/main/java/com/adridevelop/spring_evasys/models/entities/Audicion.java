package com.adridevelop.spring_evasys.models.entities;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="audiciones")
public class Audicion{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_audicion;
	
	@Column
	private String tipo;
	
	@Column(unique = true)
	private String expediente;
	
	@Column
	private Date fecha;
	
	@Column
	private Double puntuacion_total_auditoria;
	
	// Solo tiene un departamento
	@ManyToOne
	@JoinColumn(name = "audiciones_departamento")
	private Departamento departamento;
	
	// Solo tiene un coordinador que realiza la auditel
	@ManyToOne
	@JoinColumn(name = "coordinador_auditoria_id")
	private Coordinador coordinador_auditel;
	
	@Column
	private Integer activo = 1;
	
	@ManyToOne
	@JoinColumn(name = "coordinador_auditoria")
	private Coordinador coordinador;	
	
	// Solo tiene un empleado
	@ManyToOne
	@JoinColumn(name="empleado_auditado")
	private Empleado empleado;
	
	// Puede tener más colectivos
	@ManyToOne()
	@JoinColumn(name="audiciones")
	private Colectivo colectivo;
}
