package com.scoreboard.business;

import java.util.List;

public class Fixture {

    private Integer fixtureId;
    private Integer weekDay;
    private List<Match> matchList;

    public Fixture() {
    }

    public Fixture(Integer weekDay, List<Match> matchList) {

        this.weekDay = weekDay;
        this.matchList = matchList;
    }

    public Fixture(Integer fixtureId, Integer weekDay, List<Match> matchList) {
        this.fixtureId = fixtureId;
        this.weekDay = weekDay;
        this.matchList = matchList;
    }

    public Integer getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Integer fixtureId) {
        this.fixtureId = fixtureId;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
}
