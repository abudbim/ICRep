
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
        int erros = 0;
        MatrizCentroSim a,b;
        CorpoFinitoPrimo[][] x = new CorpoFinitoPrimo[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (i+j == tam - 1)
                    x[i][j] = CorpoFinitoPrimo.unitario();
                else
                    x[i][j] = CorpoFinitoPrimo.zero();
            }
        }
        
        b = new MatrizCentroSim(x);
        
        for (int i = 0; i < testes; i++) {
            a = new MatrizCentroSim(tam);

            if (!a.multiplicacao(b).compara(b.multiplicacao(a)))
                erros++;
        
        }
        
        System.out.println("Erros no testes de geração de centro simetricas : "+erros);
    }
}
