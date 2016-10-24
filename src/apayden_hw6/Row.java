/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apayden_hw6;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AP047572
 */
public class Row implements Serializable {
    private final int rowSize;
    private final int rowNum;
    private final ArrayList<Square> squares;
    private ArrayList<Integer> possibleSquares;
    private int shaded;
   
   public Row(int rowSize, int rowNum) {
       this.rowNum = rowNum;
       this.rowSize = rowSize;
       this.squares = new ArrayList<>(rowSize);
       this.possibleSquares = new ArrayList<>(rowSize);
       for(int c = 0; c < rowSize; ++c) {
           Square square = new Square(rowNum, c);
           this.squares.add(square);
           this.possibleSquares.add(c);
       }
       this.shaded = -1;
   }
   
   public int getShaded() {
       return this.shaded;
   }
   
   public void setShaded(int shaded) {
       this.getSquare(shaded).setShaded();
       this.shaded = shaded;
   }
   
   public int getRowSize() {
       return this.rowSize;
   }
   
   public void printRow() {
       for(Square square : this.squares) {
           if(square.getNumAdj() != -1) {
               System.out.print("[" + square.getNumAdj() + "]");
           } else if(square.getShaded()) {
                System.out.print("[" + 'X' + "]");
           } else {
                System.out.print("[ ]");
           }
       }
   }
   
   public int getRowNum() {
       return this.rowNum;
   }
   
   public Square getSquare(int col) {
       return this.squares.get(col);
   }
   
   public void setSquareAdj(int col, int num) {
       this.getSquare(col).setNumAdj(num);
       Integer column = col;
       this.possibleSquares.remove(column);
   }
   
   public ArrayList<Integer> getFreeSquares() {
       return this.possibleSquares;
   }
   
   public void removeFree(Integer col) {
       this.possibleSquares.remove(col);
   }
}
