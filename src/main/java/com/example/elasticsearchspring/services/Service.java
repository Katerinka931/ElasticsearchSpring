package com.example.elasticsearchspring.services;

import com.example.elasticsearchspring.dto.TeamPojo;
import com.example.elasticsearchspring.entities.Team;
import com.example.elasticsearchspring.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final TeamRepository teamRepository;

    public boolean deleteTeamById(String id) {
        boolean flag = teamRepository.existsById(id);
        teamRepository.deleteById(id);
        return flag;
    }

    public TeamPojo createTeam(TeamPojo pojo) {
        return TeamPojo.fromEntity(teamRepository.save(TeamPojo.toEntity(pojo)));
    }

    public TeamPojo updateStudent(String id, TeamPojo pojo) throws ClassNotFoundException {
        return TeamPojo.fromEntity(teamRepository.updateTeam(id, TeamPojo.toEntity(pojo)));
    }

    public List<TeamPojo> findByName(String name) {
        return TeamPojo.convertToPojoList(teamRepository.searchTeamsByNameContaining(name));
    }

    public List<TeamPojo> findAll() {
        List<Team> list = new ArrayList<>();
        teamRepository.findAll().forEach(list::add);
        return TeamPojo.convertToPojoList(list);
    }

    public List<TeamPojo> findByCountInRange(int min, int max) {
        return TeamPojo.convertToPojoList(teamRepository.findByCountInRange(min, max));
    }
}
