
import Polinomio.CorpoFinitoPrimo;
import Polinomio.MatrizCentroSim;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abud
 */
public class TesteCentroSim {
    public static void TesteCentroSim(int testes, int tam) {
        MatrizCentroSim a,b,J,c;
        CorpoFinitoPrimo[][] x = new CorpoFinitoPrimo[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (i+j == tam - 1)
                    x[i][j] = CorpoFinitoPrimo.unitario();
                else
                    x[i][j] = CorpoFinitoPrimo.zero();
            }
        }
        
        J = new MatrizCentroSim(x);
        
        int erros = 0;
        int ncs = 0;
        
        for (int i = 0; i < testes; i++) {
            a = new MatrizCentroSim(tam);
            b = new MatrizCentroSim(tam);
            c = new MatrizCentroSim(tam);
            if ( !( a.multiplicacao(J).compara(J.multiplicacao(a)) && b.multiplicacao(J).compara(J.multiplicacao(b)) && c.multiplicacao(J).compara(J.multiplicacao(c)) ) )
                ncs++;
            
            // A + 0 = A
			if (!a.soma(MatrizCentroSim.zero(tam)).compara(a)) {
				erros++;
			}
			// A + B = B + A
			if (!a.soma(b).compara(b.soma(a))) {
				erros++;
			}
			
			// A+(B+C) = C+(A+B)
			if (!a.soma(b.soma(c)).compara(c.soma(b.soma(a))) ) {
				erros++;
			} 
            
            // A + -A = 0
            if (!a.soma(a.inversoAditivo()).compara(MatrizCentroSim.zero(tam))) {
                    erros++;
            } 

            // A - 0 = A
            if (!a.subtracao(MatrizCentroSim.zero(tam)).compara(a)) {
                    erros++;
            }

            // 0 - X = -X
            if (!MatrizCentroSim.zero(tam).subtracao(a).compara(a.inversoAditivo())) {
                    erros++;
            }

            // (B-C) = -(C-B)
            if (!a.subtracao(b).compara(b.subtracao(a).inversoAditivo())) {
                    erros++;
            }

            // A-(B-C) = A-B+C
            if (!a.subtracao(b.subtracao(c)).compara(a.subtracao(b).soma(c))) {
                    erros++;
            } 
            
            // Teste Multiplicação

            // A*I = A
            if (!a.multiplicacao(MatrizCentroSim.geraIdentidade(tam)).compara(a))
                erros++;
            // A*0 = 0
            if (!a.multiplicacao(MatrizCentroSim.zero(tam)).isZero())
                erros++;
            // testa A*(B+C) = A*B + A*C
            if (!a.multiplicacao(b.soma(c)).compara(a.multiplicacao(b).soma(a.multiplicacao(c))))
                erros++;
            // testa C * 1/C  = 1
            if (c.inverteMatriz() != null && !c.multiplicacao(c.inverteMatriz()).isIdentidade())
                erros++;

            // TEste sistema linear
            if (c.solucSistLinear(b) != null && !b.compara(c.multiplicacao(c.solucSistLinear(b))))
                erros++; 
        
        }
        
        System.out.println("Erros no testes de geração de centro simetricas : "+ncs);
        System.out.println("Erros nas operações com matrizes : "+erros);
    }
}
