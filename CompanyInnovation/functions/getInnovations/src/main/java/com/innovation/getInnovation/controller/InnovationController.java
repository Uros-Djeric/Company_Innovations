package com.innovation.getInnovation.controller;

import com.innovation.getInnovation.config.TokenUtils;
import com.innovation.getInnovation.domain.dto.InnovationDTO;
import com.innovation.getInnovation.service.InnovationService;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("innovations")
public class InnovationController {

    @Autowired
    private InnovationService innovationService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping()
    @CrossOrigin("*")
    public ResponseEntity<List<InnovationDTO>> GetInnovation(@RequestHeader("Authorization") String bearerToken){
        return new ResponseEntity<>(innovationService.convertToDtoList(innovationService.GetAll(tokenUtils.getJWTClaimsSet(bearerToken.replace("Bearer ", "")))), HttpStatus.OK);
    }

}
