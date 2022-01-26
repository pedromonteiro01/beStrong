package com.beStrong.beStrong_server.controller;

import java.util.List;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.beStrong.beStrong_server.service.FitnessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.FitnessType;
import com.beStrong.beStrong_server.repository.*;

@RestController
@CrossOrigin
@RequestMapping("/types")
public class FitnessTypeController {

    @Autowired
    private FitnessTypeService fitnessTypeService;

    @GetMapping
    @ResponseBody
    public List<FitnessType> getAllClasses(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return fitnessTypeService.getFitnessTypes();
    }

    @GetMapping(value="/{name}", produces = "application/json")
    public List<FitnessType> getClassesByName(@PathVariable(value = "name") String classType, HttpServletRequest request){

        return fitnessTypeService.getFitnessTypeByName(classType);

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
