import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputHandler implements KeyListener {
    private final Gameplay gameplay;
    private final Paddle paddle;

    public UserInputHandler(Gameplay gameplay, Paddle paddle) {
        this.gameplay = gameplay;
        this.paddle = paddle;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!paddle.isAtRightBoundary()) {
                paddle.moveRight();
            }
            gameplay.setPlay(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!paddle.isAtLeftBoundary()) {
                paddle.moveLeft();
            }
            gameplay.setPlay(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!gameplay.isPlay()) {
                gameplay.restartGame();
            }
        }
    }
}