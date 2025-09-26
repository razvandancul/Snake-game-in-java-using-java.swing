import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final int TILE_SIZE = 35;
    private final int ROWS = 15;
    private final int COLS = 15;

    private Snake snake;
    private Food food;

    public GamePanel(){
        snake = new Snake(3, COLS / 2 * TILE_SIZE, ROWS / 2 * TILE_SIZE, TILE_SIZE);
        food = new Food(TILE_SIZE, ROWS, COLS, snake.getX(), snake.getY(), snake.getLength());
        this.setPreferredSize(new Dimension(TILE_SIZE * COLS, TILE_SIZE * ROWS));
        this.setBackground(Color.black);

        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:{
                        if(snake.getDirection() != 'D')
                            snake.setNextDirection('U');
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        if(snake.getDirection() != 'U')
                            snake.setNextDirection('D');
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        if(snake.getDirection() != 'R')
                            snake.setNextDirection('L');
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        if(snake.getDirection() != 'L')
                            snake.setNextDirection('R');
                        break;
                    }
                }
            }
        });

        Timer timer = new Timer(150, e -> {
            if (!snake.move(COLS, ROWS)) {
                ((Timer)e.getSource()).stop();
                System.out.println("Game Over!");
            }
            int[] x = snake.getX();
            int[] y = snake.getY();

            if(x[0] == food.getX() && y[0] == food.getY()){
                snake.grow();
                food.randomise(snake.getX(), snake.getY(), snake.getLength());
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i = 0; i < ROWS; i++)
            for(int j = 0; j < COLS; j++){
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(170, 215, 81)); //new Color(170, 215, 81)
                } else {
                    g.setColor(new Color(162, 209, 73)); // new Color(162, 209, 73)
                }
                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        snake.draw(g);
        food.draw(g);
    }
}
