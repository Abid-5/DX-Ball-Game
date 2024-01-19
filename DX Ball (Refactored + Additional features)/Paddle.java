public class Paddle {
    private static final int INITIAL_PADDLE_X = 310;
    private static final int MAX_PADDLE_X = 600;
    private static final int MIN_PADDLE_X = 10;
    public  static final int PADDLE_Y = 550;
    public  static int PADDLE_WIDTH = 100;
    public  static final int PADDLE_HEIGHT = 8;

    private int paddleX;
    private int paddleSpeed;

    public Paddle(int paddleX, int paddleSpeed) {
        this.paddleX = paddleX;
        this.paddleSpeed = paddleSpeed;
    }

    public int getPaddleX() {
        return paddleX;
    }

    public void setWidth(int width) {
        PADDLE_WIDTH = width;
    }

    public void moveRight() {
        paddleX += paddleSpeed;
    }

    public void moveLeft() {
        paddleX -= paddleSpeed;
    }

    public boolean isAtRightBoundary() {
        return getPaddleX() >= MAX_PADDLE_X;
    }

    public boolean isAtLeftBoundary() {
        return getPaddleX() < MIN_PADDLE_X;
    }

    public void reset() {
        paddleX = INITIAL_PADDLE_X;
    }
}