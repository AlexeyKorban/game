package ru.ldwx;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency moveEfficiency) {
        int result = Integer.compare(this.numberOfEmptyTiles, moveEfficiency.numberOfEmptyTiles);
        if (result == 0){
            result = Integer.compare(this.score, moveEfficiency.score);
        }
        return result;
    }
}
