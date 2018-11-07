package meusTestes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class StackImplTest {
	
	Stack<Integer> pilha1;
	Stack<Integer> pilha2;
	Stack<Integer> pilha3;
	
	@Before
	public void inicia() throws StackOverflowException {
		pilha1 = new StackImpl<>(5);
		pilha2 = new StackImpl<>(3);
		pilha3 = new StackImpl<>(2);
		
		popula();
	}
	
	private void popula() throws StackOverflowException {
		pilha1.push(6);		// pilha1 tam 5, não cheia
		pilha1.push(5);
		pilha1.push(4);
		
		// pilha2 vazia
		
		pilha3.push(1);		// pilha3 tam 2, cheia
		pilha3.push(2);
		
	}
	
	@Test
	public void testPush() throws StackOverflowException {
		assertTrue(pilha2.isEmpty());
		pilha2.push(10);
		assertEquals((Integer) 10, pilha2.top());
		assertFalse(pilha2.isEmpty());
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPushComExcecao() throws StackOverflowException {
		assertTrue(pilha3.isFull());
		pilha3.push(0);
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPushComExcecao2() throws StackOverflowException {
		pilha1.push(2);
		pilha1.push(2);
		assertTrue(pilha1.isFull());
		pilha1.push(3);
	}

	@Test
	public void testPop() throws StackUnderflowException {
		assertFalse(pilha3.isEmpty());
		assertEquals((Integer) 2, pilha3.pop());
		assertEquals((Integer) 1, pilha3.pop());
		assertTrue(pilha3.isEmpty());
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPop1() throws StackUnderflowException {
		assertFalse(pilha1.isEmpty());
		assertEquals((Integer) 4, pilha1.pop());
		assertEquals((Integer) 5, pilha1.pop());
		assertFalse(pilha1.isEmpty());
		assertEquals((Integer) 6, pilha1.pop());
		assertTrue(pilha1.isEmpty());
		
		// lança erro:
		pilha1.pop();
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPop2() throws StackUnderflowException {
		assertFalse(pilha3.isEmpty());
		assertEquals((Integer) 2, pilha3.pop());
		assertEquals((Integer) 1, pilha3.pop());
		assertTrue(pilha3.isEmpty());
		
		// lança erro:
		pilha3.pop();
	}
	
	@Test
	public void testTop() throws StackOverflowException {
		assertNull(pilha2.top());
		assertEquals((Integer) 2, pilha3.top());
		assertEquals((Integer) 4, pilha1.top());
	}
	
	@Test
	public void testTop1() throws StackOverflowException, StackUnderflowException {
		pilha1.push(11);
		assertEquals((Integer) 11, pilha1.top());
		
		assertEquals((Integer) 2, pilha3.top());
		
		assertEquals((Integer) 2, pilha3.pop());
		assertEquals((Integer) 1, pilha3.top());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(pilha2.isEmpty());
		assertFalse(pilha3.isEmpty());
		assertFalse(pilha1.isEmpty());
	}
	
	@Test
	public void testIsEmpty1() throws StackOverflowException {
		assertTrue(pilha2.isEmpty());
		pilha2.push(9);
		assertFalse(pilha2.isEmpty());
	}
	
	@Test
	public void testIsEmpty2() throws StackUnderflowException {
		assertFalse(pilha3.isEmpty());
		pilha3.pop();
		pilha3.pop();
		assertTrue(pilha3.isEmpty());
	}
	
	@Test
	public void testIsFull() {
		assertFalse(pilha2.isFull());
		assertTrue(pilha3.isFull());
		assertFalse(pilha1.isFull());
	}
	
	@Test
	public void testIsFull1() throws StackUnderflowException {
		assertTrue(pilha3.isFull());
		pilha3.pop();
		assertFalse(pilha3.isFull());
	}
	
	@Test
	public void testIsFull2() throws StackUnderflowException, StackOverflowException {
		assertFalse(pilha1.isFull());
		pilha1.push(7);
		pilha1.push(7);
		assertTrue(pilha1.isFull());
	}

}
