package designpatterns.behavioral;

import java.util.*;

/**
 * Used to iterator over the collection objects without exposing underlying representation
 */
public class IteratorDP {
    public static void main(String[] args) {
        SmartArrayList smartArrayList = new SmartArrayList(10);
        for (int i = 1; i <= 10; i++) {
            smartArrayList.addElement(i*i);
        }
        smartArrayList.print();
        for (int i = 0; i < 4; i++) {
            smartArrayList.removeLastElement();
        }
        smartArrayList.print();

        // display elements using iterators
        Iterator iterator = smartArrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

class SmartArrayList {

    private Integer[] integers;
    private int size = 0;
    private int index = -1;

    public SmartArrayList(int capacity){
        size = capacity;
        integers = new Integer[size];
    }

    public void addElement(Integer ele){
        if(index < size-1){
            integers[++index] = ele;
        }
    }

    public void removeLastElement(){
        if(index >= 0){
            index--;
        }
    }

    public void print(){
        System.out.print("[");
        for (int i = 0; i <= index; i++) {
            System.out.print(integers[i] + " ");
        }
        System.out.println("]");
    }

    public Iterator iterator(){
        return new SmartALIterator();
    }

    private class SmartALIterator implements Iterator {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor <= index;
        }

        @Override
        public Object next() {
            if(cursor <= index){
                return integers[cursor++];
            }
            return null;
        }
    }

}