package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionInterfaceTest {


    @Test
    public void testLambda() {
        //使用Lambda表达式代替上面的匿名内部类
        func((Integer x) -> {
            System.out.printf("Hello World!");
            return true;
        });
    }

    @Test
    public void testList() {
        //使用Lambda表达式代替上面的匿名内部类
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        idList.add(4);
        idList.add(5);
        idList.add(6);
        idList.add(7);
        long count = idList.stream().filter(Integer -> Integer % 2 == 0).count();
        System.out.printf(Long.toString(count));
    }

    private void func(FunctionInterface<Integer> functionInterface) {
        int a = 1;
        functionInterface.test(a);
    }
}
