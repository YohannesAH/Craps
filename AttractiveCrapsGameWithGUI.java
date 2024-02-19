// This is a simple craps game this game is famous all around the world through out alleys and casinos
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AttractiveCrapsGameWithGUI {

    private JFrame frame;
    private JButton rollButton;
    private JLabel resultLabel;
    private JLabel pointLabel;
    private JLabel diceLabel1;
    private JLabel diceLabel2;

    private int point;

    public AttractiveCrapsGameWithGUI() {
        frame = new JFrame("Attractive Craps Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(192, 192, 192));  // Silver background for the frame

        rollButton = new JButton("Roll the Dice");
        rollButton.setFont(new Font("Arial", Font.BOLD, 16));  // Adjust the font size as needed
        rollButton.setForeground(Color.WHITE);
        rollButton.setBackground(new Color(0, 102, 204));  // Dark blue background for the button
        rollButton.setFocusPainted(false);
        rollButton.setBorderPainted(false);
        rollButton.setContentAreaFilled(false); // This line makes the button transparent
        rollButton.setOpaque(true); // This line ensures the background color is painted
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        resultLabel = new JLabel("Welcome to the Craps Game!");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Adjust the font size as needed
        resultLabel.setForeground(Color.BLUE);

        pointLabel = new JLabel("");
        pointLabel.setHorizontalAlignment(JLabel.CENTER);
        pointLabel.setFont(new Font("Arial", Font.PLAIN, 18));  // Adjust the font size as needed
        pointLabel.setForeground(Color.BLUE);

        diceLabel1 = new JLabel();
        diceLabel2 = new JLabel();

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new GridLayout(1, 2));
        dicePanel.setBackground(new Color(192, 192, 192));  // Silver background for the dice panel
        dicePanel.add(diceLabel1);
        dicePanel.add(diceLabel2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(192, 192, 192));  // Silver background for the button panel
        buttonPanel.add(rollButton);

        frame.add(resultLabel, BorderLayout.CENTER);
        frame.add(pointLabel, BorderLayout.NORTH);
        frame.add(dicePanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void rollDice() {
        rollButton.setEnabled(false);
        resultLabel.setText("Rolling the Dice...");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int dice1 = random.nextInt(6) + 1;
                int dice2 = random.nextInt(6) + 1;
                int sum = dice1 + dice2;

                updateDiceImages(dice1, dice2);
                resultLabel.setText("You rolled: " + dice1 + " + " + dice2 + " = " + sum);

                if (point == 0) {
                    handleComeOutRoll(sum);
                } else {
                    handlePointRoll(sum);
                }

                rollButton.setEnabled(true);
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void updateDiceImages(int dice1, int dice2) {
        ImageIcon diceImage1 = new ImageIcon("dice" + dice1 + ".png");
        ImageIcon diceImage2 = new ImageIcon("dice" + dice2 + ".png");

        diceLabel1.setIcon(diceImage1);
        diceLabel2.setIcon(diceImage2);
    }

    private void handleComeOutRoll(int sum) {
        if (sum == 7 || sum == 11) {
            resultLabel.setText("Congratulations! You win!");
            resetGame();
        } else if (sum == 2 || sum == 3 || sum == 12) {
            resultLabel.setText("Oops! You lose. Better luck next time!");
            resetGame();
        } else {
            resultLabel.setText("Point is set to " + sum);
            point = sum;
            pointLabel.setText("Point: " + point);
        }
    }

    private void handlePointRoll(int sum) {
        if (sum == 7) {
            resultLabel.setText("Oops! You rolled a 7. You lose. Better luck next time!");
            resetGame();
        } else if (sum == point) {
            resultLabel.setText("Congratulations! You rolled the point. You win!");
            resetGame();
        }
    }

    private void resetGame() {
        point = 0;
        pointLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AttractiveCrapsGameWithGUI();
            }
        });
    }
}
