package gof.implementation;

import gof.core.IBoard;
import gof.core.IBoardProvider;

public class BoardProvider implements IBoardProvider {

    // TODO: this is necessary for the game to run, but you should make it do the right thing
    @Override
    public IBoard getBoard(int size) {
       return new Game();
    }

    // TODO: this is necessary for the game to run, but you should make it do the right thing
    @Override
    public IBoard getBoardFromString(String board, int size) {
        Game game = new Game();
        char[] boardChars = board.toCharArray();

        int offset = 0;
        for(int i = 0; i < boardChars.length; i++) {
            offset = i/size;
            if (boardChars[i] == '1') {
                game.Ressurect(offset, (i - offset*size) - 1);
            }
        }

        return game;
    }
}
