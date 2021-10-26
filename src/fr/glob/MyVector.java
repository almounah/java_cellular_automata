package fr.glob;

public class MyVector {
    public double x;
    public double y;  

    public MyVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(MyVector v) {
        y = y + v.y;
        x = x + v.x;
    }

    public void div(double n) {
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


    @Override
    public String toString() {
        return "{" +
            " x='" + x + "'" +
            ", y='" + y + "'" +
            "}";
    }

  
}
