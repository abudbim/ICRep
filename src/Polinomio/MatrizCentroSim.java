/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Polinomio;

/**
 * Matrizes Centro Simétricas
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
    
    @Override
    public MatrizCentroSim clone () {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo [this.coeficientes.length][this.coeficientes[0].length];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = this.coeficientes[i][j].clone();
			}
		}
		return new MatrizCentroSim (resp);
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
     * Gera uma amtriz de zeros com o numero de linhas e colunas especificado
     * @param linas Numero de linhas da matriz
     * @param colunas Numero de colunas da matriz
     * @return MatrizCentroSimetrica
     */
    public static MatrizCentroSim zero (int linhas, int colunas) {
        CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[linhas][colunas];
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
     * Transpoe a Matriz
     * @return uma nova Matriz equivalente a transposta da matriz atual
     */
    public MatrizCentroSim transpor() {
    	CorpoFinitoPrimo[][] a = new CorpoFinitoPrimo[this.coeficientes[0].length][this.coeficientes.length];
    	for (int i = 0; i < coeficientes.length; i++) {
    		for (int j = 0; j < coeficientes[0].length; j++) {
    			a[j][i] = coeficientes[i][j];
    		}
    	}
    	return new MatrizCentroSim(a);
    }
    
    
    /**
     * Retorna uma submatriz de acordo com os parametros passados. O coeficiente da primeira linha deve ser 1.
     * @param lin_ini numero da linha inicial
     * @param col_ini numero da coluna inicial
     * @param linhas numero de linhas
     * @param colunas numero de colunas
     * @return Submatriz de linha inicia lin_ini e numero de linhas linhas, coluna inicial col_ini e numero de colunas
     * colunas. 
     */
    public MatrizCentroSim Submatriz(int lin_ini, int col_ini, int linhas, int colunas) {
    	if ((lin_ini + linhas - 1) > coeficientes.length || (col_ini +  colunas - 1) > coeficientes[0].length)
    		return null;
    	else {
    		CorpoFinitoPrimo[][] a = new CorpoFinitoPrimo[linhas][colunas];
    		for (int i = 0; i < linhas; i++) {
    			for (int j = 0; j < colunas; j++) {
    				a[i][j] = coeficientes[i + lin_ini - 1][j + col_ini - 1];
    			}
    		}
    		return new MatrizCentroSim(a);
    	}
    }
    
    /**
     * Gera uma matriz cujos coeficientes são o complemento no corpo definido
     * @return MatrizCentroSim
     */
    public MatrizCentroSim inversoAditivo() {
        CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[this.coeficientes.length][this.coeficientes[0].length];
        for (int i = 0; i < this.coeficientes.length; i++)
            for (int j = 0; j <  this.coeficientes[0].length; j ++)
                resp[i][j] = this.coeficientes[i][j].inversoAditivo();
       return new MatrizCentroSim(resp);
    }
    
    /**
     * Soma o vetor linha passado como parametro na linha tambem passada como parametro
     * @param Linha indice da linha que sera somada com o vetor
     * @param l Vetor a ser somado na linha
     */
    public void compoeMatrizPorLinha(int linha, CorpoFinitoPrimo[] l) {
    	if (coeficientes[linha].length == l.length) {
    		for (int i = 0; i < coeficientes[linha].length; i++) {
    			coeficientes[linha][i] = coeficientes[linha][i].somaR(l[i]);
    		}
    	}
    }
    
    /**
     * Faz a soma das matrizes utilizando a soma de corposFinitos
     * @param b Segunda matriz da soma
     * @return Uma Matriz Centrossimetrica cujos coeficientes são a soma dos coeficientes das matrizes
     */
    public MatrizCentroSim soma (MatrizCentroSim b) {
        if (coeficientes.length == b.coeficientes.length && coeficientes[0].length == b.coeficientes[0].length) {
            CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo [coeficientes.length][coeficientes[0].length];
            for (int i = 0; i < resp.length; i++) {
                for (int j = 0; j < resp[0].length; j++) {
                    resp[i][j] = this.coeficientes[i][j].somaR(b.coeficientes[i][j]);
                }
            }
            return new MatrizCentroSim (resp);
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
    public MatrizCentroSim subtracao (MatrizCentroSim b) {
        if (coeficientes.length == b.coeficientes.length && coeficientes[0].length == b.coeficientes[0].length) {
            CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo [coeficientes.length][coeficientes[0].length];
            for (int i = 0; i < resp.length; i++) {
                for (int j = 0; j < resp[0].length; j++) {
                    resp[i][j] = this.coeficientes[i][j].subtraiR(b.coeficientes[i][j]);
                }
            }
            return new MatrizCentroSim (resp);
        } 
        else {
            return null;
        }
    }
    
    /**
     * Realiza a multiplicacao da matriz por um escalar.
     * @param a O escalar multiplicador.
     * @return MatrizCentroSim cujos coeficientes sao o resultado da multiplicacao pelo escalar
     */
    public MatrizCentroSim multiplicaEscalar(int a) {
    	CorpoFinitoPrimo m = new CorpoFinitoPrimo(a);
    	CorpoFinitoPrimo[][] b = new CorpoFinitoPrimo[coeficientes.length][coeficientes[0].length];
    	for (int i = 0; i < coeficientes.length; i++) {
            for (int j = 0; j < coeficientes[0].length; j++) {
            	b[i][j] = coeficientes[i][j].multR(m);
            }
        }
    	return new MatrizCentroSim(b);
    }
    
    /**
     * Realiza a multiplicação da matriz que chama o método pela Matriz passada como parametro : this*param.
     * @param a Matriz multiplicadora
     * @return Uma matriz centrossimétrica
     */
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
    
    /**
     * Realiza o escalonamento de uma determinada coluna da matriz que chama o método e da matriz passada como parametro
     * @param l1 linha que será base para escalonamento
     * @param l2 linha que será escalonada
     * @param col coluna que será escalonada
     * @param b Matriz que também passará pelo escalonamento
     */    
    public void escalona (int l1, int l2, int col, MatrizCentroSim b) {
        CorpoFinitoPrimo coef = this.coeficientes[l2][l1].multR(this.coeficientes[l1][l1].inverso()); // b/a
        for (int i = 0; i < coeficientes.length; i++) {
            this.coeficientes[l2][i] = this.coeficientes[l2][i].subtraiR(this.coeficientes[l1][i].multR(coef)); // l2 = l2 - l1*coef
            b.coeficientes[l2][i] = b.coeficientes[l2][i].subtraiR(b.coeficientes[l1][i].multR(coef)); // l2 = l2 - l1*coef
        }
    }
    
    /**
     * Realiza a normalizaçao da diagonal na matriz que chama o método assim como na matriz passada como parametro.
     * @param b Matriz que terá diagonal normalizada
     */
    public void normalizaDiagonal (MatrizCentroSim b) {
        for (int i = 0; i < coeficientes.length; i++) {
            for (int j = 0; j < b.coeficientes[0].length; j++) {
                b.coeficientes[i][j] = b.coeficientes[i][j].multR(this.coeficientes[i][i].inverso());
            }
            this.coeficientes[i][i] = this.coeficientes[i][i].multR(this.coeficientes[i][i].inverso());
        }
    }
    
    /** 
     * Troca a posição de duas linhas na matriz
     * @param l indice da linha a ser trocada
     * @param j indice da linha que será colocada no lugar da linha que será trocada
     */
    public void trocaLinha (int l, int j) {
        CorpoFinitoPrimo[] temp = coeficientes[l];
        coeficientes[l] = coeficientes[j];
        coeficientes[j] = temp;
    }
    
    /**
     * Faz a inversão da matriz.Clona-se a matriz que chama o método para não modificar seus valores originais e também é gerada uma matriz identidade
     * que será a inversa da matriz.É feito o escalonamento da matriz e depois a normalização para tranformar a matriz clonada na matriz identidade. 
     * @return MatrizCentroSim inversa ou NULL caso não seja possivel ter valores não nulos na diagonal.
     */
    public MatrizCentroSim inverteMatriz() {
        MatrizCentroSim resp = MatrizCentroSim.geraIdentidade(this.coeficientes.length);
        MatrizCentroSim ini = (MatrizCentroSim) this.clone();
        //CorpoFinitoPrimo temp = CorpoFinitoPrimo.zero(this.valor[0][0].valor.length*8;

        // Faz o escalonamento inferior ...
        for (int i = 0; i < ini.coeficientes.length; i++) {

            // Se o valor do elemento da diagonal for zero, faz troca de linhas
            if (ini.coeficientes[i][i].isZero()) {
                boolean flag = true;

                for (int j = i + 1; j < ini.coeficientes.length; j++) {
                    // Troca as linhas da matriz
                    if (!ini.coeficientes[j][i].isZero() && flag) {
                        ini.trocaLinha(i,j);
                        resp.trocaLinha(i,j);
                        flag = false;
                    }
                }

                if (flag) {
                    //System.out.println ("Matriz nao inversivel");
                    return null; //TODO det = 0
                }
            }

            // Depois da troca, devo escalonar
            for (int j = i+1; j < ini.coeficientes.length; j++) {
                ini.escalona(i, j, i, resp);
            }
        }

        // Faz o escalonamento superior
        for (int i = this.coeficientes.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                ini.escalona(i, j, i, resp);
            }
        }
        ini.normalizaDiagonal(resp);
        return resp;
    }
    
    /**
     * Resolve o sistema linear : this*resp = param.
     * @param b matriz dos resultados. 
     * @return MatrizCentroSim ou NULL caso não seja possivel resolver.
     */
    public MatrizCentroSim solucSistLinear (MatrizCentroSim b) {
    	MatrizCentroSim resp;
    	resp = this.inverteMatriz();
        if (resp != null) {
            resp = resp.multiplicacao(b);
            return resp;
        }
        else 
            return null;
    }
    
    public void MontaMatriz (int linha_ini, int col_ini, MatrizCentroSim b) {
    	if (linha_ini + b.coeficientes.length > this.coeficientes.length || col_ini 
    			+ b.coeficientes[0].length > this.coeficientes[0].length)
    		return;
    	else {
    		for (int i = 0; i < b.coeficientes.length; i++) {
    			for (int j = 0; j < b.coeficientes[0].length; j++) {
    				this.coeficientes[linha_ini + i][col_ini + j] = new CorpoFinitoPrimo(b.coeficientes[i][j].valor);
    			}
    		}
    	}
    }
    
    /**
     * Imprimi na tela a matriz
     */
    public void Mostra () {
        for (int i = 0; i < coeficientes.length; i++) {
            for (int j = 0; j < coeficientes[i].length; j++) {
                coeficientes[i][j].MostraMatriz();
            }
            System.out.println();
        }
        System.out.println();
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < this.coeficientes.length; j++)
        	for (int k = 0; k < this.coeficientes[0].length; k++){
        		s.append(this.coeficientes[j][k].valor);
        		s.append(",");
        	}
        System.out.println(s.toString());
    }
        
}
