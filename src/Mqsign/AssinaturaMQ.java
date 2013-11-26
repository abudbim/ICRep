/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mqsign;

import Polinomio.*;

/**
 *
 * @author Abud
 */
public class AssinaturaMQ {
    /**
     * Tamanho do corpoFinito que ser√° usado
     */
    private int TAM_CF;
    /**
     * Potencia de (2^n) -1 a qual corresponde o tamnho
     */
    private int EXP_CF;
    
    /**
     * Numero de matrizes que far√£o parte das chaves
     */
    private int NM;
    /**
     * Tamanho do bloco de matriz que ser√° usado
     */
    private int P;
    /**
     * Numero de blocos de cada matriz
     */
    private int NB;
    /**
     * Numero de blocos que far√£o parte dos vinagres
     */
    private int VP;
    /**
     * Tamanho de blocos que farao parte dos oils
     */
    private int OP;
    
    private MatrizCentroSim[] ChavePriv;
    
    public MatrizCentroSim[] ChavePub;
    
    /**
     * Instancia um novo Objeto de assinatura ao mesmo tempo que seta os paramatros de corpo finito e tamanho de chave.
     * @param tam_cf Tamanho do corpofinito.
     * @param exp_cf Expoente correspondete ao tamanho do corpoFinito (2^n) - 1
     * @param tam_m Tamanho dos blocos das matrizes.
     * @param num_m Numero de matrizes que fazem parte da chave.
     */
    public AssinaturaMQ(int tam_cf, int exp_cf,int num_m, int tam_bloc, int num_blocs, int vinagres) {
        this.TAM_CF = tam_cf;
        this.EXP_CF = exp_cf;
        this.NM = num_m;
        this.P = tam_bloc;
        this.NB = num_blocs;
        this.VP = vinagres;
        this.OP = this.NB - this.VP;
        geraChaves();
    }

    public int getTAM_CF() {
        return this.TAM_CF;
    }

    public void setTAM_CF(int TAM_CF) {
        this.TAM_CF = TAM_CF;
    }

    public  int getEXP_CF() {
        return this.EXP_CF;
    }

    public  void setEXP_CF(int EXP_CF) {
        this.EXP_CF = EXP_CF;
    }

    public int getTamBloco() {
        return P;
    }

    public void setTamBloco(int P) {
        this.P = P;
    }

    public int getNumBlocos() {
        return NB;
    }

    public void setNumBlocos(int NP) {
        this.NB = NP;
    }

    public int getNumVinagres() {
        return VP;
    }

    public void setNumVinagres(int VP) {
        this.VP = VP;
    }

    public int getNumOils() {
        return OP;
    }

    public void setNumOils(int OP) {
        this.OP = OP;
    }
    
    
    public MatrizCentroSim[] getChavePriv() {
		return ChavePriv;
	}

	public MatrizCentroSim[] getChavePub() {
		return ChavePub;
	}
	
	

	private void geraChaves() {
        this.ChavePriv = new MatrizCentroSim[this.NM + 2];
        this.ChavePub = new MatrizCentroSim[this.NM];
        
        // Cria√ß√£o da matriz para embaralhar.
        // Cria uma matriz de CorpoFinito com tamanho necessario (NP*P)
        CorpoFinitoPrimo[][] a = new CorpoFinitoPrimo[NB*P][NB*P];
        for (int i = 0; i < NB*P; i++)
            for (int j = 0; j < NB*P; j++) {
                a[i][j] = new CorpoFinitoPrimo();
            }
        this.ChavePriv[0] = new MatrizCentroSim(a);
        
        // Faz a transposta da matriz anteriormente criada
        a = new CorpoFinitoPrimo[NB*P][NB*P];
        for (int i = 0; i < NB*P; i++)
            for (int j = 0; j < NB*P; j++) {
                a[i][j] = this.ChavePriv[0].coeficientes[j][i];
            }
        this.ChavePriv[1] = new MatrizCentroSim(a);
        
        // Cria o resto das matrizes
        for (int k = 0; k < NM; k++) {
            a = new CorpoFinitoPrimo[NB*P][NB*P];
            for (int i = 0; i < NB*P; i++)
                for (int j = 0; j < NB*P; j++) {
                    if (i > VP*P - 1 && j > VP*P - 1)
                        a[i][j] = CorpoFinitoPrimo.zero();
                    else
                        a[i][j] = new CorpoFinitoPrimo();
                }
            this.ChavePriv[k + 2] = new MatrizCentroSim(a);
            this.ChavePub[k] = this.ChavePriv[1].multiplicacao(this.ChavePriv[k+2].multiplicacao(this.ChavePriv[0]));
        }
    }
    
