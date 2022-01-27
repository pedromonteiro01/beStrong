package com.beStrong.beStrong_server.controller;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beStrong.beStrong_server.service.FitnessClassService;
import com.beStrong.beStrong_server.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.exception.UnprocessableEntityException;
import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;
import com.beStrong.beStrong_server.repository.*;

@RestController
@CrossOrigin
@RequestMapping("/classes")
public class FitnessClassController {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FitnessClassService fitnessClassService;

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    @ResponseBody
    public List<FitnessClass> getAllClasses(HttpServletRequest request) {
        return fitnessClassService.getFitnessClasses();
    }

    @GetMapping(value="/{type}", produces = "application/json")
    public List<FitnessClass> getClassesByType(@PathVariable(value = "type") String classType, HttpServletRequest request){

        return fitnessClassService.getFitnessClassesByType(classType);
    }

    @GetMapping(value="/{type}/{trainer_id}", produces = "application/json")
    public List<FitnessClass> getClassesByTrainer(@PathVariable(value = "trainer_id") Trainer trainer, @PathVariable(value = "type") String classType,HttpServletRequest request){

        return fitnessClassService.geFitnessClassesByTrainerAndType(trainer, classType);
    }

    @GetMapping(value="/getEnlistedClasses/{client_id}", produces = "application/json")
    public Set<FitnessClass> getEnlistedClasses(@PathVariable(value = "client_id") String client_id, HttpServletRequest request){
        Optional<Client> c = clientRepository.findById(Integer.parseInt(client_id));
        System.out.println(c.get().getId());
        System.out.println(c.get().getName());
        System.out.println(c.get().getFitnessClasses());
        return c.get().getFitnessClasses();
    }

    //Adicionar Aula
    @PostMapping(value="/addClass", produces = "application/json")
    public FitnessClass addClass(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{

        Trainer trainer = this.trainerService.getTrainerById(Integer.parseInt(payload.get("trainerId").toString()));

        FitnessClass fitnessClass = new FitnessClass(
                trainer,
                payload.get("type").toString(),
                Date.valueOf(payload.get("date").toString()),
                Time.valueOf(payload.get("start_hour").toString()),
                Time.valueOf(payload.get("ending_hour").toString()),
                payload.get("local").toString(),
                Integer.parseInt(payload.get("max_capacity").toString())
        );

        return fitnessClassService.saveFitnessClass(fitnessClass);
    }

    //Reservar Lugar
    @PostMapping(value="/makeReservation", produces = "application/json")
    public String createClient(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        int classId = Integer.parseInt( payload.get("classId").toString());
        int clientId = Integer.parseInt( payload.get("clientId").toString());
        return "{\"result\" : \"" + fitnessClassService.makeReservation( classId, clientId) + "\"}";
    }

    //Cancelar Reserva
    @DeleteMapping(value="/cancelReservation", produces = "application/json")
    public String cancelClass(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        int classId = Integer.parseInt( "" + payload.get("classId"));
        int clientId = Integer.parseInt("" + payload.get("clientId"));
        System.out.println(classId  + " " + clientId);
        return "{\"result\" : \"" + fitnessClassService.cancelReservation( classId, clientId) + "\"}";
    }

    @DeleteMapping(value="/removeClass", produces = "application/json")
    public String removeClient(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        int classId = Integer.parseInt(payload.get("classId").toString());
        return "{\"result\" : \"" + fitnessClassService.removeFitnessClass(classId) + "\"}";
    }

    @PutMapping(value="/updateClass", produces = "application/json")
    public String editClass(@Valid @RequestBody Map<String, String> payload, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        System.out.println("Hello");

        int classId = Integer.parseInt(payload.get("classId").toString());

        FitnessClass fc = fitnessClassService.getFitnessClassById(classId);

        fc.setDate(Date.valueOf(payload.get("date").toString()));
        fc.setStarting(Time.valueOf(payload.get("start_hour").toString()));
        fc.setEnding(Time.valueOf(payload.get("ending_hour").toString()));
        fc.setLocal(payload.get("local").toString());


        return "{\"result\" : \"" + fitnessClassService.saveFitnessClass( fc ) + "\"}";
    }

}
