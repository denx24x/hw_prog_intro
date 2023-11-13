package game;

public class RectPosition extends AbstractPosition {
    public RectPosition(Cell[][] field, int m, int n, PlayerTurn turn){
        super(field, m, n, turn);
    }

    @Override
    public Cell getCell(int row, int column){
        return this.field[row][column];
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < this.m
                && 0 <= move.getCol() && move.getCol() < this.n
                && this.field[move.getRow()][move.getCol()] == Cell.E
                && this.turn.getTurn() == move.getValue();
    }

    @Override
    public Move convertMove(Move move){
        return move;
    }

    @Override
    public String toHumanString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n;i++) {
            sb.append("\t").append(i);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < this.m; r++) {
            sb.append(r + 1);
            for (Cell cell : this.field[r]) {
                sb.append('\t').append(this.CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}