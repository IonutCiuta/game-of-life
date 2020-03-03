package gof.implementation;

import java.util.Objects;

public class Cell {
public int i;
public int j;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        Cell otherCell = (Cell) o;
        return this.i == otherCell.i && this.j == otherCell.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
