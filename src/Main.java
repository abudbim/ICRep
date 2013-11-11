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
        MatrizPrimo x = a.GeraRojo();
        x.Mostra();
        
		
        CorpoFinitoPrimo[]  b = a.RojoDiag(new CorpoFinitoPrimo(3), a.linha0);
        

	}
}
