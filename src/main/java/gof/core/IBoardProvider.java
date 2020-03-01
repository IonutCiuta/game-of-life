package gof.core;


public interface IBoardProvider {
    // Should create a board with said size
    IBoard getBoard(int size);

    // Should create a board from its textual representation
    // If '1' - position is alive, else if '0' - position is dead
    IBoard getBoardFromString(String board, int size);
}
