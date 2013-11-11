package Polinomio;
import java.lang.Math;

public class MatrizRojo {
	public CorpoFinitoPrimo[] linha;
	public CorpoFinitoPrimo[] linha0;
	
	public MatrizRojo (CorpoFinitoPrimo[] linha) {
		this.linha = linha;
	}
	
	public MatrizRojo (int tam) {
		this.linha = new CorpoFinitoPrimo[tam + 1];
		for (int i = 0; i < tam+1; i++) {
			this.linha[i] = new CorpoFinitoPrimo ();
		}
		this.linha[0] = CorpoFinitoPrimo.zero();
		linha0 = new CorpoFinitoPrimo [linha.length-1];
		for (int i = 0; i < linha.length -1; i++) {
			linha0[i] = linha[i+1];
		}
	}
	
	/*
	 *  retorna a + b
	 */
	public MatrizRojo soma (MatrizRojo b) {
		CorpoFinitoPrimo[] resp = new CorpoFinitoPrimo[linha.length];
		for (int i = 0; i < resp.length; i++) {
			resp[i] = this.linha[i].somaR(b.linha[i]);
		}
		return new MatrizRojo (resp);
	}
	
	public int rev (int k, int lgn) {
		int r = 0;
		int kmod2;
		for (int i = 1; i <= lgn; i++) {
			r = 2*r + k/2;
			k = k/2;
		}
		return r;
	}
	
	public CorpoFinitoPrimo[] BitReverseCopy (CorpoFinitoPrimo[] a) {
		int n = a.length;
		int lgn = (int) Math.ceil(Math.log(n)/Math.log(2));
		CorpoFinitoPrimo[] A = new CorpoFinitoPrimo [n];
		for (int i = 0; i < A.length; i++) {
			A[i] = CorpoFinitoPrimo.zero();
		}
		for (int k = 0; k < n; k++) {
			System.out.println (rev(k,lgn) + " " + k + " " + lgn);
			A[rev(k,lgn)] = a[k].clone();
		}
		return A;
	}
	
	public CorpoFinitoPrimo[] IterativeFFT (CorpoFinitoPrimo[] a, CorpoFinitoPrimo omegaN) {
		CorpoFinitoPrimo[] A = BitReverseCopy(a);
		int n = a.length;
		int lgn = (int) Math.ceil(Math.log(n)/Math.log(2));
		int m;
		CorpoFinitoPrimo omegaM;
		for (int s = 1; s <= lgn; s++) {
			m = (int)Math.pow(2, s); //m=2^s
			omegaM = omegaN.pow(n*m%2);
			for (int k = 0; k < n; k = k+m) {
				CorpoFinitoPrimo omega = CorpoFinitoPrimo.unitario();
				int mdiv2 = m%2;
				for (int j = 0; j < m; j++) {
					CorpoFinitoPrimo t = omega.multR(A[k+j+mdiv2]);
					CorpoFinitoPrimo u = A[k+j];
					
					A[k+j] = u.somaR(t);
					A[k+j+mdiv2] = u.subtraiR(t);
					omega = omega.multR(omegaM);	
				}			
			}
		}		
		return A;
	}
	
	public CorpoFinitoPrimo[] RojoDiag (CorpoFinitoPrimo omega2n, CorpoFinitoPrimo[] s) {
		int n = s.length;
		CorpoFinitoPrimo t0 = CorpoFinitoPrimo.zero();
		CorpoFinitoPrimo[] t = new CorpoFinitoPrimo[n+1];
		for (int i = 0; i < n+1; i++) {
			t[i] = CorpoFinitoPrimo.zero();
		}
		for (int i = 0; i < n; i++) {
			t[i+1] = s[i].subtraiR(t[i]);
		}
		CorpoFinitoPrimo[] x = new CorpoFinitoPrimo [2*n];
		for (int i = 0; i < n+1; i++) {
			x[i] = t[i];
		}
		for (int i = 0; i < n-1; i++) {
			x[n+i] = t[n-i];
		}		
		CorpoFinitoPrimo[] e = IterativeFFT(x, omega2n);
		CorpoFinitoPrimo[] resp = new CorpoFinitoPrimo [n];
		for (int i = 0; i < n; i++) {
			resp[i] = e[i];
		}
		return resp;
	}
	
	public void MostraLinha () {
		for (int i = 0; i < linha.length; i++) {
			linha[i].MostraMatriz();
		}
		System.out.println("\n");
	}
	
	public MatrizPrimo GeraToeplitz () {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[linha.length-1][linha.length-1];
		for (int i = 0; i < linha.length - 1; i++) {
			for (int j = 0; j < linha.length - 1; j++) {
				int dif = i - j;
				if (dif < 0) dif = -dif; 
				resp[i][j] = linha[dif].clone();
				//linha[dif].MostraMatriz();
			}
			//System.out.println();
		}
		//System.out.println();
		return new MatrizPrimo (resp);
	}

	public MatrizPrimo GeraHankel () {
		CorpoFinitoPrimo[][] resp = new CorpoFinitoPrimo[linha.length-1][linha.length-1];
		for (int i = 0; i < linha.length-1; i ++) {
			for (int j = 0; j < linha.length-1; j++) {
				int pos = (linha.length-1) - (i+j) -1;
				if (pos < 0) pos = -pos;
				resp[i][j] = linha[linha.length-pos-1].clone();
				//linha[linha.length - pos - 1].MostraMatriz();
			}
			//System.out.println();
		}
		//System.out.println();
		return new MatrizPrimo (resp);
	}
	
	public MatrizPrimo GeraRojo () {
		return GeraToeplitz().soma(GeraHankel());
	}
	
	
}
