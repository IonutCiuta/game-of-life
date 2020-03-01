package gof.gui;

public interface IBoard {
    int getWidth();

    int getHeight();

    boolean getState(int x, int y);

    IBoard setup(int size);

    void update();
}
