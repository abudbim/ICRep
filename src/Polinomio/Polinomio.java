package Polinomio;

public class Polinomio {
	public int[] coeficientes;
	public int p;
	
	/*
	 * Construtor recebe um vetor int com os coeficientes e retorna um polinomio de mesmo tamanho
	 */
	public Polinomio (int[] Coeficientes){
		this.coeficientes = Coeficientes.clone();
	}
	
	/*
	 * Gera um polinomio nulo do grau indicado
	 */
	public static Polinomio PolinomioNulo (int grau) {
		int[] vetor = new int [grau+1];
		for (int i = vetor.length-1; i >= 0; i--) {
			vetor[i] = 0;
		}
		Polinomio temp = new Polinomio (vetor);
		return temp;
	}
	
	/*
	 * Gera um polinomio unitario (todos os coeficientes 0 exceto o primeiro igual a 1) do grau indicado
	 */
	public static Polinomio PolinomioUnit (int grau) {
		int[] vetor = new int [grau+1];
		vetor[0] = 1;
		for (int i = vetor.length-1; i >= 1; i--) {
			vetor[i] = 0;
		}
		Polinomio temp = new Polinomio (vetor);
		return temp;
	}
	
	/*
	 * Retorna um polinomio que vale a soma do polinomio com b
	 * return a+b
	 */
	public Polinomio Soma (Polinomio b) {
		int[] resp = new int [coeficientes.length];
		for (int i = coeficientes.length-1; i >= 0; i--) {
			resp[i] = coeficientes[i] + b.coeficientes[i];
		}
		return new Polinomio (resp);
	}
	
	/*
	 * Retorna um polinomio que é a multiplicação do polinomio com b
	 * return a*b
	 * TODO ainda nao faz a redução
	 */
	public Polinomio Mult (Polinomio b) {
		int[] resp = new int [coeficientes.length*2];
		for (int i = coeficientes.length-1; i >= 0; i--) {
			resp[i] = 0;
		}
		for (int i = coeficientes.length-1; i >= 0; i--) {
			for (int j = coeficientes.length-1; j >= 0; j--) {
				resp[i+j] = resp[i+j]+coeficientes[i]*b.coeficientes[j];
			}
		}
		return new Polinomio (resp);
	}
	
	/*
	 * Metodo que devolve o grau do polinomio
	 */
	public int Grau () {
		for (int i = coeficientes.length - 1; i >= 0; i-- ) {
			if (coeficientes[i] != 0) {
				return i;
			}
		}
		return 0;
	}
	
	/*
	 * Retorna true se os os polinomios forem iguais
	 */
	public boolean Igual (Polinomio b) {
		boolean flag = true;
		for (int i = coeficientes.length - 1; i >= 0; i-- ) {
			if (coeficientes[i] != b.coeficientes[i]){
				flag = false;
			}
		}
		return flag;
	}
	
	/*
	 * Metodo imprime os coeficientes do polinomio, em ordem
	 */
	public void Mostra() {
		for (int i = coeficientes.length-1; i >= 0; i--) {
			System.out.print (coeficientes[i] + " ");
		}
		System.out.println();
	}
	
}