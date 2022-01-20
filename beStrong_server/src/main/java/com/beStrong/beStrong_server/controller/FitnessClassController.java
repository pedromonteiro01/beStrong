package com.beStrong.beStrong_server.controller;

import java.util.List;
import java.util.Map;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beStrong.beStrong_server.service.FitnessClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.exception.UnprocessableEntityException;
import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;
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

    @GetMapping(value="/{type}/{trainer_id}", produces = "application/json")
    public List<FitnessClass> getClassesByTrainer(@PathVariable(value = "trainer_id") Trainer trainer, @PathVariable(value = "type") String classType,HttpServletRequest request){

        return fitnessClassService.geFitnessClassesByTrainerAndType(trainer, classType);
    }

    @PostMapping(value="/submitClass", produces = "application/json")
    public String createClient(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        int classId = Integer.parseInt( "" + payload.get("classId"));
        int clientId = Integer.parseInt("" + payload.get("clientId"));
        System.out.println(classId  + " " + clientId);
        return "{\"result\" : \"" + fitnessClassService.makeReservation( classId, clientId) + "\"}";
    }

    @PostMapping(value="/cancelClass", produces = "application/json")
    public String removeClient(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        int classId = Integer.parseInt( "" + payload.get("classId"));
        int clientId = Integer.parseInt("" + payload.get("clientId"));
        System.out.println(classId  + " " + clientId);
        return "{\"result\" : \"" + fitnessClassService.cancelReservation( classId, clientId) + "\"}";
    }
}
