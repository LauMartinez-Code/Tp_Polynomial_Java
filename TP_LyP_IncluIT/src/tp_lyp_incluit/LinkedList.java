package tp_lyp_incluit;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements List {
    
    private int size;
    private Nodo root;
    

    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public boolean isEmpty(){
        return size == 0; 
    }
    
    @Override
    public void add(int x){
        Nodo n = new Nodo(x);
        n.next = root;
        root = n;
        size++;
    }
    
    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        
        Nodo p = root;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        
        return p.value;    
    }
    
    public void changeValue(int index, int value) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        
        Nodo p = root;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        
        p.value = value;    
    }
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        Nodo aux = root;
        
        while(aux != null) {            
            result.append(aux.value).append(", ");
            aux = aux.next;
        }
        result.append("]");
        
        return result.toString();
    }
    
    public Iterator iterator(){
        return new ListIterator(root);
    }
}

class Nodo {
    int value;
    Nodo next;

    public Nodo(int value) {
        this.value = value;
    }
}    

class ListIterator implements Iterator{
    
    private Nodo actual;

    public ListIterator(Nodo actual) {
        this.actual = actual;
    }
    
    @Override
    public boolean hasNext(){
        return actual != null;
    }
    
    @Override
    public Object next(){
        int value = actual.value;
        actual = actual.next;
        return value;
    }
}