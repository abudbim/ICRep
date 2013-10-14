import Polinomio.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//byte [] temp = new byte[2];
		//temp[0] = 17;
		//temp[1] = 2;
		// Define o polinomio redutor (x^9 + x^4 + 1)
		//CorpoFinito.defineP(new CorpoFinito(temp));
        
        /*
         * Testa propriedades de soma e multiplicação
        Teste.TestePropriedades();
        */
        
        
        //Matriz.geraIdentidade(5).Mostra();
        //Teste.TesteMatriz();
        //Teste.TesteMultMatriz();
        //Teste.TesteInversaoMatriz();
        //Teste.TestePropriedades(10000);
		
		System.out.println ("Comeco");
                CorpoFinitoPrimo.p = 31;
                CorpoFinitoPrimo.m = 5;
                TestePrimo.TestaMatrizPrimo(1000);
                //TestePrimo.TestaMatrizPrimo();
		//TestePrimo.TestaReducao();
		//TestePrimo.TestaSoma(1000);
                //TestePrimo.TesteSub(1000);
		//TestePrimo.TestaMult(1000);
	}
}
