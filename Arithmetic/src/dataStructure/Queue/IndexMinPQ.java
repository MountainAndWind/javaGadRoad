package dataStructure.Queue;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {

    /*
     * k is index(0,maxN-1)
     * pq[i] = k means the ith node in the heap is keys[k]
     * qp[k] = i means keys[k] is the ith node in the heap
     * qp[k] = -1 if k(index) is not in the Priority Queue
     *
     */
    private Key[] keys;//items with priorities
    private int[] pq;//binary heap using 1-based indexing
    private int[] qp;//inverse: qp[pq[i]] = pq[qp[i]] = i
    private int n;//number of elements on PQ
    private int maxN;

    public IndexMinPQ(int maxN) {

        if(maxN < 0)
            throw new IllegalArgumentException("The size of IndexMinPQ should larger than 0");

        this.maxN = maxN;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for(int i = 0; i <= maxN; i++)
            qp[i] = -1;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean contains(int k) {
        if(k < 0 || k >= maxN)
            throw new IndexOutOfBoundsException();
        return qp[k] != -1;
    }

    public void insert(int k, Key key) {

        if (k < 0 || k >= maxN)
            throw new IndexOutOfBoundsException();
        if (contains(k))
            throw new IllegalArgumentException("index is already in the priority queue");

        n++;
        qp[k] = n;
        pq[n] = k;
        keys[k] = key;
        swim(n);

    }

    public Key min() {

        if(n == 0)
            throw new NoSuchElementException("IndexMinPQ underflow");

        return keys[pq[1]];

    }

    public int minIndex() {

        if(n == 0)
            throw new NoSuchElementException("IndexMinPQ underflow");

        return pq[1];
    }

    public int delMin() {

        if(n == 0)
            throw new NoSuchElementException("IndexMinPQ underflow");

        int indexOfMin = pq[1];

        exch(1, n--);
        sink(1);
        keys[pq[n+1]] = null;
        qp[pq[n+1]] = -1;

        return indexOfMin;
    }

    public void delete(int i) {

        if (i < 0 || i >= maxN)
            throw new IndexOutOfBoundsException();
        if (!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");

        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;

    }

    public void changeKey(int i, Key key) {

        if (i < 0 || i >= maxN)
            throw new IndexOutOfBoundsException();
        if (!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");

        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);

    }

    /***************************************************************************
     * General helper functions.
     ***************************************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    /***************************************************************************
     * Heap helper functions.
     ***************************************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


}