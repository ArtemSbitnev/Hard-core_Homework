package mylist;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 05.11.13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class MyListTest {
    @Test
    public void testAdd() {
        MyList<Integer> list = new MyList<Integer>();
        list.add(5);
        list.add(3);
        Integer res = list.get(2);
        assertEquals(new Integer(3), res);
    }

    @Test
    public void testRemove() {
        MyList<Integer> list = new MyList<Integer>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.remove(new Integer(3));
        Integer res = list.get(2);
        assertEquals(new Integer(8), res);
    }

    @Test
    public void TestRemoveInd() {
        MyList<Integer> list = new MyList<Integer>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.remove(2);
        Integer res = list.get(2);
        assertEquals(new Integer(8), res);
    }

    @Test
    public void TestSize() {
        MyList<Integer> list = new MyList<Integer>();
        list.add(5);
        list.add(3);
        list.add(8);
        Integer res = list.size();
        assertEquals(new Integer(3), res);
    }

    @Test
    public void TestContains() {
        MyList<Integer> list = new MyList<Integer>();
        list.add(5);
        list.add(3);
        list.add(8);
        Boolean res = list.contains(3);
        assertEquals(Boolean.TRUE, res);
    }

    @Test
    public void TestSort() {
        MyList<Integer> list = new MyList<Integer>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(13);
        list.add(8);
        list.mergeSort();
        Boolean res = (list.get(1) == 3 && list.get(2) == 5 && list.get(3) == 8 && list.get(4) == 8 && list.get(5) == 13);
        assertEquals(Boolean.TRUE, res);
    }
}
