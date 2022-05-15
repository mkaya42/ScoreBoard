package com.scoreboard.data.fixture;

import com.scoreboard.business.Fixture;
import java.io.IOException;
import java.util.*;

public class FixtureDB extends AbstractFixture  implements IFixtureDB {


    public FixtureDB() throws IOException {
    }

    @Override
    public List<Fixture> getAllFixtures() throws IOException {
        return seasonFixtureList;
    }

    @Override
    public Fixture getWeeklyFixture(int weekDay) throws IOException {

        List<Fixture> allFixture=getAllFixtures();
        for(Fixture fixture:allFixture){

            if(fixture.getWeekDay()==weekDay){
                return fixture;
            }

        }
        return null;
    }

    public Integer getTOTAL_GAMES_PER_WEEK() {
        return getTotalGamesPerWeek();
    }
    public Integer getNUMBER_OF_TEAMS() {  return getNumberOfTeams(); }


}
