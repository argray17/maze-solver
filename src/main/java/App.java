import algorithms.BFSMazeSolver;
import algorithms.DFSMazeSolver;
import java.io.File;
import java.util.List;
import structs.Coordinate;
import structs.Maze;

public class App {
  public static void main(String[] args) throws Exception {
    String fname1 = "src/main/java/data/maze_1.txt";
    File maze1 = new File(fname1);
    String fname2 = "src/main/java/data/maze_2.txt";
    File maze2 = new File(fname2);
    String fname3 = "src/main/java/data/maze_3.txt";
    File maze3 = new File(fname3);

    execute(maze1, "Maze 1");
    execute(maze2, "Maze 2");
    execute(maze3, "Maze 3");
  }

  private static void execute(File file, String mazeName) {
    System.out.println(mazeName);
    Maze maze = new Maze(file);
    dfs(maze);
    bfs(maze);
  }

  private static void bfs(Maze maze) {
    BFSMazeSolver bfs = new BFSMazeSolver();
    List<Coordinate> path = bfs.solve(maze);
    System.out.println("\nBreadth First Path");

    maze.printPath(path);
    maze.reset();
  }

  private static void dfs(Maze maze) {
    DFSMazeSolver dfs = new DFSMazeSolver();
    List<Coordinate> path = dfs.solve(maze);
    System.out.println("\nDepth First Path");

    maze.printPath(path);
    maze.reset();
  }
}
