package Yatzy;

import javax.swing.*;
import java.awt.*;

public abstract class Game {

    private static final int THROWS_AMOUNT = 3;
    private static final int ROUNDS_AMOUNT = 6;

    Database database;
    protected Color gameColor;
    private Controller controller;
    private String playerName;
    private int currentScore;
    Die[] dice = new Die[5];
    private int currentThrow;
    private int currentRound;
    private boolean isFirstRound = true;

    public Game(Controller controller) {
        this.controller = controller;
        this.currentRound = 0;
        this.currentThrow = 0;
        createDice();
    }

    public Die[] rollDice() {
        addThrow();
        JToggleButton[] toggleButtons = controller.getDiceButtons();
        for (int i = 0; i < dice.length; i++) {
            if (!toggleButtons[i].isSelected()) {
                dice[i].roll();
                System.out.println("Dice " + i + " : " + dice[i].getValue());
            }
        }
        if (currentThrow == 0) {
            if (!isFirstRound)
                currentRound++;
        }
        System.out.println("currentThrow = " + currentThrow);
        System.out.println("currentRound = " + currentRound);
        return dice;
    }

    public void createDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
            System.out.println("Tärning " + (i + 1) + ": " + dice[i].getValue());
        }
    }

    public Color getGameColor() {
        return this.gameColor;
    }

    public void addThrow() {
        if (isFirstRound) {
            this.currentThrow = 0;
            this.isFirstRound = false;
        } else
            this.currentThrow = (this.currentThrow + 1) % THROWS_AMOUNT;
    }

    public int getCurrentThrow() {
        return currentThrow;
    }

    public int getCurrentRound() {
        return currentRound;
    }
}
