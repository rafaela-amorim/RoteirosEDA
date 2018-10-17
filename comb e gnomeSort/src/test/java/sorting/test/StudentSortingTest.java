package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.variationsOfBubblesort.CombSort;
import sorting.variationsOfBubblesort.GnomeSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		this.implementation = new CombSort<>();
//		this.implementation = new GnomeSort<>();
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}
	
	public void genericTest01(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] copia = array.clone();
		Arrays.sort(copia, leftIndex, rightIndex + 1);
		this.implementation.sort(array, leftIndex, rightIndex);
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (!array[i].equals(copia[i]))
				Assert.fail();
		}
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}
	
	@Test
	public void testSort06() {
		Integer[] array = new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36, 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 };
		genericTest01(array, 4, 15);
	}
	
	@Test
	public void testSort07() {
		Integer[] array = new Integer[] {6, 6, 6, 6, 6, 6, 4, 9, 3, 4, 0, 5, 1, 4, 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31, 30, 28, 7, 29, 11 };
		genericTest01(array, 15, array.length - 1);
	}
	
	@Test
	public void testSort08() {
		Integer[] array = new Integer[] {9};
		genericTest(array);
	}
	
	@Test
	public void testSort09() {
		Integer[] array = new Integer[] {6, -6, -6, -6, 6, -6, -4, 9, 3, -4, 0, -5, 1, 4, -30, -28, 7, -29, 11, -26, 4, -22, 23,
				31, 30, 28, 7, 29, 11 };
		genericTest(array);
	}
	
	@Test
	public void testSort10() {
		Integer[] array = new Integer[] { 6, 41, 32, 7, -26, -4, -37, -49,
				11, 18, 36, -30, -28, 7, 29, -11, 26, 4, 22, 23,
				31 };
		genericTest01(array, 4, 15);
	}
	
	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
}