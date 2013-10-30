import Polinomio.*;
import Mqsign.*;


public class Main {

	/**
	 * @param args
	 */       
        
	public static void main(String[] args) {
        MatrizRojo a = new MatrizRojo (5);
        a.MostraLinha();
        a.GeraToeplitz().Mostra();
        a.GeraHankel().Mostra();
        a.GeraRojo().Mostra();
	}
}
