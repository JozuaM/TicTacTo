package TicTacTo;

import TicTacTo.Game;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}