package ua.artstood.first_task;

import java.util.*;


public class HashSetImpl<Item> implements Set<Item> {
    private int size;
    private final int bucketCapacity;
    private List<Item>[] buckets;


    public HashSetImpl() {
        bucketCapacity = 16;
        buckets = new List[bucketCapacity];
        size = 0;
    }

    public HashSetImpl(int capacity) {
        bucketCapacity = capacity;
        buckets = new List[bucketCapacity];
        size = 0;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        int bucketAddress = calculateBucketAddress(o);
        if (buckets[bucketAddress] == null) {
            return false;
        }
        for (Item item : buckets[bucketAddress]) {
            if (item == null) {
                return item == o;
            }
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            int currentBucket = 0;
            int currentItem = 0;
            Iterator<Item> bucketIterator;


            public boolean hasNext() {
                return currentItem < size && currentBucket < bucketCapacity;
            }

            public Item next() {
                if (currentItem == size) {
                    throw new NoSuchElementException();
                }
                currentItem++;
                if (bucketIterator == null || buckets[currentBucket].size() == 0 || !bucketIterator.hasNext()) {
                    do {
                        currentBucket++;
                    } while (buckets[currentBucket] == null || buckets[currentBucket].size() == 0);
                }
                bucketIterator = buckets[currentBucket].iterator();
                return bucketIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int currentItem = 0;
        for (Item i : this) {
            array[currentItem++] = i;
        }
        return array;
    }

    public <T> T[] toArray(T[] a) {
        int currentItem = 0;
        for (Item i : this) {
            a[currentItem++] = (T) i;
        }
        return a;
    }

    public boolean add(Item item) {
        if (this.contains(item)) {
            return false;
        }
        int bucketAddress = calculateBucketAddress(item);
        if (this.buckets[bucketAddress] == null) {
            this.buckets[bucketAddress] = new ArrayList<Item>();
        }
        this.buckets[bucketAddress].add(item);
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (!this.contains(o)) {
            return false;
        }
        int bucketAddress = calculateBucketAddress(o);

        for (Item item : buckets[bucketAddress]) {
            if (item.equals(o)) {
                buckets[bucketAddress].remove(item);
                size--;
                break;
            }
        }


        return true;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }

        return true;
    }

    public boolean addAll(Collection<? extends Item> c) {
        for (Item item : c) {
            this.add(item);
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (Object objectItem : c) {
            if (!this.contains(objectItem)) {
                this.remove(objectItem);
                changed = true;
            }
        }
        return changed;
    }

    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object item : c) {
            this.remove(item);
            modified = true;
        }
        return modified;
    }

    public void clear() {
        size = 0;
        buckets = new List[bucketCapacity];
    }

    private int calculateBucketAddress(Object o) {
        return o == null ? 0 : Math.abs(o.hashCode() % bucketCapacity);
    }

}
