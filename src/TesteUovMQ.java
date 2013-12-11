import Mqsign.AssinaturaMQ;
import Polinomio.*;


public class TesteUovMQ {
	public static void TestaChaves (int testes) {
		int erros = 0;
		AssinaturaMQ a;
		for (int j = 0; j < testes; j++) {
			a = new AssinaturaMQ(31, 5, 2, 3, 2);
			for (int i = 0; i < a.getChavePub().length; i++) {
				if (!a.getChavePriv()[0].inverteMatriz().multiplicacao(a.getChavePub()[i]).multiplicacao(
						a.getChavePriv()[1].inverteMatriz()).compara(a.getChavePriv()[2+i]))
					erros++;
					
			}
		}
		System.out.println("Teste da chaves");
		System.out.println("Numero de erros : " + erros);
	}
}
