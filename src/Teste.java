
import Polinomio.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abud
 */
public class Teste {
    
    public static void TestePropriedades(int testes) {
        CorpoFinito a,b,c;
        int cfSumNeutro = 0, cfSumInverso = 0, cfSumComu = 0, cfSumAssoc = 0;
        int cfMultNeutro = 0, cfMultNulo = 0, cfMultInverso = 0, cfMultComu = 0, cfMultAssoc = 0, cfMultDistr = 0;
        int mtSumNeutro = 0, mtSumInverso = 0, mtSumComu = 0, mtSumAssoc = 0;
        int mtMultNeutro = 0, mtMultNulo = 0, mtMultInverso = 0, mtMultAssoc = 0, mtMultDistr = 0; 
        
        for (int i = 0; i < testes; i++) {
        	
            a = new CorpoFinito(6);
            b = new CorpoFinito(4);
            c = new CorpoFinito(6);
            // Testes da soma : 
            
            // Testa elemento neutro
            if (!a.compara(a.soma(CorpoFinito.zero(2))) )
                cfSumNeutro++;
            
            // Testa elemento inverso
            if (!a.soma(a).isZero())
                cfSumInverso++;
            
            // Teste Comutatividade
            if (!a.soma(b).compara(b.soma(a)) )
                cfSumComu++;
            
            // Teste Associatividade
            if (!a.soma(b.soma(c)).compara(c.soma(b.soma(a))) )
            	cfSumAssoc++;
            
            
            // Testes da multiplicação : 
            
            // Testa elemento nulo : A*0 = 0
            if (!a.MultiplicaReduz(CorpoFinito.zero(2)).isZero() )
            	cfMultNulo++;
            
            // Testa elemento neutro : A*1 = 1
            if ( !a.compara(a.MultiplicaReduz(CorpoFinito.uni(2))) )
            	cfMultNeutro++;
            
            // Testa elemento inverso : A*A^-1 = 1
            if (a.inverso() != null && (!a.MultiplicaReduz(a.inverso(CorpoFinito.redutor)).isUni()))
            	cfMultInverso++;
            
            // Testa comutatividade : A*B = B*A
            if (!a.MultiplicaReduz(b).compara(b.MultiplicaReduz(a)) )
            	cfMultComu++;
            
            // Testa associatividade : (A*B)*C = A*(B*C)
            if (!a.MultiplicaReduz(b.MultiplicaReduz(c)).compara(c.MultiplicaReduz(b.MultiplicaReduz(a))) )
            	cfMultAssoc++;
            
            // Testa destributiva : (A + B)*C = A*C + B*C
            if (!a.soma(b).MultiplicaReduz(c).compara(c.MultiplicaReduz(a).soma(c.MultiplicaReduz(b))) )
            	cfMultDistr++;
            
            //================================-----------------------------===================================
            // Testes de matrizes
            // Inicializa 3 matrizes aleatorias 
            Matriz m1 = new Matriz(4);
            Matriz m2 = new Matriz(4);
            Matriz m3 = new Matriz(4);
            
            // Testes da Soma :
            // Testa Elemento neutro
            if (!m1.soma(Matriz.zero(4, 4, 4)).compara(m1))
                mtSumNeutro++;
            
            // Testa Elemento inverso
            if (!m1.soma(m1).isZero())
                mtSumInverso++;
            
            // Testa comutatividade no caso de matrizes de tamnhos iguais : A + B = B + A
            if (!m1.soma(m2).compara(m2.soma(m1)))
            	mtSumComu++;
            
            // Testa associatividade no caso de matriz de tamanhos iguais : (A+B)+C = A+(B+C)
            if (!m3.soma(m1.soma(m2)).compara(m1.soma(m2.soma(m3))))
            	mtSumAssoc++;
            
            // Testa neutro
            if (!m1.multiplicacao(Matriz.geraIdentidade(4)).compara(m1) || !Matriz.geraIdentidade(4).multiplicacao(m1).compara(m1))
                mtMultNeutro++;
            
            // Testa nulo
            if (!m1.multiplicacao(Matriz.zero(4, 4, 4)).isZero() || !Matriz.zero(4, 4, 4).multiplicacao(m1).isZero())
                mtMultNulo++;
            
            // Testa inverso
            if (m1.inverteMatriz() != null && (!m1.multiplicacao(m1.inverteMatriz()).isIdentidade() || !m1.inverteMatriz().multiplicacao(m1).isIdentidade()))
            	mtMultInverso++;
            
            // Teste distributiva A(B+C) = (AB + AC)
            if (!m1.multiplicacao(m2.soma(m3)).compara(m1.multiplicacao(m2).soma(m1.multiplicacao(m3))))
            	mtMultDistr++;
            
            // Teste Associativo : A*(B*C) = (A*B)*C => considerando tamanhos adequados para as matrizes
            if (!m1.multiplicacao(m2.multiplicacao(m3)).compara(m1.multiplicacao(m2).multiplicacao(m3)))
            	mtMultAssoc++;
        }

        System.out.println ("Erros em CF soma neutro: " + cfSumNeutro);
        System.out.println ("Erros em CF soma inverso: " + cfSumInverso);
        System.out.println ("Erros em CF soma comutativo: " + cfSumComu);
        System.out.println ("Erros em CF soma associativo: " + cfSumAssoc);
        System.out.println ("Erros em CF mult neutro: " + cfMultNeutro);
        System.out.println ("Erros em CF mult nulo: " + cfMultNulo);
        System.out.println ("Erros em CF mult inverso: " + cfMultInverso);
        System.out.println ("Erros em CF mult comutativo: " + cfMultComu);
        System.out.println ("Erros em CF mult associativo: " + cfMultAssoc);
        System.out.println ("Erros em CF mult distributivo: " + cfMultDistr);
        
        System.out.println ("Erros em MT soma neutro: " + mtSumNeutro);
        System.out.println ("Erros em MT soma inverso: " + mtSumInverso);
        System.out.println ("Erros em MT soma comutativo: " + mtSumComu);
        System.out.println ("Erros em MT soma associativo: " + mtSumAssoc);
        System.out.println ("Erros em MT mult neutro: " + mtMultNeutro);
        System.out.println ("Erros em MT mult nulo: " + mtMultNulo);
        System.out.println ("Erros em MT mult inverso: " + mtMultInverso);
        System.out.println ("Erros em MT mult associativo: " + mtMultAssoc);
        System.out.println ("Erros em MT mult distributiva: " + mtMultDistr);
        
        
        
    }
    
