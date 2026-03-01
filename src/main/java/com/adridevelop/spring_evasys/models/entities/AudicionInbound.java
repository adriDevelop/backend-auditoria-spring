package com.adridevelop.spring_evasys.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audicion_inbound")
public class AudicionInbound {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long saludoCorporativo;
	
	@Column
	private Long despedidaCorporativa;
	
	@Column
	private Long personalizacionCliente;
	
	@Column
	private Long usaLenguajeAdecuado;
	
	@Column
	private Long amabilidad;
	
	@Column
	private Long gestionSilenciosEsperas;
	
	@Column
	private Long escuchaActiva;
	
	@Column
	private Long seguridadRespuesta;
	
	@Column
	private Long claridadExplicaciones;
	
	@Column
	private Long capacidadEncontrarOfrecerAlternativa;
	
	@Column
	private Long muestraBuenaImagenServicio;
	
	@Column
	private Long gestionConflictosSituacionesDificiles;
	
	@Column
	private Long sociosOfertaFamilyDuoMgm;
	
	@Column
	private Long noSocios;
	
	@Column
	private Long ofertaPrimerLugarCobroTarjetaPagoAnual;
	
	@Column
	private Long argumentarioTarificacionesBajasAltas;
	
	@Column
	private Long creaContactoCrmAnotacionesContacto;
	
	@Column
	private Long duracionLlamadaAdaptaADificulatad;
	
	@OneToOne
	@JoinColumn(name = "id_audicion", referencedColumnName = "id_audicion")
	private Audicion audicion;
	
}
