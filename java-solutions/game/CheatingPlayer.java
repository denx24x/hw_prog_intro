package game;

import java.util.Scanner;

public class CheatingPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        return new Move(0, 0, Cell.E);
        /*
        не компилируется......
        final TicTacToeBoard board = (TicTacToeBoard) position;
        Move first = null;
        for (int r = 0; r < position.getHeight(); r++) {
            for (int c = 0; c < position.getWidth(); c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    if (first == null) {
                        first = move;
                    } else {
                        board.makeMove(move);
                    }
                }
            }
        }
        return first;
        */
    }

    @Override
    public PlayerDecision getDecision(Position position) {
        return PlayerDecision.MOVE;
    }

    @Override
    public boolean offerDraw() {
        return false;
    }
}
