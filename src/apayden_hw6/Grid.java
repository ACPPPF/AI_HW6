/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apayden_hw6;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AP047572
 */
public class Grid implements Serializable {
    private final int row;
    private final int col;
    private ArrayList<Row> grid;
    private ArrayList<Point> numberedSquares;
    
    public Grid(int row, int col) {
        this.row = row;
        this.col = col;
        this.grid = new ArrayList<>(col);
        this.numberedSquares = new ArrayList<>();
        for(int r = 0; r < col; ++r) {
            Row rowNum = new Row(row, r);
            this.grid.add(rowNum);
        }
    }
    
    public void printGrid() {
        this.grid.stream().forEach((r) -> {
            r.printRow();
            System.out.print("\n");
        });
    }
    
    public ArrayList<Point> getNumberedSquares() {
        return this.numberedSquares;
    }
    
    public Row getRow(int row) {
        return this.grid.get(row);
    }
    
    public void setSquareNumbers(int row, int col, int num) {
        Square s = this.getRow(row).getSquare(col);
        this.numberedSquares.add(new Point(col, row));
        this.getRow(row).setSquareAdj(col, num);
        removeNearby(row, col);
    }
    
    public void forwardCheck() {
        while(!canPlace()) {
            int col = 0;
            int row = 0;
            int maxFree = 0;
            for(Row r : grid) {
                if(r.getFreeSquares().size() > maxFree || r.getShaded() == -1) {
                    maxFree = r.getFreeSquares().size();
                    col = r.getFreeSquares().get(0);
                    row = r.getRowNum();
                }
            }
            Integer i = col;
            grid.get(row).setShaded(i);
            
            if(row - 1 >= 0) {
                if(col - 1 >= 0) {
                    if(this.numberedSquares.contains(new Point(col - 1, row - 1))) {
                        grid.get(row - 1).getSquare(col - 1).increaseAdjShaded();
                        this.removeNearby(row - 1, col - 1);
                    }
                    grid.get(row - 1).removeFree(col - 1);
                }
                if(col + 1 < grid.get(row - 1).getRowSize()) {
                    grid.get(row - 1).removeFree(col + 1);
                    if(this.numberedSquares.contains(new Point(col + 1, row - 1))) {
                        grid.get(row - 1).getSquare(col + 1).increaseAdjShaded();
                        this.removeNearby(row - 1, col + 1);
                    }
                }
                if(this.numberedSquares.contains(new Point(col, row - 1))) {
                    grid.get(row - 1).getSquare(col).increaseAdjShaded();
                    this.removeNearby(row - 1, col);
                }
            }
            if(row + 1 < this.grid.size()) {
                if(col - 1 >= 0) {
                    grid.get(row + 1).removeFree(col - 1);
                    if(this.numberedSquares.contains(new Point(col - 1, row + 1))) {
                        grid.get(row + 1).getSquare(col - 1).increaseAdjShaded();
                        this.removeNearby(row + 1, col - 1);
                    }
                }
                if(col + 1 < grid.get(row + 1).getRowSize()) {
                    grid.get(row + 1).removeFree(col + 1);
                    if(this.numberedSquares.contains(new Point(col + 1, row + 1))) {
                        grid.get(row + 1).getSquare(col + 1).increaseAdjShaded();
                        this.removeNearby(row + 1, col + 1);
                    }
                }
                if(this.numberedSquares.contains(new Point(col, row + 1))) {
                    grid.get(row + 1).getSquare(col).increaseAdjShaded();
                    this.removeNearby(row + 1, col);
                }
            }
            if(col - 1 >= 0) {
                if(this.numberedSquares.contains(new Point(col - 1, row))) {
                    grid.get(row).getSquare(col - 1).increaseAdjShaded();
                    this.removeNearby(row - 1, col);
                }
                grid.get(row).removeFree(col - 1);
            }
            if(col + 1 < grid.get(row).getRowSize()) {
                grid.get(row).removeFree(col + 1);
                if(this.numberedSquares.contains(new Point(col + 1, row))) {
                    grid.get(row).getSquare(col + 1).increaseAdjShaded();
                    this.removeNearby(row, col + 1);
                }
            }
            for(Row r : grid) {
                if(r.getRowNum() == row) {
                    r.getFreeSquares().clear();
                    r.getFreeSquares().add(i);
                } else {
                    r.getFreeSquares().remove(i);
                }
            }
            System.out.println();
            this.printGrid();
            this.printFree();
        }
        this.printGrid();
    }
    
    public void printFree() {
        for(Row r : grid) {
            System.out.print("[");
            for(Integer i : r.getFreeSquares()) {
                System.out.print(i);
            }
            System.out.print("]");
            System.out.println();
        }
    }
    
    public boolean canPlace() {
        for(Row r : grid) {
            if(r.getFreeSquares().isEmpty()) {
                return true;
            } else if(r.getShaded() == -1) {
                return false;
            }
        }
        return true;
    }
    
    public void removeNearby(int row, int col) {
        if(grid.get(row).getSquare(col).getAdjShaded() == grid.get(row).getSquare(col).getNumAdj()) {
            Integer column = col;
            Integer columnPlus = col + 1;
            Integer columnMinus = col - 1;
            if(row - 1 >= 0) {
                this.getRow(row - 1).getFreeSquares().remove(column);
                if(col - 1 >= 0) {
                    this.getRow(row - 1).getFreeSquares().remove(columnMinus);
                }
                if(col + 1 < this.col) {
                    this.getRow(row - 1).getFreeSquares().remove(columnPlus);
                }
            }
            if(row + 1 < this.row) {
                this.getRow(row + 1).getFreeSquares().remove(column);
                if(col - 1 >= 0) {
                    this.getRow(row + 1).getFreeSquares().remove(columnMinus);
                }
                if(col + 1 < this.col) {
                    this.getRow(row + 1).getFreeSquares().remove(columnPlus);
                }
            }
            if(col - 1 >= 0) {
                this.getRow(row).getFreeSquares().remove(columnMinus);
            }
            if(col + 1 < this.col) {
                this.getRow(row).getFreeSquares().remove(columnPlus);
            }
        }
    }
}
