import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JPanel implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 50;
    private static final int PLAYER_SPEED = 5;

    private int playerX = WIDTH / 2;
    private int playerY = HEIGHT / 2;
    private boolean running = true;

    public Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT -> playerX -= PLAYER_SPEED;
                    case KeyEvent.VK_RIGHT -> playerX += PLAYER_SPEED;
                    case KeyEvent.VK_UP -> playerY -= PLAYER_SPEED;
                    case KeyEvent.VK_DOWN -> playerY += PLAYER_SPEED;
                }

                // Ensure player stays on screen
                playerX = Math.max(0, Math.min(playerX, WIDTH - PLAYER_SIZE));
                playerY = Math.max(0, Math.min(playerY, HEIGHT - PLAYER_SIZE));

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(33); // approximately 30 frames per second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game");
        Main game = new Main();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }
}
