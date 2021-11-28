package structs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {
  private static final int ROAD = 0;
  private static final int WALL = 1;
  private static final int START = 2;
  private static final int EXIT = 3;
  private static final int PATH = 4;

  private int[][] coordMatrix;
  private boolean[][] visitedMatrix;
  private Coordinate start, end;

  public Maze(File file) {
    initialize(loadFromFile(file));
  }

  public void reset() {
    for (boolean[] matrix : visitedMatrix) Arrays.fill(matrix, false);
  }

  private ArrayList<String> loadFromFile(File file) {
    ArrayList<String> lines = new ArrayList<>();

    try (Scanner input = new Scanner(file)) {
      while (input.hasNextLine()) {
        String line = input.nextLine();
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return lines;
  }

  private void initialize(ArrayList<String> lines) {
    coordMatrix = new int[lines.size()][lines.get(0).length()];
    visitedMatrix = new boolean[lines.size()][lines.get(0).length()];
    int row = 0;

    for (String line : lines) {
      for (int col = 0; col < line.length(); col++) {
        if (line.charAt(col) == '*' || line.charAt(col) == '|' || line.charAt(col) == '-')
          coordMatrix[row][col] = WALL;
        if (Character.isAlphabetic(line.charAt(col))) coordMatrix[row][col] = ROAD;
      }
      int end_num = line.length() - 1;

      if (line.charAt(0) == '>') {
        System.out.println(line);
        coordMatrix[row][2] = START;
        start = new Coordinate(row, 2);
      } else if (line.charAt(end_num) == '>') {
        System.out.println(line);
        coordMatrix[row][end_num - 2] = EXIT;
        end = new Coordinate(row, end_num - 2);
      } else System.out.println(line);

      row++;
    }
  }

  public int getLength() {
    return coordMatrix.length;
  }

  public int getWidth() {
    return coordMatrix[0].length;
  }

  public Coordinate getStart() {
    return start;
  }

  public Coordinate getEnd() {
    return end;
  }

  public boolean isExit(Coordinate coord) {
    return coord.getX() == end.getX() && coord.getY() == end.getY();
  }

  public boolean isStart(Coordinate coord) {
    return coord.getX() == start.getX() && coord.getY() == start.getY();
  }

  public boolean isValidLocation(Coordinate coord) {
    return coord.getX() >= 0
        && coord.getX() < getLength()
        && coord.getY() >= 0
        && coord.getY() < getWidth();
  }

  public boolean isVisited(Coordinate coord) {
    return visitedMatrix[coord.getX()][coord.getY()];
  }

  public boolean isWall(Coordinate coord) {
    return coordMatrix[coord.getX()][coord.getY()] == WALL;
  }

  public void setVisited(Coordinate coord, boolean value) {
    visitedMatrix[coord.getX()][coord.getY()] = value;
  }

  public void printPath(List<Coordinate> path) {
    int[][] tempMatrix = Arrays.stream(coordMatrix).map(int[]::clone).toArray(int[][]::new);

    for (Coordinate coord : path) {
      if (isStart(coord) || isExit(coord)) {
        continue;
      }
      tempMatrix[coord.getX()][coord.getY()] = PATH;
    }
    System.out.println(toString(tempMatrix));
  }

  public String toString(int[][] matrix) {
    StringBuilder result = new StringBuilder(getWidth() * (getLength() + 1));
    for (int row = 0; row < getLength(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        if (matrix[row][col] == ROAD) {
          result.append(' ');
        } else if (matrix[row][col] == WALL) {
          result.append('*');
        } else if (matrix[row][col] == START) {
          result.append('S');
        } else if (matrix[row][col] == EXIT) {
          result.append('E');
        } else {
          result.append('+');
        }
      }
      result.append('\n');
    }
    return result.toString();
  }
}
