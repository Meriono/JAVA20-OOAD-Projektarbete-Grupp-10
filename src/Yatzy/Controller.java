package Yatzy;

import javax.swing.*;

public class Controller {

    private YatzyWindow window;
    public Game game;

    public Controller() {
        this.window = new YatzyWindow();
        setUpStartButtonListener();

        window.getYatzyPanel().roll.addActionListener(l -> {


            window.getYatzyPanel().dices = game.rollDice();
        });
    }

    public void setUpStartButtonListener(){
        window.getStartPanel().getStartGameButton().addActionListener(l -> {
            if (window.getStartPanel().getNotRankedGameButton().isSelected()){
                window.setTitle("Just playing for fun... loser");
                startUnrankedGame();
                window.changePanelTo(window.getYatzyPanel());
            }
            else if (window.getStartPanel().getRankedGameButton().isSelected()){
                if (window.getStartPanel().getNameField().getText().length() > 2){
                    this.window.setTitle("Name: " + window.getStartPanel().getNameField().getText());
                    startRankedGame();
                    window.changePanelTo(window.getYatzyPanel());
                }
                else System.out.println("Du måste ange ett namn/alias med minst 3 tecken.");
            }
        });
    }

    public void startUnrankedGame() {
        this.game = new UnrankedGame();
        this.window.getYatzyPanel().setColor(game.getGameColor());
    }

    public void startRankedGame() {
        this.game = new RankedGame();
        this.window.getYatzyPanel().setColor(game.getGameColor());
    }

    public JToggleButton[] getDiceButtons(){
        return window.getYatzyPanel().dices;
    }
}
