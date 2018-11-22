package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	
	@Override
	protected void insert(BSTNode<T> node, T elem) {
		if (node.isEmpty()) {
			node.setData(elem);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(elem) > 0)
				insert((BSTNode<T>) node.getLeft(), elem);
			else
				insert((BSTNode<T>) node.getRight(), elem);
			
			rebalance(node);
		}
	}
	
	@Override
	protected void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				rebalanceUp(node);
			} else if (hasOnlyChild(node)) {
				
				if (node.getParent() != null) {
					if (node.getParent().getData().compareTo(node.getData()) > 0) {	
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
					
				} else {
					if (node.getLeft().isEmpty()) {
						root = (BSTNode<T>) node.getRight();
						root.setParent(null);
					} else {
						root = (BSTNode<T>) node.getLeft();
						root.setParent(null);
					}
				}
				
				rebalanceUp(node);
			} else {	// complete node
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int answer = 0;
		if (node != null && !node.isEmpty())
			answer = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		return answer;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			int balance = calculateBalance(node);
			int balanceChild;
			BSTNode<T> pivot;
			
			if (balance > 1) {		// LL ou LR
				balanceChild = calculateBalance((BSTNode<T>) node.getLeft());
				
				if (balanceChild <= -1)
					Util.leftRotation((BSTNode<T>) node.getLeft());
				
				pivot = Util.rightRotation(node);
				
				if (node == root)
					root = pivot;
				
			} else if (balance < -1) {		// RR ou RL
				balanceChild = calculateBalance((BSTNode<T>) node.getRight());
				
				if (balanceChild >= 1)
					Util.rightRotation((BSTNode<T>) node.getRight());
				
				pivot = Util.leftRotation(node);
				
				if (node == root)
					root = pivot;
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		while (parent != null && !parent.isEmpty()) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
