package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int tamanho = 0;
		
		if (!isEmpty()) 
			tamanho = 1 + next.size();
				
		return tamanho;
	}

	@Override
	public T search(T element) {
		T saida = null;
		
		if (!isEmpty()) {
			if (this.data.equals(element))
				saida = data;
			else
				saida = next.search(element);
		}
		
		return saida;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<>());
			} else 
				next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				this.setData(next.getData());
				this.setNext(next.getNext());
			} else
				next.remove(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] saida = (T[]) new Object[this.size()]; 
		buildArray(saida, 0, this);
		return saida;
	}
	
	private void buildArray(T[] array, int pos, RecursiveSingleLinkedListImpl<T> node) {
		if (!node.isEmpty()) {
			array[pos] = node.getData();
			buildArray(array, ++pos, node.getNext());
		}
	}
	
	public int indexOf(T element) {
		int ind = -	1;
		
		if (!isEmpty()) {
			if (data.equals(element))
				ind++;
			else
				ind = 1 + next.indexOf(element);
		}
		
		return ind;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
