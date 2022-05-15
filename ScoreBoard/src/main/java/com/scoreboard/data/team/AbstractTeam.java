package com.scoreboard.data.team;

import com.scoreboard.business.Team;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTeam {

    public  List<Team> getAllTeams() throws IOException {

        String currentWorkingPath = System.getProperty("user.dir");

        List<Team> teams = new ArrayList<Team>();
        String fileName="teams.txt";
        String filePath = currentWorkingPath+ File.separator+fileName;


        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        while ((line = reader.readLine()) != null)
        {

            String[] parts = line.split("-", 2);

            if (parts.length >= 2)
            {
                Team team = new Team(Integer.parseInt(parts[0]),parts[1]);
                teams.add(team);
            } else {
                System.out.println("Ignore File: " + line);
            }
        }
        reader.close();

        return teams;
    }

    public  int getNumberOfTeams() throws IOException {

        List<Team> teams = (List<Team>) getAllTeams();
        return teams.size();
    }
}
