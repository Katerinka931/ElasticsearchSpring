package com.example.elasticsearchspring.dto;

import com.example.elasticsearchspring.entities.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeamPojo {
    private String id;
    private String name;
    private int count;

    public static TeamPojo fromEntity(Team team) {
        TeamPojo pojo = new TeamPojo();
        pojo.setId(team.getId());
        pojo.setName(team.getName());
        pojo.setCount(team.getCount());
        return pojo;
    }

    public static Team toEntity(TeamPojo pojo) {
        return new Team(pojo.getName(), pojo.getCount());
    }

    public static List<TeamPojo> convertToPojoList(List<Team> teams) {
        List<TeamPojo> pojoList = new ArrayList<>();
        for (Team team : teams) {
            pojoList.add(TeamPojo.fromEntity(team));
        }
        return pojoList;
    }
}