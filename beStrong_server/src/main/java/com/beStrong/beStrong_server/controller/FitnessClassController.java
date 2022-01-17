package com.beStrong.beStrong_server.controller;

import java.util.List;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.beStrong.beStrong_server.service.FitnessClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.repository.*;

@RestController
@RequestMapping("/classes")
public class FitnessClassController {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FitnessClassService fitnessClassService;

    @GetMapping
    @ResponseBody
    public List<FitnessClass> getAllClasses(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return fitnessClassService.getFitnessClasses();
    }

    @GetMapping(value="/{type}", produces = "application/json")
    public List<FitnessClass> getClassesByType(@PathVariable(value = "type") String classType, HttpServletRequest request){

        return fitnessClassService.getFitnessClassesByType(classType);

        //Boolean checkPermission = checkUserPermissions(request, classType);
//
        //if (!checkPermission){
        //    throw new AccessDeniedException("Cannot access this resource");
        //}
//
        //Optional<FitnessClass> op = fitnessClassService.getFitnessClassesByType(classType);
        //if (op.isPresent()) {
        //    Patient patient = op.get();
        //    return ResponseEntity.ok().body(patient);
        //}
        //throw new ResourceNotFoundException("Patient not found for this id: " + patientId);

    }

}
