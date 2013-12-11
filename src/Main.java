import Polinomio.*;
import Mqsign.*;


public class Main {

	/**
	 * @param args
	 */       
        
	public static void main(String[] args) {
            AssinaturaMQ c = new AssinaturaMQ(31, 5, 2, 3, 2);
            c.mostraChaves();
            CorpoFinitoPrimo[] teste = new CorpoFinitoPrimo[2];
            teste[0] = new CorpoFinitoPrimo(30);
            teste[1] = new CorpoFinitoPrimo(0);
            System.out.println(c.UOVCheck(teste, c.UOVSign(teste)));
            //TesteUovMQ.TestaChaves(1000);
            //TesteCentroSim.TesteCentroSim(100000, 15);
	}
}
