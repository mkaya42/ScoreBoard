package com.scoreboard.data.fixture;

import com.scoreboard.business.Fixture;
import com.scoreboard.business.Match;
import com.scoreboard.business.Team;
import com.scoreboard.data.match.MatchStatus;
import com.scoreboard.data.team.TeamDB;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFixture {

    public static List<Fixture> seasonFixtureList=new ArrayList<Fixture>();

    private static Integer TOTAL_GAMES_PER_WEEK = null;
    private static Integer NUMBER_OF_TEAMS=null;



    public static void setSeasonFixture() throws IOException {
        setConstants();
        String currentWorkingPath = System.getProperty("user.dir");

        String fileName = "fixture.txt";
        String filePath = currentWorkingPath + File.separator + fileName;

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Match> matches = new ArrayList<Match>();
        TeamDB teamDB=new TeamDB();


        int lineNumber=0;
        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            Team homeTeam = new Team(parts[1]);
            Team awayTeam = new Team(parts[3]);
            Match match = new Match(homeTeam, awayTeam, Integer.parseInt(parts[2]), Integer.parseInt(parts[4]), MatchStatus.valueOf(parts[5]));
            matches.add(match);
            ++lineNumber;

            if(lineNumber==TOTAL_GAMES_PER_WEEK){
                seasonFixtureList.add(new Fixture(Integer.parseInt(parts[0]), matches));
                matches = new ArrayList<Match>();
                lineNumber=0;
            }



        }
        reader.close();

    }


    public static void setConstants() throws IOException {

        TeamDB teamDB=new TeamDB();
        NUMBER_OF_TEAMS=teamDB.getNumberOfTeams();
        TOTAL_GAMES_PER_WEEK=NUMBER_OF_TEAMS/2;

    }

    public static Integer getTotalGamesPerWeek() {
        return TOTAL_GAMES_PER_WEEK;
    }

    public static Integer getNumberOfTeams() {
        return NUMBER_OF_TEAMS;
    }
}
