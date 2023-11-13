package game;

public class TwoPlayerGame {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public TwoPlayerGame(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    private int handlePlayer(Player player, Player otherPlayer, int no, boolean log){
        PlayerDecision decision = player.getDecision(board.getPosition());
        if (decision == PlayerDecision.OFFER_DRAW) {
            System.out.println("Player " + no + " offered draw");
            if (otherPlayer.offerDraw()) {
                    return 0;
            }else if (player.getDecision(board.getPosition()) != PlayerDecision.MOVE) {
                    return 2;
            }
        }
        if (decision == PlayerDecision.CONCEDE) {
            System.out.println("Player " + no + " decided to concede");
            return 2;
        }
        return makeMove(player, 1, log);
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = handlePlayer(player1, player2, 1, log);
            if (result1 != -1)  {
                return result1;
            }
            final int result2 = handlePlayer(player2, player1, 2, log);
            if (result2 != -1)  {
                return result2;
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        // :NOTE: - Ошибки игроков
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board.getPosition().toHumanString());
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN:
                return no;
            case LOOSE:
                return 3 - no;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