    public CorpoFinitoPrimo[] UOVSign(String message) {
    	boolean isInv = false;
    	
    	CorpoFinitoPrimo[][] vin;
    	CorpoFinitoPrimo[][] vinT;
    	
    	MatrizCentroSim vinegars = null;
    	MatrizCentroSim vinegarsT = null;
    	
    	while(!isInv) {
    		// Chute dos valores dos vinagres
    		vin = new CorpoFinitoPrimo[1][VP*P];
        	vinT = new CorpoFinitoPrimo[VP*P][1];
        	for (int i = 0; i < VP*P; i++) {
        		vin[0][i] = new CorpoFinitoPrimo();
        		vinT[i][0] = vin[0][i];
        	}
        	
        	vinegars = new MatrizCentroSim(vin);
        	vinegarsT = new MatrizCentroSim(vinT);
        	
        	System.out.println("Vinagres");
        	vinegars.Mostra();
        	vinegarsT.Mostra();
        	
        	// CriaÁ„o de Matriz de Zeros de tamanho oils x oils. Verticaljoin
        	MatrizCentroSim Fhat = MatrizCentroSim.zero(OP*P, OP*P);
        	MatrizCentroSim partPriv;
    	
	    	for (int i = 0; i < OP*P; i++) {
	    		System.out.println("Sub Matriz");
	    		ChavePriv[2+i].Submatriz(VP*P + 1, 1, OP*P, VP*P).Mostra();
	    		System.out.println("Multiplicacao");
	    		ChavePriv[2+i].Submatriz(VP*P + 1, 1, OP*P, VP*P).multiplicacao(vinegarsT).multiplicaEscalar(2).Mostra();
	    		// Fhat = B * vT * 2, porem B*vT È vetor coluna e precisa ser transposto
	    		ChavePriv[2+i].Submatriz(VP*P + 1, 1, OP*P, VP*P).multiplicacao(vinegarsT).multiplicaEscalar(2).transpor().Mostra();
	    		// Para cada vetor obtido forma a matriz linha a linha
	    		Fhat.compoeMatrizPorLinha(i, ChavePriv[2+i].Submatriz(VP*P + 1, 1, OP*P, VP*P).multiplicacao(vinegarsT).multiplicaEscalar(2).transpor().coeficientes[0]);
	    		System.out.println("Matriz dos produtos");
	    		Fhat.Mostra();
	    	}
	    	
	    	if (Fhat.inverteMatriz() != null) {
	    		System.out.println("A matriz È inversivel");
	    		Fhat = Fhat.inverteMatriz();
	    		isInv = true;
	    	}
    	}
    	// Calcula o valor v*A e v*B para resoluÁ„o do sistema linear
    	// Como Fhat tem tamanho v+o x v 
    	MatrizCentroSim a;
    	CorpoFinitoPrimo[][] b = MatrizCentroSim.zero(1, OP*P).coeficientes;
    	a = new MatrizCentroSim(b);
    	System.out.println("Vetor b : inicio");
    	a.Mostra();
    	
    	for (int i = 0; i < OP*P; i++) {
    		b[0][i] = new CorpoFinitoPrimo((byte) (message.charAt(i) - (vinegars.multiplicacao(ChavePriv[2+i].Submatriz(1, 1, VP*P, VP*P)).multiplicacao(vinegarsT).coeficientes[0][0].valor)));
    		a = new MatrizCentroSim(b);
        	a.Mostra();
    	}
    	System.out.println("Vetor b : fim");
    	a = new MatrizCentroSim(b);
    	a.Mostra();
    	
    	   	
    	
        return null;
    } 
    
    public void mostraChaves () {
        System.out.println("Chaves Privadas : ");
        for (int i = 0; i < ChavePriv.length; i ++)
            ChavePriv[i].Mostra();
        
        System.out.println("Chaves Publicas : ");
        for (int i = 0; i < ChavePub.length; i ++)
            ChavePub[i].Mostra();
    }
}
