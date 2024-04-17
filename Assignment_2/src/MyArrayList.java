import java.util.Iterator;
public class MyArrayList<T> implements MyList<T> {
    private Object[] elements; // Array to store elements of the list
    private int size; // Current size of the list
    private static final int DEFAULT_CAPACITY = 10; // Default initial capacity of the array

    // Constructor
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY]; // Initialize array with default capacity
        this.size = 0; // Initially the list is empty
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Adds an element to the end of the list
    @Override
    public void add(T item) {
        ensureCapacity(); // Ensure capacity of the array
        elements[size++] = item; // Add element to the array and increment size
    }

    // Sets the element at the specified index
    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = item;
    }

    // Adds an element at the specified index
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(); // Ensure capacity of the array
        System.arraycopy(elements, index, elements, index + 1, size - index); // Shift elements to the right
        elements[index] = item; // Insert new element
        size++; // Increment size
    }

    // Adds an element to the beginning of the list
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    // Adds an element to the end of the list
    @Override
    public void addLast(T item) {
        add(size, item);
    }

    // Returns the element at the specified index
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    // Returns the first element of the list
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) elements[0];
    }

    // Returns the last element of the list
    @Override
    public T getLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) elements[size - 1];
    }

    // Removes the element at the specified index
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1); // Shift elements to the left
        size--; // Decrement size
    }

    // Removes the first element of the list
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Removes the last element of the list
    @Override
    public void removeLast() {
        remove(size - 1);
    }

    // Sorts the elements of the list
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable) elements[j]).compareTo(elements[j + 1]) > 0) {
                    // Swap elements
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    // Returns the index of the first occurrence of the specified element in the list
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified element in the list
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    // Checks if the list contains the specified element
    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    // Returns an array containing all of the elements in the list
    @Override
    public T[] toArray() {
        return (T[]) java.util.Arrays.copyOf(elements, size);
    }

    // Removes all of the elements from the list
    @Override
    public void clear() {
        size = 0;
    }

    // Returns the number of elements in the list
    @Override
    public int size() {
        return size;
    }

    // Returns an iterator over the elements in the list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0; // Current index for iteration

            @Override
            public boolean hasNext() {
                return index < size; // Check if there is a next element in the list
            }

            @Override
            public T next() {
                if (!hasNext()) { // If there is no next element
                    throw new java.util.NoSuchElementException(); // Throw an exception
                }
                return (T) elements[index++]; // Return the current element and increment the index
            }


        };
    }

    // Ensures that the array has sufficient capacity to accommodate additional elements
    private void ensureCapacity() {
        if (size == elements.length) { // If the current size equals the array length
            Object[] newElements = new Object[size * 2]; // Create a new array with double the capacity
            System.arraycopy(elements, 0, newElements, 0, size); // Copy elements to the new array
            elements = newElements; // Update reference to the new array
        }
    }

    // Test method
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        // Add elements to the list
        myList.add(1);
        myList.add(2);
        myList.add(3);

        // Print information about the list
        System.out.println("Size: " + myList.size());
        System.out.println("First element: " + myList.getFirst());
        System.out.println("Last element: " + myList.getLast());

        // Remove the first element from the list
        myList.removeFirst();
        System.out.println("After removing first element, first element: " + myList.getFirst());

        // Add elements to the beginning and end of the list
        myList.addLast(4);
        myList.addFirst(0);

        // Print the index of element 3 and the last index of element 4
        System.out.println("Index of 3: " + myList.indexOf(3));
        System.out.println("Last index of 4: " + myList.lastIndexOf(4));

        // Sort the list and print it
        myList.sort();
        System.out.print("Sorted list: ");
        for (Integer item : myList) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Check if element 2 is in the list
        System.out.println("Is 2 in the list? " + myList.contains(2));

        // Print the list as an array
        System.out.print("List as array: ");
        Object[] objectArray = myList.toArray();
        Integer[] array = new Integer[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            array[i] = (Integer) objectArray[i];
        }
        for (Integer item : array) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Clear the list
        myList.clear();
        System.out.println("List cleared, size: " + myList.size());
    }
}
