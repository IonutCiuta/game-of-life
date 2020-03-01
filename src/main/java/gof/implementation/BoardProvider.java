package gof.implementation;

import gof.core.IBoard;
import gof.core.IBoardProvider;

public class BoardProvider implements IBoardProvider {

    // TODO: this is necessary for the game to run, but you should make it do the right thing
    @Override
    public IBoard getBoard(int size) {
       return new IBoard() {
           @Override
           public int getSize() {
               return 0;
           }

           @Override
           public void setup(int size) {

           }

           @Override
           public boolean getState(int x, int y) {
               return false;
           }

           @Override
           public void update() {

           }
       };
    }

    // TODO: this is necessary for the game to run, but you should make it do the right thing
    @Override
    public IBoard getBoardFromString(String board, int size) {
        return new IBoard() {
            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public void setup(int size) {

            }

            @Override
            public boolean getState(int x, int y) {
                return false;
            }

            @Override
            public void update() {

            }
        };
    }
}
