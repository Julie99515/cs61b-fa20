public class LinkedListDeque<T> {
    public StuffNode sentinel;
    public int size;

    public class StuffNode<T> {
        public T item;
        public StuffNode prev;
        public StuffNode next;
        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    public LinkedListDeque() {
        sentinel = new StuffNode((T)"null", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new StuffNode((T)"null", null, null);
        sentinel.next = new StuffNode(x, sentinel, null);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        // create a new list
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    public void addLast(T item) {
        // original: sentinel.prev = new StuffNode(item, sentinel.next, sentinel);
        sentinel.prev = new StuffNode(item, sentinel.prev, sentinel);
        // initially, sentinel.prev.prev.next point to sentinel
        // now it should point to the new last element
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
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
    public void printDeque() {
        StuffNode p = sentinel.next;
        while(p.next != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        } else {
            StuffNode p = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size = size - 1;
            return (T) p.item;
        }
    }
    public T removeLast() {
        if(size == 0) {
            return null;
        } else {
            StuffNode p = sentinel.next;
            sentinel.prev = sentinel.prev.prev;
            // change the pointer again
            sentinel.prev.prev.next = sentinel;
            size = size - 1;
            return (T) p.item;
        }
    }
    public T get(int index) {
        if(index > size - 1) {
            return null;
        } else {
            StuffNode p = sentinel.next;
            int prt = 0;
            while (prt < index) {
                p = p.next;
            }
            return (T) p.item;
        }
    }
    public T getRecursive(int index) {
        StuffNode ptr = sentinel.next;
        return getRecursiveHelper(index, ptr);
    }
    public T getRecursiveHelper(int index, StuffNode cur) {
        if(cur == null || index > size - 1) {
            return null;
        } else if(index == 0) {
            return (T) cur.item;
        } else {
            cur = cur.next;
            return getRecursiveHelper(index-1, cur);
        }
    }
}
