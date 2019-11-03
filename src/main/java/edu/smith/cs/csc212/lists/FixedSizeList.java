package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ArrayWrapper;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.EmptyListError;
import me.jjfoley.adt.errors.RanOutOfSpaceError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * FixedSizeList is a List with a maximum size.
 * @author jfoley
 *
 * @param <T>
 */
public class FixedSizeList<T> extends ListADT<T> {
	/**
	 * This is the array of fixed size.
	 */
	private ArrayWrapper<T> array;
	/**
	 * This keeps track of what we have used and what is left.
	 */
	private int fill;

	/**
	 * Construct a new FixedSizeList with a given maximum size.
	 * @param maximumSize - the size of the array to use.
	 */
	public FixedSizeList(int maximumSize) {
		this.array = new ArrayWrapper<>(maximumSize);
		this.fill = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	public int size() {
		return this.fill;
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		if(index<0) {
			throw new BadIndexError(index);
		}
		this.checkExclusiveIndex(index);
		this.array.setIndex(index, value);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		if(index<0) {
			throw new BadIndexError(index);
		}
		this.checkExclusiveIndex(index);
		return this.array.getIndex(index);
	}

	@Override
	public T getFront() {
		if(fill==0) {
			throw new EmptyListError();
		}
		return this.array.getIndex(0);
	}

	@Override
	public T getBack() {
		if(fill==0) {
			throw new EmptyListError();
		}
		return this.array.getIndex(fill-1);
	}

	@Override
	public void addIndex(int index, T value) {
		if(index<0) {
			throw new BadIndexError(index);
		}
		// slide to the right
		if(fill+1>array.size()) {
			throw new RanOutOfSpaceError();
		}
		for(int i = fill-1; i>=index;i--) {
			this.array.setIndex(i+1,array.getIndex(i));
		}
		this.array.setIndex(index, value);
		fill++;
	}

	@Override
	public void addFront(T value) {
		this.addIndex(0, value);
	}

	@Override
	public void addBack(T value) {
		if (fill < array.size()) {
			array.setIndex(fill++, value);
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public T removeIndex(int index) {
		
		checkNotEmpty();
		if(index<0) {
			throw new BadIndexError(index);
		}
		
		//store removed item
		T removed = this.getIndex(index);
		fill --;
		
		//slide everything left
		for(int i = index; i<fill; i++) {
			this.array.setIndex(i,array.getIndex(i+1));
		}
		//erase the duplicated item
		this.array.setIndex(fill, null);
		
		//return deleted item
		return removed;
		
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		T removed = this.array.getIndex(fill-1);
		this.array.setIndex(fill-1,null);
		fill--;
		return removed;
		
	}

	@Override
	public T removeFront() {
		return removeIndex(0);
	}

	/**
	 * Is this data structure full? Used in challenge: {@linkplain ChunkyArrayList}.
	 * 
	 * @return if true this FixedSizeList is full.
	 */
	public boolean isFull() {
		return this.fill == this.array.size();
	}

}
