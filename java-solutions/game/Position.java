package game;

public interface Position {
    public int getWidth();

    public int getHeight();

    public Cell getTurn();

    public boolean isValid(Move move);

    public boolean isInBounds(int row, int column);

    public Cell getCell(int row, int column);

    public String toHumanString();

    public Move convertMove(Move move);
}