    public static void TesteMultiplicacao() {
        // ---------------------------------------------------------------------    
        // Testes da multiplicação :
        byte[] temp = new byte [2];
        CorpoFinito a;
        CorpoFinito b;
        
        // Teste 1 : Elemento neutro e elemento nulo;
        temp[0] = -128;
        temp[1] = 0;
        a = new CorpoFinito(temp);
        System.out.println("Teste do elemento unitario :");
        a.Mostra();
        b = CorpoFinito.uni(2);
        
        a.multiplicacao(a.uni(2)).Mostra();
        a.uni(2).multiplicacao(a).Mostra();
        
        System.out.println();
        
        System.out.println("\nTeste do elemento nulo :");
        
        a.Mostra();
        
        temp[0] = 0;
        b = new CorpoFinito(temp);
        
        b.Mostra();
        
        a.multiplicacao(b).Mostra();
        b.multiplicacao(a).Mostra();
        
        // Teste 2 : 0 x 0 ; 1 x 1; 2 x 2; 5 x 5 ;  A x A ; F x F;
        // 1 x 1 :
        System.out.println("\n1 x 1 : ");
        
        temp[0] = 1;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 1;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 2 x 1 :
        System.out.println("\n2 x 1 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 1;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // -128 x 1 :
        System.out.println("\n-128 x 1 : ");
        
        temp[0] = -128;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 1;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 2 x 3 : 0006
        System.out.println("\n2 x 3 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 3;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        // 2 x 9 : 0012
        System.out.println("\n2 x 9 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 9;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 2 x -128 : 0100
        System.out.println("\n2 x -128 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -128;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 2 x 83 : 00A6
        System.out.println("\n2 x 83 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 83;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 2 x -54 : 0194
        System.out.println("\n2 x -54 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -54;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 7 x 3 : 0009
        System.out.println("\n7 x 3 : ");
        
        temp[0] = 7;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 3;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 7 x 78 : 01EA
        System.out.println("\n7 x 78 : ");
        
        temp[0] = 7;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 78;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 7 x -67 : 0333
        System.out.println("\n7 x -67 : ");
        
        temp[0] = 7;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -67;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 78 x 3 : 00D2
        System.out.println("\n78 x 3 : ");
        
        temp[0] = 78;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 3;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        // 78 x 113 : 1EEE
        System.out.println("\n78 x 3 : ");
        
        temp[0] = 78;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 113;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 78 x -46 : 30FC
        System.out.println("\n78 x -46 : ");
        
        temp[0] = 78;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -46;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // -128 x -128 : 4000
        System.out.println("\n-128 x -128 : ");
        
        temp[0] = -128;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -128;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // -128 x 1 | 0 : 8000
        System.out.println("\n-128 x 1 | 0 : ");
        
        temp[0] = -128;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 0;
        temp[1] = 1;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 45 | -76 x 0 | -87 : 122C94
        System.out.println("\n45 | -76 x 0 | -87 : ");
        
        temp[0] = -76;
        temp[1] = 45;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -87;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // -128 | 0 x 1 | 0 : 00800000
        System.out.println("\n-128 | 0 x 1 | 0 : ");
        
        temp[0] = 0;
        temp[1] = -128;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 0;
        temp[1] = 1;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
        // 55 | 55 x -86 | -86 : 22222222
        System.out.println("\n55 | 55 x -86 | -86 : ");
        
        temp[0] = 85;
        temp[1] = 85;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -86;
        temp[1] = -86;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
        
         // -1 | -1 x -1 | -1 : 55555555
        System.out.println("\n-1 | -1 x -1 | -1 : ");
        
        temp[0] = -1;
        temp[1] = -1;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -1;
        temp[1] = -1;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.multiplicacao(a).Mostra();
        a.multiplicacao(b).Mostra();
        
        System.out.println();
    }
    
    public static void TesteDivisao () {
    	System.out.println ("Teste da divis�o:");
    	CorpoFinito a,b;
    	byte[] temp = new byte [2];
    	
    	System.out.println ("Teste do unitario:");
    	temp[0] = 1;
    	temp[1] = 0;
    	a = new CorpoFinito (temp);
    	temp[0] = 7;
    	temp[1] = 0;
    	b = new CorpoFinito (temp);
    	a.Mostra();
    	b.Mostra();
    	b.divisao(a).Mostra();
    	
    	// 2 / 3 : 0001 / 1
    	// 3 / 2 : 0001 / 1
        System.out.println("\n2 e 3 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 3;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 2 / 9 : 0002 / 0000
        // 9 / 2 : 0001 / 0004
        System.out.println("\n2 x 9 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 9;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 2 / -128 : 0002 / 0
        // -128 / 2 : 0000 / 0400
        System.out.println("\n2 x -128 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -128;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 2 / 83 : 0002 / 0000
        // 83 / 2 : 0001 / 0029
        System.out.println("\n2 x 83 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 83;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 2 / -54 : 0002 / 0000
        // -54 / 2 : 0000 / 0065 
        System.out.println("\n2 x -54 : ");
        
        temp[0] = 2;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -54;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 7 / 3 : 0001 / 0002
        // 3 / 7 : 0003 / 0000
        System.out.println("\n7 x 3 : ");
        
        temp[0] = 7;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 3;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 7 / -67 : 0007 / 0000
        // -67 / 7 : 0000 / 003F 
        System.out.println("\n7 x -67 : ");
        
        temp[0] = 7;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -67;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 78 / 113 : 003F / 0001
        // 113 / 78 : 003F / 0001
        System.out.println("\n78 x 3 : ");
        
        temp[0] = 78;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 113;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // -128 / -128 : 0000 / 0001
        // -128 / -128 : 0000 / 0001
        System.out.println("\n-128 x -128 : ");
        
        temp[0] = -128;
        temp[1] = 0;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -128;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // 45 | -76 / 0 | -87 : 0018 / 004C
        // 0  | -87 / 45| -76 : 00A9 / 0000
        System.out.println("\n45 | -76 x 0 | -87 : ");
        
        temp[0] = -76;
        temp[1] = 45;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = -87;
        temp[1] = 0;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
        // -128 | 0 / 1 | 0 : 0000 / 0080
        // 1 | 0    / -128 | 0 : 0100 / 0000
        System.out.println("\n-128 | 0 x 1 | 0 : ");
        
        temp[0] = 0;
        temp[1] = -128;
        a = new CorpoFinito (temp);
        a.Mostra();
        
        temp[0] = 0;
        temp[1] = 1;
        b = new CorpoFinito (temp);
        b.Mostra();
        
        b.divisao(a).Mostra();
        a.divisao(b).Mostra();
        
    }

    public static void TesteMatriz () {
        CorpoFinito[][] teste1 = new CorpoFinito[3][3];
        CorpoFinito[][] teste2 = new CorpoFinito[3][3];
        byte[] temp = new byte[1];
        temp[0] = 1;
        for (int i = 0; i < teste1.length; i++) {
            for (int j = 0; j < teste1[0].length; j ++) {
                teste1[i][j] = new CorpoFinito(temp);
                teste2[i][j] = CorpoFinito.uni(6);
                temp[0] = (byte) (temp[0] << 1);
            }
            temp[0] = 1;
        }
        temp[0] = -1;
        teste2[2][2] = new CorpoFinito (temp);
        Matriz a,b;
        a = new Matriz(teste1);
        a.Mostra();
        b = new Matriz(teste2);
        b.Mostra();
        a.multiplicacao(b).Mostra();
                
    }

    public static void TesteMultMatriz () {
    	Matriz a,b;
    	a = new Matriz(3);
    	b = new Matriz(3);
    	System.out.println("Matriz A :");
    	a.Mostra();
    	System.out.println("Matriz B :");
    	b.Mostra();
    	
    	System.out.println("Matriz AxB :");
    	a.multiplicacao(b).Mostra();
    	System.out.println("Matriz BxA :");
    	b.multiplicacao(a).Mostra();
    	System.out.println("Matriz BxI :");
    	b.multiplicacao(Matriz.geraIdentidade(3)).Mostra();
    	
    }

    /*
     * Gera um dado numero de Matrizes 4x4 aleatorias e testa a inversão delas, caso seja possivel inverte-la.
     * Ao final mostra o numero de Matrizes que foram invertidas mas não geraram a identidade na multiplicação.
     */
    public static void TesteInversaoMatriz() {
        long ab = 100;
		long count = 0;
		Matriz teste,temp,clone;
		for (long i = ab; i >= - ab; i--) {
			teste = new Matriz(4);
            clone = teste.clone();
			temp = teste.inverteMatriz();
            if (temp != null && (!teste.isIdentidade() || !clone.multiplicacao(temp).isIdentidade() )) {
                count++;
                System.out.println("Matriz inicial : ");
                clone.Mostra();
                System.out.println("Matriz escalonada : ");
                teste.Mostra();
                System.out.println("Matriz inversa : ");
                temp.Mostra();
                System.out.println("Produto que devia dar identidade : ");
                clone.multiplicacao(temp).Mostra();
            }
		}
        System.out.println("Numero de erros : ");
		System.out.println(count);
    }

}
