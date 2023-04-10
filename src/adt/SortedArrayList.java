package adt;

import java.io.Serializable;

/** SortedArrayList - An implementation for SortedListInterface.
  * @author Low Zhi Yi
  */

public class SortedArrayList<T extends Comparable<T>> implements SortedListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SortedArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    @Override
    public void add(T newEntry) {
        int i = 0;
        if (isFull()) {
            expandArray();
        }
        while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
            i++;
        }
        makeRoom(i + 1);
        array[i] = newEntry;
        numberOfEntries++;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean found = false;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.compareTo(array[i]) == 0) {
                removeGap(i + 1);
                numberOfEntries--;
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public void clear() {
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public T getEntry(T anEntry) {
        T result = null;
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(anEntry)) {
                result = array[i];
                break;
            }
        }

        return result;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int i = 0; i < numberOfEntries; ++i) {
            outputStr += array[i] + "\n";
        }

        return outputStr;
    }

    // utility methods
    private void expandArray() {
        T[] oldList = array;
        int oldSize = oldList.length;

        array = (T[]) new Comparable[2 * oldSize];

        for (int i = 0; i < oldSize; i++) {
            array[i] = oldList[i];
        }
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int i = lastIndex; i >= newIndex; i--) {
            array[i + 1] = array[i];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int i = removedIndex; i < lastIndex; i++) {
            array[i] = array[i + 1];
        }
    }
}
