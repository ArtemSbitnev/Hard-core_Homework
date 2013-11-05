package mylist;
import java.util.*;

public class MyList<T extends Comparable<T>> implements List<T>{
	
	public MyList() {
		first = null;
		last = null;
		left = null;
		right = null;	
		count = 0;
		value = null;
	}
	
	private MyList(T t) {
		first = null;
		last = null;
		left = null;
		right = null;	
		count = 0;
		value = t;
	}
	
	public int size() {
		return count;
	}
	
	public boolean add(T t) {
		if (first == null) {
			first = new MyList<T>(t);
			last = first;
		}
		else if (first == last) {
			last = new MyList<T>(t);
			last.left = first;
			first.right = last;
		}
		else {
			MyList<T> temp = new MyList<T>(t);
			temp.left = last;
			last.right = temp;
			last = temp;
			temp = null;
		}
		this.count++;
		return true;
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public boolean contains(Object o) {
		for (T t : this) {
			if (t.equals(o)) return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {

			MyList<T> next = first;
			MyList<T> node = null;
			
			@Override
			public boolean hasNext() {
				return (node != last);
			}
			
			@Override
			public T next() {
				if (next == null)
				throw new NoSuchElementException();
				
				node = next;
				
				if (node == last)
					next = null;
				
				next = node.right;
				return node.value;
			}
			
			public void remove() {
				throw new UnsupportedOperationException(); 
			}
		};
	}
	
	public boolean remove(Object o) {
		if (first != null) {
			if (first.value.equals(o)) {
				MyList<T> node = first;
				first = first.right;
				first.left = null;
				node.right = null;
				node = null;
				count--;
				return true;
			}
			else if (last.value.equals(o)) {
				MyList<T> node = first;
				last = last.left;
				last.right = null;
				node.left = null;
				node = null;
				count--;
				return true;
			}
			else {
				MyList<T> node = first;
				while (node != last) {
					node = node.right;
					if (node.value.equals(o)) {
						node.right.left = node.left;
						node.left.right = node.right;
						node.right = null;
						node.left = null;
						node = null;
						count--;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		if (index <= size()) {
			MyList<T> node = first;
			for(int i = 1; i < index; i++) {
				node = node.right;
			}
			return node.value;
		}
		return null;
	}

	@Override
	public T set(int index, T element) {
		if (index <= size()) {
			MyList<T> node = first;
			for(int i = 1; i < index; i++) {
				node = node.right;
			}
			T temp = node.value;
			node.value = element;
			return temp;
		}
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
	}

	public T remove(int index) {
		T t = get(index);
        remove(t);
		return t;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void mergeSort() {
		MyList<T> tmpList = new MyList<T>();
		for(int i = 1; i <= size(); i++)
			tmpList.add(null);
		mergeSort(MyList.this, tmpList, 1, size());
	}

	private void mergeSort(MyList<T> a, MyList<T> tmpArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	private void merge(MyList<T> a, MyList<T> tmpArray, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a.get(leftPos).compareTo(a.get(rightPos)) <= 0)
				tmpArray.set(tmpPos++, a.get(leftPos++));
			else
				tmpArray.set(tmpPos++, a.get(rightPos++));
		while (leftPos <= leftEnd)
			// Copy rest of first half
			tmpArray.set(tmpPos++, a.get(leftPos++));
		while (rightPos <= rightEnd)
			// Copy rest of right half
			tmpArray.set(tmpPos++, a.get(rightPos++));
		// Copy tmpArray back
		for (int i = 1; i <= numElements; i++, rightEnd--)
			a.set(rightEnd, tmpArray.get(rightEnd));
	}
	
	private MyList<T> first;
	private MyList<T> right;
	private MyList<T> left;
	private MyList<T> last;
	private int count;
	private T value;
}
