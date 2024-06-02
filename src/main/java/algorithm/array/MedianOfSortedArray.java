package algorithm.array;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */

public class MedianOfSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0;
        int i=0;
        int j=0;
        int prev = 0;
        if(nums1 == null){
            return nums2.length % 2 == 0 ? (nums2[nums2.length/2] + nums2[nums2.length/2 + 1])/2 : nums2[nums2.length /2];
        }
        if(nums2 == null){
            return nums1.length % 2 == 0 ? (nums1[nums1.length/2] + nums1[nums1.length/2 + 1])/2 : nums1[nums1.length /2];
        }
        boolean isEven = (nums1.length + nums2.length) % 2 == 0;

        for(int k=0; k<((nums1.length + nums2.length) /2) + 1 ;k++){
            int current = 0;
            median = isEven ? (prev + current) : current;
            prev = current;
        }
        return median;
    }
}
