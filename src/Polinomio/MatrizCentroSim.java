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
    
    /**
    * Checa se uma determinada matriz é identidade(deve ser quadrada)
    * @return TRUE se Identidade FALSE caso contrario
    */
    public boolean isIdentidade() {

       if (this.coeficientes.length == this.coeficientes[0].length) {
           for (int i = 0; i < this.coeficientes.length; i ++) {
               for (int j = 0; j < this.coeficientes.length; j++) {
                   if (i != j && !this.coeficientes[i][j].isZero())
                       return false;
                   else if (i == j && !this.coeficientes[i][j].isUnitario())
                       return false;
               }
           }
           return true;
       }
       else
           return false;
    }
    
     /**
    * Checa se a Matriz contem apenas zeros como elementos
    * @return TRUE caso todos elementos sejam zero, FALSE caso contrario
    */
    public boolean isZero() {
        for (int i  = 0; i < this.coeficientes.length; i++) {
            for (int j = 0; j < this.coeficientes[0].length; j++) {
                if (!coeficientes[i][j].isZero())
                    return false;
            }
        }
       return true;
    }
    
    /**
     * Método que compara duas matrizes 
     * @param b Matriz para comparação
     * @return TRUE se as matrizes possuem os mesmos coeficientes, FALSE caso contrario
     */
    public boolean compara(MatrizCentroSim b) {
        // Se as matrizes tiverem tamanhos iguais ...
        if (this.coeficientes.length == b.coeficientes.length && this.coeficientes[0].length == b.coeficientes[0].length) {
            for (int i = 0; i < this.coeficientes.length; i++) {
                for (int j = 0; j < this.coeficientes[0].length; j++) {
                    if (!this.coeficientes[i][j].isEqual(b.coeficientes[i][j]))
                        return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Faz a soma das matrizes utilizando a soma de corposFinitos
     * @param b Segunda matriz da soma
     * @return Uma Matriz Centrossimetrica cujos coeficientes são a soma dos coeficientes das matrizes
     */
    public MatrizPrimo soma (MatrizPrimo b) {
        if (coeficientes.length == b.coeficientes.length && coeficientes[0].length == b.coeficientes[0].length) {
            CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo [coeficientes.length][coeficientes[0].length];
            for (int i = 0; i < resp.length; i++) {
                for (int j = 0; j < resp[0].length; j++) {
                    resp[i][j] = this.coeficientes[i][j].somaR(b.coeficientes[i][j]);
                }
            }
            return new MatrizPrimo (resp);
        } 
        else {
                return null;
        }
    }
    
    /**
     * Faz a subtraçção das matrizes utilizando a soma de corposFinitos
     * @param b segunda matriz da subtração
     * @return Uma Matriz Centrossimetrica cujos coeficientes são a difereça entre os coeficientes das matrizes
     */
    public MatrizPrimo subtracao (MatrizPrimo b) {
        if (coeficientes.length == b.coeficientes.length && coeficientes[0].length == b.coeficientes[0].length) {
            CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo [coeficientes.length][coeficientes[0].length];
            for (int i = 0; i < resp.length; i++) {
                for (int j = 0; j < resp[0].length; j++) {
                    resp[i][j] = this.coeficientes[i][j].subtraiR(b.coeficientes[i][j]);
                }
            }
            return new MatrizPrimo (resp);
        } 
        else {
            return null;
        }
    }
    
    public MatrizCentroSim multiplicacao(MatrizCentroSim a) {
        CorpoFinitoPrimo[][] resp;
        MatrizCentroSim b;
        if (this.coeficientes[0].length == a.coeficientes.length) {
            resp = MatrizPrimo.zero( this.coeficientes.length, a.coeficientes[0].length).coeficientes;
            for (int i = 0; i < this.coeficientes.length; i++) {
                for (int j = 0; j < a.coeficientes[0].length; j++) {
                    for (int k = 0; k < this.coeficientes[0].length; k++) {
                        resp[i][j] = resp[i][j].somaR(this.coeficientes[i][k].multR(a.coeficientes[k][j]));
                    }
                }
            }
           
            return new MatrizCentroSim(resp);
        }
        else {
            return null;
        }
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
