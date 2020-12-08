package Yatzy;

import javax.swing.*;
import java.awt.*;

public abstract class Game {

    public static final int THROWS_AMOUNT = 3;
    public static final int ROUNDS_AMOUNT = 6;

    Database database;
    protected Color gameColor;
    private Controller controller;
    private String playerName;
    private int currentScore;
    Die[] dice = new Die[5];
    private int currentThrow;
    private int currentRound;
    private boolean isFirstRound;

    public Game(Controller controller) {
        currentScore = 0;
        this.controller = controller;
        this.isFirstRound = true;
        createDice();
    }

    public Die[] rollDice() {
        JToggleButton[] toggleButtons = controller.getDiceButtons();
        for (int i = 0; i < dice.length; i++) {
            if (!toggleButtons[i].isSelected()) {
                dice[i].roll();
                System.out.println("Dice " + i + " : " + dice[i].getValue());
            }
        }

        if (isFirstRound) {
            this.currentRound = 0;
            this.currentThrow = 0;
            this.isFirstRound = false;
        } else {
            addThrow();
            if (currentThrow == 0)
                currentRound++;
        }

        System.out.println("currentThrow = " + currentThrow);
        System.out.println("currentRound = " + currentRound);
        return dice;
    }

    public void createDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

    // omgång * antal tärningar med value "omgång" = summan
    public int calculateRoundScore(){
        int sum = 0;

        for(var die : dice){
            if(die.getValue() == currentRound+1){
                sum += die.getValue();
            }
        }
        return sum;
    }

    public Color getGameColor() {
        return this.gameColor;
    }

    public void addThrow() {
        this.currentThrow = (this.currentThrow + 1) % THROWS_AMOUNT;
    }

    public int getCurrentThrow() {
        return currentThrow;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
