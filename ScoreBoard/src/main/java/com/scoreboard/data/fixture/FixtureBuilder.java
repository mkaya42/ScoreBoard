package com.scoreboard.data.fixture;

import com.scoreboard.business.Fixture;
import com.scoreboard.business.Match;
import com.scoreboard.business.Team;
import com.scoreboard.data.match.MatchStatus;
import com.scoreboard.data.team.TeamDB;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixtureBuilder {

    public static void setupFixtures() throws IOException {
        buildFixture();
        AbstractFixture.setSeasonFixture();
        System.out.println("Fixture was Built..");
    }

    private static void buildFixture() throws IOException {
        System.out.println("Building Weekly Fixtures...");

        TeamDB teamDB = new TeamDB();
        List<Team> teams = (List<Team>) teamDB.getAllTeams();

        Collections.shuffle(teams);
        List<Fixture> fixtureList = new ArrayList<Fixture>();

        Team[] teamsArr = new Team[teams.size()];
        teams.toArray(teamsArr);

        fixtureList=roundRobinScheduler(teamsArr);

        FileWriter fw = new FileWriter("fixture.txt");
        int weekDay=1;
        for(Fixture fixture:fixtureList){
            for(Match match:fixture.getMatchList()){

                fw.write(fixture.getWeekDay()+","+match.getHomeTeam().getTeamName()+","+match.getHomeScore()+","+match.getAwayTeam().getTeamName()+","+match.getAwayScore()+","+match.getMatchStatus() +"\r\n");

            }
        }
        fw.close();
    }

    private static List<Fixture> roundRobinScheduler(Team[] teams)  throws IOException {


        int numOfTeams = teams.length;
        Team[] evenTeams;
        int k = 0;

        evenTeams = new Team[numOfTeams-1];
        for(k = 0; k < numOfTeams-1; k++){

            evenTeams[k] = teams[k+1];
        }


        int teamsSize = evenTeams.length;
        int total = ((teamsSize+1) - 1);
        int halfSize = (teamsSize+1)/ 2;
        int count = 0;

        List<Match>   matchListInThatWeek;
        List<Fixture> totalMatchListInFixture = new ArrayList<Fixture>();

        for (int week = total-1; week >= 0; week--)  {

            matchListInThatWeek = new ArrayList<Match>();

            int teamIdx = week % teamsSize;
            Match match = new Match(teams[0],evenTeams[teamIdx],0,0, MatchStatus.NOT_STARTED);

            matchListInThatWeek.add(match);

            for (int i = 1; i < halfSize; i++) {
                int firstTeam = (week + i) % teamsSize;
                int secondTeam = (week  + teamsSize - i) % teamsSize;
                match = new Match(evenTeams[firstTeam],evenTeams[secondTeam],0,0, MatchStatus.NOT_STARTED);
                matchListInThatWeek.add(match);

            }

            count++;
            Fixture weeklyFixture=new Fixture(count,matchListInThatWeek);
            totalMatchListInFixture.add(weeklyFixture);
        }
        return totalMatchListInFixture;
    }

    public static void main(String[] args) throws IOException {
        FixtureBuilder build =new FixtureBuilder();
        build.setupFixtures();
    }
}
