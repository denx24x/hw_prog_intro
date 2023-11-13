package game;

public interface Player {
    Move makeMove(Position position);
    boolean offerDraw();
    PlayerDecision getDecision(Position position);
}
