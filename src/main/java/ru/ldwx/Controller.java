package ru.ldwx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private final static int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.getScore();
    }

    private void resetGame() {
        model.setScore(0);
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();

    }

    public void reset() {
        resetGame();
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
        }
        if (!model.canMove()) {
            view.isGameLost = true;
        }
        if (!view.isGameLost && !view.isGameWon) {
            switch (e.getKeyCode()) {
                case (KeyEvent.VK_LEFT):
                    model.left();
                    break;
                case (KeyEvent.VK_RIGHT):
                    model.right();
                    break;
                case (KeyEvent.VK_UP):
                    model.up();
                    break;
                case (KeyEvent.VK_DOWN):
                    model.down();
                    break;
                default:
                    break;
            }
        }
        if (model.getMaxTile() == WINNING_TILE) {
            view.isGameWon = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            model.rollback();
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            model.randomMove();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            model.autoMove();
        }
        view.repaint();
    }

    public View getView() {
        return view;
    }
}
