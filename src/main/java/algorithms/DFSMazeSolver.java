package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import structs.Coordinate;
import structs.Maze;

public class DFSMazeSolver {
  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public List<Coordinate> solve(Maze maze) {
    List<Coordinate> path = new ArrayList<>();
    if (explore(maze, maze.getStart(), path)) return path;

    return Collections.emptyList();
  }

  private boolean explore(Maze maze, Coordinate coord, List<Coordinate> path) {
    if (!maze.isValidLocation(coord) || maze.isWall(coord) || maze.isVisited(coord)) return false;

    path.add(coord);
    maze.setVisited(coord, true);

    if (maze.isExit(coord)) return true;

    for (int[] direction : DIRECTIONS) {
      Coordinate nextCoordinate = getNextCoordinate(coord, direction[0], direction[1]);
      if (explore(maze, nextCoordinate, path)) return true;
    }

    path.remove(path.size() - 1);
    return false;
  }

  private Coordinate getNextCoordinate(Coordinate coord, int i, int j) {
    return new Coordinate(coord.getX() + i, coord.getY() + j);
  }
}
