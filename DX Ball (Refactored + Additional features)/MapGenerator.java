import java.awt.*;

public class MapGenerator {
    private static final int BRICK_WIDTH_FACTOR = 540;
    private static final int BRICK_HEIGHT_FACTOR = 150;
    private static final int BRICK_X_OFFSET = 80;
    private static final int BRICK_Y_OFFSET = 50;
    private static final int BRICK_STROKE = 3;

    public int[][] map;
    private int brickWidth;
    private int brickHeight;

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }

    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickWidth = BRICK_WIDTH_FACTOR / col;
        brickHeight = BRICK_HEIGHT_FACTOR / row;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0){
                    g.setColor(Color.cyan);
                    g.fillRect(j * brickWidth + BRICK_X_OFFSET, i * brickHeight + BRICK_Y_OFFSET, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(BRICK_STROKE));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + BRICK_X_OFFSET, i * brickHeight + BRICK_Y_OFFSET, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}