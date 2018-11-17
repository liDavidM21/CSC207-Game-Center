package fall2018.csc2017.game2048;

/**
 * Class of individual tile unit in 2048 game. Value of zero implies an empty tile.
 *
 * @author dkurniawan
 */
public class Tile {

    private int value;

    /**
     * Instantiate tile with a value of zero (empty).
     */
    public Tile(){
        this(0);
    }

    /**
     * Instantiate tile with a specific value.
     *
     * @param value
     */
    public Tile(int value){
        this.value = value;
    }


    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    /**
     * Two tiles are the same if they have the same value. (Useful for merging tile)
     *
     * @param tile
     * @return true if two tiles are equal, false otherwise
     */
    public boolean equals(Tile tile){
        return tile.getValue() == this.getValue();
    }

    /**
     * Add the value of this tile by the value of the tile in the parameter.
     *
     * @param tile
     */
    public void merge(Tile tile){
        this.setValue(value + tile.getValue());
    }

    /**
     * Set the value to zero. In other words, delete / empty the tile.
     *
     */
    public void clear(){
        this.setValue(0);
    }

    public String toString(){
        return (Integer.toString(this.getValue()));
    }

}
