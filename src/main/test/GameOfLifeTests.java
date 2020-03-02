import gof.implementation.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeTests {

    @Test
    public void CleanSlateTest()
    {
        Game game = new Game();
        Assert.assertFalse(game.getCell(3,2));
    }

    @Test
    public void CleanSlateNegativeValuesTest()
    {
        Game game = new Game();
        Assert.assertFalse(game.getCell(-5,21));
    }

@Test
    public void Ressurect()
    {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(3,2);

        //assert
        Assert.assertTrue(game.getCell(3,2));
    }

    @Test
    public void MultipleCellsRessurectTest()
    {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(3,2);
        game.Ressurect(4,2 );

        //assert
        Assert.assertTrue(game.getCell(3,2));
        Assert.assertTrue(game.getCell(4,2));
    }

    @Test
    public void AliveCellWithNoAliveNeigboursDiesTest()
    {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(3,2);
        game.NextGeneration();

        //assert
        Assert.assertFalse(game.getCell(3,2));
    }

    @Test
    public void AliveCellWithOneAliveNeigbourDiesTest()
    {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(3,2);
        game.Ressurect(3,3);
        game.NextGeneration();

        //assert
        Assert.assertFalse(game.getCell(3,2));
    }

    @Test
    public void AliveCellWithTwoAliveNeigbourRemainsAliveTest()
    {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(2,2);
        game.Ressurect(2,1);
        game.Ressurect(2,3);

        game.NextGeneration();

        //assert
        Assert.assertTrue(game.getCell(2,2));
    }

    @Test
    public void AliveCellWithMoreThanThreeAliveNeigboursDiesTest() {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(2, 2);
        game.Ressurect(1, 1);
        game.Ressurect(1, 2);
        game.Ressurect(1, 3);


        game.Ressurect(2, 1);
        game.Ressurect(2, 3);

        game.Ressurect(3, 1);
        game.Ressurect(3, 2);
        game.Ressurect(3, 3);

        game.NextGeneration();

        //assert
        Assert.assertFalse(game.getCell(2, 2));

    }

    @Test
    public void DeadCellWithThreeAliveNeigboursRessurectsTest() {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(2, 1);
        game.Ressurect(2, 2);
        game.Ressurect(3, 1);

        game.NextGeneration();

        //assert
        Assert.assertTrue(game.getCell(3, 2));

    }

    @Test
    public void AliveCellWhichDiesNextGenerationContributesToNewCellsBeingRessurectedTest() {
        //arrange
        Game game = new Game();

        //act
        game.Ressurect(1, 2);
        game.Ressurect(2, 2);
        game.Ressurect(3, 3);

        game.NextGeneration();

        //assert
        Assert.assertFalse(game.getCell(1, 2));
        Assert.assertTrue(game.getCell(2, 3));

    }

}
