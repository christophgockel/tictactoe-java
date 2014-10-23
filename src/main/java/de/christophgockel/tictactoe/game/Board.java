package de.christophgockel.tictactoe.game;

import java.util.*;

public class Board {
  private final Size size;
  private final List<Mark> cells;

  public Board() {
    this(Size.ThreeByThree);
  }

  public Board(Size size) {
    this.size = size;
    cells = Arrays.asList(new Mark[size.getNumberOfCells()]);
  }

  private Board(Size size, List<Mark> cells) {
    this.size = size;
    this.cells = cells;
  }

  public static Map<Integer, Size> getAvailableSizes() {
    Map<Integer, Size> sizes = new HashMap<>();
    int index = 1;

    for (Size size : Size.values()) {
      sizes.put(index++, size);
    }

    return sizes;
  }

  public int getSideLength() {
    return size.getSideLength();
  }

  public int getMoveCount() {
    return size.getNumberOfCells() - getFreeLocations().size();
  }

  public boolean isPlayable() {
    return hasRemainingMoves() && !hasWinner();
  }

  public boolean hasWinner() {
    return getWinner() != null;
  }

  public Mark getWinner() {
    for (Line line : getLineCombinations()) {
      if (line.containsOnlySame()) {
        return line.get(0);
      }
    }

    return null;
  }

  public Board setMove(int move, Mark mark) {
    int convertedMove = move - 1;

    if (isInvalidMove(convertedMove)) {
      throw new InvalidMove(move);
    }

    List<Mark> newCells = new ArrayList<>(cells);

    newCells.set(convertedMove, mark);

    return new Board(size, newCells);
  }

  public Map<Integer, Mark> getMarks() {
    Map<Integer, Mark> marks = new HashMap<>();

    for (int i = 0; i < cells.size(); i++) {
      marks.put(i + 1, cells.get(i));
    }

    return Collections.unmodifiableMap(marks);
  }

  public List<Integer> getFreeLocations() {
    List<Integer> locations = new ArrayList<>();

    for (Map.Entry<Integer, Mark> entry : getMarks().entrySet()) {
      if (entry.getValue() == null) {
        locations.add(entry.getKey());
      }
    }

    return locations;
  }

  private boolean hasRemainingMoves() {
    return getFreeLocations().size() > 0;
  }

  private boolean isInvalidMove(int convertedMove) {
    return convertedMove < 0 || convertedMove >= size.getNumberOfCells() || cells.get(convertedMove) != null;
  }

  private List<Line> getLineCombinations() {
    List<Line> lines = new ArrayList<>();

    lines.addAll(getRows());
    lines.addAll(getColumns());
    lines.addAll(getDiagonals());

    return lines;
  }

  private List<Line> getRows() {
    List<Line> rows = new ArrayList<>();

    for (int i = 0; i < size.getSideLength(); i++) {
      rows.add(getRow(i));
    }

    return rows;
  }

  private Line getRow(int index) {
    List<Mark> marks = new ArrayList<>();

    int start = index * size.getSideLength();
    int end = start + size.getSideLength();

    for (int i = start; i < end; i++) {
      marks.add(cells.get(i));
    }

    return new Line(marks);
  }

  private List<Line> getColumns() {
    List<Line> columns = new ArrayList<>();

    for (int i = 0; i < size.getSideLength(); i++) {
      columns.add(getColumn(i));
    }

    return columns;
  }

  private Line getColumn(int index) {
    List<Mark> marks = new ArrayList<>();
    List<Line> rows = getRows();

    for (Line row : rows) {
      marks.add(row.get(index));
    }

    return new Line(marks);
  }

  private List<Line> getDiagonals() {
    List<Line> diagonals = new ArrayList<>();
    List<Mark> leftDiagonal = new ArrayList<>();
    List<Mark> rightDiagonal = new ArrayList<>();

    List<Line> rows = getRows();

    for (int i = 0; i < size.getSideLength(); i++) {
      leftDiagonal.add(rows.get(i).get(i));
      rightDiagonal.add(rows.get(i).get((size.getSideLength() - 1) - i));
    }

    diagonals.add(new Line(leftDiagonal));
    diagonals.add(new Line(rightDiagonal));

    return diagonals;
  }

  public enum Size {
    ThreeByThree(3, "3x3"),
    FourByFour(4, "4x4");

    private final int sideLength;
    private final String description;

    private Size(int sideLength, String description) {
      this.sideLength = sideLength;
      this.description = description;
    }

    public int getNumberOfCells() {
      return sideLength * sideLength;
    }

    public int getSideLength() {
      return sideLength;
    }

    public String getDescription() {
      return description;
    }
  }

  public class InvalidMove extends RuntimeException {
    public InvalidMove(int move) {
      super("Invalid location for move: " + move);
    }
  }

  private class Line {
    private final List<Mark> marks;

    public Line(List<Mark> marks) {
      this.marks = marks;
    }

    public boolean containsOnlySame() {
      boolean same = false;

      for (Mark mark : Mark.values()) {
        if (containsOnly(mark)) {
          same = true;
        }
      }

      return same;
    }

    public Mark get(int index) {
      return marks.get(index);
    }

    private boolean containsOnly(Mark mark) {
      for (Mark lineMark : marks) {
        if (!mark.equals(lineMark)) {
          return false;
        }
      }

      return true;
    }
  }
}
