package ua.artstood.first_task;


import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HashSetImplTest {

    @Test
    public void testSize() {
        Set<String> set = new HashSetImpl<String>();

        assertEquals(0, set.size());

        set.add("Hello");
        set.add("World");
        set.add("!");

        int expectedSize = 3;
        assertEquals(expectedSize, set.size());

        set.remove("Hello");
        expectedSize = 2;
        assertEquals(expectedSize, set.size());

        set.add("!");
        assertEquals(expectedSize, set.size());
    }

    @Test
    public void addNull() {
        Set<String> set = new HashSetImpl<String>();
        set.add(null);
        assertTrue(set.contains(null));
    }

    @Test
    public void testIsEmpty() {
        Set<String> set = new HashSetImpl<String>();

        assertTrue(set.isEmpty());

        set.add("world");

        assertFalse(set.isEmpty());

        set.remove("world");

        assertTrue(set.isEmpty());
    }

    @Test
    public void testContains() {
        Set<Integer> set = new HashSetImpl<Integer>(16);

        set.add(1);

        assertTrue(set.contains(1));
        set.remove(1);
        assertFalse(set.contains(1));
        assertFalse(set.contains("Hello"));
        assertFalse(set.contains(null));

        set.add(1);
        set.add(17);// 1 and 17 generates the same hash for hashSetImpl
        assertTrue(set.contains(17));
        set.remove(1);
        assertTrue(set.contains(17));
    }

    @Test
    public void testIterator() {
        Set<Integer> set = new HashSetImpl<Integer>();
        set.add(1);
        set.add(3);
        set.add(-22);
        set.add(7);
        set.add(-10);
        set.add(-10);
        Iterator<Integer> iterator = set.iterator();
        int sum = 0;
        int expected = -21;

        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        assertEquals(expected, sum);

        sum = 0;
        for (Integer item : set) {
            sum += item;
        }
        assertEquals(expected, sum);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElement() {
        Set<Integer> set = new HashSetImpl<Integer>();
        set.add(1);
        set.add(3);
        set.add(-22);
        set.add(7);
        set.add(-10);
        set.add(-10);
        Iterator<Integer> iterator = set.iterator();

        while (true) {
            iterator.next();
        }
    }

    @Test
    public void testToArray() {
        Set<Integer> set = new HashSetImpl<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        Object[] actual = set.toArray();
        Object[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testToArrayWithParameter() {
        Set<Integer> set = new HashSetImpl<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        Integer[] firstArray = new Integer[set.size()];
        Integer[] secondArray = set.toArray(firstArray);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, firstArray);
        assertSame(firstArray, secondArray);

        expected = new Integer[]{1, 2, 3, 4, 5, null};
        Integer[] actual = new Integer[set.size() + 1];
        set.toArray(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testContainAll() {
        Set<String> set = new HashSetImpl<String>();
        List<String> list = new ArrayList<String>();

        set.add("Hello");
        set.add("World");
        set.add("Beautiful");
        set.add("!");


        list.add("Hello");
        list.add("World");
        list.add("!");
        assertTrue(set.containsAll(list));

        list.add("Welcome");
        assertFalse(set.containsAll(list));

        assertTrue(set.containsAll(new LinkedList<String>()));

        List<Pair> anotherClassList = new ArrayList<Pair>();
        anotherClassList.add(new Pair(0, 0));
        assertFalse(set.containsAll(anotherClassList));
    }

    @Test
    public void testAddAll() {
        List<Long> list1 = new ArrayList<Long>();
        List<Integer> list2 = new ArrayList<Integer>();
        Set<Number> set = new HashSetImpl<Number>();
        list1.add(10L);
        list1.add(20L);
        list2.add(20);
        list2.add(30);
        list2.add(40);

        set.addAll(list1);
        set.addAll(list2);
        long sum = 0;
        long expected = 100L;
        for (Number number : set) {
            sum += number.longValue();
        }
        assertEquals(expected, sum);
    }

    @Test
    public void testRemoveAll() {
        Set<Integer> set = new HashSetImpl<Integer>();
        List<Integer> removeList = new ArrayList<Integer>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        removeList.add(3);
        removeList.add(2);
        set.removeAll(removeList);

        int sum = 0;
        int expected = 10;
        for (Integer integer : set) {
            sum += integer;
        }
        assertEquals(expected, sum);
    }






}