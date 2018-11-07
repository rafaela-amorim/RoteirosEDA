package adt.hashtable.closed;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class MeuTeste {
	
	HashtableClosedAddressImpl<Integer> t1;
	HashtableClosedAddressImpl<Integer> t2;
	final int TAMANHO = 100;
	
	
	@Before
	public void inicia() {
		t1 = new HashtableClosedAddressImpl<>(TAMANHO, HashFunctionClosedAddressMethod.MULTIPLICATION);
		t2 = new HashtableClosedAddressImpl<>(TAMANHO, HashFunctionClosedAddressMethod.DIVISION);
		
		int numero = 100;
		
		while (numero < 400) {
			t1.insert(numero);
			t2.insert(numero);
			numero += 5;
		}
	}

	@Test
	public void testInsert() {
		assertEquals(60, t1.size());
		t1.insert(null);
		assertEquals(60, t1.size());
		
		t1.insert(1214232);
		assertEquals(61, t1.size());
		assertEquals((Integer) 1214232, t1.search(1214232));
		
		assertEquals(60, t2.size());
		t1.insert(null);
		assertEquals(60, t2.size());
		
		t2.insert(1214232);
		assertEquals(61, t2.size());
		assertEquals((Integer) 1214232, t2.search(1214232));
	}

	@Test
	public void testRemove() {
		assertEquals((Integer) 345, t1.search(345));
		t1.remove(345);
		assertNull(t1.search(345));
		
		t1.remove(null);
		
		t1.remove(345);
		
		assertEquals((Integer) 345, t2.search(345));
		t2.remove(345);
		assertNull(t2.search(345));
		
		t2.remove(null);
		
		t2.remove(345);
	}

	@Test
	public void testSearch() {
		assertEquals((Integer) 200, t1.search(200));
		assertNull(t1.search(13));
		
		t1.remove(200);
		assertNull(t1.search(200));
		
		assertEquals((Integer) 200, t2.search(200));
		assertNull(t2.search(13));
		
		t2.remove(200);
		assertNull(t2.search(200));
		
	}

	@Test
	public void testIndexOf() {
		assertFalse(-1 == t1.indexOf(200));
		assertEquals(-1, t1.indexOf(13));
		
		assertFalse(-1 == t2.indexOf(200));
		assertEquals(-1, t2.indexOf(13));
	}

}
