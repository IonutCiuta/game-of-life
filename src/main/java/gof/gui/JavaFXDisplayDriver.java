package gof.gui;

import gof.core.DisplayDriver;
import gof.core.IBoard;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JavaFXDisplayDriver implements DisplayDriver {
    private int sz;
    private TilePane tilePane = new TilePane(5,5);

    public JavaFXDisplayDriver(int boardSize, int cellSizePx, IBoard board) {
        sz = boardSize;
        tilePane.setPrefRows(boardSize);
        tilePane.setPrefColumns(boardSize);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Color c = board.getState(i, j) ? Color.STEELBLUE : Color.WHITE;
                Rectangle rect = new Rectangle(cellSizePx, cellSizePx, c);
                tilePane.getChildren().add(rect);
            }
        }
    }

    @Override
    public void displayBoard(IBoard board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle r = (Rectangle) tilePane.getChildren().get(boardToPaneCoords(i, j));
                r.setFill(board.getState(i, j) ? Color.STEELBLUE : Color.WHITE);
            }
        }
    }

    public TilePane getPane() {
        return tilePane;
    }

    private int boardToPaneCoords(int i, int j) {
        return i * sz + j;
    }
}
