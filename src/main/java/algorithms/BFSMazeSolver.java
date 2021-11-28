package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import structs.Coordinate;
import structs.Maze;

public class BFSMazeSolver {
  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public List<Coordinate> solve(Maze maze) {
    LinkedList<Coordinate> nextToVisit = new LinkedList<>();
    Coordinate start = maze.getStart();
    nextToVisit.add(start);

    while (!nextToVisit.isEmpty()) {
      Coordinate cur = nextToVisit.remove();

      if (!maze.isValidLocation(cur) || maze.isVisited(cur)) continue;

      if (maze.isWall(cur)) {
        maze.setVisited(cur, true);
        continue;
      }

      if (maze.isExit(cur)) return backtrackPath(cur);

      for (int[] direction : DIRECTIONS) {
        Coordinate coordinate =
            new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
        nextToVisit.add(coordinate);
        maze.setVisited(cur, true);
      }
    }
    return Collections.emptyList();
  }

  private List<Coordinate> backtrackPath(Coordinate cur) {
    List<Coordinate> path = new ArrayList<>();
    Coordinate iter = cur;

    while (iter != null) {
      path.add(iter);
      iter = iter.parent;
    }

    return path;
  }
}
