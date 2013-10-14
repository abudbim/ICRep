package Polinomio;

public class Matriz {
	public CorpoFinito[][] valor;
	
	public Matriz (CorpoFinito[][] valor) {
		this.valor = valor.clone();
	}
	
	public Matriz (int tam) {
		CorpoFinito[][] resp = new CorpoFinito[tam][tam];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = new CorpoFinito (CorpoFinito.tam/8);
			}
		}
		this.valor = resp;
	}
	
	public Matriz (int linha, int col) {
		CorpoFinito[][] resp = new CorpoFinito[linha][col];
		for (int i = 0; i < linha; i ++) {
			for (int j = 0; j < col; j++) {
				resp[i][j] = new CorpoFinito (CorpoFinito.tam/8);
			}
		}
		
		this.valor = resp;
	}
	
	public Matriz clone () {
		CorpoFinito[][] resp = new CorpoFinito [this.valor.length][this.valor[0].length];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = this.valor[i][j].clone();
			}
		}
		return new Matriz (resp);
	}
	
	public static Matriz zero (int grau, int linhas, int colunas) {
		CorpoFinito[][] resp = new CorpoFinito[linhas][colunas];
		for (int i = 0; i < resp.length; i++) {
			for (int j = 0; j < resp[0].length; j++) {
				resp[i][j] = CorpoFinito.zero(grau);
			}
		}
		return new Matriz (resp);
	}
        
        public static Matriz geraIdentidade(int tam) {
            CorpoFinito[][] resp = new CorpoFinito[tam][tam];
            for (int i = 0; i < resp.length; i++) {
                    for (int j = 0; j < resp[0].length; j++) {
                            if ( i == j)
                                resp[i][j] = CorpoFinito.uni(2);
                            else            
                                resp[i][j] = CorpoFinito.zero(2);
                    }
            }
            return new Matriz (resp);
        }
        
        public boolean isIdentidade() {
            for (int i = 0; i < this.valor.length; i ++) {
                for (int j = 0; j < this.valor.length; j++) {
                    if (i != j && !this.valor[i][j].isZero())
                        return false;
                    else if (i == j && !this.valor[i][j].isUni())
                        return false;
                }
            }
            return true;
        }
        
        public boolean isZero() {
            for (int i  = 0; i < this.valor.length; i++) {
                for (int j = 0; j < this.valor[0].length; j++) {
                    if (!valor[i][j].isZero())
                        return false;
                }
            }
            return true;
        }
        
        /*
         * Compara as duas matrizes. Se forem iguais retorna true, caso contrario retorna false.
         */
        public boolean compara(Matriz b) {
            // Se as matrizes tiverem tamanhos iguais ...
            if (this.valor.length == b.valor.length && this.valor[0].length == b.valor[0].length) {
                for (int i = 0; i < this.valor.length; i++) {
                    for (int j = 0; j < this.valor[0].length; j++) {
                        if (!this.valor[i][j].compara(b.valor[i][j]))
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
        public void escalona (int l1, int l2, int col, Matriz b) {
            CorpoFinito coef = this.valor[l2][l1].MultiplicaReduz(this.valor[l1][l1].inverso()); // b/a
            for (int i = 0; i < valor[0].length; i++) {
                this.valor[l2][i] = this.valor[l2][i].soma(this.valor[l1][i].MultiplicaReduz(coef)); // l2 = l2 + l1*coef
                b.valor[l2][i] = b.valor[l2][i].soma(b.valor[l1][i].MultiplicaReduz(coef)); // l2 = l2 + l1*coef
            }
        }
        
        public void escalona (int l1, int l2, int col) {
            CorpoFinito coef = this.valor[l2][l1].MultiplicaReduz(this.valor[l1][l1].inverso()); // b/a
            for (int i = 0; i < valor[0].length; i++) {
                this.valor[l2][i] = this.valor[l2][i].soma(this.valor[l1][i].MultiplicaReduz(coef)); // l2 = l2 + l1*coef
            }
        }
    
        public void normalizaDiagonal (Matriz b) {
            for (int i = 0; i < valor.length; i++) {
                for (int j = 0; j < b.valor[0].length; j++) {
                    b.valor[i][j] = b.valor[i][j].MultiplicaReduz(this.valor[i][i].inverso());
                }
                this.valor[i][i] = this.valor[i][i].MultiplicaReduz(this.valor[i][i].inverso());
            }
        }
        
        /*
        * troca posicao das linhas i e j
        */
       public void trocaLinha (int l, int j) {
           CorpoFinito[] temp = valor[l];
           valor[l] = valor[j];
           valor[j] = temp;
       }

        // TODO fazer a matriz nao se alterar
        public Matriz inverteMatriz() {
            Matriz resp = Matriz.geraIdentidade(this.valor.length);
            Matriz ini = this.clone();
            //CorpoFinito temp = CorpoFinito.zero(this.valor[0][0].valor.length*8;
            
            // Faz o escalonamento inferior ...
            for (int i = 0; i < ini.valor.length; i++) {
                
                // Se o valor do elemento da diagonal for zero, faz troca de linhas
                if (ini.valor[i][i].isZero()) {
                	boolean flag = true;
                    
                    for (int j = i + 1; j < ini.valor.length; j++) {
                        // Troca as linhas da matriz
                        if (!ini.valor[j][i].isZero() && flag) {
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
                for (int j = i+1; j < ini.valor.length; j++) {
                    ini.escalona(i, j, i, resp);
                }
            }
            
            // Faz o escalonamento superior
            for (int i = this.valor.length - 1; i >= 0; i--) {
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
        public CorpoFinito determinante() {
        	CorpoFinito resp = CorpoFinito.uni(this.valor[0][0].valor.length*8);
        	Matriz temp = this.clone();
        	
        	for (int i = 0; i < this.valor.length; i++) {
                
                // Se o valor do elemento da diagonal for zero, faz troca de linhas
                if (this.valor[i][i].isZero()) {
                	boolean flag = true;
                    
                    for (int j = i + 1; j < this.valor.length; j++) {
                        // Troca as linhas da matriz
                        if (!this.valor[j][i].isZero() && flag) {
                            temp.trocaLinha(i,j);
                            flag = false;
                        }
                    }
                    
                    if (flag) {
                    	//System.out.println ("Matriz nao inversivel");
                    	return CorpoFinito.zero(this.valor[0][0].valor.length*8); //TODO det = 0
                    }
                }
                
                // Depois da troca, devo escalonar
                for (int j = i+1; j < this.valor.length; j++) {
                    temp.escalona(i, j, i);
                }
            }
        	//apos escalonada o det � a multiplicacao da diagonal
        	for (int i = 0; i < valor.length; i++) {
        		resp = resp.MultiplicaReduz(temp.valor[i][i]);
        	}
        	return resp;
        }
	
	/*
	 * retorna a + b
	 */
	public Matriz soma (Matriz b) {
		if (valor.length == b.valor.length && valor[0].length == b.valor[0].length) {
			CorpoFinito[][] resp = new CorpoFinito [valor.length][valor[0].length];
			for (int i = 0; i < resp.length; i++) {
				for (int j = 0; j < resp[0].length; j++) {
					resp[i][j] = this.valor[i][j].soma(b.valor[i][j]);
				}
			}
			return new Matriz (resp);
			
		} else {
			return null;
		}
	}
    
	/*
	 * retorna a*b
	 */
    public Matriz multiplicacao(Matriz a) {
        CorpoFinito[][] resp;
        Matriz b;
        if (this.valor[0].length == a.valor.length) {
            resp = Matriz.zero(this.valor[0][0].valor.length*8 - 1, this.valor.length, a.valor[0].length).valor;
            for (int i = 0; i < this.valor.length; i++) {
                for (int j = 0; j < a.valor[0].length; j++) {
                    for (int k = 0; k < this.valor[0].length; k++) {
                        resp[i][j] = resp[i][j].soma(this.valor[i][k].MultiplicaReduz(a.valor[k][j]));
                    }
                }
            }
           
            return new Matriz(resp);
        }
        else {
            return null;
        }
    }
    
    /*
     * resolve o sistema Ax = b, 
     */
    public Matriz solucSistLinear (Matriz b) {
    	Matriz resp;
    	resp = this.inverteMatriz();
    	resp = resp.multiplicacao(b);
    	return resp;
    }
    
	public void Mostra () {
		for (int i = 0; i < valor.length; i++) {
			for (int j = 0; j < valor[i].length; j++) {
				valor[i][j].MostraMatriz();
				System.out.print (" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
