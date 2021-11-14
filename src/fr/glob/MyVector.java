package fr.glob;

/** This class simulates a vector in the mathematical sense.
 */
public class MyVector {
    /**Value on the x-axis  */
    public double x;
    /**Value on the y-axis  */
    public double y;  

    /** The constructor of MyVector.
     *  @param x The wanted x value
     *  @param y The wanted y value
     * */
    public MyVector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    /** The copy constructor of MyVector.
     *  @param v The vector to clone
     * */
    public MyVector(final MyVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    /** Make a subtraction between two vector 'a' and 'b'.
     * @param a
     * @param b
     * @return The new MyVector (a-b)
     * */
    public static MyVector sub(final MyVector a, final MyVector b) {
        return new MyVector(a.x - b.x, a.y - b.y);
    }

    /** Compute the sclar product of two vector 'a' and 'b'.
     * @param a
     * @param b
     * @return the value of the scalar product
     * */
    public static double produitSclaire(final MyVector a, final MyVector b) {
        return a.x * b.x + a.y * b.y;
    }

    /** Compute the angle between two vector 'a' and 'b'.
     * @param a
     * @param b
     * @return the value of the angle [0°,180°]
     * */
    public static double angle(final MyVector a, final MyVector b) {
        double value = produitSclaire(a, b) / (a.masse() * b.masse());
        if (value < -1 || value > 1) {
            System.out.println("ERREUR: non-logical cos!");
        }
        return java.lang.Math.acos(value) * (180.0 / java.lang.Math.PI);
    }

    /** Compute the distance between two point represented by this MyVector and 'a'.
     * @param a second position.
     * @return the distance between this vector and 'a'
     * */
    public final double dst(final MyVector a) {
        MyVector d = sub(this, a);
        return d.masse();
    }

    /** Seter, copy the x and y field of a as field of this vector
     * @param a the vector to copy
     * */
    public final void setXY(final MyVector a) {
        this.x = a.x;
        this.y = a.y;
    }

    /** Add 'a' to this MyVector
     * @param a the vector to add
     * */
    public final void add(final MyVector v) {
        y = y + v.y;
        x = x + v.x;
    }

    /** Divide this Vector by a scalar 'n'
     * @param n value used to divide
     * */
    public final void div(final double n) {
        if (n == 0.0) {
            return;
        }
        x = x / n;
        y = y / n;
    }

    /** Multiply this Vector by a scalar 'n'
     * @param n value used to multiply
     * */
    public final void mult(final double n) {
        x = x * n;
        y = y * n;
    }

    /** Compute the norm (or mass) of this MyVector
     * @return The norm of this vector
     * */
    public final double masse() {
        return java.lang.Math.sqrt(x * x + y * y);
    }

    /** Normalize the norm of this vector to 1
     * */
    public final void normalize() {
        double m = masse();
        if (m != 0) {
            div(m);
        }
    }

    /** Limit the norm of this vector to 'max'
     * @param max The limit used
     * */
    public final void limit(final double max) {
        if (masse() > max) {
            normalize();
            mult(max);
        }
    }

    /** The toString method.
     * @return the string representing the vector.
     */
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
