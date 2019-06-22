package jvm;

import java.util.ArrayList;
import java.util.List;

public class StringOOMMock {

    static String string = "String";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = string + string;
            list.add(str.intern());
        }
    }
}
