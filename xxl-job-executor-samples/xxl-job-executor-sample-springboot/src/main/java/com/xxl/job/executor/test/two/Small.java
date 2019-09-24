package com.xxl.job.executor.test.two;


public class Small {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2};
        int num = 3;
        sort(arr,num);
        int ret = calculate(arr, num);
        System.out.println(ret);
    }

    public static int calculate(int[] arr, int num){
            int r = 0, ans = -1;
            for (int i = 0;i < num ;i++)
            {
                int tl = arr[i], tr = r + arr[i];
                if (tl <= r + 1)
                {
                    r = tr;
                }
                else
                {
                    ans = r + 1;
                    break;
                }
            }
            if (ans == -1) ans = r + 1;
            return ans;
    }

    public static void sort(int arr[], int num) {
        int i;
        int j;
        for (i = 1; i < num; i++)
        {
            int m = arr[i];
            for (j = i - 1; (j >= 0) && (arr[j] > m);j--)
            {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = m;
        }
    }
}
