package game;

import java.util.Arrays;
import java.util.Map;

public class RectMnkBoard extends AbstractMnkBoard {
    public RectMnkBoard(int m, int n, int k) {
        super(m, n, k);
        this.position = new RectPosition(this.field, m, n, this.turn);
    }
}
