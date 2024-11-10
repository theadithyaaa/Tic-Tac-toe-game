import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Game extends JFrame {
    private JButton[][] buttons; 
    private JLabel label;
    private JButton restartButton; 
    private boolean xTurn = true;
    private String winner = ""; 

    Game() {
        setSize(500, 600);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        label = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        label.setFont(new Font("1", Font.BOLD, 25));

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        topPanel.add(restartButton, BorderLayout.WEST);
        topPanel.add(label, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 2, 2));

        buttons = new JButton[3][3]; 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("", Font.BOLD, 25));
                buttons[i][j].addActionListener(new click(i, j));
                buttonPanel.add(buttons[i][j]);
            }
        }

        add(buttonPanel);
    }

    private class click implements ActionListener {
        private int row;
        private int col;

        public click(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent evt) {
            if (!buttons[row][col].getText().equals("") || !winner.equals("")) {
                return; 
            }

            if (xTurn) {
                buttons[row][col].setText("X");
            } else {
                buttons[row][col].setText("O");
            }

            xTurn = !xTurn;
            checkWin();
        }
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][0].getText().equals(buttons[i][2].getText())) {
                winner = buttons[i][0].getText();
                label.setText(winner + " wins!");
                return;
            }

            if (!buttons[0][i].getText().equals("") &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[0][i].getText().equals(buttons[2][i].getText())) {
                winner = buttons[0][i].getText();
                label.setText(winner + " wins!");
                return;
            }
        }

        if (!buttons[0][0].getText().equals("") &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[0][0].getText().equals(buttons[2][2].getText())) {
            winner = buttons[0][0].getText();
            label.setText(winner + " wins!");
            return;
        }

        if (!buttons[0][2].getText().equals("") &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[0][2].getText().equals(buttons[2][0].getText())) {
            winner = buttons[0][2].getText();
            label.setText(winner + " wins!");
            return;
        }

        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }

        if (draw && winner.equals("")) {
            label.setText("Draw!");
        }
    }

    private void restartGame() {
        xTurn = true;
        winner = "";
        label.setText("Tic Tac Toe");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}
