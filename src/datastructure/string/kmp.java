package datastructure.string;

public class kmp {
    public static void main(String[] args) {
        String main = "afsdfasawersavfdbfgerwerlakjfiodsjioboinsoidfnoiwejoi";
        String part = "rlakjf";
        System.out.println(kmpPattern(main, part));
    }

    /***
     *
     * @param main 主字符串
     * @param pattern 匹配串
     * @return
     */
    public static int kmpPattern(String main, String pattern) {
        char[] mainChars = main.toCharArray();
        char[] pChars = pattern.toCharArray();
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        while (i < mainChars.length && j < pChars.length) {
            if (mainChars[i] == pChars[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j - 1] + 1;
            }
        }
        return j == pChars.length ? i - pChars.length : -1;
    }

    /***
     *KMP算法next数组的生成原理
     * 设字符串数组为p next数组为n
     * 在p的第j个元素时有
     *         max{i}   p[0....i]=p[j-i ......j],0<=i<j
     * n[j]=
     *           -1                  else
     * 又在匹配j+1处时有️以下两种可能性
     * 1、p[j+1]=p[n[j]+1]  此时令n[j+1]=n[j]+1即可
     * 2、p[j+1]!=p[n[j]+1]  此时需要找到可以满足p[0,....k+1]=p[j-k.......j+1]的k值，又k应存在于n[n[n[....n[j]]]]中
     * 所以
     *            (n^k)[j]+1          p[(n^k)[j]+1]=p[j+1],min{k}
     * n[j+1]=
     *                   -1                    else
     * 得到了生层next的递推公式
     *
     * @param p 需要匹配的字符串
     * @return next数组
     */
    private static int[] getNext(String p) {
        char[] chars = p.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        for (int i = 1; i < chars.length; i++) {
            for (int j = next[i - 1]; ; j = next[j]) {
                if (chars[j + 1] == chars[i]) {
                    next[i] = j + 1;
                    break;
                } else if (j == -1) {
                    next[i] = -1;
                    break;
                }
            }
        }
        return next;
    }
}
