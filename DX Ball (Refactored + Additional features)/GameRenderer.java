import java.awt.*;

public class GameRenderer {
    private static final int BACKGROUND_X = 1;
    private static final int BACKGROUND_Y = 1;
    private static final int BACKGROUND_WIDTH = 692;
    private static final int BACKGROUND_HEIGHT = 592;
    private static final int BORDER_THICKNESS = 3;
    private static final int SCORE_X = 590;
    private static final int SCORE_Y = 30;
    private static final int BALL_SIZE = 20;

    private MapGenerator map;
    private Ball ball;
    private Paddle paddle;
    private Gameplay gameplay;

    public GameRenderer(MapGenerator map, Ball ball, Paddle paddle, Gameplay gameplay) {
        this.map = map;
        this.ball = ball;
        this.paddle = paddle;
        this.gameplay = gameplay;
    }

    public void setMap(MapGenerator map) {
        this.map = map;
    }

    public void render(Graphics g) {
        drawBackground(g);
        map.draw((Graphics2D)g);
        drawBorders(g);
        drawScores(g);
        drawPaddle(g);
        drawBall(g);
        checkGameStatus(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(BACKGROUND_X, BACKGROUND_Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    }

    private void drawBorders(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, BORDER_THICKNESS, BACKGROUND_HEIGHT);
        g.fillRect(0, 0, BACKGROUND_WIDTH, BORDER_THICKNESS);
        g.fillRect(BACKGROUND_WIDTH - BORDER_THICKNESS, 0, BORDER_THICKNESS, BACKGROUND_HEIGHT);
    }

    private void drawScores(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+ gameplay.getScore() , SCORE_X, SCORE_Y);
    }

    private void drawPaddle(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(paddle.getPaddleX(), Paddle.PADDLE_Y, Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
    }

    private void drawBall(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(ball.getBallposX(), ball.getBallposY(), BALL_SIZE, BALL_SIZE);
    }

    private void checkGameStatus(Graphics g) {
        if(gameplay.getTotalBricks() <= 0 || ball.getBallposY() > BACKGROUND_HEIGHT) {
            gameplay.stopGame();
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over, Your  Score: "+gameplay.getScore(),150,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Play Again",230,350);
        }
    }

}