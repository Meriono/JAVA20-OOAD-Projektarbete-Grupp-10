package Yatzy;

public abstract class Game {
    private String player;
    private int currentScore;
    Die[] dice = new Die[5];
    private int currentThrow;
    Database database;

    public void rollDice(){

    }

    public void createDices(){
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
            System.out.println("Tärning " + (i+1) + ": " + dice[i].getValue());
        }
    }
}
