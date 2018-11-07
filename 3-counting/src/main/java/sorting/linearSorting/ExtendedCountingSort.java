package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > leftIndex) {
			int min = array[leftIndex];
			int max = array[leftIndex];
			Integer[] resposta = new Integer[rightIndex - leftIndex + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] < min)
					min = array[i];
				if (array[i] > max)
					max = array[i];
			}
			
			int[] count = new int[max - min + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) 
				count[array[i] - min]++;
			
			for (int i = 1; i <= (max - min); i++)
				count[i] += count[i - 1];
			
			for (int i = rightIndex; i >= leftIndex; i--) {
				count[array[i] - min]--;
				resposta[count[array[i] - min]] = array[i];
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) 
				array[i] = resposta[i - leftIndex];
			
		}
	}

}
