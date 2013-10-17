import Polinomio.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
        MatrizCentroSim a,b;
        a = new MatrizCentroSim(7);
        a.Mostra();
        a = new MatrizCentroSim(3);
        a.Mostra();
        b = new MatrizCentroSim(3);
        b.Mostra();
        a.multiplicacao(b).Mostra();
        
        TesteCentroSim.TesteCentroSim(100000, 3);
	}
}
