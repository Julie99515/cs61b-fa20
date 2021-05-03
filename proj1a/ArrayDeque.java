/**
 * created on April 30th, 2021
 * @param <T>
 */
public class ArrayDeque<T> {
    public int size;
    public T items[];
    public int nextFirst;
    public int nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**remove first: next first add 1;
     * remove last: next last minus 1
     * add first: next first minus 1
     * add last: next last add 1 */
    /** compute array indices */
    public int minusOne(int index, int length) {
        /** compute the index immediately before a given index */
        // attention! distinguish between size and items.length
        int new_index = 0;
        if(index - 1 < 0) {
            new_index = length - 1;
        } else {
            new_index = index - 1;
        }
        return new_index;
    }

    public int plusOne(int index, int length) {
        /** compute the index immediately after a given index */
        int new_index = 0;
        if(index + 1 == length) {
            new_index = 0;
        } else {
            new_index = index + 1;
        }
        return new_index;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void resize() {
        if(size / items.length < 0.25 && items.length > 8) {
            resizeHelper(items.length / 2);
        }
        if(size == items.length) {
            resizeHelper(items.length * 2);
        }
    }

    // problem: how to copy the element to the resized array?
    // might not start from zero, so arraycopy is not useful any more
    // where should the values be assigned to?
    public void resizeHelper(int capacity) {
        /**
         *  method: keep the initial next first point
         *  use it and the length(same as minusOne/plusOne) to calculate the last point
         */
        T[] temp = items;
        int start = plusOne(nextFirst, items.length);
        int end = minusOne(nextLast, items.length);
        //change items.length
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for(int i = start; i != end; i = plusOne(i, temp.length)) {
            items[nextLast] = temp[i];
            nextLast = minusOne(nextLast, items.length);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast, items.length);
    }

    /** first: no resizing at all */
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst, items.length);
        size = size + 1;
        resize();
    }
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = plusOne(nextLast, items.length);
        size = size + 1;
        resize();
    }
    // when to change the size?
    public T removeFirst() {
        // the position still store the value of original first
        // size minus one, nextFirst became the original first position
        // but if plusOne larger than new size?
        // store the items[first] at first
        // if the original first is the last element
        // next first could still be at the same position
        // size is separated from number like 8 !!!
        T original_first = items[plusOne(nextFirst, items.length)];
        nextFirst = plusOne(nextFirst, items.length);
        size = size - 1;
        resize();
        return original_first;
    }
    public T removeLast() {
        nextLast = minusOne(nextLast, items.length);
        size = size - 1;
        resize();
        return items[nextLast];
    }

    /** attention!!!!!!!!!!!! */
    public void printDeque() {
        for(int index = plusOne(nextFirst, items.length); index != nextLast; index = plusOne(index, items.length)) {
            // index = plusOne(index) so no need for Mod any more
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFirst, items.length) + index, size);
        return items[index];
    }
}
