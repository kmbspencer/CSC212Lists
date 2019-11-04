package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Singly-Linked List is a list that has only knowledge of its very first
 * element. Elements after that are chained, ending with a null node.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();
		Node<T> removed = start;
		start= removed.next;
		return removed.value;
		
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		Node<T> temp;
		if(start.next == null) {
			temp = start;
			start = null;
			return temp.value;
		}
		Node<T> n = start;
		while(n.next.next !=null) {
			n=n.next;
		}
		temp = n.next;
		n.next=null;
		return temp.value;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		int at = 0;
		//while
		if(index==0) {
			return removeFront();
		}
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at+1 == index) {
				Node <T> temp = n.next;
				n.next = n.next.next;
				return temp.value;
			}
			at++;
		}
		throw new BadIndexError(index);
	}

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		//checkNotEmpty();
		if(start == null) {
			start = new Node<T>(item,null);
			
		}else{
			Node<T> n = start;
			while(n.next != null) {
				n= n.next;
			}
			n.next = new Node<T>(item,null);
		}	
	}

	@Override
	public void addIndex(int index, T item) {
		checkNotEmpty();
		int at = 0;
		if(index == 0) {
			start = new Node <T>(item, start);
			return;
		}
		
				
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at+1 == index) {
				Node<T> temp = n.next;
				n.next = new Node<T>(item, temp);
				return;
			}
			at++;
		}
		throw new BadIndexError(index);
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		Node <T> n = start;
		while(n.next != null) {
			n = n.next;
		}
		return n.value;
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
			
		}
		throw new BadIndexError(index);
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		int at = 0;
		if(index == 0) {
			start.value = value;
			return;
		}
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				n.value = value;
				return;
			}
		}
		throw new BadIndexError(index);
		
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 * @param next - the successor to this node.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

}
