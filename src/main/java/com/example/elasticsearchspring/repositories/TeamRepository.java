package org.example.repository;

import org.example.entities.Team;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TeamRepository extends ElasticsearchRepository<Team, String> {
    @Query("{ \"match\": { \"content\": { \"query\": \"?0\" } } }")
    List<Team> searchTeams(String query);
}

//    @Query("{ \"wildcard\": { \"content\": { \"value\": \"*?0*\" } } }")
//    List<Doc> advancedSearchDocs(String keyword);
//
//    @Query("{ \"function_score\": { \"query\": { \"match\": { \"content\": { \"query\": \"?0\" } } }, \"functions\": [ { \"script_score\": { \"script\": { \"source\": \"Math.log(2 + doc['popularity'].value)\" } } } ]} }")
//    List<Doc> functionScoreQuery(String query);