import java.awt.*;
import java.util.Random;

public class Food {
    private int x;
    private int y;
    private int tileSize, rows, cols;
    private Random random;

    public Food(int tileSize, int rows, int cols, int[] x, int[] y, int len){
        this.cols = cols;
        this.tileSize = tileSize;
        this.rows = rows;
        this.random = new Random();
        randomise(x, y, len);
    }

    public void randomise(int[] snakeX, int[] snakeY, int length){
        boolean flag = false;
        while(!flag){
            x = random.nextInt(cols) * tileSize;
            y = random.nextInt(rows) * tileSize;

            flag = checkPos(x, y, snakeX, snakeY, length);
        }
    }

    private boolean checkPos(int x, int y, int[] snakeX, int[] snakeY, int length){
        for(int i = 0; i < length; i++)
            if(x == snakeX[i] && y == snakeY[i])
                return false;
        return true;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x + 4, y + 4, tileSize - 10, tileSize - 10);

        g.setColor(Color.BLACK);
        g.drawOval(x + 4, y + 4, tileSize - 10, tileSize - 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
