
import java.util.*;

public class Puzzlee {

    private static final int[][] GOAL_STATE = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    private static final int[][] MOVES = {
        {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };

    private int[][] state;
    private int blankRow;
    private int blankCol;

    public Puzzlee(int[][] state) {
        this.state = state;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    return;
                }
            }
        }
    }

    public boolean isSolvable() {
        int inversions = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = i + 1; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (state[i][j] > 0 && state[k][l] > 0 && state[i][j] > state[k][l]) {
                            inversions++;
                        }
                    }
                }
            }
        }
        return inversions % 2 == 0;
    }

    public List<Puzzlee> getNeighbors() {
        List<Puzzlee> neighbors = new ArrayList<>();
        for (int[] move : MOVES) {
            int newBlankRow = blankRow + move[0];
            int newBlankCol = blankCol + move[1];
            if (newBlankRow >= 0 && newBlankRow < 3 && newBlankCol >= 0 && newBlankCol < 3) {
                Puzzlee neighbor = new Puzzlee(state);
                neighbor.state[blankRow][blankCol] = neighbor.state[newBlankRow][newBlankCol];
                neighbor.state[newBlankRow][newBlankCol] = 0;
                neighbor.blankRow = newBlankRow;
                neighbor.blankCol = newBlankCol;
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public boolean isGoal() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != GOAL_STATE[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(state[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] initialState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        Puzzlee puzzle = new Puzzlee(initialState);

        if (puzzle.isSolvable()) {
            Queue<Puzzlee> queue = new LinkedList<>();
            queue.add(puzzle);
            Map<Puzzlee, Puzzlee> visited = new HashMap<>();
            visited.put(puzzle, null);

            while (!queue.isEmpty()) {
                Puzzlee current = queue.remove();

                if (current.isGoal()) {
                    List<Puzzlee> solution = new ArrayList<>();
                    solution.add(current);
                    while (visited.get(current) != null) {
                        current = visited.get(current);
                        solution.add(current);
                    }
                    Collections.reverse(solution);

                    for (Puzzlee state : solution) {
                        System.out.println(state);
                        System.out.println();
                    }

                    return;
                }

                for (Puzzlee neighbor : current.getNeighbors()) {
                    if (!visited.containsKey(neighbor)) {
                        visited.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        } else {
            System.out.println("Puzzle is not solvable.");
        }
    }
}