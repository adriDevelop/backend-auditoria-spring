package com.adridevelop.spring_evasys.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audiciones_entrantes")
public class AudicionAsistenciaEntrantes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "recepcion-llamada")
	private Double recepcionLlamada;
	
	@Column(name = "saludo-corporativo")
	private Double saludoCorporativo;
	
	@Column(name = "despedida-corporativa")
	private Double despedidaCorporativa;
	
	@Column
	private Double personalizacionCliente;
	
	@Column
	private Double formulasCortesia;
	
	@Column
	private Double realizaPreguntasEstimulo;
	
	@Column
	private Double vocalizacionCorrecta;
	
	@Column
	private Double evitaTonoMonotono;
	
	@Column
	private Double usaLenguajeAdecuado;
	
	@Column
	private Double amabilidad;
	
	@Column
	private Double cercaniaHumana;
	
	@Column
	private Double gestionSilencios;
	
	@Column
	private Double seguridadRespuestas;
	
	@Column
	private Double escuchaActiva;
	
	@Column
	private Double empaticaCliente;
	
	@Column
	private Double capacidadEncontrarAlternativa;
	
	@Column
	private Double ofreceAgilmenteSolucion;
	
	@Column
	private Double gestionaOptimizacionCostes;
	
	@Column
	private Double claridadExplicaciones;
	
	@Column
	private Double indicaPasosASeguir;
	
	@Column
	private Double muestraBuenaImagen;
	
	@Column
	private Double gestionConflictos;
	
	@Column
	private Double realizaArgumentarioPercance;
	
	@Column
	private Double completaExpedienteCorrectamente;
	
	@Column
	private Double duracionAdaptadaDificultad;
	
	@Column
	private Double ofreceTallerConcertado;
	
	@Column
	private Double satisfaccionCliente;
	
}
