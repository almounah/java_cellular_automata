package fr.glob;

public class MyVector {
    public double x;
    public double y;  

    public MyVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MyVector(MyVector v) {
        this.x = v.x;
        this.y = v.y;
    }


    public static MyVector sub(MyVector a, MyVector b){
        return new MyVector(a.x - b.x, a.y - b.y);
    }

    public static double produitSclaire(MyVector a, MyVector b){
        return a.x*b.x + a.y*b.y;
    }

    public static double angle(MyVector a, MyVector b){
        double value = produitSclaire(a, b) / (a.masse()*b.masse());
        if(value<-1 || value>1) System.out.println("ERREUR: non-logical cos!");
        return java.lang.Math.acos(value)* (180.0/java.lang.Math.PI);
    }

    public double dst(MyVector a){
        MyVector d = sub(this,a);
        return d.masse();
    }

    public void setXY(MyVector a){
        this.x = a.x;
        this.y = a.y;
    }

    public void add(MyVector v) {
        y = y + v.y;
        x = x + v.x;
    }

    public void div(double n) {
        if(n==0.0) return;
        x = x / n;
        y = y / n;
    }

    public void mult(double n) {
        x = x * n;
        y = y * n;
    }

    public double masse(){
        return java.lang.Math.sqrt(x*x + y*y);
    }

    public void normalize(){
        double m = masse();
        if(m!=0) div(m);
    }

    public void limit(double max){
        if(masse() > max){
            normalize();
            mult(max);
        }
    }


    @Override
    public String toString() { // 2 chiffres après la virgule
        String fin = "{x=";
        String s = String.valueOf(x)+"000";
        int ip = s.indexOf('.');
        fin += s.substring(0,ip+3);
        fin += ", y=";

        s = String.valueOf(y)+"000";
        ip = s.indexOf('.');
        fin += s.substring(0,ip+3);
        fin += "}";
        return fin;
    }

  
}
