import gof.core.Board;
import gof.core.Cell;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BoardTest {
   
    @Test
    public void testGetNeighbours() {
        //Board b = new Board(3, 3, 0.0);
        
        Cell[][] cells = {
            {new Cell(true), new Cell(true), new Cell(true)}, 
            {new Cell(true), new Cell(true), new Cell(true)}, 
            {new Cell(true), new Cell(true), new Cell(true)}
        };
        Board b = new Board(cells);

        assertEquals(3, b.countNeighbours(0,0));
        assertEquals(8, b.countNeighbours(1,1));
        assertEquals(5, b.countNeighbours(1,0));
    }

    @Test
    public void testAllCellsDie() {
        Cell[][] cells = {
            {new Cell(true), new Cell(), new Cell(true)}, 
            {new Cell(), new Cell(), new Cell()}, 
            {new Cell(true), new Cell(), new Cell(true)}
        };

        Board b = new Board(cells);

        assertEquals(true, b.isCellAlive(0, 0));
        assertEquals(false, b.isCellAlive(1, 1));

        b.update();

        assertEquals(false, b.isCellAlive(0, 0));
        assertEquals(false, b.isCellAlive(2, 2));
        assertEquals(false, b.isCellAlive(2, 0));
        assertEquals(false, b.isCellAlive(0, 2));
        assertEquals(false, b.isCellAlive(1, 1));
    }

    @Test
    public void testGridStaysTheSame() {
        Cell[][] cells = {
            {new Cell(true), new Cell(true), new Cell()}, 
            {new Cell(true), new Cell(true), new Cell()}, 
            {new Cell(), new Cell(), new Cell()}
        };

        Board b = new Board(cells);

        b.update();

        assertSame(cells, b.getGrid());
    }

    @Test
    public void testOverpopulationAndIsBorn() {
        Cell[][] cells = {
            {new Cell(true), new Cell(true), new Cell(true)}, 
            {new Cell(true), new Cell(true), new Cell()}, 
            {new Cell(), new Cell(), new Cell()}
        };   
        
        Board b = new Board(cells);
        
        b.update();
        
        assertEquals(true, b.isCellAlive(0, 0));
        assertEquals(true, b.isCellAlive(0, 2));
        assertEquals(true, b.isCellAlive(1, 0));
        assertEquals(true, b.isCellAlive(1, 2));
        
        assertEquals(false, b.isCellAlive(0, 1));
        assertEquals(false, b.isCellAlive(1, 1));
    }
}
