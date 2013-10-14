package Polinomio;

public class MatrizPrimo {
	public CorpoFinitoPrimo[][] coeficientes;
        
        /**
         * gera uma Matriz com coeficientes passados por parametro
         * @param valor 
         */	
	public MatrizPrimo (CorpoFinitoPrimo[][] valor) {
		this.coeficientes = valor.clone();
	}
	
        /**
         * Gera uma matriz quadrada aleatoria cujos coeficientes pertencem ao corpo finito especificado
         * @param tam Dimensão da matriz quadrada
         */
	public MatrizPrimo (int tam) {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[tam][tam];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = new CorpoFinitoPrimo ();
			}
		}
		this.coeficientes = resp;
	}
	
        /**
         * Gera uma Matriz aleatorio com tamanho especificado pelos parametros e cujos coeficientes estão
         * definidos no corpo finito especificado.
         * @param linha Numero de linhas
         * @param col Numero de colunas
         */
	public MatrizPrimo (int linha, int col) {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[linha][col];
		for (int i = 0; i < linha; i ++) {
			for (int j = 0; j < col; j++) {
				resp[i][j] = new CorpoFinitoPrimo ();
			}
		}
		
		this.coeficientes = resp;
	}
	
        /**
         * Cria uma igual a matriz atual clonando os seus coeficientes
         * @return a Matriz clonada
         */
	public MatrizPrimo clone () {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo [this.coeficientes.length][this.coeficientes[0].length];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = this.coeficientes[i][j].clone();
			}
		}
		return new MatrizPrimo (resp);
	}
	
        /**
         * Cria uma Matriz cujos coeficientes são zero
         * @param linhas Numero de linhas desejado.
         * @param colunas Numero de colunas desejado.
         * @return Matriz(linhas x colunas) cujos coeficientes são zero.
         */
	public static MatrizPrimo zero (int linhas, int colunas) {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[linhas][colunas];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = CorpoFinitoPrimo.zero();
			}
		}
		return new MatrizPrimo (resp);
	}
        
        /**
         * Gera uma matriz identidade com dimensão especificada no parametro
         * @param tam Dimensão da matriz
         * @return A matriz Identidade
         */
        public static MatrizPrimo geraIdentidade(int tam) {
            CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[tam][tam];
            for (int i = 0; i < resp.length; i++) {
                    for (int j = 0; j < resp[0].length; j++) {
                            if ( i == j)
                                resp[i][j] = CorpoFinitoPrimo.unitario();
                            else            
                                resp[i][j] = CorpoFinitoPrimo.zero();
                    }
            }
            return new MatrizPrimo (resp);
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
        
        /*
         * Compara as duas matrizes. Se forem iguais retorna true, caso contrario retorna false.
         */
        
        /**
         * Compara coeficientes de duas matrizes (Devem ter o mesmo tamanho)
         * @param b
         * @return TRUE caso todos sejam iguais, FALSE caso contrario
         */
        public boolean compara(MatrizPrimo b) {
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
        /*
        * escalona a linha2, utilizando o elemento na posicao col da linha1
        * linha1 a
        * linha2 b
        */
        
        /**
         * Escalona a linha l2 da matriz utilizando o elemento na posicao col da linha l1
         * @param l1 linha usada como referencia
         * @param l2 linha em sera feito o escalonamento
         * @param col coluna a ser escalonada
         * @param b Matriz em que também se realiza o escalonamento
         */
        public void escalona (int l1, int l2, int col, MatrizPrimo b) {
            CorpoFinitoPrimo coef = this.coeficientes[l2][l1].multR(this.coeficientes[l1][l1].inverso()); // b/a
            for (int i = 0; i < coeficientes[0].length; i++) {
                this.coeficientes[l2][i] = this.coeficientes[l2][i].subtraiR(this.coeficientes[l1][i].multR(coef)); // l2 = l2 - l1*coef
                b.coeficientes[l2][i] = b.coeficientes[l2][i].subtraiR(b.coeficientes[l1][i].multR(coef)); // l2 = l2 - l1*coef
            }
        }
        
        public void escalona (int l1, int l2, int col) {
            CorpoFinitoPrimo coef = this.coeficientes[l2][l1].multR(this.coeficientes[l1][l1].inverso()); // b/a
            for (int i = 0; i < coeficientes[0].length; i++) {
                this.coeficientes[l2][i] = this.coeficientes[l2][i].subtraiR(this.coeficientes[l1][i].multR(coef)); // l2 = l2 - l1*coef
            }
        }
        
        /**
         * Realiza a normalização da diagonal da matriz aramazena em this e tambem na matriz passada como parametro
         * @param b Uma matriz quadrada
         */
        public void normalizaDiagonal (MatrizPrimo b) {
            for (int i = 0; i < coeficientes.length; i++) {
                for (int j = 0; j < b.coeficientes[0].length; j++) {
                    b.coeficientes[i][j] = b.coeficientes[i][j].multR(this.coeficientes[i][i].inverso());
                }
                this.coeficientes[i][i] = this.coeficientes[i][i].multR(this.coeficientes[i][i].inverso());
            }
        }
        
        /*
        * troca posicao das linhas i e j
        */
       public void trocaLinha (int l, int j) {
           CorpoFinitoPrimo[] temp = coeficientes[l];
           coeficientes[l] = coeficientes[j];
           coeficientes[j] = temp;
       }

        // TODO fazer a matriz nao se alterar
        public MatrizPrimo inverteMatriz() {
            MatrizPrimo resp = MatrizPrimo.geraIdentidade(this.coeficientes.length);
            MatrizPrimo ini = this.clone();
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
        
        /*
         * retorna o det da matriz
         */
        public CorpoFinitoPrimo determinante() {
        	CorpoFinitoPrimo resp = CorpoFinitoPrimo.unitario();
        	MatrizPrimo temp = this.clone();
        	
        	for (int i = 0; i < this.coeficientes.length; i++) {
                
                // Se o valor do elemento da diagonal for zero, faz troca de linhas
                if (this.coeficientes[i][i].isZero()) {
                	boolean flag = true;
                    
                    for (int j = i + 1; j < this.coeficientes.length; j++) {
                        // Troca as linhas da matriz
                        if (!this.coeficientes[j][i].isZero() && flag) {
                            temp.trocaLinha(i,j);
                            flag = false;
                        }
                    }
                    
                    if (flag) {
                    	//System.out.println ("Matriz nao inversivel");
                    	return CorpoFinitoPrimo.zero(); //TODO det = 0
                    }
                }
                
                // Depois da troca, devo escalonar
                for (int j = i+1; j < this.coeficientes.length; j++) {
                    temp.escalona(i, j, i);
                }
            }
        	//apos escalonada o det � a multiplicacao da diagonal
        	for (int i = 0; i < coeficientes.length; i++) {
        		resp = resp.multR(temp.coeficientes[i][i]);
        	}
        	return resp;
        }
	
	/*
	 * retorna a + b
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
			
		} else {
			return null;
		}
	}
        
        /*
         * retorna a-b
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
			
		} else {
			return null;
		}
	}
    
	/*
	 * retorna a*b
	 */
    public MatrizPrimo multiplicacao(MatrizPrimo a) {
        CorpoFinitoPrimo[][] resp;
        MatrizPrimo b;
        if (this.coeficientes[0].length == a.coeficientes.length) {
            resp = MatrizPrimo.zero( this.coeficientes.length, a.coeficientes[0].length).coeficientes;
            for (int i = 0; i < this.coeficientes.length; i++) {
                for (int j = 0; j < a.coeficientes[0].length; j++) {
                    for (int k = 0; k < this.coeficientes[0].length; k++) {
                        resp[i][j] = resp[i][j].somaR(this.coeficientes[i][k].multR(a.coeficientes[k][j]));
                    }
                }
            }
           
            return new MatrizPrimo(resp);
        }
        else {
            return null;
        }
    }
    
    /*
     * resolve o sistema Ax = b, 
     */
    public MatrizPrimo solucSistLinear (MatrizPrimo b) {
    	MatrizPrimo resp;
    	resp = this.inverteMatriz();
        if (resp != null) {
            resp = resp.multiplicacao(b);
            return resp;
        }
        else 
            return null;
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
