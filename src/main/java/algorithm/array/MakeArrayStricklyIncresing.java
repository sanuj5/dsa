package algorithm.array;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/make-array-strictly-increasing/
 */
public class MakeArrayStricklyIncresing {

    public static void main(String[] args) {
        int[] ints = {5,16,19,2,1,12,7,14,5,16};
        System.out.println(makeArrayIncreasing(ints,new int[]{6,17,4,3,6,13,4,3,18,17,16,7,14,1,16}));


    }
    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int operation = 0;
        TreeSet<Integer> tree = new TreeSet<>();
        for(int i : arr2){
            tree.add(i);
        }
        int prev = arr1[0];
        for(int i=1;i<arr1.length ;i++){
            if(prev >= arr1[i] || (i!=arr1.length-1 && arr1[i] >= arr1[i+1])){
                Integer floor = tree.ceiling(prev+1);
                if(floor == null || prev > floor){
                    return -1;
                }
                else{
                    arr1[i] = floor;
                    operation++;
                    tree.remove(floor);
                }
            }
            prev = arr1[i];
            System.out.print("for iteration "+ i + " --> " );
            printArray(arr1);
        }
        return  operation;
    }
    private static void printArray(int[] arr){
        for(int i:arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
