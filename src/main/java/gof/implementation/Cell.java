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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return i == cell.i &&
                j == cell.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
