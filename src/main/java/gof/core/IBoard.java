package gof.core;

public interface IBoard {
    int getWidth();

    int getHeight();

    boolean getState(int x, int y);

    void setup(int size);

    void update();
}
