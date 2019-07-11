package org.lhx.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author lhx
 * @date 2019/7/10 - 15:42
 */
public class HuffmanCode {

    static StringBuilder stringBuilder = new StringBuilder();

    static Map<Byte, String> huffmanCodes = new HashMap<>();

    /*
     * 生成哈夫曼树对应的哈夫曼编码
     * 将哈夫曼编码表存放在Map<Byte, String>
     * 再生成哈夫曼编码表示，需要拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
     *
     */

    public static void main(String[] args) {
        String zipFile = "d://dst.zip";
        String dstFile = "d://all2.docx";
        unZipFile(zipFile, dstFile);
//        String srcFile = "d://all.docx";
//        String dstFile = "d://dst.zip";
//        zipFile(srcFile, dstFile);
//        String str = "i like like like java do you like a java";
////        byte[] strBytes = str.getBytes();
////        System.out.println(strBytes.length);
////        List<Node> nodes = getNodes(strBytes);
////        System.out.println(nodes);
////        Node huffmanTree = createHuffmanTree(nodes);
////        System.out.println();
////        preOrder(huffmanTree);
////
////        Map<Byte, String> codes = getCodes(huffmanTree);
////        System.out.println(codes);
////
////        byte[] bytes = zip(strBytes, codes);
////        System.out.println(Arrays.toString(bytes));
//        byte[] bytes = str.getBytes();
//        byte[] huffmanZIP = huffmanZIP(bytes);
//        System.out.println(Arrays.toString(huffmanZIP));
//        byte[] decode = decode(huffmanCodes, huffmanZIP);
//        System.out.println(new String(decode));
    }

    /**
     * 完成对文件的解压
     * @param zipFile 准备解压的文件
     * @param dstFile 解压后文件存放的路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            //读取byte数组
            byte[] hufamanBytes = (byte[]) objectInputStream.readObject();
            //读取哈夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>)objectInputStream.readObject();
            //解码
            byte[] decode = decode(huffmanCodes, hufamanBytes);
            //将byte数组写入到文件中
            outputStream = new FileOutputStream(dstFile);
            //写数据
            outputStream.write(decode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将文件进行压缩
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param dstFile 将压缩后的文件放入到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输入流
        FileInputStream fileInputStream = null;
        OutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[fileInputStream.available()];
            //读取文件
            fileInputStream.read(b);
            //直接对源文件压缩
            byte[] bytes = huffmanZIP(b);
            //创建文件输出流，存放压缩文件
            fileOutputStream = new FileOutputStream(dstFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //把哈夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(bytes);
            //以对象流的方式写入哈夫曼编码。为了以后恢复原文件时使用
            objectOutputStream.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param huffmanCodes 哈夫曼编码表 map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte[]转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitStrting(!flag, huffmanBytes[i]));
        }
        //把字符串按照指定的哈夫曼编码进行解码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            //计数器
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转为二进制字符串
     *
     * @param flag 标识是否需要补高位 true表示需要，false表示不需要,如果是最后一个字节，无需补高位
     * @param b    传入的byte
     * @return 该b对应的二进制字符串，按补码返回
     */
    private static String byteToBitStrting(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp);
        if (flag) {
            return string.substring(string.length() - 8);
        } else {
            return string;
        }
    }

    /**
     * @param bytes 原始字符串对应的byte[]
     * @return 经过哈夫曼编码处理后的字节树
     */
    private static byte[] huffmanZIP(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTree = createHuffmanTree(nodes);
        Map<Byte, String> codes = getCodes(huffmanTree);
        byte[] huffmanCodeBytes = zip(bytes, codes);
        return huffmanCodeBytes;
    }

    /**
     * 编写一个将字符串转为对应byte数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
     *
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的哈夫曼编码
     * @return 返回哈夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //统计返回byte[] 的长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        //记录是第几个byte
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将 strByte 转成一个byte放入huffmanCodeBytes[]
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码得到并放入huffmanCoses集合
     *
     * @param node          传入结点
     * @param code          路径： 左子节点是0 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBulider2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前结点是否是叶子节点
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //说明是叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        //遍历bates，统计每个字符出现的次数存入map中
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
