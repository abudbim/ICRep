package Polinomio;

import java.security.SecureRandom;

public class CorpoFinito{
	public byte[] valor;
	public static CorpoFinito redutor;
	public static String hex = "0123456789ABCDEF";
	public SecureRandom sr;
	public static int tam = 16;
	
	public CorpoFinito (byte[] valor) {
		this.valor = valor.clone();
	}
	
	public CorpoFinito (int tam) {
		sr = new SecureRandom ();
		byte[] temp = new byte[tam];
		sr.nextBytes(temp);
		this.valor = temp;
		this.valor = this.divisao(redutor).valor.clone();
	}
	
	public static void defineP(CorpoFinito redutor) {
		CorpoFinito.redutor = redutor;
	}
	
	/*
	 * retorn polinomio 0 de tamanho m
	 */
	public static CorpoFinito zero (int m) {
		byte[] v = new byte [(m)/8 + 1];
		for (int i = m/8 - 1; i > -1; i--) {
			v[i] = 0;
		}
		return new CorpoFinito (v);
	}
	
	/*
	 * return polinomio 1 de tamanho m 
	 */
	public static CorpoFinito uni (int m) {
        byte[] v = new byte [(m)/8 + 1];
        for (int i = m/8 - 1; i > 0; i--) {
            v[i] = 0;
        }
        v[0] = 1;
        return new CorpoFinito (v);
    }
    
    public boolean isZero() {
        boolean resp = true;
        for (int i = this.valor.length - 1; i > -1; i--) {
            if(valor[i] != 0)
                resp = false;
        }
        return resp;
    }
	
	public boolean isUni () {
		boolean resp = true;
		for (int i = this.valor.length -1; i > 0; i--) {
			if (valor[i] != 0) resp = false;
		}
		if (valor[0] != 1) resp = false;
		return resp;
	}
	
	public CorpoFinito clone () {
		return new CorpoFinito(this.valor);
	}
    
    public boolean compara (CorpoFinito c) {
        boolean resp = true;
        // Opção 1 : Considerar o static tam definido como o tamanho padrão para os bytearrays 
        /*for (int i =0; i < CorpoFinito.tam; i++) {
            if (this.valor[i] != c.valor[i])
                resp = false;
        }
        return resp;*/
        // Opção 2 : Considerar tamnhos diferentes
        if (this.valor.length >= c.valor.length) {
            // Crio um vetor com o tamnho do maior e comparo
            CorpoFinito temp = CorpoFinito.zero(this.valor.length * 8);
            for (int i = 0; i < c.valor.length; i++) {
                temp.valor[i] = c.valor[i];
                if (this.valor[i] != temp.valor[i]) {
                    resp = false;
                    break;
                }
            }
            return resp;
            
        }
        else {
            CorpoFinito temp = CorpoFinito.zero(c.valor.length * 8);
            for (int i = 0; i < this.valor.length; i++) {
                temp.valor[i] = this.valor[i];
                if (this.valor[i] != temp.valor[i]) {
                    resp = false;
                    break;
                }
            }
            return resp;
        }
    }
	
	/*
	 * return a + b
	 */
	public CorpoFinito soma (CorpoFinito b) {
		byte[] temp;
		if (this.valor.length >= b.valor.length) {
			temp = new byte[this.valor.length];
			for (int i = valor.length - 1; i > -1; i--) {
				if (i < b.valor.length)
					temp[i] = (byte)(valor[i]^b.valor[i]);
				else 
					temp[i] = valor[i];
			}
		}
		else {
			temp = new byte[b.valor.length];
			for (int i = b.valor.length - 1; i > -1; i--) {
				if (i < this.valor.length)
					temp[i] = (byte)(valor[i]^b.valor[i]);
				else 
					temp[i] = b.valor[i];
			}
		}
		
		
		
		
		/*if (this.valor.length >= b.valor.length) temp = new byte[this.valor.length];
		else temp = new byte[b.valor.length];
		for (int i = valor.length - 1; i > -1; i--) {
			if (i < b.valor.length)temp[i] = (byte)(valor[i]^b.valor[i]);
			else temp[i] = valor[i];
		} */
		
		
		return new CorpoFinito (temp);
	}
	
