import java.awt.*;

public class CollisionDetector {
    private static final int BALL_SIZE = 20;
    private static final int SCORE_INCREMENT = 5;

    private MapGenerator map;
    private Ball ball;
    private Paddle paddle;
    private Gameplay gameplay;

    public CollisionDetector(MapGenerator map, Ball ball, Paddle paddle, Gameplay gameplay) {
        this.map = map;
        this.ball = ball;
        this.paddle = paddle;
        this.gameplay = gameplay;
    }

    public void setMap(MapGenerator map) {
        this.map = map;
    }

    public void detect() {
        detectPaddleCollision();
        detectBrickCollision();
    }

    private void detectPaddleCollision() {
        if (new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20).intersects(new Rectangle(paddle.getPaddleX(), 550, paddle.PADDLE_WIDTH, paddle.PADDLE_HEIGHT))) {
            ball.reverseYDirection();

            int hitLocation = ball.getBallposX() - paddle.getPaddleX();

            float normalizedHitLocation = (float)hitLocation / (paddle.PADDLE_WIDTH / 2) - 1;

            int newBallXdir = (int)(normalizedHitLocation * 5);

            ball.setBallXdir(newBallXdir);
        }
    }

    private void detectBrickCollision() {
        A:for(int i=0;i<map.map.length;i++){
            for(int j=0;j<map.map[0].length;j++){
                if(map.map[i][j]>0){
                    int brickX=j*map.getBrickWidth()+80;
                    int brickY=i*map.getBrickHeight()+50;
                    int brickWidth=map.getBrickWidth();
                    int brickHeight=map.getBrickHeight();

                    Rectangle brickRect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    Rectangle ballRect=new Rectangle(ball.getBallposX(), ball.getBallposY(), BALL_SIZE, BALL_SIZE);


                    if(ballRect.intersects(brickRect)){
                        map.setBrickValue(0,i,j);
                        gameplay.setTotalBricks(gameplay.getTotalBricks()-1);
                        gameplay.setScore(gameplay.getScore()+SCORE_INCREMENT);

                        if(ball.getBallposX()+19<=brickRect.x||ball.getBallposX()+1>=brickRect.x+brickRect.width){
                            ball.reverseXDirection();
                        }else{
                            ball.reverseYDirection();
                        }
                        break A;
                    }
                }
            }
        }
    }
}