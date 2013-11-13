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
                    if (i > VP - 1 && j > VP - 1)
                        a[i][j] = CorpoFinitoPrimo.zero();
                    else
                        a[i][j] = new CorpoFinitoPrimo();
                }
            this.ChavePriv[k + 2] = new MatrizCentroSim(a);
            this.ChavePub[k] = this.ChavePriv[1].multiplicacao(this.ChavePriv[k+2].multiplicacao(this.ChavePriv[0]));
        }
    }
    
    public static CorpoFinitoPrimo[] UOVSign(AssinaturaMQ ass, String message) {
    	// Chute dos valores dos vinagres
    	CorpoFinitoPrimo[] vinegars = new CorpoFinitoPrimo[ass.getNumVinagres()*ass.getTamBloco()];
    	for (int i = 0; i < vinegars.length; i++) {
    		vinegars[i] = new CorpoFinitoPrimo();
    	}
    	// CriaÁ„o de Matriz de Zeros
    	MatrizPrimo Fhat = MatrizPrimo.zero(ass.getNumBlocos()*ass.getTamBloco(), ass.getNumOils()*ass.getTamBloco());
    	// Calcula o valor v*A e v*B para resoluÁ„o do sistema linear
    	// Como Fhat tem tamanho v+o x v 
    	for (int i = 0; i < vinegars.length; i++) {
    		for (int j = 0; j < ass.getTamBloco()*ass.getNumBlocos(); j++) {
    			for (int k = 0; k < ass.getTamBloco()*vinegars.length; k++) {
    				Fhat.coeficientes[j][k] = ass.ChavePriv[2+i].coeficientes[j][k].multR(vinegars[i]);
    			}
    		}
    	}
    	
    	
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
