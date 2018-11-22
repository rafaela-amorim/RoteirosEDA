package adt.avltree;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeImplTest {
	
	AVLTreeImpl<Integer> tree;
	
	@Before
	public void inicia() {
		tree = new AVLTreeImpl<>();
	}
	
	@Test
	public void insert() {
		tree.insert(25);
		tree.insert(40);
		tree.insert(10);
		
		tree.insert(12);
//		System.out.println(Arrays.toString(tree.preOrder()));
		tree.insert(13);
//		System.out.println(Arrays.toString(tree.preOrder()));
		
		tree.insert(18);
		tree.insert(15);
//		System.out.println(tree.getRoot() + "   " + Arrays.toString(tree.order()));
		System.out.println(Arrays.toString(tree.preOrder()));
		
	}
	
	@Test
	public void testRemoveBSTNodeOfT() {
		fail("Not yet implemented");
	}

}
