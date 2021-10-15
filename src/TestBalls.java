public class TestBalls {
    public static void main(String[] args) {
        Balls b = new Balls(5, 100);
        System.out.println("Voici mes balls -> " + b);

        System.out.println("Mooving balls ...");
        b.translate(2, 10);
        System.out.println("Mooved balls là -> " + b);
        System.out.println("Copyed balls là -> " + b.toStringCP());

        System.out.println("Reinit ...");
        b.reInit();
        System.out.println("Voici mes balls -> " + b);
    }
}
