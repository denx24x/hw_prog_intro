package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;
    private Move lastMove;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public PlayerDecision getDecision(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position.toHumanString()); // :NOTE: Плохо
        System.out.println("Enter you move for " + position.getTurn());
        while (true) {
            String first = in.next();
            if (first.equals("draw")) {
                return PlayerDecision.OFFER_DRAW;
            } else if(first.equals("concede")) {
                return PlayerDecision.CONCEDE;
            } else {
                try {
                    // :NOTE: Руками
                    int x = Integer.parseInt(first);
                    // :NOTE: - Ошибки
                    int y = in.nextInt();
                    Move move = new Move(x - 1, y - 1, position.getTurn());
                    if (position.isValid(move)) {
                        lastMove = move;
                        return PlayerDecision.MOVE;
                    } else {
                        System.out.println("Move is not valid, try again!");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Wrong input, try again!");
                }
            }
        }
    }

    @Override
    public Move makeMove(Position position) {
        return this.lastMove;
    }

    @Override
    public boolean offerDraw() {
        System.out.println("Other player offered to end the game with draw, do you accept it?");
        while (true) {
            String inp = in.next();
            if (inp.toLowerCase().equals("yes")) {
                return true;
            } else if(inp.toLowerCase().equals("no")) {
                return false;
            }
            System.out.println("Wrong input, try again!");
        }
    }
}
