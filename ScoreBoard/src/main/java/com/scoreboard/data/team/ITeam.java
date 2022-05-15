package com.scoreboard.data.team;

import com.scoreboard.business.Team;

import java.io.IOException;
import java.util.Collection;

public interface ITeam {
    /*
     * List of all teams.txt.
     * */
    public Collection<Team> getAllTeams() throws IOException;

}
