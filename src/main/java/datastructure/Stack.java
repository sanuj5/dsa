package datastructure;

import java.util.Iterator;

public class Stack<Item extends Comparable<Item>> implements Iterable<Item>{

	private Node lastAddedNode;

	private class Node{
		Item value;
		Node next;
	}

	public void push(final Item item){
		final Node currentNode = new Node();
		currentNode.value = item;
		currentNode.next = lastAddedNode;
		lastAddedNode = currentNode;
	}

	public Item pop(){
		final Node temp = lastAddedNode;
		if(temp == null) {
			return null;
		}
		lastAddedNode = temp.next;
		return temp.value;
	}

	public Iterator<Item> iterator() {
		return new stackIterator();
	}

	private class stackIterator implements Iterator<Item>{
		private Node currentNode;

		public stackIterator() {
			currentNode = lastAddedNode;
		}
		public boolean hasNext() {
			return currentNode!=null;
		}

		public Item next() {
			final Node temp = currentNode;
			currentNode = currentNode.next;
			return temp.value;
		}

	}

	public static void main(final String[] args) {
		final Stack<Integer> stack = new Stack<Integer>();
		stack.push(10);
		stack.pop();
		stack.push(20);
		stack.push(0);
		stack.push(30);
		stack.pop();
		stack.push(40);

		for(final Integer i:stack){
			System.out.println(i);
		}
	}

}
