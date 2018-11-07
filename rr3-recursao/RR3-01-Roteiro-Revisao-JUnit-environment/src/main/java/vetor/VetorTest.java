package vetor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VetorTest {
	
	Vetor v;
	
	@Before
	public void testVetor() {
		v = new Vetor<>(3);
	}

	@Test
	public void testInserir() {
		String s = "matheus lindo";
		v.inserir(s);
		assertEquals("matheus lindo", v.procurar(s));
	}

	@Test
	public void testRemover() {
		Aluno a = new Aluno("rafa", 10);
		v.inserir(a);
		assertEquals(false, v.isVazio());
		v.remover(a);
		assertEquals(true, v.isVazio());
	}

	@Test
	public void testProcurar() {
		Aluno a = new Aluno("rafa", 10);
		v.inserir(a);
		assertEquals(a, v.procurar(a));
	}

	@Test
	public void testIsVazio() {
		assertTrue(v.isVazio());
		Aluno a = new Aluno("rafa", 10);
		v.inserir(a);
		assertFalse(v.isVazio());
	}

	@Test
	public void testIsCheio() {
		Aluno a = new Aluno("rafa", 9.5);
		Aluno b = new Aluno("beijinho", 10.5);
		Aluno c = new Aluno("joazinho", 6);
		v.inserir(a);
		assertFalse(v.isCheio());
		v.inserir(b);
		v.inserir(c);
		assertTrue(v.isCheio());
	}

}
