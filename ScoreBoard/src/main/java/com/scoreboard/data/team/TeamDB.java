package com.scoreboard.data.team;

import com.scoreboard.business.Team;
import java.io.IOException;
import java.util.List;

public class TeamDB extends AbstractTeam implements ITeam {

    @Override
    public List<Team> getAllTeams() throws IOException {
        return super.getAllTeams();
    }
}
