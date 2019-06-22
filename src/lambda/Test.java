package lambda;

public class Test {

    public static void main(String[] args) {
        int i = -1;
        int j = 1;
        printBinary(i);
        printBinary(j);
        printBinary(i ^ j);
        printBinary(i & j);
        printBinary(i | j);

        i >>>= 10;
        printBinary(i);
        long l = -1;
        printBinary(l);
        l >>>= 10;
        printBinary(l);
        l <<= 10;
        printBinary(l);
        short s = -1;
        printBinary(s);
        s >>>= 10;
        printBinary(s);
        byte b = -1;
        printBinary(b);
        b >>>= 10;
        printBinary(b);
        b = -1;
        printBinary(b);
        printBinary(b >>> 10);
    }

    public static void printBinary(int i) {
        System.out.println(Integer.toBinaryString(i));
    }

    public static void printBinary(long i) {
        System.out.println(Long.toBinaryString(i));
    }

    public static void printBinary(short i) {
        System.out.println(Integer.toBinaryString(i));
    }

    public static void printBinary(byte i) {
        System.out.println(Integer.toBinaryString(i));
    }
}
