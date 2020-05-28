package tp_lyp_incluit;

public class Program {

    public static void main(String[] args) {
        // ejemplos utilizados para los tests, solo para pruebas
        
          int a[] = {2,6,8,9};
          int b[] = {3,5,6,7};
          int c[] = {5,1,3,2,4};
          int d[] = {12,0,5,3};
          int e[] = {5,0,3,0,2,4,7};
          int f[] = {10,5,2,3,4};
          int g[] = {12,0,1,0,3};
          int h[] = {0,5,0,2};
          Polynomial polim1 = new Polynomial(a);
          Polynomial polim2 = new Polynomial(b);
          Polynomial result1,result2,result3,result4, res;
          Polynomial polim3 = new Polynomial(c);
          Polynomial polim4 = new Polynomial(d);
          Polynomial polim5 = new Polynomial(e);
          Polynomial polim6 = new Polynomial(f);          
          Polynomial polim7 = new Polynomial(g); 
          Polynomial polim8 = new Polynomial(h); 
          result1 = polim1.add(polim2);
          result2 = polim3.add(polim4);
          res = polim3.add(polim4);
          result3 = polim5.add(polim6);
          result4 = polim7.add(polim8);
          
          System.out.println(result1);
          System.out.println(result2);
          
          int as = result4.getCoefficient(4);
          int sda = result3.getCoefficient(0);
          
         result1.setCoefficient(3, 8);
         result1.setCoefficient(1, 2);
          
          float test = result1.valueOf(2.5f);
          
           boolean x = result2.equals(result1);
           result1 = result2;
           boolean y = result2.equals(result1);
           boolean dss = res.equals(result2);
       
         

          System.out.println(polim1);
          System.out.println(polim2);
          System.out.println(result1);
          System.out.println(result2);
          System.out.println(result3);
          System.out.println(result4);
          System.out.println(res);

    }
    
}
