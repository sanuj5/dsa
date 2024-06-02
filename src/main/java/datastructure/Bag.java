package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	private int N;
	private Node<Item> first;

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public Bag() {
		first = null;
		N = 0;
	}

	public void add(final Item item) {
		final Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;
		N++;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	private class ListIterator<T> implements Iterator<T> {
		private Node<T> current;

		public ListIterator(final Node<T> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			final T item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
		}

	}

	public static void main(final String[] args) {
		final Bag<String> bag = new Bag<String>();
		bag.add("1");
		System.out.println("size of bag = " + bag.size());
		for (final String s : bag) {
			System.out.println(s);
		}
	}

}
