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
    public String toString() {
        return "{" +
            " x='" + x + "'" +
            ", y='" + y + "'" +
            "}";
    }

  
}
