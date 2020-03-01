package gof.core;

public interface IBoard {
    // Should return the size of the board.
    // Keep in mind that the board is square.
    int getSize();

    // Should perform any necessary setup for the board
    void setup(int size);

    // Should return state of the board for position (x, y)
    // If alive - true, else false
    boolean getState(int x, int y);

    // Should update the state of the board
    void update();
}