	/*
	 * return a*b SEM fazer redu��o
	 */
	/*public CorpoFinito mult (CorpoFinito b) {
		byte[] temp = new byte [valor.length + b.valor.length];
		CorpoFinito c = new CorpoFinito (temp);
		for (int i = b.valor.length - 1; i > -1; i--) {
			for (int j = 7; j > -1; j--) {
				byte a;
				//System.out.print(i*8 + j + " ");
				a = b.valor[i];
				//System.out.print(a + " ");
				a = (byte) ((a&0xff)<<(7-j));
				//System.out.print(a + " ");
				a = (byte)(a>>>7&(byte)(0x01)); // isolo o bit na posição j do byte i
				//System.out.println(a + " ");
				if (a == 1) { //se ele for igual a um, multiplo o polinomio resposta por ele
					//System.out.print("C antes: ");
					//c.Mostra();
					//System.out.print("Somar com ");
					//mult1(j+i*8).Mostra();
					c = c.soma(mult1(j+i*8));
					//System.out.print("C depois: ");
					//c.Mostra();
				}
			}
		}
		return c;
	}*/
	
	/*
	 *  multiplica o corpofinito por um corpofinito que possui grau n-1 e apenas um coeficiente nao nulo
	 *  equivalente a << n
	 */
	/*public CorpoFinito mult1 (int n) {
		int byteInt = n/8;
		int byteQue = n%8;
		int tam = (valor.length*8+n)/8;
		//if ((grau()+n+1)% 8 != 0) tam++;
		byte[] resp = new byte [tam];
		for (int i = valor.length-1 ; i > -1; i--) {
			resp[i+byteInt] = valor[i]; //faco o deslocamento inteiro de bytes
		}
		for (int i = resp.length - 1; i > -1; i--) {
			resp[i] = (byte)(resp[i]<<byteQue); //deslocamento dentro do byte
			if (i > 0) {
				resp[i] = (byte)(resp[i]^((resp[i-1]&0xff)>>(8-byteQue))); //pedaco que veio do byte vizinho
			}
		}
		return new CorpoFinito (resp);
	}*/
	
	/*
	 * retorna a posicao do maior coeficiente nao nulo
	 */
	public int grau () {
		int resp = valor.length*8;
		boolean flag = true;
		for (int i = valor.length - 1; i > -1 && flag; i--) {
			if (valor[i] == 0) {
				resp -= 8;
			} else {
				flag = false;
				if (valor[i] < 0) {
					resp -= 1;
				} else if (valor[i] >= 64) {
					resp -= 2;
				} else if (valor[i] >= 32) {
					resp -= 3;
				} else if (valor[i] >= 16) {
					resp -= 4;
				} else if (valor[i] >= 8) {
					resp -= 5;
				} else if (valor[i] >= 4) {
					resp -= 6;
				} else if (valor[i] >= 2) {
					resp -= 7;
				} else if (valor[i] >= 1) {
					resp -= 8;
				}
			}
		}
		return resp;
	}
    
    public byte[] geraMonomioGrau(int x) {
        int byte_int = x/8;
        int byte_grau = x%8; // resto da divisão
        //System.out.println("Parte inteira : " + byte_int);
        //System.out.println("Parte resto : " + byte_grau);
        byte[] mm = new byte[byte_int + 1];// gera um byte array com o tamanho necessario.
        mm[byte_int] = (byte) ((byte) 1 << byte_grau);
//        for (int i = byte_int; i > -1; i--) {
//			byte a = (byte) (mm[i] >> 4);
//			System.out.print(hex.charAt(a&(byte)0x0F));
//			System.out.print(hex.charAt(mm[i]&(byte)0xF));
//		}
//		System.out.println();
//        System.out.println("");
        return mm;
    }
    
    public CorpoFinito multiplicacao(CorpoFinito c) {
        byte[] ba = new byte[c.valor.length + this.valor.length];
        byte a;
        for (int i = 0; i < c.valor.length; i++) {
            // Checa cada bit do byte para ver se é 1
            for (int j = 0; j < 8; j++) {
                a = c.valor[i];
                a <<= (7 - j);
                a = (byte) ((a&0xFF) >>> 7);
                // caso seja 1, faz o deslocamento adequado e soma na resposta
                if (a == 1) {
                    /*
                     * Deslocar um dado byte para x vezes para a esquerda é equivalente a somar na posição i+1 do vetor
                     * o valor do byte desloca 8-x vezes para direita e somar na posição i o valor do byte deslocado x vezes
                     * para a direita.
                     */
                    for (int k = this.valor.length - 1; k > -1; k--) {
                        ba[k + i + 1] = (byte) ((byte) ba[k + i + 1]^((this.valor[k] & 0xFF) >> (8 - j) ) );
                        ba[k + i] = (byte) ((byte) ba[k + i]^((this.valor[k] & 0xFF) << j ) );
                    }
                }
            }
        }
        return new CorpoFinito(ba);
    }
    
