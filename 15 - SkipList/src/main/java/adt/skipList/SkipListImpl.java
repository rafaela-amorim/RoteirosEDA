package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue != null) {	
			
			SkipListNode<T> node = root;
			SkipListNode<T>[] update = new SkipListNode[maxHeight];
			
			for (int i = maxHeight - 1; i >= 0; i--) {
				while (node.getForward(i).getKey() < key) {
					node = node.getForward(i);
				}
				update[i] = node;
			}
			
			node = node.getForward(0);
			
			if (node.getKey() == key) {
				node.setValue(newValue);
			} else {
				if (height > maxHeight) {
					throw new RuntimeException();
				}
				
				node = new SkipListNode<T>(key, height, newValue);
				
				for (int i = 0; i < height; i++) {
					node.forward[i] = update[i].forward[i];		// recebe o forward do seu anterior
					update[i].forward[i] = node;				// reatribui o forward
					
				}
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;
		
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
			update[i] = node;
		}
		
		node = node.getForward(0);
		
		if (node.getKey() == key) {
			int i = 0;
			
			while (i < node.height() && update[i].forward[i] == node) {
				update[i].forward[i] = node.forward[i];
				i++;
			}
		}
	}

	@Override
	public int height() {
		int height = 0;
		int i = maxHeight;
		
		while (i >= 0 || root.getForward(i) == NIL) {
			i--;
			height++;
		}
		
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> answer = null;
		
		SkipListNode<T> node = root;
		
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
		}
		node = node.getForward(0);
		
		if (node.getKey() == key) {
			answer = node;
		}
		
		return answer;
	}

	@Override
	public int size() {
		SkipListNode<T> node = root.getForward(0);
		int size = 0;
		
		while (node != NIL) {
			node = node.getForward(0);
			size++;
		}
		
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		int i = 0;
		SkipListNode<T> node = root;
		
		while (node != NIL) {
			array[i++] = node;
			node = node.getForward(0);
		}
		array[i++] = node;
		
		return array;
	}
	
}
