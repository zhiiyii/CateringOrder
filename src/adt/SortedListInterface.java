package adt;

/** SortedListInterface - An interface for the ADT sorted list.
  * @author Low Zhi Yi
  */

public interface SortedListInterface<T extends Comparable<T>> {

    /** Adds newEntry to the sorted list such that the list remains sorted. */
    public void add(T newEntry);

    /** Removes the first or only occurrence of anEntry from the sorted list.
     *  @return true if anEntry was located and removed, else false
     */
    public boolean remove(T anEntry);

    /** Retrieves the entry at position givenPosition in the list.
     *  @return the entry at position givenPosition.
     */
    public T getEntry(int givenPosition);

    /** Retrieves the entry by comparing it with anEntry using equals function.
     *  @return the entry that equals to anEntry.
     */
    public T getEntry(T anEntry);

    /** Removes all entries from the list. */
    public void clear();

    /** Gets the number of entries currently in the list. 
     *  @return the number of entries currently in the list.
     */
    public int getNumberOfEntries();

    /** Determines whether the list is empty. 
     *  @return true if the list is empty, else false.
     */
    public boolean isEmpty();

    /**  Determines whether the list is full.
     *   @return true if the list is full, else false.
     */
    public boolean isFull();
}