    /*
     * retorna a*b COM redu��o
     */
    public CorpoFinito MultiplicaReduz (CorpoFinito b) {
    	return multiplicacao(b).divisao(CorpoFinito.redutor);
    }
    
    
                
    public CorpoFinito divisao(CorpoFinito c, CorpoFinito quo) {
        // Inicializa os valores usados nas contas
        CorpoFinito dividendo = new CorpoFinito(this.valor);
        CorpoFinito quociente = new CorpoFinito(new byte[this.valor.length]);
        //CorpoFinito resto = new CorpoFinito(new byte[this.valor.length]);
        //CorpoFinito uni = CorpoFinito.uni(this.valor.length * 8);
        CorpoFinito temp;

        /*
         * Enquento o grau do dividendo for maior ou igual ao do divisor ....
         * mas antes verifico se estou dividindo por unitario
         */
        if (c.isUni()) return CorpoFinito.zero(c.valor.length*8);
        while ( ( dividendo.grau() - c.grau() ) >= 0) {
            temp = new CorpoFinito(quociente.geraMonomioGrau(dividendo.grau() - c.grau() ) );
            
            // Quociente é somado polinomio que o multiplica para anular o termo de maior grau do dividendo
            quociente = quociente.soma(new CorpoFinito(quociente.geraMonomioGrau(dividendo.grau() - c.grau() ) ) );
            
            //System.out.println("Dividendo : ");
            //dividendo.Mostra();
            
            // Soma ao dividendo o valor do divisor x o monomio calculado para anular o termo
            dividendo = dividendo.soma(c.multiplicacao(temp));
            
            //System.out.println("Novo Dividendo : ");
            //dividendo.Mostra();
            
            //System.out.println("Quociente : ");
            //quociente.Mostra();
            
        }
        //quociente.Mostra();
        quo.valor = quociente.valor.clone();
        
        if (c.valor.length < dividendo.valor.length) { //devo remover a parte inutil para realmente reduzir o tamanho da resp
        	byte[] temp2 = new byte [c.valor.length];
        	for (int i = 0; i < c.valor.length; i++) {
        		temp2[i] = dividendo.valor[i];
        	}
        	dividendo = new CorpoFinito(temp2);
        }
        return dividendo;
    }
    
    public CorpoFinito divisao(CorpoFinito c) {
    	return divisao (c,zero(c.valor.length));
    }
    
    /*
     * algoritmo estendido de euclides que retorna A^-1 mod mod
     */
    public CorpoFinito inverso (CorpoFinito mod) {
        if (!isZero()) {
            CorpoFinito F,G,B,C,alpha;
            F = clone();
            B = CorpoFinito.uni(mod.valor.length);
            G = mod.clone();
            C = CorpoFinito.zero (mod.valor.length);
            alpha = CorpoFinito.zero (mod.valor.length);
            while (F.grau() > 0) {
                if (F.grau() < G.grau()) { // troco F <> G e B <> C
                    alpha = F.clone();
                    F = G.clone();
                    G = alpha.clone();
                    alpha = B.clone();
                    B = C.clone();
                    C = alpha.clone();
                }
                F = F.divisao (G, alpha);
                B = B.soma(C.multiplicacao(alpha));
            }
            if (F.grau() == 0 && !F.isZero()) return B;
            else return null;
        }
        else
            return null;
    }
    
    public CorpoFinito inverso () {
    	return inverso(CorpoFinito.redutor);
    }
	
	public void Mostra () {
		for (int i = valor.length - 1; i > -1; i--) {
			byte a = (byte) (valor[i] >> 4);
			System.out.print(hex.charAt(a&(byte)0x0F));
			System.out.print(hex.charAt(valor[i]&(byte)0xF));
		}
		System.out.println();
	}
    
    public void MostraMatriz () {
		for (int i = valor.length - 1; i > -1; i--) {
			byte a = (byte) (valor[i] >> 4);
			System.out.print(hex.charAt(a&(byte)0x0F));
			System.out.print(hex.charAt(valor[i]&(byte)0xF));
		}
	}

	
}
