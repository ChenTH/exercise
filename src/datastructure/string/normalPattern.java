package datastructure.string;

public class normalPattern {
    public static void main(String[] args) {
        String main = "afsdfasawersavfdbfgerwerlakjfiodsjioboinsoidfnoiwejoi";
        String part = "rlakjf";
        for (int i = 0; i < main.length() - part.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < part.length(); j++) {
                if (main.charAt(i + j) != part.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i);
                break;
            }
        }
    }
}
