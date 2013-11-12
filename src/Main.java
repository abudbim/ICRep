import Polinomio.*;
import Mqsign.*;


public class Main {

	/**
	 * @param args
	 */       
        
	public static void main(String[] args) {
<<<<<<< Updated upstream
		
        MatrizRojo a = new MatrizRojo (5);
        a.MostraLinha();
        a.GeraToeplitz().Mostra();
        a.GeraHankel().Mostra();
        MatrizPrimo x = a.GeraRojo();
        x.Mostra();
        
		
        CorpoFinitoPrimo[]  b = a.RojoDiag(new CorpoFinitoPrimo(3), a.linha0);
        

=======
            AssinaturaMQ a = new AssinaturaMQ(31, 5, 1, 1, 2, 4);
            a.mostraChaves();
            //TesteCentroSim.TesteCentroSim(100000, 15);        
>>>>>>> Stashed changes
	}
}
