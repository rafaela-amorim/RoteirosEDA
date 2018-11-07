package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	private boolean verificaIndices(T[] array, int leftIndex, int rightIndex) {
		return (rightIndex < array.length && leftIndex <= rightIndex && leftIndex >= 0 && array.length > 0);
	}
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verificaIndices(array, leftIndex, rightIndex)) {
			int gap = rightIndex - leftIndex + 1;
			boolean swaps = true;
			int i;
			
			while (gap > 1 || swaps) {
				gap /= 1.25;
				if (gap < 1) gap = 1;
				swaps = false;
				i = leftIndex;
				
				while (i + gap <= rightIndex) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						swaps = true;
					}
						
					i++;
				}
			}
		}
		
	}
}
