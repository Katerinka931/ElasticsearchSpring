package org.example.services;

import org.example.entities.Team;
import org.example.repository.TeamRepository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Service {
    private final TeamRepository teamRepository;

//    public Service(TeamRepository teamRepository){
//        this.teamRepository = teamRepository;
//    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> findTeamById(String id) {
        return teamRepository.findById(id);
    }

    public void deleteTeamById(String id) {
        teamRepository.deleteById(id);
    }

    public List<Team> searchTeams(String query) {
        return teamRepository.searchTeams(query);
    }
}
