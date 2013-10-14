/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Polinomio;

import java.security.SecureRandom;


/**
 *
 * @author Abud, Denis
 */
public class MatrizPrimo1 {
    
    /**
     * 
     */
    public CorpoFinitoPrimo[][] coeficientes;
    
    /**
     * Construtor que gera a matriz com valores contidos no parametro.
     * @param val Valores dos corposFinitos farão parte da matriz.
     */
    public MatrizPrimo1(CorpoFinitoPrimo[][] val) {
        this.coeficientes = val.clone();
    }
    
    /**
     * Construtor que gera uma matriz quadrada com coeficientes aleatorios
     * @param tam 
     */
    public MatrizPrimo1 (int tam) {
        CorpoFinitoPrimo[][] m = new CorpoFinitoPrimo[tam][tam];
        SecureRandom sc = new SecureRandom();
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                m[i][j] = new CorpoFinitoPrimo( (byte) sc.nextInt(32) );
            }
        }
        this.coeficientes = m;
    }
    
    public MatrizPrimo1 zero (int l, int c) {
        CorpoFinitoPrimo [][] a = new CorpoFinitoPrimo[l][c];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                a[i][j] = CorpoFinitoPrimo.zero();
            }
        }
        return new MatrizPrimo1(a);
    }
    
    /**
     * Método reponsavel por realizar a soma de duas matrizes. Para tento é necesasrio que as dimensões
     * das matrizes envolvidas sejam iguais.
     * @param b A matriz que deseja-se somar.
     */
    public void soma (MatrizPrimo1 b) {
        if (this.coeficientes.length == b.coeficientes.length && this.coeficientes[0].length == b.coeficientes[0].length) {
            for (int i = 0; i < this.coeficientes.length; i++) {
                for (int j = 0; j < this.coeficientes[0].length; j++) {
                    this. coeficientes[i][j] = this.coeficientes[i][j].somaR(b.coeficientes[i][j]);
                }
            }
        }
        else
            System.out.println("Matrizes imconpativeis");
    }
    
    /**
     * Método reponsavel por realizar a subtração de duas matrizes. Para tento é necesasrio que as dimensões
     * das matrizes envolvidas sejam iguais.
     * @param b A matriz que deseja-se somar.
     */
    public void subtrai (MatrizPrimo1 b) {
        if (this.coeficientes.length == b.coeficientes.length && this.coeficientes[0].length == b.coeficientes[0].length) {
            for (int i = 0; i < this.coeficientes.length; i++) {
                for (int j = 0; j < this.coeficientes[0].length; j++) {
                    this. coeficientes[i][j] = this.coeficientes[i][j].subtraiR(b.coeficientes[i][j]);
                }
            }
        }
        else
            System.out.println("Matrizes imconpativeis");
    }
    
    public MatrizPrimo1 multiplicacao(MatrizPrimo1 b) {
        MatrizPrimo1 resp;
        if (this.coeficientes[0].length == b.coeficientes.length) {
            resp = this.zero(this.coeficientes.length, b.coeficientes[0].length);
            for (int i = 0; i < this.coeficientes.length; i++) {
                for (int j = 0; j < b.coeficientes[0].length; j++) {
                    for (int k = 0; k < this.coeficientes[0].length; k++) {
                        resp.coeficientes[i][j] = resp.coeficientes[i][j].somaR(this.coeficientes[i][k].multR(b.coeficientes[k][j]));
                    }
                }
            }
           
            return resp;
        }
        else 
            return null;
    }
    
    /**
     * Método responsavel por imprimir a Matriz.
     */
    public void Mostra () {
        System.out.println();
        for (int i = 0; i < this.coeficientes.length; i++) {
            for (int j = 0; j < this.coeficientes[0].length; j++) {
                System.out.print(this.coeficientes[i][j].valor + "\t");
            }
            System.out.println();
        }
    }
    
}
