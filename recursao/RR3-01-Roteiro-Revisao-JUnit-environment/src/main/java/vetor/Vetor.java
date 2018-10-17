package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = 0;
		arrayInterno = (T[]) new Object[tamanho];
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		if (indice < tamanho) {
			arrayInterno[indice++] = o;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Remove um objeto do vetor
	public Object remover(T o) {
		T ans = null;
		boolean removido = false;
		int i = 0;
		
		while (i < indice) {
			if (arrayInterno[i].equals(o)) {
				ans = arrayInterno[i];
				arrayInterno[i] = null;
				removido = true;
				break;
			}
			i++;
		}
		
		if (removido) {
			T objAux;
			while (i < indice - 1) {
				objAux = arrayInterno[i + 1];
				arrayInterno[i + 1] = arrayInterno[i];
				arrayInterno[i] = objAux;
				i++;
			}
			indice--;
		}
		
		return (Object) ans;
	}

	// Procura um elemento no vetor
	public Object procurar(Object o) {
		int i = 0;
		
		while (i < indice) {
			if (arrayInterno[i].equals(o)) {
				return arrayInterno[i];
			}
			i++;
		}
		throw new IllegalArgumentException();
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return arrayInterno[0] == null;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return arrayInterno[tamanho - 1] != null; 
	}

}
