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
     * Cria a matriz cm os coeficientes dados.
     * @param valor Coeficientes da matriz.
     */
    public MatrizCentroSim (CorpoFinitoPrimo[][] valor) {
        this.coeficientes = valor.clone();
	}    
    /**
     * Inicializa a Matriz Centrossimetrica com valores aleatorios dentro do corpofinito.
     * @param tam Dimensão da matriz(Quadrada).
     */
    public MatrizCentroSim (int tam) {
        this.coeficientes = new CorpoFinitoPrimo[tam][tam];
        for (int i = 0; i < (tam + 1) / 2; i++) {
            for (int j = 0; j < tam; j++) {
                this.coeficientes[ i ][ j ] = new CorpoFinitoPrimo();
                this.coeficientes[tam - i - 1][tam - j - 1] = this.coeficientes[ i ][ j ];
            }
        }
    }
    
    /**
     * Gera uma Matriz Cujos coeficientes são 0.
     * @param tam Tamanho da matriz.
     * @return MatrizCentroSim com coeficientes 0.
     */
    public static MatrizCentroSim zero (int tam) {
        CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[tam][tam];
        for (int i = 0; i < resp.length; i++) {
            for (int j = 0; j < resp[0].length; j++) {
                resp[i][j] = CorpoFinitoPrimo.zero();
            }
        }
        return new MatrizCentroSim (resp);
    }
    
    /**
     * Gera uma matriz identidade com o tamanho especificado.
     * @param tam tamnho da matriz.
     * @return A matriz identidade.
     */
    public static MatrizCentroSim geraIdentidade(int tam) {
        CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[tam][tam];
        for (int i = 0; i < resp.length; i++) {
            for (int j = 0; j < resp[0].length; j++) {
                if ( i == j)
                    resp[i][j] = CorpoFinitoPrimo.unitario();
                else            
                    resp[i][j] = CorpoFinitoPrimo.zero();
            }
        }
        return new MatrizCentroSim (resp);
     }
    
    
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
