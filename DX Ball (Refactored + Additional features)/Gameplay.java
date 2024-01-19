import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gameplay extends JPanel implements ActionListener {
    private  int  level = 1;
    private static final int INITIAL_DELAY = 8;
    private static  int MAP_ROWS = 3;
    private static  int MAP_COLS = 7;
    private static  int INITIAL_TOTAL_BRICKS = MAP_ROWS * MAP_COLS;

    boolean play = false;
    private int score = 0;
    private int totalBricks = INITIAL_TOTAL_BRICKS;

    private final Timer timer;
    private MapGenerator map;
    private Ball ball;
    private Paddle paddle;
    private GameRenderer renderer;
    private final CollisionDetector collisionDetector;
    private final UserInputHandler userInputHandler;

    public Gameplay() {
        map = new MapGenerator(MAP_ROWS, MAP_COLS);
        ball = new Ball(120, 350, -1, -2);
        paddle = new Paddle(310, 20);
        renderer = new GameRenderer(map, ball, paddle, this);
        collisionDetector = new CollisionDetector(map, ball, paddle, this);
        userInputHandler = new UserInputHandler(this, paddle);

        addKeyListener(userInputHandler);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(INITIAL_DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g);
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            collisionDetector.detect();
            ball.move();
            checkGameStatus();
            repaint();
        }
    }

    public void checkGameStatus() {
        if (totalBricks <= 0) {
            nextLevel();
        }
    }

    private void resetGame() {
        play = true;
        ball.reset();
        paddle.reset();
        totalBricks = INITIAL_TOTAL_BRICKS;
        map = new MapGenerator(MAP_ROWS, MAP_COLS);
        renderer.setMap(map);
        collisionDetector.setMap(map);
        repaint();
    }

    void restartGame() {
        score = 0;
        Paddle.PADDLE_WIDTH = 100;
        resetGame();
    }

    public void  nextLevel(){
        level++;
        MAP_ROWS++;
        MAP_COLS++;
        INITIAL_TOTAL_BRICKS = MAP_ROWS * MAP_COLS;
        ball.setSpeed(level * 2);
        paddle.setWidth(Paddle.PADDLE_WIDTH/ level);
        resetGame();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalBricks() {
        return totalBricks;
    }

    public void setTotalBricks(int total) {
        this.totalBricks = total;
    }

    public void stopGame() {
        play = false;
        ball.setBallXdir(0);
        ball.setBallYdir(0);
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isPlay() {
        return play;
    }
}