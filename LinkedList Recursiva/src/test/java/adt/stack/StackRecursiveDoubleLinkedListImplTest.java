package adt.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackRecursiveDoubleLinkedListImplTest {
	
	StackRecursiveDoubleLinkedListImpl<Integer> pila1;
	StackRecursiveDoubleLinkedListImpl<Integer> pila2;
	
	@Before
	public void setUp() throws StackOverflowException{
		pila1 = new StackRecursiveDoubleLinkedListImpl<>(4);
		pila2 = new StackRecursiveDoubleLinkedListImpl<>(2);
		
		// pilha2 cheia
		pila2.push(1);
		pila2.push(2);
		
		// pilha1 nao cheia
		pila1.push(9);
		pila1.push(8);
		
	}

	@Test
	public void testPush() throws StackOverflowException {
		assertEquals(8, (int) pila1.top());
		pila1.push(10);
		assertEquals(10, (int) pila1.top());
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPush2() throws StackOverflowException {
		assertTrue(pila2.isFull());
		// exceção
		pila2.push(-1);
	}

	@Test
	public void testPop() throws StackUnderflowException {
		assertEquals(8, (int) pila1.top());
		pila1.pop();
		assertEquals(9, (int) pila1.top());
		pila1.pop();
		assertEquals(null, pila1.top());
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPop2() throws StackUnderflowException {
		assertEquals(8, (int) pila1.top());
		pila1.pop();
		assertEquals(9, (int) pila1.top());
		pila1.pop();
		assertNull(pila1.top());
		
		// exceção
		pila1.pop();
	}
	
	@Test
	public void testTop() throws StackUnderflowException {
		assertEquals(2, (int) pila2.top());
		pila2.pop();
		pila2.pop();
		assertNull(pila2.top());
	}

	@Test
	public void testIsEmpty() throws StackUnderflowException {
		assertFalse(pila2.isEmpty());
		pila2.pop();
		pila2.pop();
		assertTrue(pila2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(pila1.isFull());
		assertTrue(pila2.isFull());
	}


}
