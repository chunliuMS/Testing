package LeetCodeTest;

import junit.framework.TestCase;

//Given a [sorted] array of integers, return indices of 2 numbers such that they add up to a specific target.
//You may assume that each input would have exactly one solution. 
public class LC_004_FindMedian extends TestCase {
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int m = nums1.length;
	        int n = nums2.length;
	        if (m > n)
	            return findMedianSortedArrays(nums2, nums1);
	            
	        int l = 0; 
	        int r = m;
	        
	        while (l <= r) {
	            int i = (l + r) / 2;
	            int j = (m + n + 1) / 2 - i;
	            if (j > 0 && i < m && nums2[j - 1] > nums1[i])
	                l = i + 1;
	            else if (i > 0 && j < n && nums1[i - 1] > nums2[j])
	                r = i - 1;
	            else {
	            	int maxOfLeft;
	            	if (i == 0) maxOfLeft = nums2[j - 1];
	            	else if (j == 0) maxOfLeft = nums1[i - 1];
	            	else maxOfLeft = Math.max(nums1[i - 1], nums2[j - 1]);
	            	
	                if ((m + n) % 2 > 0) {
	                    return maxOfLeft; 
	                } 
	                else {
	                	int minOfRight;
	                	if (i == m) minOfRight = nums2[j];
	                	else if (j == n) minOfRight = nums1[i];
	                	else minOfRight = Math.min(nums1[i], nums2[j]);
	                	return (maxOfLeft + minOfRight) / 2.0;
	                }
	            }
	        }
	        
	        return 0.0;
	    }
	 
	   
	public void testFun(){
		
		int[] a = { 1, 1}; 
		int[] b = {1,2};
		double f = findMedianSortedArrays(a, b);
		System.out.println(Double.valueOf(f));
	}
}
