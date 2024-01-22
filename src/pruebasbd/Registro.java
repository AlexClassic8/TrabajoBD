/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebasbd;

/**
 *
 * @author Alex
 */
public class Registro {
    private String columna1;
    private String columna2;
    private String columna3;
    private String columna4;
    private String columna5;
    private String columna6;
    private String columna7;
    private String columna9;
    private String columna10;

    public Registro(String columna1, String columna2, String columna3, String columna4, String columna5, String columna6, String columna7, String columna9, String columna10) {
        this.columna1 = columna1;
        this.columna2 = columna2;
        this.columna3 = columna3;
        this.columna4 = columna4;
        this.columna5 = columna5;
        this.columna6 = columna6;
        this.columna7 = columna7;
        this.columna9 = columna9;
        this.columna10 = columna10;
    }

    public String getColumna1() {
        return columna1;
    }

    public void setColumna1(String columna1) {
        this.columna1 = columna1;
    }

    public String getColumna2() {
        return columna2;
    }

    public void setColumna2(String columna2) {
        this.columna2 = columna2;
    }

    public String getColumna3() {
        return columna3;
    }

    public void setColumna3(String columna3) {
        this.columna3 = columna3;
    }

    public String getColumna4() {
        return columna4;
    }

    public void setColumna4(String columna4) {
        this.columna4 = columna4;
    }

    public String getColumna5() {
        return columna5;
    }

    public void setColumna5(String columna5) {
        this.columna5 = columna5;
    }

    public String getColumna6() {
        return columna6;
    }

    public void setColumna6(String columna6) {
        this.columna6 = columna6;
    }

    public String getColumna7() {
        return columna7;
    }

    public void setColumna7(String columna7) {
        this.columna7 = columna7;
    }

    public String getColumna9() {
        return columna9;
    }

    public void setColumna9(String columna9) {
        this.columna9 = columna9;
    }

    public String getColumna10() {
        return columna10;
    }

    public void setColumna10(String columna10) {
        this.columna10 = columna10;
    }

    @Override
    public String toString() {
        return "{" + "columna1=" + columna1 + ", columna2=" + columna2 + ", columna3=" + columna3 + ", columna4=" + columna4 + ", columna5=" + columna5 + ", columna6=" + columna6 + ", columna7=" + columna7 + ", columna9=" + columna9 + ", columna10=" + columna10 + '}';
    }
    
    
}
