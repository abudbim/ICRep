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
    public AssinaturaMQ(int tam_cf, int exp_cf, int tam_bloc, int num_blocs, int vinagres) {
        this.TAM_CF = tam_cf;
        this.EXP_CF = exp_cf;
        this.P = tam_bloc;
        this.NB = num_blocs;
        this.VP = vinagres;
        this.OP = this.NB - this.VP;
        this.NM = OP*P;
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
	
	
	/**
	 * Metodo responsavel pela geracao das chaves que serao usadas para a assinatura baseada em MQ.
	 */
	private void geraChaves() {
        this.ChavePriv = new MatrizCentroSim[this.NM + 2];
        this.ChavePub = new MatrizCentroSim[this.NM];
        boolean isInv = false;
        MatrizCentroSim a1;
        CorpoFinitoPrimo[][] a;
        MatrizCentroSim b;
        
        while (!isInv) {
        	
        	// Cria uma matriz Zero para preencher cada bloco com matrizes centrossimetricas
        	a1 = MatrizCentroSim.zero(NB*P);
        	for (int i = 0; i < VP + OP; i++) {
        		for (int j = 0; j < VP + OP; j++) {
        			a1.MontaMatriz(i*P, j*P, new MatrizCentroSim(P));
        		}
        	}
        	
        	if (a1.inverteMatriz() != null) {
        		isInv = true;
        	}
        	
        	ChavePriv[0] = a1;
        	ChavePriv[1] = a1.transpor();
        
	        // Cria√ß√£o da matriz para embaralhar.
	        // Cria uma matriz de CorpoFinito com tamanho necessario (NP*P)
	        /*a = new CorpoFinitoPrimo[NB*P][NB*P];
	        for (int i = 0; i < NB*P; i++)
	            for (int j = 0; j < NB*P; j++) {
	                a[i][j] = new CorpoFinitoPrimo();
	            }
	        b = new MatrizCentroSim(a);
	        if (b.inverteMatriz() != null) {
	        	isInv = true;
	        	this.ChavePriv[0] = b;
	        	// Faz a transposta da matriz anteriormente criada
		        a = new CorpoFinitoPrimo[NB*P][NB*P];
		        for (int i = 0; i < NB*P; i++)
		            for (int j = 0; j < NB*P; j++) {
		                a[i][j] = this.ChavePriv[0].coeficientes[j][i];
		            }
		        this.ChavePriv[1] = new MatrizCentroSim(a);
	        }*/        
        }
        
        // Cria o resto das matrizes
        for (int k = 0; k < NM; k++) {
            a1 = MatrizCentroSim.zero(NB*P);
            for (int i = 0; i < VP + OP; i++)
                for (int j = 0; j < VP + OP; j++) {
                    a1.MontaMatriz(i*P, j*P, new MatrizCentroSim(P));
                }
            a1.MontaMatriz(VP*P, VP*P, MatrizCentroSim.zero(OP*P));
            this.ChavePriv[k + 2] = a1;
            this.ChavePub[k] = this.ChavePriv[0].multiplicacao(this.ChavePriv[k+2].multiplicacao(this.ChavePriv[1]));
        }
    }
    
	/**
	 * Tem como objetivo gera a assinatura da mensagem fornecido com as chaves ja criadas. v*A*vt + 2*o*Bt*vt = message, a equacao a ser resolvida as 
	 * variaveis sao v e o.
	 * @param message um vetor de CorpoFinitoPrimo com a mensagem a ser assinada.
	 * @return A assinatura da mensagem.
	 */
    public MatrizCentroSim UOVSign(CorpoFinitoPrimo[] message) {
    	boolean isInv = false;
    	
    	CorpoFinitoPrimo[][] vin;
    	CorpoFinitoPrimo[][] vinT;
    	
    	MatrizCentroSim vinegars = null;
    	MatrizCentroSim vinegarsT = null;
    	
    	MatrizCentroSim Fhat = MatrizCentroSim.zero(OP*P, OP*P);
    	
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
    	
	    	for (int i = 0; i < OP*P; i++) {
	    		// Realiza a operaÁ„o Bt*v + vt*B para obter o 2Bt*v. Porem, agrupando os vetores resultantes das operacoes com cada chave
	    		// temos uma matriz de OxO
	    		ChavePriv[2+i].Submatriz(VP*P + 1, 1, OP*P, VP*P).multiplicacao(vinegarsT).transpor().soma(vinegarsT.transpor().multiplicacao(ChavePriv[2+i].Submatriz(1, VP*P + 1, VP*P, OP*P))).Mostra();
	    		Fhat.compoeMatrizPorLinha(i, ChavePriv[2+i].Submatriz(VP*P + 1, 1, OP*P, VP*P).multiplicacao(vinegarsT).transpor().soma(vinegarsT.transpor().multiplicacao(ChavePriv[2+i].Submatriz(1, VP*P + 1, VP*P, OP*P))).coeficientes[0]);
	    		System.out.println("Matriz dos produtos");
	    		Fhat.Mostra();
	    	}
	    	
	    	if (Fhat.inverteMatriz() != null) {
	    		System.out.println("A matriz È inversivel");
	    		Fhat = Fhat.inverteMatriz();
	    		isInv = true;
	    	}
    	}
    	
    	MatrizCentroSim a;
    	CorpoFinitoPrimo[][] b = MatrizCentroSim.zero(1, OP*P).coeficientes;
    	a = new MatrizCentroSim(b);
    	System.out.println("Vetor b : inicio");
    	a.Mostra();
    	
    	// Realiza  as operacao para obtencao de v*A*vt. O resultado dessa conta , para cada uma das chaves privadas È colocado no vetor b
    	for (int i = 0; i < OP*P; i++) {
    		message[i].Mostra();
    		vinegars.multiplicacao(ChavePriv[2+i].Submatriz(1, 1, VP*P, VP*P)).multiplicacao(vinegarsT).coeficientes[0][0].Mostra();
    		b[0][i] = (message[i].subtraiR(vinegars.multiplicacao(ChavePriv[2+i].Submatriz(1, 1, VP*P, VP*P)).multiplicacao(vinegarsT).coeficientes[0][0]));
    		a = new MatrizCentroSim(b);
        	a.Mostra();
    	}
    	System.out.println("Vetor b : fim");
    	a = new MatrizCentroSim(b);
    	a.Mostra();
    	
    	// Resolve o sistema linear obtendo o valor dos oils.
    	MatrizCentroSim oils = a.multiplicacao(Fhat.transpor());
    	
    	System.out.println("oilssss :");
    	oils.Mostra();
    	
    	// Cria um vetor que junta os valores chutados para os vinegars com o valor obtido para os Oils.
    	CorpoFinitoPrimo[][] signCoef = new CorpoFinitoPrimo[1][OP*P+VP*P];
    	
    	for (int i = 0; i < VP*P; i++) {
    		signCoef[0][i] = vinegars.coeficientes[0][i];
    	}
    	
    	for (int j = VP*P; j < VP*P + OP*P; j++) {
    		signCoef[0][j] = oils.coeficientes[0][j - VP*P];
    	}
    	System.out.println("Assinatura depois de vinagres e oils");
    	
    	MatrizCentroSim a1 = new MatrizCentroSim(signCoef);
    	a1.Mostra();
    	
    	// Multiplica pelo inverso de S (matriz que embaralha ChavesPriv) para terminar o calculo da assinatura
    	System.out.println("S inverso :");
    	ChavePriv[0].inverteMatriz().Mostra();
    	
    	a1 = a1.multiplicacao(ChavePriv[0].inverteMatriz());
    	System.out.println("Vetor de oils e vinegars multiplicado por Sinv");
    	a1.Mostra();
    	
        return a1;
    } 
    
    /*
     * Tamanho da mensagem e numero de matrizes deve ser oils.
     * 
     */
    
    /**
     * Faz a verificacao da assinatura checando se u*Pi*ut = messagei para todas as matrizes que compoe a chave a publica
     * @param message Mensagem a qual deseja-se checar a assinatura.
     * @param sign Assisnatura fornecida.
     * @return True caso a assinatura esteja de acordo com a mensagem, False caso contrario.
     */
    public boolean UOVCheck(CorpoFinitoPrimo[] message, MatrizCentroSim sign) {
    	System.out.println("Multiplicacoes");
    	
    	for (int i = 0; i < OP*P; i++) { 
    		System.out.println("Valor encontrado em " + i);
			sign.multiplicacao(ChavePub[i]).multiplicacao(sign.transpor()).Mostra();
    		if (!sign.multiplicacao(ChavePub[i]).multiplicacao(sign.transpor()).coeficientes[0][0].isEqual(message[i])) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public void mostraChaves () {
        System.out.println("Chaves Privadas : ");
        StringBuilder s;
        for (int i = 0; i < ChavePriv.length; i ++) {
            ChavePriv[i].Mostra();
        }
        
        System.out.println("Chaves Publicas : ");
        s = new StringBuilder();
        for (int i = 0; i < ChavePub.length; i ++) {
            ChavePub[i].Mostra();
        }
    }
}
