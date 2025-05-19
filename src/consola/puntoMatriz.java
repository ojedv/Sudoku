package consola;

public class puntoMatriz {
    private int x;
    private int y;

    public puntoMatriz(int x, int y) {
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
    public String toString() {
        return "puntoMatriz{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
