package gof.core;

public interface IBoard {
    int getSize();

    void setup(int size);

    boolean getState(int x, int y);

    void update();
}
