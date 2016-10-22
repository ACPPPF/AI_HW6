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
public class Grid implements Serializable {
    private ArrayList<Row> grid;
    
    public Grid(int row, int col) {
        this.grid = new ArrayList<>(col);
        for(int c = 0; c < col; ++c) {
            Row r = new Row(row);
            this.grid.add(r);
        }
    }
    
    public void printGrid() {
        this.grid.stream().forEach((r) -> {
            r.printRow();
            System.out.print("\n");
        });
    }
    
    public Row getRow(int row) {
        return this.grid.get(row);
    }
}
