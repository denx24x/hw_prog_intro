package game;

import java.util.Arrays;

public abstract class AbstractMnkBoard implements Board {
    protected final Cell[][] field;
    protected PlayerTurn turn;
    protected final int k;
    protected int emptyCount;
    protected Position position;
    protected int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};

    public AbstractMnkBoard(final int m, final int n, final int k) {
        this.field = new Cell[m][n];
        for (final Cell[] row : this.field) {
            Arrays.fill(row, Cell.E);
        }
        this.k = k;
        this.turn = new PlayerTurn(Cell.X);
        this.emptyCount = m * n;
    }

    @Override
    public GameResult makeMove(final Move move) {
        final Move convertedMove = this.position.convertMove(move);
        if (!this.position.isValid(move)) {
            return GameResult.LOOSE;
        }
        this.emptyCount -= 1;
        this.field[convertedMove.getRow()][convertedMove.getCol()] = move.getValue();
        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        this.turn.setTurn(this.turn.getTurn() == Cell.X ? Cell.O : Cell.X);
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        return this.emptyCount == 0;
    }

    protected int tryMove(final Move start, final int dx, final int dy) {
        int cnt = 0;
        int x = start.getRow();
        int y = start.getCol();
        while (this.position.isInBounds(x, y) && this.position.getCell(x, y) == start.getValue() && cnt <= this.k) {
            cnt++;
            x += dx;
            y += dy;
        }
        return cnt;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    protected boolean checkWin(final Move lastMove) {
        for (final int[] dir : this.directions) {
            if (tryMove(lastMove, dir[0], dir[1]) + tryMove(lastMove, -dir[0], -dir[1]) - 1 >= k) {
                return true;
            }
        }
        return false;
    }
}
