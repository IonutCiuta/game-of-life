package gof.core;

import gof.gui.IBoard;

public class Board implements IBoard {
    private Cell[][] grid;
    private int height = 3;
    private int width = 3;

    public Board() {}

    public Board(Cell[][] grid) {
        this.grid = grid;
        height = width = grid.length;
    }

    @Override
    public IBoard setup(int size) {
        this.grid = new Cell[size][size];
        this.height = this.width = grid.length;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j] = new Cell();
            }
        }
        return this;
    }

    // Neighbour configuration
    // N N N
    // N * N
    // N N N
    public int countNeighbours(int row, int col) {
        int sum = 0;

        // Top left neighbour
        if (row != 0 && col != 0) {    //1
            if(isCellAlive(row - 1,col - 1)) {
                sum++;
            }
        }

        // Top neighbour
        if (row != 0){
            if(isCellAlive(row - 1, col)) {
                sum++;
            }
        }

        // Top right neighbour
        if (row != 0 && col != width - 1) {
            if(isCellAlive(row - 1,col + 1)) {
                sum++;
            }
        }

        // Left neighbour
        if (col != 0) {
            if(isCellAlive(row,col - 1)) {
                sum++;
            }
        }

        // Right neighbour
        if (col != width - 1) {
            if(isCellAlive(row,col + 1)) {
                sum++;
            }
        }

        // Bottom left neighbour
        if (row != height - 1 && col != 0){
            if(isCellAlive(row + 1,col - 1)) {
                sum++;
            }
        }

        // Bottom neighbour
        if (row != height - 1){
            if(isCellAlive(row + 1, col)) {
                sum++;
            }
        }

        // Bottom right neighbour
        if (row != height - 1 && col != width - 1) {
            if(isCellAlive(row + 1,col + 1)) {
                sum++;
            }
        }

        return sum;
    }

    public boolean isCellAlive(int row, int col) {
        return this.grid[row][col].getState();
    }

    @Override
    public void update() {
        prepare();
        commit();
    }

    private void prepare() {
        for (int h = 0; h < this.height; h++){
            for (int w = 0; w < this.width; w++){

                int neighbours = countNeighbours(h, w);

                if (neighbours < 2) {
                    // Die
                    this.grid[h][w].setNewState(false);
                }  else if (neighbours > 3) {
                    // Die
                    this.grid[h][w].setNewState(false);
                } else if (neighbours == 3) {
                    // Live
                    this.grid[h][w].setNewState(true);
                } else if (neighbours == 2) {
                    // Don't change
                    this.grid[h][w].setNewState(this.grid[h][w].getState());
                }
            }
        }
    }

    private void commit() {
        for (int h = 0; h < this.height; h++){
            for (int w = 0; w < this.width; w++){
                this.grid[h][w].updateState();
            }
        }
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public int getSize() {
        return this.width;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public boolean getState(int x, int y) {
        return this.grid[x][y].getState();
    }
}
