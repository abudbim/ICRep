import Polinomio.*;
import Mqsign.*;


public class Main {

	/**
	 * @param args
	 */       
        
	public static void main(String[] args) {
            AssinaturaMQ a = new AssinaturaMQ(31, 5, 3, 2, 3, 2);
            a.mostraChaves();
            a.UOVSign("Huehihihihi");
            //TesteCentroSim.TesteCentroSim(100000, 15); 
	}
}
