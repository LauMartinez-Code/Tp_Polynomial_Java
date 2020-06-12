package tp_lyp_incluit;

public interface List {

    int getSize();
    
    boolean isEmpty();
    
    void add(int x);
    
    int get(int index);

    @Override
    String toString();
}