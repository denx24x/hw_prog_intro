package game;

import java.util.Map;

public abstract class AbstractPosition implements Position {
    protected int m, n;
    protected Cell[][] field;
    protected PlayerTurn turn;
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    public AbstractPosition(Cell[][] field, int m, int n, PlayerTurn turn) {
        this.field = field;
        this.turn = turn;
        this.m = m;
        this.n = n;
    }

    public int getWidth() {
        return this.n;
    }

    public int getHeight() {
        return this.m;
    }

    public Cell getTurn(){
        return this.turn.getTurn();
    }

    @Override
    public boolean isInBounds(int row, int column) {
        return 0 <= row && row < this.m && 0 <= column && column < this.n;
    }
}
