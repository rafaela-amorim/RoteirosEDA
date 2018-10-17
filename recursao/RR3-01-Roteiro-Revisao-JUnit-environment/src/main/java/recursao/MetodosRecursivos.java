package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		return result;
	}
	
	public long calcularFatorial(int n) {
		long res = 1;
		if (n == 0 || n == 1) {
			System.out.println(n + "! = 1");
		} else {
			res = n * calcularFatorial(n - 1); 
			System.out.println(n + "! = " + res);
		}
		return res;
	}
	
	public static int calcularFibonacci(int n) {
		int ans = 1;
		if (n == 0 || n == 1) {
		} else {
			ans = calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
		}
		return ans;
	}
	
	// depende do metodo privado
	public static int countNotNull(Object[] array) {
		return countNotNull(array, array.length - 1);
	}
	
	private static int countNotNull(Object[] array, int indice) {
		int ans = 0;
		
		if (indice >= 0) {
			if (array[indice] != null) 
				ans++;
			ans += countNotNull(array, indice - 1);
		}
		
		return ans;
	}
	// fim de countNotNull
	
	public static long potenciaDe2(int expoente) {
		long result = 1;
		
		if (expoente == 1) {
			result *= 2;
		} else {
			result *= potenciaDe2(expoente - 1) * 2;
		}
		
		return result;
	}

	public static double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		
		if (n == 1) {
			result += termoInicial;
		} else {
			result += razao + progressaoAritmetica(termoInicial, razao, n - 1);
		}
		
		return result;
	}

	public static double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		
		if (n == 1) {
			result *= termoInicial;
		} else {
			result *= razao * progressaoGeometrica(termoInicial, razao, n - 1);
		}
		
		return result;
	}
	
}
