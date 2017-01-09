package LeetCodeTest;

import java.util.HashMap;

import junit.framework.TestCase;

public class LeetCodeTestAll extends TestCase {
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	};
	
//	LeetCode 001 -- two sum
//	Difficulty: Easy
//	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//	You may assume that each input would have exactly one solution.
//
//	Example:
//	Given nums = [2, 7, 11, 15], target = 9,
//
//	Because nums[0] + nums[1] = 2 + 7 = 9,
//	return [0, 1].
//	UPDATE (2016/2/13):
//	The return format had been changed to zero-based indices. Please read the above updated description carefully.
	
	public int[] LeetCode_001_twoSum(int[] nums, int target) {
		if (nums == null)
			return null;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
		for (int i = 0; i < nums.length; i++) {
			int v = target - nums[i];
			if (map.containsKey(v)) {
				int[] indexes = {i, map.get(v)};
				return indexes;
			}
			map.put(nums[i], i);
		}
		
		return null;
	}
	
//	LeetCode 002 - Add Two Numbers 
//	Difficulty: Medium
//	You are given two linked lists representing two non-negative numbers. 
//	The digits are stored in reverse order and each of their nodes contain a single digit. 
//	Add the two numbers and return it as a linked list.
//
//	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//	Output: 7 -> 0 -> 8
	
	public ListNode LeetCode_002_addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }
	
//	LeetCode 004 - Median of Two Sorted Arrays  QuestionEditorial Solution  
//	Difficulty: Hard
//	There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//	Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//	Example 1:
//	nums1 = [1, 3]
//	nums2 = [2]
//
//	The median is 2.0
//	Example 2:
//	nums1 = [1, 2]
//	nums2 = [3, 4]
//
//	The median is (2 + 3)/2 = 2.5
	public double LeetCode_004_findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n)
            return LeetCode_004_findMedianSortedArrays(nums2, nums1);
            
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
		
	}
}
