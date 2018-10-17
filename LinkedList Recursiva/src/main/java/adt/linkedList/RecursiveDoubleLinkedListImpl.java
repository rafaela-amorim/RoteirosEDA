package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) 
			insert(element);
		else if (element != null) {
			RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, new RecursiveDoubleLinkedListImpl<>());
			this.setData(element);
			((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(aux);
			this.setNext(aux);
			aux.setPrevious(this);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			setData(next.getData());
			if (next != null)
				setNext(next.getNext());
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (getNext().isEmpty()) {
				RecursiveDoubleLinkedListImpl<T> ant = getPrevious();
				ant.setNext(getNext());
			} else
				((DoubleLinkedList<T>) next).removeLast();
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<>());
				((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
				
				if (getPrevious() == null) {
					setPrevious(new RecursiveDoubleLinkedListImpl<>());
					getPrevious().setNext(this);
				}
				
			} else {
				next.insert(element);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (data.equals(element)) {
				setData(next.getData());
				setNext(next.getNext());
				if (getNext() == null) 
					setNext(new RecursiveDoubleLinkedListImpl<>());
				
				((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);

			} else 
				next.remove(element);
		}
	}
	
	public void inverte() {
		if (!isEmpty() && !getNext().isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> last = this;
			RecursiveDoubleLinkedListImpl<T> first = this;
			T aux; 
			
			while (!last.getNext().isEmpty())
				last = (RecursiveDoubleLinkedListImpl<T>) last.getNext();
			
			while (first != last && last.getPrevious() != first) {
				aux = first.getData();
				first.setData(last.getData());
				last.setData(aux);
				
				first = (RecursiveDoubleLinkedListImpl<T>) first.getNext();
				last = last.getPrevious();
			}
			
			if (this.size() % 2 == 0) {
				aux = first.getData();
				first.setData(last.getData());
				last.setData(aux);
			}
		}
		
	}
	
	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
