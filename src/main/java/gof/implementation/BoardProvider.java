package gof.implementation;

import gof.core.IBoard;
import gof.core.IBoardProvider;

public class BoardProvider implements IBoardProvider {

    @Override
    public IBoard getBoard(int size) {
        IBoard board = new Board();
        board.setup(size);
        return board;
    }
}
