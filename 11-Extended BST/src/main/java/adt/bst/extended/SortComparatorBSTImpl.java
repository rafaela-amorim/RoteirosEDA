package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while (!isEmpty())
			remove(root);
		
		for (int i = 0; i < array.length; i++) 
			insert(array[i]);
		
		return order();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] reverseOrder() {
		ArrayList<T> list = new ArrayList<>();
		reverseOrder(list, root);
		T[] array = (T[]) list.toArray(new Comparable[list.size()]);
		return array;
	}
	
	private void reverseOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			reverseOrder(list, (BSTNode<T>) node.getRight());
			list.add(node.getData());
			reverseOrder(list, (BSTNode<T>) node.getLeft());
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) 
			insert(root, element);
	}
	
	private void insert(BSTNode<T> node, T element)	{
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (comparator.compare(node.getData(), element) > 0)
				insert((BSTNode<T>) node.getLeft(), element);
			else
				insert((BSTNode<T>) node.getRight(), element);
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> answer = null;
		
		if (element == null) 
			answer = new BSTNode<T>();
		else if (node.isEmpty() || comparator.compare(node.getData(), element) == 0)
			answer = node;
		else {
			if (comparator.compare(node.getData(), element) > 0)
				answer = search((BSTNode<T>) node.getLeft(), element);
			else
				answer = search((BSTNode<T>) node.getRight(), element);
		}
		
		return answer;
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
