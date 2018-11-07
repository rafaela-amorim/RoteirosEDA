package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RecursiveDoubleLinkedListImplTest extends RecursiveSingleLinkedListImplTest {
	
	RecursiveDoubleLinkedListImpl<Integer> l1;
	RecursiveDoubleLinkedListImpl<Integer> l2;
	
	@Before
	public void inicializa() {
		l1 = new RecursiveDoubleLinkedListImpl<>();
		l2 = new RecursiveDoubleLinkedListImpl<>();
		
		l1.insert(1);
		l1.insert(2);
		l1.insert(3);
		// l2 vazia
	}
	
	@Test
	public void testInsertFirst() {
		assertEquals(1, (int) l1.getData());
		l1.insertFirst(9);
		assertEquals(9, (int) l1.getData());
		l1.insertFirst(null);
		assertEquals(9, (int) l1.getData());
		
		int i = 0;
		int tam = l1.size();
		Integer[] array = new Integer[] {9,1,2,3};
		assertArrayEquals(array, l1.toArray());		
	}

	@Test
	public void testRemoveFirst() {
		assertEquals(1, (int) l1.getData());
		l1.removeFirst();
		assertEquals(2, (int) l1.getData());
		l1.removeFirst();
		l1.removeFirst();
		assertTrue(l1.isEmpty());
		l1.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		assertEquals(3, l1.size());
		assertEquals(1, (int) l1.getData());
		l1.removeLast();
		assertEquals(1, (int) l1.getData());
		assertEquals(2, l1.size());
	}
	
	@Test
	public void percorrePFrente() {
		l2.insert(4);
		l2.insert(5);
		l2.insert(6);
		
		l2.insertFirst(3);
		l2.insertFirst(2);
		l2.insertFirst(1);
		
		Integer[] array = new Integer[] {1,2,3,4,5,6};
 		int i = 0;
 		int tam = l2.size();
		
		while (i < tam) {
			assertEquals(array[i++], l2.getData());
			l2 = (RecursiveDoubleLinkedListImpl<Integer>) l2.getNext();
		}
 		
	}
	
	@Test
	public void percorrePTras() {
		l2.insert(4);
		l2.insert(5);
		l2.insert(6);
		
		l2.insertFirst(3);
		l2.insertFirst(2);
		l2.insertFirst(1);
		
		int i = 0;
		
		while (!l2.isEmpty()) {	// pega o ultimo elemento
			l2 = (RecursiveDoubleLinkedListImpl<Integer>) l2.getNext();
			i++;
		}
		
		i = 0;
		l2 = l2.getPrevious(); 	// o while ultrapassou
		Integer[] array = new Integer[] {6,5,4,3,2,1};

		while (!l2.isEmpty()) {
			assertEquals(array[i++], l2.getData());
			l2 = l2.getPrevious();
		}
		
	}
	
	@Test
	public void testInverte() {
		l1.insert(4);
		l1.inverte();
		
		while (!l1.isEmpty()) {
			System.out.println(l1.getData());
			l1 = (RecursiveDoubleLinkedListImpl<Integer>) l1.getNext();
		}
			
		
	}
}
