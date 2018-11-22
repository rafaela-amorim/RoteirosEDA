package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl() {

   }

   @Override
   public int LLcount() {
      return LLcounter;
   }

   @Override
   public int LRcount() {
      return LRcounter;
   }

   @Override
   public int RRcount() {
      return RRcounter;
   }

   @Override
   public int RLcount() {
      return RLcounter;
   }

   @Override
   protected void rebalance(BSTNode<T> node) {
      if (!node.isEmpty()) {
         int balance = calculateBalance(node);
         int balanceChild;
         BSTNode<T> pivot;

         if (balance > 1) { // LL ou LR
            balanceChild = calculateBalance((BSTNode<T>) node.getLeft());

            if (balanceChild <= -1) {
               LRcounter++;
               Util.leftRotation((BSTNode<T>) node.getLeft());
            } else {
               LLcounter++;
            }

            pivot = Util.rightRotation(node);

            if (node == root) {
               root = pivot;
            }

         } else if (balance < -1) { // RR ou RL
            balanceChild = calculateBalance((BSTNode<T>) node.getRight());

            if (balanceChild >= 1) {
               Util.rightRotation((BSTNode<T>) node.getRight());
               RLcounter++;
            } else {
               RRcounter++;
            }

            pivot = Util.leftRotation(node);

            if (node == root) {
               root = pivot;
            }
         }
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public void fillWithoutRebalance(T[] array) {
	   if (array != null) {
		 ArrayList<T> list = new ArrayList<>();
		 addAllNodes(list);
		 
		 for (int i = 0; i < array.length; i++) {
			 list.add(array[i]);
		 }
		 
		 array = (T[]) list.toArray(new Comparable[list.size()]);
		 Arrays.sort(array);
		 
		 Integer[] indices = new Integer[2 * array.length + 2];
		 fillIndexArray(indices, 0, array.length, 0);
		 
		 for (int i = 0; i < indices.length; i++) {
			 if (indices[i] != null && search(array[indices[i]]).isEmpty()) {
				 insert(array[indices[i]]);
			 }
		 }
		 
	   }

   }
   
   private void addAllNodes(ArrayList<T> list) {
	   while (!isEmpty()) {
		   list.add(root.getData());
		   remove(root);
	   }
	   
	   LLcounter = 0;
	   LRcounter = 0;
	   RLcounter = 0;
	   RRcounter = 0;
   }
   
   protected void fillIndexArray(Integer[] array, int inicio, int fim, int pos) {
	   if (inicio <= fim && pos < array.length) {
		   int meio = (inicio + fim) / 2;
		   
		   array[pos] = meio;
		   fillIndexArray(array, inicio, meio, 2 * pos + 1);
		   fillIndexArray(array, meio, fim, 2 * pos + 2);
		   
	   }
   }
}
























