package com.example.elasticsearchspring.repositories;

import com.example.elasticsearchspring.entities.Team;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends ElasticsearchRepository<Team, String> {

    default Team updateTeam(String pk, Team team) throws ClassNotFoundException {
        Optional<Team> existingTeam = findById(pk);
        if (existingTeam.isPresent()) {
            Team updTeam = existingTeam.get();
            updTeam.setId(pk);
            updTeam.setName(team.getName());
            updTeam.setCount(team.getCount());
            this.save(updTeam);
            return updTeam;
        } else {
            throw new ClassNotFoundException("Команда с заданным идентификатором не найдена");
        }
    }

    //по полному имени
//    @Query("{ \"match\": { \"name\": \"?0\" } }")
//    List<Team> searchTeams(String query);

    @Query("{\"wildcard\": {\"name\": {\"value\": \"*?0*\"}}}")
    List<Team> searchTeamsByNameContaining(String query);

    @Query("{\"range\": {\"count\": {\"gte\": ?0, \"lte\": ?1}}}")
    List<Team> findByCountInRange(int min, int max);
}