package meusTestes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.queue.CircularQueue;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;

public class CircularQueueTest {
	
	public CircularQueue<Integer> queue1;
	public CircularQueue<Integer> queue2;
	public CircularQueue<Integer> queue3;
	
	@Before
	public void inicializa() throws QueueOverflowException {
		queue1 = new CircularQueue<>(5);
		queue2 = new CircularQueue<>(3);
		queue3 = new CircularQueue<>(2);
		popula();
	}
	
	private void popula() throws QueueOverflowException {
		queue1.enqueue(1);	// tamanho 5, não cheio
		queue1.enqueue(2);
		queue1.enqueue(3);
		
		// queue2 sem elementos
		
		queue3.enqueue(1);	// tamanho 2, cheio
		queue3.enqueue(2);
	}
	
	@Test
	public void testEnqueue() throws QueueOverflowException {
		queue1.enqueue(20);
		assertFalse(queue1.isFull());
	}
	
	@Test(expected=QueueOverflowException.class)
	public void testEnqueueComExcecao() throws QueueOverflowException {
		queue1.enqueue(20);
		queue1.enqueue(18);
		assertTrue(queue1.isFull());
		queue1.enqueue(1);
	}
	
	@Test
	public void testDequeue() throws QueueUnderflowException {
		// queue1 é iniciada com 3 elementos
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		assertTrue(queue1.isEmpty());
	}

	@Test(expected=QueueUnderflowException.class)
	public void testDequeueComExcecao() throws QueueUnderflowException {
		// queue1 é iniciada com 3 elementos
		queue1.dequeue();
		queue1.dequeue();
		queue1.dequeue();
		assertTrue(queue1.isEmpty());
		// esse deve lançar exceção ao tentar remover um elemento qd não existem mais
		queue1.dequeue();
	}
	
	@Test
	public void testHead() throws QueueUnderflowException {
		assertNull(queue2.head());
		assertEquals((Integer) 1, queue3.head());
		queue3.dequeue();
		assertEquals((Integer) 2, queue3.head());
		queue3.dequeue();
		assertNull(queue3.head());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(queue2.isEmpty());
	}
	
	@Test
	public void testIsEmpty2() throws QueueUnderflowException {
		assertFalse(queue3.isEmpty());
		queue3.dequeue();
		queue3.dequeue();
		assertTrue(queue3.isEmpty());
	}
	
	@Test
	public void testIsFull() throws QueueUnderflowException {
		assertTrue(queue3.isFull());
		queue3.dequeue();
		assertFalse(queue3.isFull());
	}
	
	@Test
	public void testIsFull2() throws QueueUnderflowException {
		assertFalse(queue2.isFull());
	}

}
