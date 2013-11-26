import Polinomio.*;
import Mqsign.*;


public class Main {

	/**
	 * @param args
	 */       
        
	public static void main(String[] args) {
            AssinaturaMQ a = new AssinaturaMQ(31, 5, 2, 3, 2);
            a.mostraChaves();
            CorpoFinitoPrimo[] teste = new CorpoFinitoPrimo[2];
            teste[0] = new CorpoFinitoPrimo(5);
            teste[1] = new CorpoFinitoPrimo(5);
            System.out.println(a.UOVCheck(teste, a.UOVSign(teste)));
            //TesteCentroSim.TesteCentroSim(100000, 15); 
	}
}
