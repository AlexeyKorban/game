package local.ldwx;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;

public class GameTest {
    private Model model;
    private Controller controller;

    @Before
    public void init() {
        model = new Model();
        controller = new Controller(model);
    }

    @Test
    public void startGameScoreTest(){
        Assert.assertEquals(0, controller.getScore());
    }

    @Test
    public void modelSizeTest(){
        Assert.assertEquals(4, controller.getGameTiles().length);
        Assert.assertEquals(4, controller.getGameTiles()[0].length);
    }

    @Test
    public void rollBackTest() {
        Tile[][] gameTiles = copy(model.getGameTiles());
        model.randomMove();
        model.rollback();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                Assert.assertEquals(gameTiles[i][j].getValue(), model.getGameTiles()[i][j].getValue());
            }
        }
    }

    private Tile[][] copy(Tile[][] tiles) {
        Tile[][] result = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = new Tile();
                result[i][j].setValue(tiles[i][j].getValue());
            }
        }
        return result;
    }

    @Test
    public void randomMoveTest() {
        int score = controller.getScore();
        KeyEvent key = new KeyEvent(controller.getView(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_R, 'R');
        controller.keyPressed(key);
        controller.keyPressed(key);
        controller.keyPressed(key);
        controller.keyPressed(key);
        Assert.assertEquals(true, controller.getScore() > score);
    }

/*    @Test
    public void leftMoveTest() {
        KeyEvent key = new KeyEvent(controller.view, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,(char) KeyEvent.VK_LEFT);
        controller.keyPressed(key);
    }*/

}