package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
 		if (array.length > leftIndex) {
 			int max = array[leftIndex];
 		 		
			Integer[] resposta = new Integer[rightIndex - leftIndex + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > max)
					max = array[i];
			}
			
			int[] count = new int[max + 1];
			
			// conta qtos de cada elemento aparecem
			for (int i = leftIndex; i <= rightIndex; i++) 
				count[array[i]]++;
			
			// array de soma
			for (int i = 1; i <= max; i++) 
				 count[i] += count[i - 1];
	 		
			// ordena
			for (int i = rightIndex; i >= leftIndex; i--) {
				count[array[i]]--;
				resposta[count[array[i]]] = array[i];
			}
			
			// reatribui
			for (int i = leftIndex; i <= rightIndex; i++) 
				array[i] = resposta[i - leftIndex];
 	
 		}
	}
	
}
