package gof.implementation;

import gof.core.IBoard;

import java.util.ArrayList;
import java.util.List;

public class Game implements IBoard {

    public List<Cell> aliveCells = new ArrayList<>();

    List<Cell> cellsToBeRessurected = new ArrayList<>();
    List<Cell> cellsToBeRemoved = new ArrayList<>();

    @Override
    public int getSize() {
        return 15;
    }

    @Override
    public void setup(int size) {

    }

    @Override
    public boolean getState(int x, int y) {
        return getCell(x, y);
    }

    @Override
    public void update() {
        NextGeneration();
    }

    public boolean getCell(int i, int j) {
        for(Cell cell : aliveCells)
        {
            if(cell.i == i && cell.j == j)
            {
                return true;
            }
        }
        return false;
    }

    public void Ressurect(int i, int j) {
        Cell newCell = new Cell(i, j);
        aliveCells.add(newCell);
    }

    public void NextGeneration() {
        CalculateCellsToBeRemoved();
        CalculateCellsToBeRessurected();

        aliveCells.removeAll(cellsToBeRemoved);
        aliveCells.addAll(cellsToBeRessurected);

        cellsToBeRemoved.clear();
        cellsToBeRessurected.clear();
    }

    public void CalculateCellsToBeRemoved()
    {
        for(Cell cell : aliveCells)
        {
            int n = GetNumberOfAliveNeighbours(cell);
            if(n < 2 || n > 3)
            {
                MarkCellToBeRemoved(cell);

            }
        }
    }

    private void MarkCellToBeRemoved(Cell cell) {
        if(!cellsToBeRemoved.contains(cell))
        {
            cellsToBeRemoved.add(cell);
        }
    }

    public void CalculateCellsToBeRessurected()
    {
        for(Cell cell : aliveCells) {
            List<Cell> neighbours = GetAllNeighbours(cell);
            for(Cell neighbour : neighbours)
            {
                if(GetNumberOfAliveNeighbours(neighbour) == 3)
                {
                    MarkCellToBeRessurected(neighbour);
                }
            }
        }
    }

    private void MarkCellToBeRessurected(Cell neighbour) {
        if (!cellsToBeRessurected.contains(neighbour)) {
            cellsToBeRessurected.add(neighbour);
        }
    }


    private List<Cell> GetAllNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(cell.i-1, cell.j-1));
        neighbours.add(new Cell(cell.i-1, cell.j));
        neighbours.add(new Cell(cell.i-1, cell.j+1));
        neighbours.add(new Cell(cell.i, cell.j-1));
        neighbours.add(new Cell(cell.i, cell.j+1));
        neighbours.add(new Cell(cell.i+1, cell.j-1));
        neighbours.add(new Cell(cell.i+1, cell.j));
        neighbours.add(new Cell(cell.i+1, cell.j+1));

        return neighbours;
    }

    private int GetNumberOfAliveNeighbours(Cell cell) {
        int noOfAliveNeighbours = 0;

        if(aliveCells.contains(new Cell(cell.i-1, cell.j-1))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i-1, cell.j))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i-1, cell.j+1))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i, cell.j-1))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i, cell.j+1))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i+1, cell.j-1))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i+1, cell.j))) noOfAliveNeighbours++;
        if(aliveCells.contains(new Cell(cell.i+1, cell.j+1))) noOfAliveNeighbours++;
        return noOfAliveNeighbours;
    }
}
