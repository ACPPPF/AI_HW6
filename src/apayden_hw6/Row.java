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
    private final ArrayList<Square> squares;
    private ArrayList<Square> possibleSquares;
    private int shaded;
   
   public Row(int rowSize) {
       this.rowSize = rowSize;
       this.squares = new ArrayList<>(rowSize);
       this.possibleSquares = new ArrayList<>(rowSize);
       for(int r = 0; r < rowSize; ++r) {
           Square square = new Square(r);
           this.squares.add(square);
           this.possibleSquares = squares;
       }
       this.shaded = -1;
   }
   
   public int getShaded() {
       return this.shaded;
   }
   
   public void setShaded(int shaded) {
       this.shaded = -1;
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
   
   public Square getSquare(int col) {
       return this.squares.get(col);
   }
}
