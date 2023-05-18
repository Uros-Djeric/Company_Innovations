package com.innovation.getInnovation.service;

import com.innovation.getInnovation.domain.dto.InnovationDTO;
import com.innovation.getInnovation.domain.model.Innovation;
import com.nimbusds.jwt.JWTClaimsSet;

import java.util.List;


public interface InnovationService {

    List<Innovation> GetAll(JWTClaimsSet claimsSet);

    List<InnovationDTO> convertToDtoList(List<Innovation> innovationList);

}
