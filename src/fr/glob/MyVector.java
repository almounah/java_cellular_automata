package fr.glob;

public class MyVector {
    public double x;
    public double y;  

    public MyVector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public MyVector(final MyVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public static MyVector sub(final MyVector a, final MyVector b) {
        return new MyVector(a.x - b.x, a.y - b.y);
    }

    public static double produitSclaire(final MyVector a, final MyVector b) {
        return a.x * b.x + a.y * b.y;
    }

    public static double angle(final MyVector a, final MyVector b) {
        double value = produitSclaire(a, b) / (a.masse() * b.masse());
        if (value < -1 || value > 1) {
            System.out.println("ERREUR: non-logical cos!");
        }
        return java.lang.Math.acos(value) * (180.0 / java.lang.Math.PI);
    }

    public final double dst(final MyVector a) {
        MyVector d = sub(this, a);
        return d.masse();
    }

    public final void setXY(final MyVector a) {
        this.x = a.x;
        this.y = a.y;
    }

    public final void add(final MyVector v) {
        y = y + v.y;
        x = x + v.x;
    }

    public final void div(final double n) {
        if (n == 0.0) {
            return;
        }
        x = x / n;
        y = y / n;
    }

    public final void mult(final double n) {
        x = x * n;
        y = y * n;
    }

    public final double masse() {
        return java.lang.Math.sqrt(x * x + y * y);
    }

    public final void normalize() {
        double m = masse();
        if (m != 0) {
            div(m);
        }
    }

    public final void limit(final double max) {
        if (masse() > max) {
            normalize();
            mult(max);
        }
    }

    @Override
    public final String toString() { // 2 chiffres après la virgule
        String fin = "{x=";
        String s = String.valueOf(x) + "000";
        int ip = s.indexOf('.');
        fin += s.substring(0, ip + 3);
        fin += ", y=";

        s = String.valueOf(y) + "000";
        ip = s.indexOf('.');
        fin += s.substring(0, ip + 3);
        fin += "}";
        return fin;
    }

  
}
