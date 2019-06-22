package designpattern;

public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {

    }

    public Singleton getInstance() {
        return instance;
    }
}
