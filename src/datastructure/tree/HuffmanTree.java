package datastructure.tree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanTree {
    private HuffmanTree.Node[] nodes;
    private HuffmanTree.Node root;
    private HashMap<String, HuffmanTree.Node> nodeMap;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请依次输入哈夫曼树节点标识，以单个空格区分：");
        String nameLine = scanner.nextLine();
        String[] names = nameLine.split(" ");
        System.out.println("请依次输入哈夫曼树节点权值，以单个空格区分：");
        String scoreLine = scanner.nextLine();
        String[] scoreStrs = nameLine.split(" ");
        int[] scores = new int[names.length];
        for (int i = 0; i < scoreLine.length(); i++) {
            scores[i] = Integer.parseInt(scoreStrs[i]);
        }
        Node[] nodes = new Node[names.length];
        for (int i = 0; i < names.length; i++) {
            Node node = new HuffmanTree.Node();
            node.name = names[i];
            node.score = scores[i];
            nodes[i] = node;
        }
        HuffmanTree huffmanTree = new HuffmanTree(nodes);

    }

    public HuffmanTree(HuffmanTree.Node[] nodes) {
        this.nodes = nodes;
        this.nodeMap = new HashMap<>(nodes.length);
        for (HuffmanTree.Node node : nodes) {
            nodeMap.put(node.name, node);
        }
        create();
    }

    /***
     * 创建哈夫曼树
     */
    private void create() {
        //todo
    }

    /***
     * 获取哈夫曼编码
     * @param name 节点名称
     * @return 哈夫曼编码
     */
    public String getHuffmanCode(String name) {
        //todo
        return "";
    }

    /***
     * 获取所有哈夫曼编码
     */
    public void getAllHuffmanCodes() {
        //todo
    }

    public void minHeapSort(HuffmanTree.Node[] array) {
        for (int i = array.length / 2 + 1; i >= 0; i--) {
            adjustMinHeap(array, i, array.length);
        }
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            adjustMinHeap(array, 0, i);
        }
        for (int i = 0; i < array.length / 2; i++) {
            swap(array, i, array.length - 1 - i);
        }
    }

    private void adjustMinHeap(HuffmanTree.Node[] array, int beg, int end) {
        for (int i = beg * 2 + 1; i < end; i = i * 2 + 1) {
            if (i + 1 < end && array[i].score > array[i + 1].score) {
                i = i + 1;
            }
            if (array[beg].score > array[i].score) {
                swap(array, i, beg);
                beg = i;
            } else {
                return;
            }
        }
    }

    private void swap(HuffmanTree.Node[] array, int i, int j) {
        HuffmanTree.Node temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static class Node {
        String name;
        int score;
        Node left;
        Node right;
        Node parent;
    }
}
