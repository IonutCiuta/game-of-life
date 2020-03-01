import static org.junit.Assert.*;

import org.junit.Test;

import gof.implementation.Cell;

public class CellTest {

    @Test
    public void testUpdateState() {
        Cell c = new Cell();

        c.setNewState(true);
        c.updateState();
        assertTrue(c.getState());
    }

    @Test
    public void testConstructor() {
        Cell c = new Cell(true);

        assertTrue(c.getState());
    }

}
