package ge.chessplayer.model;

public class Point {

    private int x;

    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) {
            return false;
        }

        return x == ((Point) obj).getX() && y == ((Point) obj).getY();
    }

    public boolean isValid() {
        if(x < 0 || x > 8) {
            return false;
        }
        return y >= 0 && y <= 8;
    }
}
