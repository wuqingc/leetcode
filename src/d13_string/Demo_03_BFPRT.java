package d13_string;


/**
 * @author tianjiaxuan
 * BFPRT 算法实现
 */
public class Demo_03_BFPRT {

    public static int[] getMinKNumsByBFPRT(int[] arr,int k){
        if (k < 1 || k > arr.length){
            return arr;
        }
        /*
         * 获取第K小的数,将其存在答案数组中.
         */
        int minKth = getMinKthByBFPRT(arr,k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++){
            if (arr[i] < minKth){
                res[index++] = arr[i];

            }
        }
        for (; index != res.length; index++){
            res[index] = minKth;
        }
        return res;
    }

    private static int getMinKthByBFPRT(int[] arr, int k) {
        /*
         * 将原数组复制出来.
         */
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++){
            res[i] = arr[i];
        }
        return bfprt(res,0,res.length - 1,k -1);
    }

    /**
     * 获得第 i 小的数.
     */
    public static int bfprt(int[] arr,int begin,int end,int i){
        if (begin == end){
            return arr[begin];
        }
        /*
         * 获得中位数数组中的中位数,作为划分值.
         */
        int pivot = medianOfMedians(arr,begin,end);
        int[] pivotRange = partition(arr,begin,end,pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]){
            return arr[i];
        } else if (i < pivotRange[0]){
            return bfprt(arr,begin,pivotRange[0] - 1,i);
        } else {
            return bfprt(arr,pivotRange[1] + 1,end,i);
        }
    }

    /**
     * 获得中位数组中的中位数
     */
    private static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        /*
         * 不足5个的自成一组,创建一个由中位数组成的数组.
         */
        int offset = (num % 5 == 0) ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++){
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr,beginI,Math.min(end,endI));
        }
        /*
         * 递归调用bfprt,获取这个数组的中位数.
         */
        return bfprt(arr,0,mArr.length - 1,mArr.length / 2);
    }

    /**
     * 求五个数中的中位数.
     * sum为奇数时,/2会导致mid在中位数的前一位,需要+1.
     */
    private static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr,begin,end);
        int sum = begin + end;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }
    private static void insertionSort(int[] arr,int begin,int end){
        for (int i = begin + 1; i != end + 1; i++){
            for (int j = i; j != begin; j--){
                if (arr[j - 1] > arr[j]){
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
    }


    private static int[] partition(int[] arr,int begin,int end,int pivotValue){
        return null;
    }


}
