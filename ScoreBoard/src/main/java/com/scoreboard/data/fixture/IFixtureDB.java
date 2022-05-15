package com.scoreboard.data.fixture;

import com.scoreboard.business.Fixture;

import java.io.IOException;
import java.util.List;

public interface IFixtureDB {

    public List<Fixture> getAllFixtures() throws IOException;
    public Fixture getWeeklyFixture(int weekDay) throws IOException;
}
