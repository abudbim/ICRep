/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Polinomio;

/**
 *
 * @author Abud
 */
public class MatrizCentroSim {
    public CorpoFinitoPrimo[][] coeficientes;
    /**
     * Inicializa um Matriz com Valores aleatorios no corpofinito usado.
     * @param tam dimensão da matriz
     */
    public MatrizCentroSim (int tam) {
        CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[tam][tam];
        for (int i = 0; i < tam/2 + 1; i++) {
            for (int j = 0; j < tam / 2; j++) {
                resp[ i ] [ j ] = new CorpoFinitoPrimo ();
                resp [tam - i + 1][tam - j + 1] = resp [i][j];
            }
        }
        this.coeficientes = resp;
        
    }
    
    /**
     * Imprime a matriz.
     */
    public void Mostra () {
        for (int i = 0; i < coeficientes.length; i++) {
            for (int j = 0; j < coeficientes[i].length; j++) {
                coeficientes[i][j].MostraMatriz();
            }
            System.out.println();
        }
        System.out.println();
    }
}
