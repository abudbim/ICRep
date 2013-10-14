import Polinomio.*;


public class TestePrimo {

	public static void TestaReducao (){
		CorpoFinitoPrimo x;
		int j = 123;
		for (int i = -258; i < 10000; i++) {
			x = CorpoFinitoPrimo.reducao (i);
			//System.out.print (i + " ");
			//x.Mostra();
			if (j != x.valor) {
				System.out.println ("Erro na reducao!! Do numero: " + i);
			}
			j++;
			if (j > CorpoFinitoPrimo.p - 1) {
				j = 0;
			}
		}
        System.out.println ("Fim do teste da reducao");
	}
	
	public static void TestaSoma (int testes) {
		System.out.println ("Teste da soma: ");
		CorpoFinitoPrimo a ,b ,c;
		int erroCom = 0;
		int erroAss = 0;
		int erroNeu = 0;
		for (int i  = 0; i < testes; i++) {
			a = new CorpoFinitoPrimo();
			b = new CorpoFinitoPrimo();
			c = new CorpoFinitoPrimo();
			
			// A + 0 = A
			if (!a.somaR(CorpoFinitoPrimo.zero()).isEqual(a)) {
				erroNeu++;
			}
			// A + B = B + A
			if (!a.somaR(b).isEqual(b.somaR(a))) {
				erroCom++;
			}
			
			// A+(B+C) = C+(A+B)
			if (!a.somaR(b.somaR(c)).isEqual(c.somaR(b.somaR(a))) ) {
				erroAss++;
			}		
		}
		
		System.out.println("Erros no elemento nulo : " + erroNeu);
		System.out.println("Erros na comuta��o : " + erroCom);
		System.out.println("Erros na associatividade : " + erroAss);
		
	}
        
        public static void TesteSub (int testes) {
            int inversoS = 0, neutro = 0, inversoN = 0, comu = 0, dist = 0;
            CorpoFinitoPrimo a,b,c;
            
            System.out.println("\nTeste da Subtração:\n");
            
            for (int i = 0; i < testes; i++) {
                a = new CorpoFinitoPrimo();
                b = new CorpoFinitoPrimo();
                c = new CorpoFinitoPrimo();
                
                // A + -A = 0
                if (!a.somaR(a.inversoAditivo()).isEqual(CorpoFinitoPrimo.zero())) {
                        inversoS++;
                }
                
                // A - 0 = A
                if (!a.subtraiR(CorpoFinitoPrimo.zero()).isEqual(a)) {
                        neutro++;
                }
                
                // 0 - X = -X
                if (!CorpoFinitoPrimo.zero().subtraiR(a).isEqual(a.inversoAditivo())) {
                        inversoN++;
                }
                
                // (B-C) = -(C-B)
                if (!a.subtraiR(b).isEqual(b.subtraiR(a).inversoAditivo())) {
                        comu++;
                }

                // A-(B-C) = A-B+C
                if (!a.subtraiR(b.subtraiR(c)).isEqual(a.subtraiR(b).somaR(c))) {
                        dist++;
                }
            }
            
            System.out.println("Erros no elemento neutro : " + neutro);
            System.out.println("Erros na comutação : " + comu);
            System.out.println("Erros na Inversao A+-A : " + inversoS);
            System.out.println("Erros na Inverso 0-A: " + inversoN);
            System.out.println("Erros na Distributiva: " + dist);
            
        }
	
	public static void TestaMult (int testes) {
		System.out.println ("\nTeste da multiplicacao: \n");
		CorpoFinitoPrimo a ,b ,c;
		int erroCom = 0;
		int erroAss = 0;
		int erroDist = 0;
		int erroNeu = 0;
		int erroInv = 0;
                int erroDistS = 0;
		for (int i  = 0; i < testes; i++) {
			a = new CorpoFinitoPrimo();
			b = new CorpoFinitoPrimo();
			c = new CorpoFinitoPrimo();
			
			// A*1 = A
			if (!a.multR(CorpoFinitoPrimo.unitario()).isEqual(a)) {
				erroNeu++;
			}
			// A * B = B * A
			if (!a.multR(b).isEqual(b.multR(a))) {
				erroCom++;
			}
			
			// A*(B*C) = C*(A*B)
			if (!a.multR(b.multR(c)).isEqual(c.multR(b.multR(a))) ) {
				erroAss++;
			}
			
			//A*(B+C) = A*B + A*C
			if (!a.multR(b.somaR(c)).isEqual((a.multR(b)).somaR(a.multR(c)))) {
				erroDist++;
			}
                        
                        //A*(B-C) = A*B - A*C
			if (!a.multR(b.subtraiR(c)).isEqual((a.multR(b)).subtraiR(a.multR(c)))) {
				erroDistS++;
			}
			
			//A*(1/A) = 1
			if (!a.isZero()) {
				if (!(a.inverso()).multR(a).isEqual(CorpoFinitoPrimo.unitario()) ) {
					erroInv++;
				}
			}
		}
		
		System.out.println("Erros no elemento nulo : " + erroNeu);
		System.out.println("Erros na comuta��o : " + erroCom);
		System.out.println("Erros na associatividade : " + erroAss);
		System.out.println("Erros na distributiva : " + erroDist);
                System.out.println("Erros na distributiva com subtracao : " + erroDistS);
		System.out.println("Erros na inversao : " + erroInv);
		
		
	}
        
