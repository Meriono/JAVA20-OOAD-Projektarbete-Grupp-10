package Yatzy;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel {

    // Backgroundcolor
    private Color color = new Color(184,207,229);

    // Panels
    private JPanel topPanel = new JPanel(new GridLayout(1,2,10,10));
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel(); // new GridLayout(1,1)

    // Buttons
    private JToggleButton answerYesButton = new JToggleButton("Ja");
    private JToggleButton answerNoButton = new JToggleButton("Nej");
    private JButton saveHighscoreButton = new JButton("Spara");

    // Textfield
    private JTextField nameField = new JTextField("",14);

    // Label
    private JLabel questionLabel = new JLabel();
    private JLabel nameLabel = new JLabel("Enter your name: ");

    public EndPanel(){
        setUpThisJPanel();
        setUpJPanels();
        setUpAndAddToggleButtons();
        setUpAndAddLabel();
        setUpAndAddTextfield();
        setUpAndAddSaveHighscoreButton();

        this.revalidate();
        this.repaint();
    }

    public void setUpThisJPanel(){
        this.setBackground(color);  // TestFärg
        this.setLayout(new GridLayout(3,1,10,10));
        this.setPreferredSize(new Dimension(500, 480));
    }

    public void setUpJPanels(){
        topPanel.setOpaque(false);
        middlePanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);
    }

    public void setUpAndAddToggleButtons(){
        answerYesButton.setOpaque(true);
        answerYesButton.setBorder(BorderFactory.createLineBorder(color,30));
        answerYesButton.setFont(new Font("SansSerif", Font.BOLD,20));
        answerNoButton.setOpaque(true);
        answerNoButton.setBorder(BorderFactory.createLineBorder(color,30));
        answerNoButton.setFont(new Font("SansSerif", Font.BOLD,20));
        topPanel.add(answerYesButton);
        topPanel.add(answerNoButton);
    }

    public void setUpAndAddLabel(){
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        nameLabel.setVisible(false);
        middlePanel.add(nameLabel, CENTER_ALIGNMENT);

        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        topPanel.add(questionLabel, CENTER_ALIGNMENT);
    }

    public void setUpAndAddTextfield(){
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("SansSerif",Font.ITALIC,25));
        nameField.setVisible(false);
        middlePanel.add(nameField, CENTER_ALIGNMENT);
    }

    public void setUpAndAddSaveHighscoreButton(){
        saveHighscoreButton.setFont(new Font("SansSerif", Font.BOLD,20));
        saveHighscoreButton.setEnabled(false);
        bottomPanel.add(saveHighscoreButton);
    }

    public void repaintTextField(){
        middlePanel.revalidate();
        middlePanel.repaint();
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JToggleButton getAnswerYesButton() {
        return answerYesButton;
    }

    public JToggleButton getAnswerNoButton() {
        return answerNoButton;
    }

    public JButton getSaveHighscoreButton() {
        return saveHighscoreButton;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public Color getColor() {
        return color;
    }

    public void setQuestionLabelText(String question) {
        questionLabel.setText(question);
    }
}
