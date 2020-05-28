package tp_lyp_incluit;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Polynomial extends Lista {

    Lista coef, exp;    //coeficiente y exponente    
    
    public Polynomial() {   // 1. constructor debe crear un objeto Polynomial igual al polinomio 0(cero) (grado n = 0).

        this.coef = new Lista();
        this.exp = new Lista();
        coef.add(0);
        exp.add(0);
    }

    public Polynomial(int coef[]) {  // 2. crear un objeto Polynomial cuyo grado sea igual al tamaño del arreglo coef
        this.coef = new Lista();
        this.exp = new Lista();

        for (int i = 0; i < coef.length; i++) {
            if (coef[i] != 0) {
                this.coef.add(coef[i]);
                this.exp.add(i);
            }
        }
    }
    
    @Override
    public void add(int x){} //sobreescrito para que no haga nada
    
    public Polynomial add(Polynomial pol) { //retorna un Polynomial igual a la suma entre this y pol.
        //es distinto al add() de Lista,este suma 2 pol, retorna y tiene parametros diferentes, No agrega nodos
        
        Polynomial result, mayorPol, menorPol;  //mayor o menor segun la cant. de terminos
        result = new Polynomial();
        
        if( this.coef.getSize() > pol.coef.getSize() ) { 
            mayorPol = this;
            menorPol = pol;
        }
        else if ( this.coef.getSize() < pol.coef.getSize() ) {
            mayorPol = pol;
            menorPol = this;
        }
        else{
            mayorPol = this;
            menorPol = pol;
        }
        
        final int Mp = mayorPol.getSize();
        final int mp = menorPol.getSize();
        boolean sort = false; // indica si es necesario reacomodar los terminos del Polinomio
        
        SearchForCommonExponents:
        for (int i = 0; i < Mp; i++) { 
            for (int j = 0; j < mp; j++) {
                if (mayorPol.exp.get(i) == menorPol.exp.get(j)) {
                    result.coef.add(mayorPol.coef.get(i) + menorPol.coef.get(j));
                    result.exp.add(mayorPol.exp.get(i));
                    continue SearchForCommonExponents;
                }
            }
            
            //no encontro exponentes comunes en el termino con grado i
            result.coef.add(mayorPol.coef.get(i));
            result.exp.add(mayorPol.exp.get(i));
        }
        
        //checkea terminos del menorPol que pueden haberse salteado en las comparaciones y los agrega al resultado
        for (int i = 0; i < mp; i++) {
            if (!PolynomialContainsExponent(menorPol.exp.get(i), result)) {
                result.coef.add(menorPol.coef.get(i));
                result.exp.add(menorPol.exp.get(i));
                sort = true;
            }
        }
        
        result.bubbleSort();    //ordena e invierte el orden de toString
    
        return result;
    }
    
    //public para tests pero pordria ser private
    public boolean PolynomialContainsExponent(int exponent, Polynomial result) {
        
        for (int i = 0; i < result.exp.getSize(); i++) {
            if (exponent == result.exp.get(i)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void bubbleSort(){  //ordena de mayor a menor segun grado, para imprimir al revez(m a M)

        int auxExp, auxCoef;
                                           // -2 para no comparar nunca el valor 0 creado al usar el constructor sin param
        for (int j = 0; j < this.exp.getSize()-2 ; j++) {
          for (int i = 0; i < (this.exp.getSize()-2)- j; i++) {
            if ( this.exp.get(i) < this.exp.get(i+1)){
              auxExp = this.exp.get(i);
              auxCoef = this.coef.get(i);
              
              this.exp.changeValue(i, this.exp.get(i+1));
              this.coef.changeValue(i, this.coef.get(i+1));

              this.exp.changeValue(i+1, auxExp);
              this.coef.changeValue(i+1, auxCoef);
            }
          }
        }
    }
    
    @Override
    public int getSize(){   //en lugar de darle su propio atrib 'size' usa internamente el de sus atrib(exp, coef) que son listas tmb
        return this.coef.getSize();
    }
    

    public int getCoefficient(int x){ //4. Devuelve el valor del coeficiente del grado x
        if ( x < 0 || x >= this.exp.getSize() ) {
            throw new NoSuchElementException();
        }
        
        for (int i = 0; i < this.exp.getSize(); i++) {
            if (this.exp.get(i) == x) {
                return this.coef.get(i);
            }
        }
        
        return 0;
    }
    
    public void setCoefficient(int x, int coef){ // 5. establece el valor del coeficiente de grado x al valor coef
        if ( x < 0 || x >= this.exp.getSize() ) {
            throw new NoSuchElementException();
        }
        
        for (int i = 0; i < this.exp.getSize(); i++) {
            if (this.exp.get(i) == x) {
                this.coef.changeValue(i, coef);
            }
        }
    }
    
    
    public float valueOf(float x){ // 6. calcula y retorna el valor del polinomio en el punto x.

       float result = 0f;
       
        for (int i = 0; i < this.coef.getSize(); i++) {
            result += ( this.coef.get(i) *  Math.pow(x, this.exp.get(i)) );
        }
       
        return result;
    }
    
    @Override
    public boolean equals(Object x){  //7. retorna true si this es igual a x, y false en caso contrario
        
        if ( x instanceof Polynomial ) {
            
            this.bubbleSort();
            ((Polynomial) x).bubbleSort();
            
            if (this.coef.getSize() == ((Polynomial) x).getSize()) {
                
               for (int i = 0; i < this.coef.getSize(); i++) {
                
                    if ( coef.get(i) == ((Polynomial) x).coef.get(i) && (this.exp.get(i) == ((Polynomial) x).exp.get(i)) ) { // == coef && exp
                        if (i+1 == this.coef.getSize()) { //termino de comparar con exito
                            return true;
                        }
                        continue;
                    }
                    else{
                        return false;
                    }
                } 
            }
        }    
        
        return false;
    }
    
    @Override    
    public String toString(){
        StringBuilder result = new StringBuilder("F(x)= ");
        
        for (int i = 0; i < this.getSize(); i++) {
            if (this.coef.get(i) != 0 ) {
                result.append(this.coef.get(i));
                
                if (this.exp.get(i) != 0) {
                    result.append(".X^").append(this.exp.get(i));
                }
                
                if (i+1 != this.coef.getSize() && this.coef.get(i+1) != 0) {
                    result.append(" + ");
                }
            }
        }
        
        return result.toString();
    }
    
    
    
    
    //lois siguientes metodos son unicamente creados para acceder a los atributos desde el Tester que se
    // encuentra en otro package, no pensado ni usado para implementacion.
    public int getCoef(int x) {
        return coef.get(x);
    }

    public int getExp(int x) {
        return exp.get(x);
    }
    
}
