package test;

import java.util.Scanner;

/**
 * @author lhx
 * @date 2019/6/30 - 20:36
 */
public class Jump {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int start = sc.nextInt();
        int[] step = new int[length];
        for (int i = 0; i < length; i++) {
            step[i] = sc.nextInt();
        }
        int[][] temp = new int[step.length][2];
        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = step[i];
            temp[i][1] = 0;
        }
        System.out.println(jump(temp, start));
    }

    public static int jump(int[][] step, int start) {
        int res = 0;
        step[start][1] = 1;
        if (step[start - step[start][0]][0] != 0) {
            res += jump(step,step[start - step[start][0]][0]);
        } else {
            return 0;
        }
        if (step[start + step[start][0]][0] != 0) {
            res += jump(step,step[start + step[start][0]][0]);
        } else {
            return 0;
        }
        if (step[start][0] >= step.length){
            res = 1;
            return res;
        }
        return 1;
    }

}
