package game;

public class HexMnkBoard extends AbstractMnkBoard {
    public HexMnkBoard(int n, int k) {
        super(n, n, k);
        this.position = new HexPosition(this.field, n, n, this.turn);
    }
}