package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador é utilizado para fazer as comparações da heap. O ideal é
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap não precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return (i * 2 + 1) + 1;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }

   @SuppressWarnings("unchecked")
   @Override
   public T[] toArray() {
      ArrayList<T> resp = new ArrayList<T>();
      for (T elem : this.heap) {
         if (elem != null) {
            resp.add(elem);
         }
      }
      return (T[]) resp.toArray(new Comparable[0]);
   }

   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
	   int l = left(position);
	   int r = right(position);
	   int largest;
	   
	   if (l <= index && comparator.compare(heap[l], heap[position]) > 0) 
		   largest = l;
	   else	
		   largest = position;
	   
	   if (r <= index && comparator.compare(heap[r], heap[largest]) > 0)
		   largest = r;
	   
	   if (largest != position) {
		   T aux = heap[position];
		   heap[position] = heap[largest];
		   heap[largest] = aux;
		   heapify(largest);
	   }
   }

   @Override
   public void insert(T element) {
      // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
      if (index == heap.length - 1) {
         heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
      }
      // /////////////////////////////////////////////////////////////////

      if (element != null) {
         this.index++;
         int i = index;

         while (i > 0 && comparator.compare(heap[parent(i)], element) < 0) {
            heap[i] = heap[parent(i)];
            i = parent(i);
         }

         heap[i] = element;
      }
   }

   @Override
   public void buildHeap(T[] array) {
	   if (array != null) {
		   emptyHeap();
		   
		   for (int i = 0; i < array.length; i++) {
			   if (array[i] != null)
				   heap[++index] = array[i];
		   }
	   
		   for (int i = (array.length / 2); i >= 0; i--)
			   heapify(i);
	   }
   }
   
   private void emptyHeap() {
	   while (!isEmpty()) 
		   extractRootElement();
   }
   
   @Override
   public T extractRootElement() {
	   T root = null;
	   
	   if (!isEmpty()) {
		   root = heap[0];
		   
		   heap[0] = heap[index];
		   heap[index--] = null;
		   heapify(0);
	   }
	   
	   return root;
   }

   @Override
   public T rootElement() {
	   return heap[0];
   }

   @SuppressWarnings("unchecked")
   @Override
   public T[] heapsort(T[] array) {
	   T[] answer = (T[]) new Comparable[array.length];
	   
	   if (array != null) {
		   buildHeap(array);
		   
		   if (size() > 1) {
			   if (isMaxHeap()) {
				   for (int i = array.length - 1; i >= 0; i--) 
					   answer[i] = extractRootElement();

			   } else { // eh min heap
				   for (int i = 0; i < array.length; i++) 
					   answer[i] = extractRootElement();
				   
			   }
		   } else 
			   answer[0] = heap[0];
	   }
	   
	   return answer;
   }
   
   private boolean isMaxHeap() {
	   return heap[0].compareTo(heap[1]) > 0;
   }
   
   @Override
   public int size() {
	   return index + 1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }

}
