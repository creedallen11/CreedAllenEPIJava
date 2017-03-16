package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Creed on 1/18/17.
 */
public class SearchMaze {

    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate) o;
            if (x != that.x || y!= that.y) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static enum Color {WHITE, BLACK};

    /* A DFS search of the maze. Will return a list of coordinates representing the path.
    * Note: If one wanted to return the shortest path, a BFS would guarantee it.
    */

    public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate start, Coordinate finish) {

        List<Coordinate> path = new ArrayList<>();
        maze.get(start.x).set(start.y, Color.BLACK);
        path.add(start);
        if (!searchMazeHelper(maze, start, finish, path)) {
            path.remove(path.size() - 1);
        }
        return path;
    }

    private static boolean searchMazeHelper(List<List<Color>> maze, Coordinate current,
                                                Coordinate end, List<Coordinate> path) {
        if (current.equals(end)) {
            return true; // reached target coordinate
        }

        final int[][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // possible moves

        for (int[] s : SHIFT) {
            Coordinate next = new Coordinate(current.x + s[0], current.y + s[1]);

            if (isFeasible(next, maze)) { // if this coordinate is a legal move
                maze.get(next.x).set(next.y, Color.BLACK); // mark this position black (visited or illegal so we dont return here
                path.add(next);
                if (searchMazeHelper(maze, next, end, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    private static boolean isFeasible(Coordinate current, List<List<Color>> maze) {
        // confirm color is white and coordinate is located in the grid
        return current.x >= 0 && current.x < maze.size() && current.y >= 0 && current.y < maze.get(current.x).size()
                && maze.get(current.x).get(current.y) == Color.WHITE;
    }
}
