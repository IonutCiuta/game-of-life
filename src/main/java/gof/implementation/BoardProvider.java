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

    @Override
    public IBoard getBoardFromString(String board, int size) {
        Cell[][] cells = new Cell[size][size];
        int pos = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean state = (board.charAt(pos) == '1');
                cells[i][j] = new Cell(state);
                pos++;
            }
        }

        return new Board(cells);
    }
}
