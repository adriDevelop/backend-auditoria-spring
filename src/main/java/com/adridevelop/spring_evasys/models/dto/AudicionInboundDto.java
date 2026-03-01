package com.adridevelop.spring_evasys.models.dto;

import com.adridevelop.spring_evasys.models.entities.AudicionInbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AudicionInboundDto {

	private Long idAudicion;
	private AudicionInbound audicionInbound;
	
}
