/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apayden_hw6;

/**
 *
 * @author AP047572
 */
public class APayden_HW6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid g = new Grid(5,5);
        g.getRow(4).getSquare(0).setNumAdj(0);
        g.getRow(3).getSquare(2).setNumAdj(1);
        g.printGrid();
        g.printGrid();
    }
    
}
