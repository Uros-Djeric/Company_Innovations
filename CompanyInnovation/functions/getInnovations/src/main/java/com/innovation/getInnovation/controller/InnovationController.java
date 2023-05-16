package com.innovation.getInnovation.controller;

import com.innovation.getInnovation.domain.dto.InnovationDTO;
import com.innovation.getInnovation.service.InnovationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("innovations")
public class InnovationController {

    @Autowired
    private InnovationService innovationService;

    @GetMapping()
    public ResponseEntity<List<InnovationDTO>> GetInnovation(){

//        List<InnovationDTO> innovationDTOS

        System.out.println(innovationService.GetAll());

        return new ResponseEntity<>(innovationService.convertToDtoList(innovationService.GetAll()), HttpStatus.OK);
    }

}
