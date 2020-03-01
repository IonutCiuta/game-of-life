package gof.core;


public interface IBoardProvider {
    IBoard getBoard(int size);

    IBoard getBoardFromString(String board, int size);
}
