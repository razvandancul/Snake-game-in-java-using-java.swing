import java.awt.*;

public class Snake {
    private int[] x;
    private int[] y;
    private int length;
    private final int tileSize;
    private char direction = 'R';
    private char nextDirection = 'R';

    public Snake(int initialLength, int startX, int startY, int tileSize) {
        this.tileSize = tileSize;
        this.length = initialLength;
        x = new int[100];
        y = new int[100];

        x[0] = startX;
        y[0] = startY;

        for (int i = 1; i < length; i++) {
            x[i] = x[0] - i * tileSize;
            y[i] = y[0];
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                g.setColor(new Color(0, 120, 0));
                g.fillRoundRect(x[i], y[i], tileSize, tileSize, 15, 15);

                g.setColor(Color.BLACK);
                g.drawRoundRect(x[i], y[i], tileSize, tileSize, 15, 15);

                int eyeSize = 6;
                int pupilSize = 3;

                switch (direction) {
                    case 'U':
                        g.setColor(Color.WHITE);
                        g.fillOval(x[i] + 7, y[i] + 20, eyeSize, eyeSize);
                        g.fillOval(x[i] + 22, y[i] + 20, eyeSize, eyeSize);

                        g.setColor(Color.BLACK);
                        g.fillOval(x[i] + 9, y[i] + 22, pupilSize, pupilSize);
                        g.fillOval(x[i] + 24, y[i] + 22, pupilSize, pupilSize);

                        g.setColor(Color.BLACK);
                        g.fillRect(x[i] + 12, y[i] + 5, 10, 3);

                        g.setColor(Color.RED);
                        g.drawLine(x[i] + 17, y[i] + 5, x[i] + 17, y[i] - 2);
                        g.drawLine(x[i] + 17, y[i] - 2, x[i] + 13, y[i] + 1);
                        g.drawLine(x[i] + 17, y[i] - 2, x[i] + 21, y[i] + 1);
                        break;

                    case 'D':
                        g.setColor(Color.WHITE);
                        g.fillOval(x[i] + 7, y[i] + 5, eyeSize, eyeSize);
                        g.fillOval(x[i] + 22, y[i] + 5, eyeSize, eyeSize);

                        g.setColor(Color.BLACK);
                        g.fillOval(x[i] + 9, y[i] + 7, pupilSize, pupilSize);
                        g.fillOval(x[i] + 24, y[i] + 7, pupilSize, pupilSize);

                        g.setColor(Color.BLACK);
                        g.fillRect(x[i] + 12, y[i] + 25, 10, 3);

                        g.setColor(Color.RED);
                        g.drawLine(x[i] + 17, y[i] + 28, x[i] + 17, y[i] + 35);
                        g.drawLine(x[i] + 17, y[i] + 35, x[i] + 13, y[i] + 32);
                        g.drawLine(x[i] + 17, y[i] + 35, x[i] + 21, y[i] + 32);
                        break;

                    case 'L':
                        g.setColor(Color.WHITE);
                        g.fillOval(x[i] + 20, y[i] + 7, eyeSize, eyeSize);
                        g.fillOval(x[i] + 20, y[i] + 22, eyeSize, eyeSize);

                        g.setColor(Color.BLACK);
                        g.fillOval(x[i] + 22, y[i] + 9, pupilSize, pupilSize);
                        g.fillOval(x[i] + 22, y[i] + 24, pupilSize, pupilSize);

                        g.setColor(Color.BLACK);
                        g.fillRect(x[i] + 5, y[i] + 12, 3, 10);

                        g.setColor(Color.RED);
                        g.drawLine(x[i] + 5, y[i] + 17, x[i] - 2, y[i] + 17);
                        g.drawLine(x[i] - 2, y[i] + 17, x[i] + 1, y[i] + 13);
                        g.drawLine(x[i] - 2, y[i] + 17, x[i] + 1, y[i] + 21);
                        break;

                    case 'R':
                        g.setColor(Color.WHITE);
                        g.fillOval(x[i] + 5, y[i] + 7, eyeSize, eyeSize);
                        g.fillOval(x[i] + 5, y[i] + 22, eyeSize, eyeSize);

                        g.setColor(Color.BLACK);
                        g.fillOval(x[i] + 7, y[i] + 9, pupilSize, pupilSize);
                        g.fillOval(x[i] + 7, y[i] + 24, pupilSize, pupilSize);

                        g.setColor(Color.BLACK);
                        g.fillRect(x[i] + 25, y[i] + 12, 3, 10);

                        g.setColor(Color.RED);
                        g.drawLine(x[i] + 28, y[i] + 17, x[i] + 35, y[i] + 17);
                        g.drawLine(x[i] + 35, y[i] + 17, x[i] + 32, y[i] + 13);
                        g.drawLine(x[i] + 35, y[i] + 17, x[i] + 32, y[i] + 21);
                        break;
                }

            } else {
                g.setColor(new Color(0, 120, 0));
                g.fillRoundRect(x[i], y[i], tileSize, tileSize, 10, 10);

                g.setColor(new Color(0, 255, 100, 120));
                g.fillOval(x[i] + tileSize / 4, y[i] + tileSize / 4, tileSize / 2, tileSize / 2);

                g.setColor(Color.BLACK);
                g.drawRoundRect(x[i], y[i], tileSize, tileSize, 10, 10);
            }
        }
    }

    public boolean move(int maxCols, int maxRows) {
        direction = nextDirection;

        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U': y[0] -= tileSize; break;
            case 'D': y[0] += tileSize; break;
            case 'L': x[0] -= tileSize; break;
            case 'R': x[0] += tileSize; break;
        }

        if (x[0] < 0 || x[0] >= maxCols * tileSize || y[0] < 0 || y[0] >= maxRows * tileSize || !checkSelfCollision()) {
            return false;
        }
        return true;
    }

    private boolean checkSelfCollision(){
        for(int i = 1; i < length; i++)
            if(x[0] == x[i] && y[0] == y[i])
                return false;
        return true;
    }

    public void setNextDirection(char dir) {
        nextDirection = dir;
    }

    public void grow(){
        x[length] = x[length - 1];
        y[length] = y[length - 1];
        length++;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTileSize() {
        return tileSize;
    }
    public char getDirection(){
        return direction;
    }
    public void setDirection(char direction){
        this.direction = direction;
    }
}
