package UTest;

import java.util.Iterator;
import org.junit.Before;
import tp_lyp_incluit.Lista;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import tp_lyp_incluit.Polynomial;

public class Tester {
    
    private Lista lista;
    private Polynomial polym, polym1, polym2, polym3,polym4, result1,result2;
    
    int a[] = {2,6,8,9};
    int b[] = {3,5,6,7};
    int c[] = {5,1,3,2,4};
    int d[] = {12,0,5,3};
    
    public Tester() {
    }
    
    @Before
    public void setUp() {
        lista = new Lista();
        polym = new Polynomial();
        polym1 = new Polynomial(a);
        polym2 = new Polynomial(b);
        polym3 = new Polynomial(c);
        polym4 = new Polynomial(d);
        
        result1 = polym1.add(polym2);
        result2 = polym3.add(polym4);
    }
    
    @Test
    public void testIsZeroInitially(){
        assertEquals( 0, lista.getSize() );
    }
    
    @Test
    public void testListIsEmpty() {
        assertTrue(lista.isEmpty());
    }
    
    @Test
    public void testEmptyIsFalseWhenElementsAreAdded() {
        lista.add(1);
        assertFalse(lista.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testGetNegativeNumbersFails() {
        lista.get(-1);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testGetInvalidIndexFails() {
        lista.get(0);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testGetInvalidIndexFails2() {
        lista.get(5);
    }
    
    @Test
    public void testChangeValue(){
        lista.add(5);
        lista.changeValue(0, 10);
        assertEquals(lista.get(0), 10);
    }
    
    @Test
    public void testChangeValueTwice(){
        lista.add(5);//1 index
        lista.add(3);//0
        assertEquals(lista.get(0), 3);
        assertEquals(lista.get(1), 5);
        lista.changeValue(1, 10);
        lista.changeValue(0, 7);
        assertEquals(lista.get(0), 7);
        assertEquals(lista.get(1), 10);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testChangevalueNegativeNumbersFails() {
        lista.changeValue(-1, 0);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testChangevalueInvalidIndexFails() {
        lista.changeValue(0, 0);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testChangevalueInvalidIndexFails2() {
        lista.changeValue(5, 0);
    }
    
    @Test
    public void testToStringEmptyList() {
        assertEquals("[]", lista.toString());
    }
    
    @Test
    public void testToStringNotEmptyList() {
        lista.add(3);
        lista.add(5);
        assertEquals( "[5, 3, ]", lista.toString());
    }               //LIFO
    
    @Test
    public void testToStringNotEmptyListTwice() {
        lista.add(3);
        lista.add(5);
        assertEquals("[5, 3, ]", lista.toString());
        assertEquals("[5, 3, ]", lista.toString());
    }
   
    
    @Test
    public void testGet() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }
        assertEquals(9, lista.get(0));
        assertEquals(4, lista.get(5));
        assertEquals(0, lista.get(9));
    }
    
    @Test
    public void testIterate() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        int expected = 9;
        Iterator it = lista.iterator();
        
        while(it.hasNext()) {
            int x = (int) it.next();
            assertEquals(expected, x);
            expected--;
        }
        assertEquals(-1, expected);
    }  
    /////////////////Tests Polynomial\\\\\\\\\\\\\\\\\
    
    @Test
    public void testConstructorVoidCreatesPolynomialGrade0(){
        assertEquals(0, polym.getCoef(0));
        assertEquals(0, polym.getExp(0));
    }
    
    @Test
    public void testConstructorWithParamsCreatesPolynomialOK(){
        //instancia creada con el array 'a' declarado al comienzo de la clase
        assertEquals(2, polym1.getCoef(3));
        assertEquals(0, polym1.getExp(3));
        assertEquals(6, polym1.getCoef(2));
        assertEquals(1, polym1.getExp(2));
        assertEquals(8, polym1.getCoef(1));
        assertEquals(2, polym1.getExp(1));
        assertEquals(9, polym1.getCoef(0));
        assertEquals(3, polym1.getExp(0));
        
    }
    
    @Test
    public void testAddBetween2PolynomialsANDtoString(){
        
        //result1 = polym1.add(polym2);
        assertEquals("F(x)= 9.X^3 + 8.X^2 + 6.X^1 + 2", polym1.toString());
        assertEquals("F(x)= 7.X^3 + 6.X^2 + 5.X^1 + 3", polym2.toString());
        assertEquals("F(x)= 16.X^3 + 14.X^2 + 11.X^1 + 5", result1.toString());
    }
    
    @Test
    public void testBubbleSort(){
        //result2 = polym3.add(polym4);
        //assertEquals("F(x)= 17 + 1.X^1 + 8.X^2 + 5.X^3 + 4.X^4", result2.toString()); sin implementar el sort
        assertEquals("F(x)= 4.X^4 + 5.X^3 + 8.X^2 + 1.X^1 + 17", result2.toString());
    }
    
    @Test
    public void testPolymomialContainsExponents(){
        assertTrue(result1.PolynomialContainsExponent(3, result1 ));
    }
    
    @Test
    public void testGetCoefficient(){
        assertEquals(5, result2.getCoefficient(3));
    }
    
    @Test
    public void testSetCoefficient(){
        result1.setCoefficient(3, 8);
    }
    
    @Test
    public void testValueOf(){

        assertEquals(211.0, result1.valueOf(2), 0);
        assertEquals(370.0, result1.valueOf(2.5f), 0);
        
    }
    
    @Test
    public void testEquals(){
        Polynomial res;
        res = polym3.add(polym4);   //misma def. que result2
        
        assertFalse(result2.equals(result1));
        result1 = result2;
        
        assertTrue(result2.equals(result1));
        assertTrue(res.equals(result2));
    }
}






