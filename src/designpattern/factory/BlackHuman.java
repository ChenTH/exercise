package designpattern.factory;

public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("black");
    }

    @Override
    public void talk() {
        System.out.printf("cannot know");
    }
}
