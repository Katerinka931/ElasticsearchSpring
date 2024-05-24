package com.example.elasticsearchspring.controllers;

import com.example.elasticsearchspring.dto.TeamPojo;
import com.example.elasticsearchspring.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/teams")
public class Controller {
    private final Service service;

    public Controller(com.example.elasticsearchspring.services.Service service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TeamPojo> createTeam(@RequestBody TeamPojo pojo) {
        try {
            return new ResponseEntity<>(service.createTeam(pojo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{pk}")
    public ResponseEntity<String> deleteTeam(@PathVariable String pk) {
        boolean flag = service.deleteTeamById(pk);
        if (flag)
            return new ResponseEntity<>("Удаление успешно", HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>("Не удалось удалить команду", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{pk}")
    public ResponseEntity<TeamPojo> updateStudent(@PathVariable String pk, @RequestBody TeamPojo pojo) {
        try {
            return new ResponseEntity<>(service.updateStudent(pk, pojo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<TeamPojo> getAllTeams() {
        return service.findAll();
    }

    @GetMapping("/{name}")
    public List<TeamPojo> findAllByName(@PathVariable String name) {
        return service.findByName(name);
    }
    @GetMapping("/range")
    public List<TeamPojo> findAllByName(@RequestParam(name = "min", required = false) Integer min,
                                        @RequestParam(name = "max", required = false) Integer max){

        return service.findByCountInRange(min, max);
    }
}
