package lambda;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTest {

    public static void main(String[] args) {

    }

    @Test
    public void limitTest() {
        String a = "1";
        //   主要用来限制显示的量：eg:
        //该random函数若是不传递参数，那么就采用当前时间的毫秒数当做种子数，若是传递了参数，就用传递的数字作为种子数了，但是这样的话，生成的随机数就是伪随机数，虽然随机，但是点几次，都基本一样，因为传入的种子数限制了函数的选择性
        Random random = new Random();
        //受到limit限制，只会随机显示10个数字，因为没有传递参数，那么每次点击都会不一样，否则，若传递了种子数，点击几次都一样
        random.ints().limit(10).forEach(System.out::println);
    }

    @Test
    public void forEachTest() {
        // 用jdk8来遍历集合效果很快
        List<String> list = Arrays.asList("a", "ad", "dr");
        list.stream().forEach(System.out::println);
        //或者如下也可以
        list.stream().forEach(a -> System.out.println(a));
        //或者不创建流也可以直接使用函数
        list.forEach(System.out::println);
        //或者
        list.forEach(a -> System.out.println(a));
    }

    @Test
    public void mapTest() {
        // 主要用来对传入的参数进行逻辑处理
        //用数组来转换集合
        List<Integer> list = Arrays.asList(9, 3, 3);
        //distinct()函数，是去重复函数
        list = list.stream().distinct().map(i -> i * i).collect(Collectors.toList());
        //打印输出list
        list.forEach(System.out::println);
    }

    @Test
    public void filterTest() {
        //用来过滤所需要的数据
        List<String> list = Arrays.asList("1", "sd");
        list = list.stream().filter(i -> !i.equals("1")).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    @Test
    public void statsTest() {
        List<Integer> list = Arrays.asList(12, 34, 23, 12, 3, 34);
        IntSummaryStatistics stats = list.stream().mapToInt(x -> x).summaryStatistics();
        //最大值
        System.out.println(stats.getMax());

        //最小值
        System.out.println(stats.getMin());

        //平均值
        System.out.println(stats.getAverage());

        //总数
        System.out.println(stats.getCount());

        //总和
        System.out.println(stats.getSum());

    }

    @Test
    public void reduceTest() {
//        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
//        System.out.println(s.reduce((s1, s2) -> s1.concat(s2)).get());
//        s = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
//        System.out.println(s.reduce("[value]", (s1, s2) -> s1.concat(s2)));
//        System.out.println(Stream.of(1, 2, 3).reduce((x, y) -> x + y).get());
//        System.out.println(Stream.of(1, 2, 3).reduce(10, (x, y) -> x + y).intValue());
//        System.out.println(Stream.of(1, 2, 3).map(n -> n + 4).reduce((s1, s2) -> s1 + s2).get());
//        System.out.println(Stream.of(1, 2, 3).parallel().reduce(4, (x, y) -> x + y, (x, y) -> x + y));
//        Stream.of("aa", "ab", "c", "ad").parallel().reduce(new ArrayList<String>(), (r, t) -> {
//                    if (t.contains("a")) r.add(t);
//                    return r;
//                },
//                (r1, r2) -> {
//                    System.out.println(r1 == r2);
//                    return r2;
//                }).forEach(System.out::println);

        Stream.of("aa", "ab", "c", "ad").parallel().reduce(new ArrayList<String>(), (r, t) -> {
                    if (t.contains("a")) r.add(t);
                    return r;
                },
                (r1, r2) -> {
                    r1.addAll(r2);
                    return r1;
                }).forEach(System.out::println);
    }
}
