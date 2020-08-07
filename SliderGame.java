package slidergame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Rachel Kwan
 */
public class SliderGame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new SliderGame();
    }

    boolean checkGame = true;
    private JFrame JFrame1;
    private JButton[] buttons = new JButton[16];
    private JButton playButton;
    private JPanel mainJPanel;
    private JLabel bottomText, moveTracker, highScoreLabel;
    private int emptyIndex; //Variable will track the empty spot.
    private int moveCounter = 0;
    private int highScore = 100000; //setting initial "impossible" score
    private int inversionsCount = 0;

    public SliderGame() {

        //set up JFrame
        JFrame1 = new JFrame("Slider Game");
        JFrame1.setSize(550, 550);
        JFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame1.setResizable(false);

        Font f = new Font("Serif", Font.PLAIN, 35);

        mainJPanel = new JPanel();
        mainJPanel.setSize(350, 350);
        mainJPanel.setVisible(true);
        mainJPanel.setEnabled(true);
        mainJPanel.setLayout(new GridLayout(5, 4, 5, 5));

        mainJPanel.setBackground(Color.white);

        Color[] colours = {Color.lightGray, Color.orange};

        for (int i = 0; i < buttons.length; i++) {

            int colourIndex = 0;
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].addActionListener(this);
            colourIndex = 0; //default colour of square is orange
            if ((i >= 0 && i <= 3 && i % 2 != 0)
                    || (i >= 4 && i <= 7 && i % 2 == 0)
                    || (i >= 8 && i <= 11 && i % 2 != 0)
                    || (i >= 12 && i <= 15 && i % 2 == 0)) {
                colourIndex = 1; //make white if necessary
            }
            buttons[i].setBackground(colours[colourIndex]);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(f);
            buttons[i].setSize(125, 125);

            mainJPanel.add(buttons[i]);
        }

        //set last piece to nothing 
        buttons[15].setVisible(false);
        emptyIndex = 15;

        playButton = new JButton(" Play ");
        playButton.setVisible(true);
        playButton.addActionListener(this);
        playButton.setBackground(Color.black);
        playButton.setForeground(Color.white);
        playButton.setFont(f);
        playButton.setSize(20, 20);
        playButton.setLocation(250, 500);

        bottomText = new JLabel();
        bottomText.setFont(new Font("Serif", Font.PLAIN, 17));
        bottomText.setSize(100, 20);
        bottomText.setLocation(400, 500);

        moveTracker = new JLabel(" # of moves: " + moveCounter);
        moveTracker.setFont(new Font("Serif", Font.PLAIN, 15));
        moveTracker.setSize(200, 20);
        moveTracker.setLocation(600, 500);

        highScoreLabel = new JLabel(" HS: 0");
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        highScoreLabel.setSize(200, 20);
        highScoreLabel.setLocation(800, 500);

        JFrame1.setContentPane(mainJPanel);
        JFrame1.add(playButton);
        JFrame1.add(bottomText);
        JFrame1.add(moveTracker);
        JFrame1.add(highScoreLabel);
        JFrame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* for (int i = 0; i < buttons.length; i++) {
            if(e.getSource() == buttons[i]){
                System.out.println(buttons[i].getText());
                        
            }
        }*/
        if (e.getSource() == playButton) {
            
            reset();
            Scramble();
            //resets inversionsCount everytime
            inversionsCount = 0;
                while(solvable() != true || inversionsCount == 5){
                    Scramble();
                }
                    

            playButton.setText(" Play ");
            playButton.setFont(new Font("Serif", Font.PLAIN, 35));
            bottomText.setText("");

        }

        if (e.getSource() == buttons[0]) {
            swapPieces(0);
            moveCounter++;
        }

        if (e.getSource() == buttons[1]) {
            swapPieces(1);
            moveCounter++;
        }

        if (e.getSource() == buttons[2]) {
            swapPieces(2);
            moveCounter++;
        }

        if (e.getSource() == buttons[3]) {
            swapPieces(3);
            moveCounter++;
        }

        if (e.getSource() == buttons[4]) {
            swapPieces(4);
            moveCounter++;
        }

        if (e.getSource() == buttons[5]) {
            swapPieces(5);
            moveCounter++;
        }

        if (e.getSource() == buttons[6]) {
            swapPieces(6);
            moveCounter++;
        }

        if (e.getSource() == buttons[7]) {
            swapPieces(7);
            moveCounter++;
        }

        if (e.getSource() == buttons[8]) {
            swapPieces(8);
            moveCounter++;
        }

        if (e.getSource() == buttons[9]) {
            swapPieces(9);
            moveCounter++;
        }

        if (e.getSource() == buttons[10]) {
            swapPieces(10);
            moveCounter++;
        }

        if (e.getSource() == buttons[11]) {
            swapPieces(11);
            moveCounter++;
        }

        if (e.getSource() == buttons[12]) {
            swapPieces(12);
            moveCounter++;
        }

        if (e.getSource() == buttons[13]) {
            swapPieces(13);
            moveCounter++;
        }

        if (e.getSource() == buttons[14]) {
            swapPieces(14);
            moveCounter++;
        }

        if (e.getSource() == buttons[15]) {
            swapPieces(15);
            moveCounter++;
        }

        moveTracker.setText(" # of moves: " + moveCounter);

        int buttonNum[] = new int[buttons.length];
        int numbers[] = new int[buttons.length];
        int counters = 0;
        int score = moveCounter;

        for (int i = 0; i < buttons.length - 1; i++) {
            buttonNum[i] = Integer.parseInt(buttons[i].getText());
            numbers[i] = i + 1;

            if (buttonNum[i] == numbers[i]) {
                counters++;

            }
            if (counters == 15) {
                bottomText.setText("You Win!");
                playButton.setText(" Play Again ");
                playButton.setFont(new Font("Serif", Font.PLAIN, 20));
                if (score < highScore) {
                    highScore = score;
                    highScoreLabel.setText(" HS: " + highScore);

                }

            }
        }

    }

    public void Scramble() {

        int numbers[] = new int[15];
        reset();
        //setting the initial variables
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;

        }

        for (int i = 0; i < numbers.length; i++) {

            int randomIndex = (int) (Math.random() * 15);

            //swap
            int temp = numbers[randomIndex];
            numbers[randomIndex] = numbers[i];
            numbers[i] = temp;

        }

        //printing out array 
        for (int k = 0; k < buttons.length - 1; k++) {
            buttons[k].setText("" + numbers[k]);

        }
        
       inversionsCount = 0;
       

    }

    private void swapPieces(int btnIndex) {
        if (emptyIndex == btnIndex + 1 || emptyIndex == btnIndex - 1 || emptyIndex == btnIndex + 4 || emptyIndex == btnIndex - 4) {
            buttons[emptyIndex].setText(buttons[btnIndex].getText());
            buttons[emptyIndex].setVisible(true);
            buttons[btnIndex].setVisible(false);
            buttons[emptyIndex].setVisible(true);
            emptyIndex = btnIndex;

        }
    }

    public void reset() {
        //reset buttons to orginal place
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setText("" + (i+1));
        }
        buttons[emptyIndex].setVisible(true);
        emptyIndex = 15;
        buttons[15].setVisible(false);
        moveCounter = 0;

    }
    
    public boolean solvable(){
        
        int numbers[] = new int[buttons.length];
        
        for(int i = 0; i < buttons.length; i++){
            numbers[i] = Integer.parseInt(buttons[i].getText());
            
            //able to compare two numbers at a tiem and see which one is greater
            int pair = i+2;
            
            if(numbers[i] > pair){
                inversionsCount++; 

            }
            else{
            
            }
            numbers[i] = i+1;
            
         }
        
        //only true if the inversion count is odd "If the grid width is even, and the blank is on an even row counting from the bottom (second-last, fourth-last etc), then the number of inversions in a solvable situation is odd."
        if(inversionsCount %2 == 0){           
            return false;
        }
        
        else{
            return true;
        }
        
   }

}
