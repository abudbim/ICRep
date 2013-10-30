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
     * Tamanho do corpoFinito que será usado
     */
    private int TAM_CF;
    /**
     * Potencia de (2^n) -1 a qual corresponde o tamnho
     */
    private int EXP_CF;
    /**
     * Tamanho do bloco de matriz que será usado
     */
    private int TAM_M;
    /**
     * Numero de matrizes que farão parte das chaves
     */
    private int NUM_M;
    
    private MatrizCentroSim[] ChavePriv;
    
    public MatrizCentroSim[] ChavePub;
    
    /**
     * Instancia um novo Objeto de assinatura ao mesmo tempo que seta os paramatros de corpo finito e tamanho de chave.
     * @param tam_cf Tamanho do corpofinito.
     * @param exp_cf Expoente correspondete ao tamanho do corpoFinito (2^n) - 1
     * @param tam_m Tamanho dos blocos das matrizes.
     * @param num_m Numero de matrizes que fazem parte da chave.
     */
    public AssinaturaMQ(int tam_cf, int exp_cf, int tam_m, int num_m) {
        setTAM_CF(tam_cf);
        setEXP_CF(exp_cf);
        setTAM_M(tam_m);
        setNUM_M(num_m);
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

    public int getTAM_M() {
        return TAM_M;
    }

    public void setTAM_M(int TAM_M) {
        this.TAM_M = TAM_M;
    }

    public int getNUM_M() {
        return NUM_M;
    }

    public void setNUM_M(int NUM_M) {
        this.NUM_M = NUM_M;
    }
    
    private void geraChaves() {
        this.ChavePriv = new MatrizCentroSim[this.NUM_M + 2];
        this.ChavePub = new MatrizCentroSim[this.NUM_M];
        
        // Criação da matriz para embaralhar.
        CorpoFinitoPrimo[][] a = new CorpoFinitoPrimo[3*TAM_M][3*TAM_M];
        for (int i = 0; i < 3*TAM_M; i++)
            for (int j = 0; j < 3*TAM_M; j++) {
                a[i][j] = new CorpoFinitoPrimo();
            }
        this.ChavePriv[0] = new MatrizCentroSim(a);
        
        // Faz a transposta da matriz anteriormente criada
        a = new CorpoFinitoPrimo[3*TAM_M][3*TAM_M];
        for (int i = 0; i < 3*TAM_M; i++)
            for (int j = 0; j < 3*TAM_M; j++) {
                a[i][j] = this.ChavePriv[0].coeficientes[j][i];
            }
        this.ChavePriv[1] = new MatrizCentroSim(a);
        
        // Cria o resto das matrizes
        for (int k = 0; k < NUM_M; k++) {
            a = new CorpoFinitoPrimo[3*TAM_M][3*TAM_M];
            for (int i = 0; i < 3*TAM_M; i++)
                for (int j = 0; j < 3*TAM_M; j++) {
                    if (i > 2*TAM_M - 1 && j > 2*TAM_M - 1)
                        a[i][j] = CorpoFinitoPrimo.zero();
                    else
                        a[i][j] = new CorpoFinitoPrimo();
                }
            this.ChavePriv[k + 2] = new MatrizCentroSim(a);
            this.ChavePub[k] = this.ChavePriv[1].multiplicacao(this.ChavePriv[k+2].multiplicacao(this.ChavePriv[0]));
        }
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
