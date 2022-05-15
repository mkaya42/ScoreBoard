package com.scoreboard.menu;


import com.scoreboard.business.Fixture;
import com.scoreboard.business.Match;
import com.scoreboard.data.match.MatchStatus;


import java.io.IOException;
import java.util.*;

public class MatchMenu implements IScoreBoardMenu {
    private static Scanner input = new Scanner(System.in);

    @Override
    public void showMenuOptions() {

        System.out.println("[1] Display/Update Match Details");
        System.out.println("[2] Display Current Match List");
        System.out.println("[3] Back to main menu");
        System.out.print("Your Choice : ");

    }

    @Override
    public void menuSelection() {

    }

    @Override
    public void matchMenuSelection(Fixture weeklyFixture) {

        showMenuOptions();
        input = new Scanner(System.in);
        boolean isExit = false;
        do {
            try {

                switch (input.nextInt()) {
                    case 1:
                        displayMatchDetails(weeklyFixture);
                        break;
                    case 2:
                        displayMatchList(weeklyFixture);
                        break;
                    case 3:
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.menuSelection();
                        break;
                    default:
                        System.out.println("Invalid selection! Please try again.");
                        break;
                }


            } catch (InputMismatchException | IOException ex) {
                System.out.println("Invalid entry! Please try again.");
                input.next();
            }
        } while (!isExit);
        input.close();

    }

    private void displayMatchList(Fixture weeklyFixture) {

        List<Match> startedMatchList = new ArrayList<Match>();
        List<Match> notStartedMatchList = new ArrayList<Match>();
        List<Match> finishedMatchList = new ArrayList<Match>();


        for (Match weeksMatch : weeklyFixture.getMatchList()) {
            if (weeksMatch.getMatchStatus().equals(MatchStatus.STARTED)) {
                startedMatchList.add(weeksMatch);

            }
            if (weeksMatch.getMatchStatus().equals(MatchStatus.NOT_STARTED)) {
                notStartedMatchList.add(weeksMatch);
            }

            if (weeksMatch.getMatchStatus().equals(MatchStatus.FINISHED)) {
                finishedMatchList.add(weeksMatch);
            }
        }

        System.out.println("-------------------------------");
        System.out.println("Started Matches");
        startedMatchList=sortMatches(startedMatchList);
        if (startedMatchList.size() != 0) {

            for (Match match : startedMatchList) {

                System.out.println(match.getHomeTeam().getTeamName() + ":" + match.getHomeScore() + " " + match.getAwayTeam().getTeamName() + ":" + match.getAwayScore());

            }
        } else {
            System.out.println("* There is no any started match in the weekly fixture");

        }
        System.out.println("-------------------------------");
        System.out.println("Not Started Matches");
        notStartedMatchList=sortMatches(notStartedMatchList);
        if (notStartedMatchList.size() != 0) {

            for (Match match : notStartedMatchList) {

                System.out.println(match.getHomeTeam().getTeamName() + ":" + match.getHomeScore() + " " + match.getAwayTeam().getTeamName() + ":" + match.getAwayScore());

            }
        } else {
            System.out.println("* All matches in the weekly fixture were started");

        }
        System.out.println("-------------------------------");
        System.out.println("Finished Matches");
        finishedMatchList=sortMatches(finishedMatchList);
        if (finishedMatchList.size() != 0) {

            for (Match match : finishedMatchList) {

                System.out.println(match.getHomeTeam().getTeamName() + ":" + match.getHomeScore() + " " + match.getAwayTeam().getTeamName() + ":" + match.getAwayScore());

            }
        } else {
            System.out.println("* There is not any finished match in the weekly fixture");

        }
        System.out.println("-------------------------------");
        showMenuOptions();

    }


    private void displayMatchDetails(Fixture weeklyFixture) throws IOException {
        int count = 0;
        for (Match weeksMatch : weeklyFixture.getMatchList()) {
            System.out.println("[" + (++count) + "]" + weeksMatch.getHomeTeam().getTeamName() + ":" + weeksMatch.getHomeScore() + " " + weeksMatch.getAwayTeam().getTeamName() + ":" + weeksMatch.getAwayScore() + " Status:" + weeksMatch.getMatchStatus());
        }
        chooseMatch(weeklyFixture, weeklyFixture.getMatchList());
    }

    private void chooseMatch(Fixture weeklyFixture, List<Match> matchList) throws IOException {
        input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Please select the Match from the list that you would like to see details / Enter 0 to back to the match list menu");
        System.out.print("Your Choice :");
        int i = input.nextInt();
        if (i <= matchList.size() && i > 0) {
            Match match = matchList.get(i - 1);
            changeMatchDetails(weeklyFixture, match);
        } else if (i == 0) {
            MatchMenu matchMenu = new MatchMenu();
            matchMenu.matchMenuSelection(weeklyFixture);
        } else {
            System.out.println("Invalid Entry Please Try again.");
            chooseMatch(weeklyFixture, matchList);
        }


    }

    private void changeMatchDetails(Fixture weeklyFixture, Match match) throws IOException {
        input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Welcome to the match  " + match.getHomeTeam().getTeamName() + " vs " + match.getAwayTeam().getTeamName());
        int index;
        if (match.getMatchStatus().equals(MatchStatus.NOT_STARTED)) {
            System.out.println("[1] Start Match");
            System.out.println("[2] Back to main menu");
            System.out.print("Your Choice : ");
            index = input.nextInt();
            switch (index) {
                case 1:
                    match.setMatchStatus(MatchStatus.STARTED);
                    System.out.println("Match Started");
                    matchMenuSelection(weeklyFixture);
                    break;
                case 2:
                    MatchMenu matchMenu = new MatchMenu();
                    matchMenu.matchMenuSelection(weeklyFixture);

                    break;
                default:
                    System.out.println("Invalid selection!");

                    break;
            }


        } else if (!match.getMatchStatus().equals(MatchStatus.FINISHED)) {

            input = new Scanner(System.in);

            System.out.println("[1] End Match");
            System.out.println("[2] Update Score");
            System.out.println("[3] Back to main menu");
            System.out.print("Your Choice : ");
            index = input.nextInt();

            switch (index) {
                case 1:
                    match.setMatchStatus(MatchStatus.FINISHED);
                    System.out.println("Match Ended");
                    matchMenuSelection(weeklyFixture);
                    break;
                case 2:
                    updateMatchScore(match);
                    matchMenuSelection(weeklyFixture);
                    break;
                case 3:
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.menuSelection();
                    break;
                default:
                    System.out.println("Invalid selection!");
                    break;
            }
            //input.close();
        }


    }

    private static void updateMatchScore(Match match) {
        int totalMatchScore= match.getTotalScore();
        input = new Scanner(System.in);

        System.out.print("Enter Home Score : ");

        int index = input.nextInt();
        match.setHomeScore(index);
        totalMatchScore+=index;
        System.out.print("Enter Away Score : ");
        index = input.nextInt();
        match.setAwayScore(index);
        totalMatchScore+=index;
        match.setTotalScore(totalMatchScore);
        System.out.println("Match Updated");

         //input.close();
    }

   private List<Match> sortMatches(List<Match> matches){

       Collections.sort(matches, new Comparator<Match>() {
           @Override
           public int compare(Match match1, Match match2) {
               return match1.getTotalScore() - match2.getTotalScore();
           }
       });

       return matches;
   }
}
