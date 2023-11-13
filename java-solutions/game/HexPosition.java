package game;

public class HexPosition extends RectPosition {
    public HexPosition(Cell[][] field, int m, int n, PlayerTurn turn) {
        super(field, m, n, turn);
    }

    public Move convertMove(Move move) {
        Move res = new Move(move.getRow(), (move.getRow() - this.n + 1 + move.getCol()), move.getValue());
        System.out.println(res);
        return res;
    }

    @Override
    public int getWidth() {
        return this.n * 2 - 1;
    }

    @Override
    public boolean isInBounds(int row, int column) {
        return super.isInBounds(row, row - this.n + 1 + column);
    }


    @Override
    public Cell getCell(int row, int column) {
        return super.getCell(row, row - this.n + 1 + column);
    }

    @Override
    public boolean isValid(final Move move) {
        return super.isValid(this.convertMove(move));
    }

    @Override
    public String toHumanString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n * 2;i++) {
            sb.append("\t").append(i);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < this.m; r++) {
            sb.append(r + 1);
            for (int i = 0 ;i < this.m - r - 1;i++) {
                sb.append('\t');
            }
            for (Cell cell : this.field[r]) {
                sb.append('\t').append(this.CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}