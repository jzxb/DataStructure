package test;

import java.util.Scanner;

/**
 * @author lhx
 * @date 2019/6/30 - 19:47
 */
public class Candy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stuNum = sc.nextInt();
        int[] stuScore = new int[stuNum];
        for (int i = 0; i < stuNum; i++) {
            stuScore[i] = sc.nextInt();
        }
        System.out.println(candys(stuScore));
    }

    public static int candys(int[] stuScore) {
        int[] temp = new int[stuScore.length + 2];
        temp[0] = -1;
        temp[temp.length - 1] = -1;
        for (int i = 1; i <= stuScore.length; i++) {
           temp[i] = stuScore[i - 1];
        }
        int[] candyNums = new int[stuScore.length];
        int index = 0;
        int candyNum = 0;
        for (int i = 1; i <= stuScore.length; i++) {
            if (temp[i] > temp[i - 1]) {
                candyNum++;
                candyNums[index] = candyNum;
            } else if (temp[i] < temp[i - 1]) {
                candyNum--;
                candyNums[index] = candyNum;
            } else {
                candyNums[index] = candyNum;
            }
            index++;
        }
        int res = 0;
        for (int i = 0; i < candyNums.length; i++) {
            res += candyNums[i];
        }
        return res;
//        int min = stuScore[0];
//        int minIndex = 0;
//        for (int i = 1; i < stuScore.length; i++) {
//            if (stuScore[i] < min) {
//                min = stuScore[i];
//                minIndex = i;
//            }
//        }
//        int res = 0;
//        if (minIndex == 0) {
//            res = candy(stuScore, minIndex, 1, minIndex);
//        } else if (minIndex == stuScore.length - 1) {
//            res = candy(stuScore, minIndex, 1, minIndex);
//        } else {
//            res = candy(stuScore, minIndex, 1, minIndex);
//        }
//        return res;
    }

//    public static int candy(int[] stuScore, int index, int candy , int minIndex) {
//        int res = candy;
//        if (index < minIndex) {
//            if (stuScore[index - 1] > stuScore[index]) {
//                res += candy(stuScore, index - 1, candy + 1, minIndex);
//            } else if (stuScore[index - 1] < stuScore[index]) {
//                res += candy(stuScore, index - 1, candy - 1, minIndex);
//            } else {
//                res += candy(stuScore, index - 1, candy, minIndex);
//            }
//        } else if (index > minIndex) {
//            if (stuScore[index + 1] > stuScore[index]) {
//                res += candy(stuScore, index + 1, candy + 1, minIndex);
//            } else if (stuScore[index + 1] < stuScore[index]) {
//                res += candy(stuScore, index + 1, candy - 1, minIndex);
//            } else {
//                res += candy(stuScore, index + 1, candy, minIndex);
//            }
//        } else {
//            if (stuScore[index - 1] > stuScore[index]) {
//                res += candy(stuScore, index - 1, candy + 1, minIndex);
//            } else if (stuScore[index - 1] < stuScore[index]) {
//                res += candy(stuScore, index - 1, candy - 1, minIndex);
//            } else {
//                res += candy(stuScore, index - 1, candy, minIndex);
//            }
//            if (stuScore[index + 1] > stuScore[index]) {
//                res += candy(stuScore, index + 1, candy + 1, minIndex);
//            } else if (stuScore[index + 1] < stuScore[index]) {
//                res += candy(stuScore, index + 1, candy - 1, minIndex);
//            } else {
//                res += candy(stuScore, index + 1, candy, minIndex);
//            }
//        }
//        return res;
//    }

}
