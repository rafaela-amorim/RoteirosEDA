package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		T key;
		int j;
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			key = array[i];
			j = i - 1;
			
			while (j >= leftIndex && (key.compareTo(array[j])) < 0) {
				array[j + 1] = array[j];
				j--;
			}
			
			if (j != i - 1)
				array[j + 1] = key;
		}
	}
}
