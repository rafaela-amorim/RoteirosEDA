package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			
			int middle = (rightIndex + leftIndex) / 2;
			
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
			
			merge(array, leftIndex, rightIndex);
		}
	}
	
	private void merge(T[] array, int leftIndex, int rightIndex) {
		int middle = (rightIndex + leftIndex) / 2; 

		int k = 0;
		int j = 0;

		T[] left = (T[]) new Comparable[array.length];
		T[] right = (T[]) new Comparable[array.length];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (i <= middle)
				left[k++] = array[i];
			else
				right[j++] = array[i];
		}
		
		k = 0;
		j = 0;
		int i = leftIndex;
		
		while (left[k] != null && right[j] != null) {
			if (left[k].compareTo(right[j]) <= 0)
				array[i++] = left[k++];
			else
				array[i++] = right[j++];
		}
		
		while (left[k] != null)		// verifica se ainda ha elementos disponiveis no array left
			array[i++] = left[k++];	
		
		while (right[j] != null)	// verifica se ainda ha elementos disponiveis no array right
			array[i++] = right[j++];
		
	}
	
}
