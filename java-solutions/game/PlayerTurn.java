package game;

public class PlayerTurn{
    private Cell turn;

    public PlayerTurn(Cell turn) {
        this.turn = turn;
    }

    public Cell getTurn() {
        return this.turn;
    }

    public void setTurn(Cell val) {
        this.turn = val;
    }
}