        private static boolean comparaCoef(int coef, MatrizPrimo m) {
            for (int i = 0; i < m.coeficientes.length; i++) {
                for (int j = 0; j < m.coeficientes[0].length; j++) {
                    if (m.coeficientes[i][j].valor != coef)
                        return false;
                }
            }
            return true;
        }
        
        public static void TestaMatrizPrimo (int testes) {
            // TODO
            
            /*Parametros para testes de propriedades da soma/sub das matrizes*/
            int SumDist = 0, SubDist = 0, SumAssoc = 0, SumNeutro = 0,
                    SubNeutro = 0, SubInv = 0;
            /*---------------------------------------------------*/
            
            /*Parametros para testes de propriedades da multiplicacao das matrizes*/
            int MultNeutro = 0, MultNulo = 0, MultDist = 0,MultAssoc = 0,inversoMult = 0;
            /*---------------------------------------------------*/
            int SolLin = 0;
            int tam = 4;
            MatrizPrimo a1,b1,c1;
            a1 = new MatrizPrimo(tam);
            b1 = new MatrizPrimo(tam);
            c1 = new MatrizPrimo(tam);
            
            
            
            // Preenche duas matrizes com os coeficientes especificados para
            // obter um resultado previamente calculado
            CorpoFinitoPrimo[][] a = new CorpoFinitoPrimo[2][2];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    a[i][j] = new CorpoFinitoPrimo(1);
                }
            }
            CorpoFinitoPrimo[][] b = new CorpoFinitoPrimo[2][2];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    b[i][j] = new CorpoFinitoPrimo(2);
                }
            }
            a1 = new MatrizPrimo(a);
            b1 = new MatrizPrimo(b);
            
            for (int i = 0; i < testes; i++) { 
                a1 = new MatrizPrimo(3);
                b1 = new MatrizPrimo(3);
                c1 = new MatrizPrimo(3);
                // Testes Soma

                // A+0 = A
                if (!a1.soma(MatrizPrimo.zero(3, 3)).compara(a1))
                    SumNeutro++;
                // A+B = B+A
                if (!a1.soma(b1).compara(b1.soma(a1)))
                    SumAssoc++;
                //A+(B+C) = (A+B)+C
                if (!a1.soma(b1.soma(c1)).compara(a1.soma(b1).soma(c1)))
                    SumDist++;
                // A-0 = A
                if (!a1.subtracao(MatrizPrimo.zero(3, 3)).compara(a1))
                    SubNeutro++;
                // A+B = B+A
                if (!a1.soma(b1).compara(b1.soma(a1)))
                    SumAssoc++;
                //A-(B+C) = A-B-C
                if (!a1.subtracao(b1.soma(c1)).compara(a1.subtracao(b1).subtracao(c1)))
                    SubDist++;
                // A-A = 0
                if (!a1.subtracao(a1).isZero())
                    SubInv++;

               // Teste Multiplicação

                // A*I = A
                if (!a1.multiplicacao(MatrizPrimo.geraIdentidade(3)).compara(a1))
                    MultNeutro++;
                // A*0 = 0
                if (!a1.multiplicacao(MatrizPrimo.zero(3, 3)).isZero())
                    MultNulo++;
                // testa A*(B+C) = A*B + A*C
                if (!a1.multiplicacao(b1.soma(c1)).compara(a1.multiplicacao(b1).soma(a1.multiplicacao(c1))))
                    MultDist++;
                // testa C * 1/C  = 1
                if (c1.inverteMatriz() != null && !c1.multiplicacao(c1.inverteMatriz()).isIdentidade())
                    inversoMult++;
                
                // TEste sistema linear
                if (c1.solucSistLinear(b1) != null && !b1.compara(c1.multiplicacao(c1.solucSistLinear(b1))))
                    SolLin++;
            }
            
            System.out.println(SumAssoc+" "+SumDist+" "+SumNeutro+" "+SubInv+" "+SubDist+" "+SubNeutro+" "
                    +MultNeutro+" "+MultNulo+" "+inversoMult+" "+MultDist+" "+SolLin);

        }
	
}
