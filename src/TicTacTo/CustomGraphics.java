package TicTacTo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class CustomGraphics extends JPanel implements ActionListener {

    static final int WIDTH = 750;
    static final int HEIGHT = 750;

    static final String MARK_X = "X";
    static final String MARK_O = "O";

    final JButton[] tiles = new JButton[9];
    boolean isFirstPlayerActive;

    public CustomGraphics() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            tiles[i] = new JButton();
            tiles[i].setFont(new Font("Times New Roman", Font.BOLD, 150));
            tiles[i].setFocusPainted(false);
            tiles[i].addActionListener(this);
            this.add(tiles[i]);
        }

        isFirstPlayerActive = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == tiles[i]) {
                if (tiles[i].getText().isEmpty()) {
                    if (isFirstPlayerActive) {
                        tiles[i].setForeground(Color.RED);
                        tiles[i].setText(MARK_X);
                        isFirstPlayerActive = false;
                    } else {
                        tiles[i].setForeground(Color.YELLOW);
                        tiles[i].setText(MARK_O);
                        isFirstPlayerActive = true;
                    }
                    checkState();
                }
            }
        }
    }

    protected void checkState() {
        if (checkMark(MARK_X) || checkMark(MARK_O) || checkDraw()) {
            return;
        }
    }

    protected boolean checkDraw() {
        for (JButton tile : tiles) {
            if (tile.getText().isEmpty()) {
                return false;
            }
        }
        Arrays.stream(tiles).forEach(t -> t.setEnabled(false));
        return true;
    }

    protected boolean checkMark(String mark) {
        boolean isDone = checkDirection(0, 1, 2, mark) ||
                checkDirection(3, 4, 5, mark) ||
                checkDirection(6, 7, 8, mark) ||
                checkDirection(0, 3, 6, mark) ||
                checkDirection(1, 4, 7, mark) ||
                checkDirection(2, 5, 8, mark) ||
                checkDirection(0, 4, 8, mark) ||
                checkDirection(2, 4, 6, mark);

        return isDone;
    }

    protected boolean checkDirection(int posA, int posB, int posC, String mark) {
        if (tiles[posA].getText().equals(mark) && tiles[posB].getText().equals(mark) && tiles[posC].getText().equals(mark)) {
            setWinner(posA, posB, posC);
            return true;
        }
        return false;
    }

    protected void setWinner(int posA, int posB, int posC) {
        tiles[posA].setBackground(Color.CYAN);
        tiles[posA].setOpaque(true);
        tiles[posB].setBackground(Color.CYAN);
        tiles[posB].setOpaque(true);
        tiles[posC].setBackground(Color.CYAN);
        tiles[posC].setOpaque(true);

        Arrays.stream(tiles).forEach(t -> t.setEnabled(false));
    }
}