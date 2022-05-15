package com.scoreboard.menu;

import com.scoreboard.business.Fixture;
import com.scoreboard.business.Match;
import com.scoreboard.data.fixture.AbstractFixture;
import com.scoreboard.data.fixture.FixtureBuilder;
import com.scoreboard.data.fixture.FixtureDB;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainMenu implements  IScoreBoardMenu{

    private static Scanner input = new Scanner(System.in);

    @Override
    public void matchMenuSelection(Fixture fixture) {

    }

    @Override
    public void showMenuOptions() {

        System.out.println("Welcome to the Score Board Menu for the World Cup - Press 0 to quit!");
        System.out.println("[1] Create a New Fixture");
        System.out.print("Your Choice : ");

    }
    public void showSubMenuOptions() {

        System.out.println("Welcome to the Score Board Menu for the World Cup - Press 0 to quit!");
        System.out.println("[1] Create a New Fixture");
        System.out.println("[2] View Upcoming Fixtures");
        System.out.println("[3] View Selected Weeks Fixture");
        System.out.print("Your Choice : ");

    }

    public void forwardMenuOptions(){
        if(AbstractFixture.seasonFixtureList.size()==0){
            showMenuOptions();
        }
        else {
            showSubMenuOptions();
        }
    }

    @Override
    public void menuSelection(){

        try {
            forwardMenuOptions();
            input = new Scanner(System.in);

            boolean isExit = false;

            do {
                try {
                    switch (input.nextInt()){
                        case 0:
                            isExit = true;
                            break;
                        case 1: FixtureBuilder.setupFixtures();
                            forwardMenuOptions();
                            break;
                        case 2:
                            getUpcomingFixtures();
                            break;
                        case 3:
                            displayMatchMenu();
                            break;

                        default:
                            System.out.println("Invalid selection! Please try again.");
                            break;
                    }
                }
                catch (InputMismatchException | IOException ex){
                    System.out.println("Invalid entry! Please try again.");
                    System.out.print("Your Choice : ");
                    input.next();
                }
            }while(!isExit);
            input.close();
        }
        catch (NoSuchElementException e){
            System.exit(0);
        }

    }

    private void getUpcomingFixtures() throws IOException {

        FixtureDB fixtureDB=new FixtureDB();

        for(int i =0;i<fixtureDB.getAllFixtures().size();i++){
           Fixture weeklyFixture=fixtureDB.getAllFixtures().get(i);
           for(Match weeksMatch: weeklyFixture.getMatchList()){
               System.out.println("Week #"+weeklyFixture.getWeekDay()+" "+weeksMatch.getHomeTeam().getTeamName()+" vs "+weeksMatch.getAwayTeam().getTeamName());

           }
            System.out.println("-------------------------------");
        }

        forwardMenuOptions();

    }
    private Fixture getUpcomingWeekFixture(int weekDay) throws IOException {

        FixtureDB fixtureDB=new FixtureDB();
        Fixture weeklyFixture=fixtureDB.getWeeklyFixture(weekDay);
        System.out.println("-------------------------------");
        System.out.println("The Match List for the week "+weekDay);
        int count=0;
        for(Match weeksMatch: weeklyFixture.getMatchList()){
            System.out.println("["+(++count)+"]"+weeksMatch.getHomeTeam().getTeamName()+" vs "+weeksMatch.getAwayTeam().getTeamName());
        }
        System.out.println("-------------------------------");
        return weeklyFixture;
    }

    private void displayMatchMenu() throws IOException {

        System.out.println("Enter Week number to see Weekly Fixture / Enter 0 to back to the Main Menu");
        System.out.print("Your Entry : ");
        FixtureDB fixtureDB=new FixtureDB();

        int weekDay=input.nextInt();
        if(weekDay<= fixtureDB.getNUMBER_OF_TEAMS()-1 && weekDay>0){
            Fixture weeklyFixture=getUpcomingWeekFixture(weekDay);
            MatchMenu matchMenu = new MatchMenu();
            matchMenu.matchMenuSelection(weeklyFixture);
        }
        else if(weekDay==0){
            MainMenu mainMenu = new MainMenu();
            mainMenu.forwardMenuOptions();
        }
        else {
            System.out.println("Invalid selection! Please try again to see Weekly Fixture.");
            displayMatchMenu();
        }



    }
}
