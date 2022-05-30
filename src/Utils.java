public class Utils {

    // P je prost broj, g je primitivni koren broja p
    public static int p = 23;
    public static int g = 5;

    public static int brzoStepenovanjeMod(int a, int exp) {
        if(exp == 0) return 1;
        if(exp == 1) return a%p;
        int m = brzoStepenovanjeMod(a, exp/2);
        m = (m*m)%p;
        if(exp % 2 == 1) m = (m*a)%p;
        return m;
    }
}
