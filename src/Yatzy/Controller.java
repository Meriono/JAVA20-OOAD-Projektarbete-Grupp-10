package Yatzy;

import javax.swing.*;
import java.awt.*;

public class Controller {

    private YatzyWindow window;
    private Game game;

    public Controller() {
        this.window = new YatzyWindow();
        setUpStartPanelListener();

        setUpStartButtonListener();

        setUpRollButtonListener();

        setUpCloseButtonListener();

        setUpHighscoreButtonListener();

        setUpSelectedDieColorListener();
    }

    public void setUpStartPanelListener(){
        window.getStartPanel().getRankedGameButton().addActionListener(l -> {
            if(window.getStartPanel().getRankedGameButton().isSelected()){
                window.getStartPanel().getUnrankedGameButton().setSelected(false);
                window.getStartPanel().getNameField().setVisible(true);
                window.getStartPanel().getStartGameButton().setEnabled(true);
                window.getStartPanel().getRankedGameButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 30));
                window.getStartPanel().getUnrankedGameButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,30));
                window.getStartPanel().setBackground(Color.LIGHT_GRAY);
                window.getStartPanel().getNameLabel().setVisible(true);
                window.getStartPanel().repaintTextField();
            }
            else if(!window.getStartPanel().getRankedGameButton().isSelected()){
                window.getStartPanel().getNameField().setVisible(false);
                window.getStartPanel().getNameLabel().setVisible(false);
                window.getStartPanel().getRankedGameButton().setBorder(BorderFactory.createLineBorder(
                        window.getStartPanel().getColor(), 30));
                window.getStartPanel().getUnrankedGameButton().setBorder(BorderFactory.createLineBorder(
                        window.getStartPanel().getColor(),30));
                window.getStartPanel().setBackground(window.getStartPanel().getColor());
                window.getStartPanel().getStartGameButton().setEnabled(false);
                window.getStartPanel().repaintTextField();
            }
        });
        window.getStartPanel().getUnrankedGameButton().addActionListener(l -> {
            if(window.getStartPanel().getUnrankedGameButton().isSelected()){
                window.getStartPanel().getRankedGameButton().setSelected(false);
                window.getStartPanel().getNameField().setVisible(false);
                window.getStartPanel().getNameLabel().setVisible(false);
                window.getStartPanel().getUnrankedGameButton().setBorder(BorderFactory.createLineBorder(Color.PINK,30));
                window.getStartPanel().getRankedGameButton().setBorder(BorderFactory.createLineBorder(Color.PINK, 30));
                window.getStartPanel().setBackground(Color.PINK);
                window.getStartPanel().getStartGameButton().setEnabled(true);
                window.getStartPanel().repaintTextField();
            }
            else if(!window.getStartPanel().getUnrankedGameButton().isSelected()){
                window.getStartPanel().getStartGameButton().setEnabled(false);
                window.getStartPanel().getRankedGameButton().setBorder(BorderFactory.createLineBorder(
                        window.getStartPanel().getColor(), 30));
                window.getStartPanel().getUnrankedGameButton().setBorder(BorderFactory.createLineBorder(
                        window.getStartPanel().getColor(),30));
                window.getStartPanel().setBackground(
                        window.getStartPanel().getColor());
            }
        });
    }

    public void setUpEndPanelListener(){
        window.getEndPanel().getAnswerYesButton().addActionListener(l -> {
            if(window.getEndPanel().getAnswerYesButton().isSelected()){
                window.getEndPanel().getAnswerNoButton().setSelected(false);
                window.getEndPanel().getNameField().setVisible(true);
                window.getEndPanel().getSaveHighscoreButton().setEnabled(true);
                window.getEndPanel().getAnswerYesButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 30));
                window.getEndPanel().getAnswerNoButton().setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,30));
                window.getEndPanel().setBackground(Color.LIGHT_GRAY);
                window.getEndPanel().getSaveHighscoreButton().setText("Spara");
                window.getEndPanel().getNameLabel().setVisible(true);
                window.getEndPanel().repaintTextField();
            }
            else if(!window.getEndPanel().getAnswerYesButton().isSelected()){
                window.getEndPanel().getNameField().setVisible(false);
                window.getEndPanel().getNameLabel().setVisible(false);
                window.getEndPanel().getAnswerYesButton().setBorder(BorderFactory.createLineBorder(
                        window.getEndPanel().getColor(), 30));
                window.getEndPanel().getAnswerNoButton().setBorder(BorderFactory.createLineBorder(
                        window.getEndPanel().getColor(),30));
                window.getEndPanel().setBackground(window.getStartPanel().getColor());
                window.getEndPanel().getSaveHighscoreButton().setText("Tillbaka");
                window.getEndPanel().getSaveHighscoreButton().setEnabled(false);
                window.getEndPanel().repaintTextField();
            }
        });
        window.getEndPanel().getAnswerNoButton().addActionListener(l -> {
            if(window.getEndPanel().getAnswerNoButton().isSelected()){
                window.getEndPanel().getAnswerYesButton().setSelected(false);
                window.getEndPanel().getNameField().setVisible(false);
                window.getEndPanel().getNameLabel().setVisible(false);
                window.getEndPanel().getAnswerNoButton().setBorder(BorderFactory.createLineBorder(Color.PINK,30));
                window.getEndPanel().getAnswerYesButton().setBorder(BorderFactory.createLineBorder(Color.PINK, 30));
                window.getEndPanel().setBackground(Color.PINK);
                window.getEndPanel().getSaveHighscoreButton().setText("Tillbaka");
                window.getEndPanel().getSaveHighscoreButton().setEnabled(true);
                window.getEndPanel().repaintTextField();
            }
            else if(!window.getEndPanel().getAnswerNoButton().isSelected()){
                window.getEndPanel().getSaveHighscoreButton().setEnabled(false);
                window.getEndPanel().getAnswerYesButton().setBorder(BorderFactory.createLineBorder(
                        window.getEndPanel().getColor(), 30));
                window.getEndPanel().getAnswerNoButton().setBorder(BorderFactory.createLineBorder(
                        window.getEndPanel().getColor(),30));
                window.getEndPanel().setBackground(
                        window.getEndPanel().getColor());
            }
        });
    }

    public void setUpRollButtonListener(){
        window.getYatzyPanel().getRollButton().addActionListener(l -> {
            changeButtonStates(true);

            if(window.getYatzyPanel().getRollButton().getText().equalsIgnoreCase("Spela igen")){
                window.setVisible(false);
                new Controller();
            }

            Die[] dice = game.rollDice();
            JToggleButton[] toggleButtons = getDiceButtons();
            for (int i = 0; i < dice.length; i++) {
                if (!toggleButtons[i].isSelected()) {
                    toggleButtons[i].setText("" + dice[i].getValue());
                }
            }
            window.getYatzyPanel().getRollButton().setText("Kasta");

            if(game.getCurrentThrow() == 2){
                for (JToggleButton diceButton: window.getYatzyPanel().getDiceButtons()) {
                    diceButton.setSelected(false);
                    diceButton.setBackground(game.getGameColor());
                }
                changeButtonStates(false);

                int roundScore = game.calculateRoundScore();
                window.getYatzyPanel().getScoreLabels().get(game.getCurrentRound()).setText(String.valueOf(roundScore));
            }

            setRoundColors();

            if (game.getCurrentRound() == Game.ROUNDS_AMOUNT-1 && game.getCurrentThrow() == Game.THROWS_AMOUNT-1){
                setFinalScore();
            }
        });
    }

    public void setUpCloseButtonListener(){
        window.getYatzyPanel().getCloseButton().setEnabled(false);
        window.getYatzyPanel().getCloseButton().addActionListener(l -> {
                System.exit(0);
        });
    }

    public void setUpStartButtonListener() {
        window.getStartPanel().getStartGameButton().addActionListener(l -> {
            if (window.getStartPanel().getUnrankedGameButton().isSelected()) {
                window.setTitle("YATZY");
                startUnrankedGame();
                window.changePanelTo(window.getYatzyPanel());
            } else if (window.getStartPanel().getRankedGameButton().isSelected()) {
                if (!window.getStartPanel().getNameField().getText().isBlank()
                        && window.getStartPanel().getNameField().getText().length() < 11) {
                    String name = window.getStartPanel().getNameField().getText();
                    this.window.setTitle("Name: " + name);
                    startRankedGame();
                    game.setPlayerName(name);
                    window.changePanelTo(window.getYatzyPanel());
                } else System.out.println("Ange namn mellan 1 och 10.");
            }
        });
    }

    public void setUpSaveHighscoreButtonListener() {
        window.getEndPanel().getSaveHighscoreButton().addActionListener(l ->{
            if(window.getEndPanel().getAnswerNoButton().isSelected()){
                window.changePanelTo(window.getYatzyPanel());
            } else if(window.getEndPanel().getAnswerYesButton().isSelected()){
                if(!window.getEndPanel().getNameField().getText().isBlank()
                    && window.getEndPanel().getNameField().getText().length() < 11){
                    String unrankedName = window.getEndPanel().getNameField().getText();
                    game.database.addScore(new Score(unrankedName, game.getCurrentScore()));
                    game.database.saveData();
                    new HighScoreWindow(game.database.getListOfScores());
                    window.changePanelTo(window.getYatzyPanel());
                } else System.out.println("Ange namn mellan 1 och 10 tecken");
            }
        });
    }

    public void setUpHighscoreButtonListener(){
        window.getYatzyPanel().getShowScoreButton().addActionListener(l -> {
            new HighScoreWindow(game.database.getListOfScores());
        });
    }

    public void setUpSelectedDieColorListener(){
        for (JToggleButton diceButton : window.getYatzyPanel().getDiceButtons()) {
            diceButton.addActionListener(l -> {
                if (diceButton.isSelected())
                    diceButton.setBackground(new Color(184,207,229));
                else
                    diceButton.setBackground(game.gameColor);
            });
        }
    }

    public void startUnrankedGame() {
        this.game = new UnrankedGame(this);
        this.window.getYatzyPanel().setDieColor(game.getGameColor());
    }

    public void startRankedGame() {
        this.game = new RankedGame(this);
        this.window.getYatzyPanel().setDieColor(game.getGameColor());
    }

    public JToggleButton[] getDiceButtons() {
        return window.getYatzyPanel().getDiceButtons();
    }

    public void setRoundColors(){
        if(game.getCurrentRound() < Game.ROUNDS_AMOUNT){
            if(Integer.parseInt(window.getYatzyPanel().getRoundLabels().get(game.getCurrentRound()).getText())-1 == game.getCurrentRound()){
                window.getYatzyPanel().getRoundLabels().get(game.getCurrentRound()).setBackground(game.getGameColor());
            }
            else{
                window.getYatzyPanel().getRoundLabels().get(game.getCurrentRound()).setBackground(Color.white);
            }
        }
    }

    public void changeButtonStates(Boolean state){
        for(var button : window.getYatzyPanel().getDiceButtons()){
            button.setEnabled(state);
        }
        if (!state) setUpNewRound();
    }

    public void setUpNewRound(){
        window.getYatzyPanel().getRollButton().setText("Nästa omgång");
    }

    public void setFinalScore(){
        if(game.isBonusQualified()){
            window.getYatzyPanel().getScoreLabels().get(6).setText("35");
        }
        else window.getYatzyPanel().getScoreLabels().get(6).setText("0");
        window.getYatzyPanel().getScoreLabels().get(7).setText(String.valueOf(game.getCurrentScore()));

        if(game instanceof RankedGame){
            game.database.addScore(new Score(game.getPlayerName(), game.getCurrentScore()));
            game.database.saveData();
        }
        else {
            addHighScoreToUnrankedGame();
        }

        window.getYatzyPanel().getRollButton().setText("Spela igen");
        window.getYatzyPanel().getCloseButton().setEnabled(true);
    }

    public void addHighScoreToUnrankedGame() {
        new HighScoreWindow(game.database.getListOfScores());
        window.getEndPanel().setQuestionLabelText(game.getCurrentScore());
        window.changePanelTo(window.getEndPanel());
        setUpEndPanelListener();
        setUpSaveHighscoreButtonListener();
    }
}