//import static java.lang.Math.ceil;

public class Ball {
    private static final int INITIAL_POS_X = 120;
    private static final int INITIAL_POS_Y = 350;
    private static final int INITIAL_DIR_X = -1;
    private static final int INITIAL_DIR_Y = -2;
    private static final int MAX_POS_X = 670;

    private int ballposX;
    private int ballposY;
    private int ballXdir;
    private int ballYdir;

    public Ball(int ballposX, int ballposY, int ballXdir, int ballYdir) {
        this.ballposX = ballposX;
        this.ballposY = ballposY;
        this.ballXdir = ballXdir;
        this.ballYdir = ballYdir;
    }

    public void setSpeed(int speed) {
        double currentSpeed = Math.abs(ballXdir) + Math.abs(ballYdir);
        double speedRatio = (speed*2) / currentSpeed;
        ballXdir *= (int) speedRatio;
        ballYdir *= (int) speedRatio;
    }
    public int getBallposX() {
        return ballposX;
    }

    public int getBallposY() {
        return ballposY;
    }

    public void setBallXdir(int ballXdir) {
        this.ballXdir = ballXdir;
    }

    public void setBallYdir(int ballYdir) {
        this.ballYdir = ballYdir;
    }

    public void move() {
        ballposX += ballXdir;
        ballposY += ballYdir;
        if (ballposX < 0 || ballposX > MAX_POS_X) {
            ballXdir = -ballXdir;
        }
        if (ballposY < 0) {
            ballYdir = -ballYdir;
        }
    }

    public void reset() {
        ballposX = INITIAL_POS_X;
        ballposY = INITIAL_POS_Y;
        ballXdir = INITIAL_DIR_X;
        ballYdir = INITIAL_DIR_Y;
    }

    public void reverseXDirection() {
        ballXdir= -ballXdir;
    }

    public void reverseYDirection() {
        ballYdir= -ballYdir;
    }
}