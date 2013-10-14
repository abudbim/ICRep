package Polinomio;

import java.security.SecureRandom;

public class CorpoFinitoPrimo {
	public byte valor;
	private static final CorpoFinitoPrimo ZERO = new CorpoFinitoPrimo((byte)0);
	private static final CorpoFinitoPrimo UNI = new CorpoFinitoPrimo((byte)1);
	public static int p = 31;
	public static int m = 5;
	
	/*
	 * Gera um int aleatorio e instancia um corpo finito
	 * com o valor do inteiro reduzido
	 */
	public CorpoFinitoPrimo () {
		SecureRandom sr = new SecureRandom();
		int temp = sr.nextInt(p);
		this.valor = (byte) temp;
	}
	/*
	 * Instanceia um CorpoFinitoPrimo a partir de um byte SEM reduzir
	 */
	public CorpoFinitoPrimo (byte valor) {
		this.valor = valor;
	}
	
	/*
	 * Instanceia um CorpoFinitoPrimo a partir de um int COM reduzir
	 */
	public CorpoFinitoPrimo (int valor) {
		this.valor = CorpoFinitoPrimo.reducao(valor).valor;
	}
	
	/*
	 * Gera um novo corpo finito com valor igual a 0
	 */
	public static CorpoFinitoPrimo zero () {
		return CorpoFinitoPrimo.ZERO;
	}
	
	/*
	 * Retorna um corpo finito cujo valor � 1
	 */
	public static CorpoFinitoPrimo unitario () {
		return CorpoFinitoPrimo.UNI;
	}
	
	/*
	 * Cria um novo corpo finito com o mesmo valor do corpo a ser clonado
	 */
	public CorpoFinitoPrimo clone () {
		return new CorpoFinitoPrimo(this.valor);
	}
	
	/*
	 * return true se a = b
	 */
	public boolean isEqual (CorpoFinitoPrimo b) {
		if (this.valor == b.valor) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * return true se igual a 0
	 */
	public boolean isZero () {
		if (valor == 0) {
			return true;
		} else {
			return false;
		}
	}
        
        public boolean isUnitario() {
            if (valor == 1 )
                return true;
            else
                return false;
        }
	
	/*
	 * return a + b reduzido
	 */
	public CorpoFinitoPrimo somaR (CorpoFinitoPrimo b) {
		int resp = this.valor + b.valor;
		return CorpoFinitoPrimo.reducao(resp);
	}
        
        public CorpoFinitoPrimo inversoAditivo() {
            return CorpoFinitoPrimo.ZERO.subtraiR(this);
        }
	
	/*
	 * return a - b reduzido
	 */
	public CorpoFinitoPrimo subtraiR (CorpoFinitoPrimo b) {
		int resp = this.valor +(p - b.valor);
		return CorpoFinitoPrimo.reducao(resp);
	}
	
	/*
	 * return a * b reduzido
	 */
	public CorpoFinitoPrimo multR (CorpoFinitoPrimo b) {
		int resp = this.valor * b.valor;
		return CorpoFinitoPrimo.reducao(resp);
	}
	
	/*
	 *  retorna o inverso de a (a *b = 1)
	 */
	public CorpoFinitoPrimo inverso () {
        if (!isZero()) {
            int F,G,B,C,alpha;
            F = this.valor;
            B = 1;
            G = CorpoFinitoPrimo.p; //p
            C = 0;
            alpha = 0;
            while (F > 1) {
                if (F < G) { // troco F <> G e B <> C
                    alpha = F;
                    F = G;
                    G = alpha;
                    alpha = B;
                    B = C;
                    C = alpha;
                }
                alpha = F/G;
                F = F - alpha*G;
                B = B - alpha*C;
            }
            if (F == 1) return new CorpoFinitoPrimo ((byte)B);
            else return null;
        }
        else
            return null;
    }
	
	/*
	 * return a/b
	 */
	public CorpoFinitoPrimo divisao (CorpoFinitoPrimo b) {
		return multR(b.inverso());
	}
	
	public static CorpoFinitoPrimo	reducaoIt (int valor) {
		while (valor < 0) {
			valor += 31;
		}
		boolean flag = true;
		int maisSig,menosSig;
		while (flag) {
			menosSig = valor & 31;
			maisSig = valor >> 5;
			valor = menosSig + maisSig;
			if (valor < 32) {
				flag = false;
			}
		}
		if (valor == 31) {
			valor =  0;
		}
		return new CorpoFinitoPrimo ((byte)valor);
	}
	
	public static CorpoFinitoPrimo	reducao (int valor) {
        int resp = (byte) (valor & p) + (byte) (valor >>> m);
		if (resp >= p) {
			resp = resp - p;
		}
		return new CorpoFinitoPrimo ((byte)resp);
	}

	public void Mostra () {
		System.out.println (valor);
	}
        
        public void MostraMatriz () {
            System.out.print(this.valor + "\t");
        }
}
