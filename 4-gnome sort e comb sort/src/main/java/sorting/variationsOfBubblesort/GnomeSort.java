package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	private boolean verificaIndices(T[] array, int leftIndex, int rightIndex) {
		return (rightIndex < array.length && leftIndex <= rightIndex && leftIndex >= 0 && array.length > 0);
	}
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verificaIndices(array, leftIndex, rightIndex)) {
			int pivo = leftIndex + 1;
			
			while (pivo <= rightIndex) {
				if (array[pivo].compareTo(array[pivo - 1]) >= 0)
					pivo++;
				else {
					Util.swap(array, pivo, pivo - 1);
					if (pivo > leftIndex + 1)
						pivo--;
					else
						pivo++;
				}
			}
		}
		
	}
}
