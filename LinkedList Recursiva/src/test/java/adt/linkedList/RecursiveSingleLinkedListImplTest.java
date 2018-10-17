package adt.linkedList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class RecursiveSingleLinkedListImplTest {
	
	RecursiveSingleLinkedListImpl<Integer> l1;
	RecursiveSingleLinkedListImpl<Integer> l2;
	
	@Before
	public void inicia() {
		l1 = new RecursiveSingleLinkedListImpl<>();
		l2 = new RecursiveSingleLinkedListImpl<>();
		
		l1.insert(1);
		l1.insert(2);
		l1.insert(3);
		// l2 esta vazio
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(l2.isEmpty());
		assertFalse(l1.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(3, l1.size());
		assertEquals(0, l2.size());
		l1.remove(1);
		l1.remove(2);
		assertEquals(1, l1.size());
	}

	@Test
	public void testSearch() {
		assertEquals(2, (int) l1.search(2));
		assertNull(l1.search(28));
		assertNull(l2.search(4));
		assertEquals(1, (int) l1.search(1));
		assertEquals(3, (int) l1.search(3));
	}

	@Test
	public void testInsert() {
		assertTrue(l2.isEmpty());
		
		l2.insert(5);
		assertFalse(l2.isEmpty());
		
		l2.insert(6);
		l2.insert(7);
		l2.insert(6);
		l2.insert(9);
		l2.insert(10);
		
		int i = 0;
		int tam = l2.size();
		Integer[] teste = new Integer[] {5,6,7,6,9,10};
		
		while (i < tam) {
			assertEquals(teste[i], l2.getData());
			l2 = l2.getNext();
			i++;
		}
	}

	@Test
	public void testRemove() {
		l2.insert(5);
		l2.insert(6);
		l2.insert(7);
		l2.insert(6);
		l2.insert(9);
		l2.insert(10);
		
		int i = 0;
		int tam = l2.size();
		Integer[] teste = new Integer[] {5,6,7,6,9,10};
		
		while (tam > 0) {
			assertEquals(tam--, l2.size());
			l2.remove(teste[i]);
			i++;
		}
		assertTrue(l2.isEmpty());
	}

	@Test
	public void testToArray() {
		l2.insert(5);
		l2.insert(6);
		l2.insert(7);
		l2.insert(6);
		l2.insert(9);
		l2.insert(10);
		l2.insert(11);
		
		Integer[] array = new Integer[] {5,6,7,6,9,10,11};
		assertArrayEquals(array, l2.toArray());
	}
	
	@Test
	public void testIndexOf() {
		assertEquals(0, l1.indexOf(1));
		assertEquals(1, l1.indexOf(2));
		assertEquals(2, l1.indexOf(3));
	}
}
