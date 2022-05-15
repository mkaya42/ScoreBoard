package com.scoreboard.business;

import com.scoreboard.data.match.MatchStatus;

public class Match {

    private int matchId;
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore ;
    MatchStatus matchStatus;
    private int totalScore;
    public Match() {
    }

    public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore, MatchStatus matchStatus) {

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.matchStatus = matchStatus;
        this.totalScore=homeScore+awayScore;
    }
    public Match(int matchId, Team homeTeam, Team awayTeam, int homeScore, int awayScore, MatchStatus matchStatus) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.matchStatus = matchStatus;
        this.totalScore=homeScore+awayScore;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
