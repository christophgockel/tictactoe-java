package de.christophgockel.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {
  public class InvalidMove extends RuntimeException {
  }

  private final int SIZE = 9;
  private final List<Mark> cells;

  public Board() {
    cells = Arrays.asList(new Mark[SIZE]);
  }

  private Board(List<Mark> cells) {
    this.cells = cells;
  }

  public boolean isPlayable() {
    return emptyCellCount() > 0;
  }

  public boolean isWinner(Mark mark) {
    for (Line line : getLineCombinations()) {
      if (line.containsOnly(mark)) {
        return true;
      }
    }

    return false;
  }

  public Board setMove(int move, Mark mark) {
    int convertedMove = move - 1;

    if (convertedMove < 0 || convertedMove >= SIZE) {
      throw new InvalidMove();
    }

    if (cells.get(convertedMove) == null) {
      cells.set(convertedMove, mark);
    } else {
      throw new InvalidMove();
    }

    return new Board(new ArrayList<Mark>(cells));
  }

  public List<Mark> getCells() {
    return Collections.unmodifiableList(cells);
  }

  private int emptyCellCount() {
    int count = 0;

    for (Mark mark : cells) {
      if (mark == null) {
        count++;
      }
    }

    return count;
  }

  private List<Line> getLineCombinations() {
    List<Line> lines = new ArrayList<Line>();

    lines.addAll(getRows());
    lines.addAll(getColumns());
    lines.addAll(getDiagonals());

    return lines;
  }

  private List<Line> getRows() {
    List<Line> rows = new ArrayList<Line>();

    rows.add(new Line(cells.subList(0, 2)));
    rows.add(new Line(cells.subList(3, 5)));
    rows.add(new Line(cells.subList(6, 8)));

    return rows;
  }

  private List<Line> getColumns() {
    List<Line> columns = new ArrayList<Line>();
    List<Mark> cellsForColumn = new ArrayList<Mark>();

    cellsForColumn.add(cells.get(0));
    cellsForColumn.add(cells.get(3));
    cellsForColumn.add(cells.get(6));
    columns.add(new Line(new ArrayList<Mark>(cellsForColumn)));

    cellsForColumn.clear();
    cellsForColumn.add(cells.get(1));
    cellsForColumn.add(cells.get(4));
    cellsForColumn.add(cells.get(7));
    columns.add(new Line(new ArrayList<Mark>(cellsForColumn)));

    cellsForColumn.clear();
    cellsForColumn.add(cells.get(2));
    cellsForColumn.add(cells.get(5));
    cellsForColumn.add(cells.get(8));
    columns.add(new Line(new ArrayList<Mark>(cellsForColumn)));

    return columns;
  }

  private List<Line> getDiagonals() {
    List<Line> diagonals = new ArrayList<Line>();
    List<Mark> cellsForDiagonal = new ArrayList<Mark>();

    cellsForDiagonal.add(cells.get(0));
    cellsForDiagonal.add(cells.get(4));
    cellsForDiagonal.add(cells.get(8));
    diagonals.add(new Line(new ArrayList<Mark>(cellsForDiagonal)));

    cellsForDiagonal.clear();
    cellsForDiagonal.add(cells.get(2));
    cellsForDiagonal.add(cells.get(4));
    cellsForDiagonal.add(cells.get(6));
    diagonals.add(new Line(new ArrayList<Mark>(cellsForDiagonal)));

    return diagonals;
  }

  private class Line {
    private final List<Mark> marks;

    public Line(List<Mark> marks) {
      this.marks = marks;
    }

    public boolean containsOnly(Mark mark) {
      for (Mark lineMark : marks) {
        if (!mark.equals(lineMark)) {
          return false;
        }
      }

      return true;
    }
  }
}
