package LeetCodeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import junit.framework.TestCase;

public class LeetcodeQuestions extends TestCase {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	};
	
	/**
	 * Definition for a binary tree node.*/
	 public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 };
	

	//	1. Two Sum   QuestionEditorial Solution  
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
		
	public class Solution_1 {
	    public int[] twoSum(int[] nums, int target) {
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
	}
	
	//	2. Add Two Numbers   QuestionEditorial Solution  
	//	Difficulty: Medium
	//	You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
	//
	//	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	//	Output: 7 -> 0 -> 8
	
	public class Solution_2 {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode head = new ListNode(0);
	        ListNode prev = head;
	        int carry = 0;
	        
	        while (l1 != null || l2 != null) {
	            int v = carry;
	            if (l1 != null) v += l1.val;
	            if (l2 != null) v += l2.val;
	            
	            if (v >= 10) {
	                carry = 1;
	                v -= 10;
	            } else {
	                carry = 0;
	            }
	            
	            prev.next = new ListNode(v);
	            
	            prev = prev.next;
	            if (l1 != null) l1 = l1.next;
	            if (l2 != null) l2 = l2.next;
	        }
	        
	        if (carry > 0)
	            prev.next = new ListNode(carry);
	        
	        return head.next;
	    }
	}
	
	/*3. Longest Substring Without Repeating Characters   QuestionEditorial Solution  
	Difficulty: Medium
	Contributors: Admin
	Given a string, find the length of the longest substring without repeating characters.

	Examples:

	Given "abcabcbb", the answer is "abc", which the length is 3.

	Given "bbbbb", the answer is "b", with the length of 1.

	Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	*/	
	
	public class Solution_3 {
	    public int lengthOfLongestSubstring(String s) {
	        if (s == null) return 0;
	        
	        HashMap<Character, Integer> charMap = new HashMap<Character, Integer> (s.length());
	        int si = 0;
	        int maxLength = 0;
	        
	        for (int i = 0; i < s.length(); i++) {
	            if (charMap.containsKey(s.charAt(i))) {
	                int index = (int) charMap.get(s.charAt(i));
	                for (int j = si; j <= index; j++)
	                    charMap.remove(s.charAt(j));
	                    
	                si = ++index;
	            } else {
	                int currMax = i - si + 1;
	                if (currMax > maxLength)
	                    maxLength = currMax;
	            }
	            charMap.put(s.charAt(i), i);
	        }
	        
	        return maxLength;
	    }
	}
	
	/*4. Median of Two Sorted Arrays   QuestionEditorial Solution  
	Difficulty: Hard
	There are two sorted arrays nums1 and nums2 of size m and n respectively.

	Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

	Example 1:
	nums1 = [1, 3]
	nums2 = [2]

	The median is 2.0
	Example 2:
	nums1 = [1, 2]
	nums2 = [3, 4]

	The median is (2 + 3)/2 = 2.5*/
	
	public class Solution_4 {
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
	}
	
	/*5. Longest Palindromic Substring   QuestionEditorial Solution  
	Difficulty: Medium
	Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

	Subscribe to see which companies asked this question*/
	
	public class Solution_5 {
	    public String longestPalindrome(String s) {
	        if (s == null) return s;
	        int len = s.length();
	        if (len == 1) return s;
	        int maxLen = 0;
	        int maxL = 0;
	        for (int i = 0; i < len && len - i > maxLen / 2; i++ ) {
	            int l = i; 
	            int r = i;
	            
	            while (r < len - 1 && s.charAt(r) == s.charAt(r + 1)) {
	                r++;
	                int curLen = r - l + 1;
	                if (curLen > maxLen) {
	                    maxLen = curLen;
	                    maxL = l;
	                }
	            }
	                
	            while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
	                int curLen = r - l + 1;
	                if (curLen > maxLen) {
	                    maxLen = curLen;
	                    maxL = l;
	                }
	                
	                l--;
	                r++;
	            }
	        }
	        
	        return s.substring(maxL, maxL + maxLen);
	    }
	}
	
	/*6. ZigZag Conversion   QuestionEditorial Solution  
	Difficulty: Easy
	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

	P   A   H   N
	A P L S I I G
	Y   I   R
	And then read line by line: "PAHNAPLSIIGYIR"
	Write the code that will take a string and make this conversion given a number of rows:

	string convert(string text, int nRows);
	convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".*/
	
	public class Solution_6 {
	    public String convert(String s, int numRows) {
	        if (s == null || numRows <= 1)
	            return s;
	        
	        StringBuffer[] sba = new StringBuffer[numRows];
	        for (int i = 0; i < numRows; i++)
	            sba[i] = new StringBuffer("");
	            
	        int inc = 1;
	        int index = 0;
	        
	        for (int i = 0; i < s.length(); i++) {
	            sba[index].append(s.charAt(i));
	            
	            if (index == 0) inc = 1;
	            if (index == numRows - 1) inc = -1;
	            index += inc;
	        }
	        
	        StringBuffer str = new StringBuffer("");
	        for (StringBuffer sb: sba)
	            str.append(sb);
	            
	        return str.toString();
	    }
	}
	
	/*7. Reverse Integer   QuestionEditorial Solution 
	Difficulty: Easy
	Reverse digits of an integer.

	Example1: x = 123, return 321
	Example2: x = -123, return -321*/
	
	public class Solution_7 {
	    public int reverse(int x) {
	       return reverseLong(x);
	    }
	    
	    public int reverseLong(long x) {
	        if ( x < 0)
	            return -reverseLong(-x);
	            
	        long ret = 0;
	        while (x > 0) {
	            long m = x % 10;
	            x = x / 10;
	            ret *= 10;
	            ret += m;
	        }
	        
	        if (ret >= Integer.MAX_VALUE || ret <= Integer.MIN_VALUE)
	            return 0;
	        else return (int) ret;
	    }
	}
	
	
	/*8. String to Integer (atoi)   QuestionEditorial Solution
	Difficulty: Easy
	Implement atoi to convert a string to an integer.

	Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

	Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
	*/
	
	public class Solution_8 {
	    public int myAtoi(String str) {
	        if (str == null || str.length() == 0) return 0;
	        str = str.trim();
	        
	        long value = 0;
	        long sign = 1;
	        boolean setSign = false;
	        boolean firstDigital = false;
	        
	        for (int i = 0; i < str.length(); i++) {
	            char c = str.charAt(i);
	            if (!Character.isDigit(c)){
	                if (firstDigital)
	                     break;
	                else if (c == '+' && !setSign) {
	                    sign = 1;
	                    setSign = true;
	                }
	                else if (c == '-' && !setSign) {
	                    sign = -1;
	                    setSign = true;
	                }   
	                else return 0;
	                
	            } else {
	                if (!firstDigital) {
	                    firstDigital = true;
	                    value = c - '0';
	                } else {
	                    value = value * 10 + c - '0';
	                    if (value > Integer.MAX_VALUE && sign == 1)
	                        return Integer.MAX_VALUE;
	                    else if (sign == -1 && -value < Integer.MIN_VALUE )
	                        return Integer.MIN_VALUE;
	                }
	                
	            }
	        }
	        
	        value *= sign;
	        if (value > Integer.MAX_VALUE)
	            return Integer.MAX_VALUE;
	        if (value < Integer.MIN_VALUE) 
	            return Integer.MIN_VALUE;
	        
	        return (int)value;
	    }
	}
	
	/*9. Palindrome Number   QuestionEditorial Solution 
	Difficulty: Easy
	Determine whether an integer is a palindrome. Do this without extra space.*/
	
	public class Solution_9 {
	    public boolean isPalindrome(int x) {
	        if (x < 0) return false;
	        else if (x < 10) return true;
	        
	        int powerOf10 = 10;
	        while (x / powerOf10 >= 10)
	            powerOf10 *= 10;
	            
	        while (x > 0) {
	            if (x / powerOf10 == x % 10) {
	                x %= powerOf10;
	                x /= 10;
	                powerOf10 /= 100;
	            }
	            else return false;
	        }
	        
	        return true;
	    }
	}
	
	/*10. Regular Expression Matching   QuestionEditorial Solution 
	Difficulty: Hard
	Implement regular expression matching with support for '.' and '*'.

	'.' Matches any single character.
	'*' Matches zero or more of the preceding element.

	The matching should cover the entire input string (not partial).

	The function prototype should be:
	bool isMatch(const char *s, const char *p)

	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "a*") → true
	isMatch("aa", ".*") → true
	isMatch("ab", ".*") → true
	isMatch("aab", "c*a*b") → true*/
	
	public class Solution_10 {
	    public boolean isMatch(String s, String p) {
	        if (s == null || p == null) return false;
	        
	        int m = s.length();
	        int n = p.length();
	        
	        boolean [][]dp = new boolean[m + 1][n + 1];
	        dp[0][0] = true;
	        
	        for (int i = 0; i <= m; i++) {
	            for (int j = 1; j <= n; j++) {
	                char c = p.charAt(j -1);
	                if (c != '*') {
	                    dp[i][j] = (i > 0 ) && dp[i -1][j - 1] && (c == '.' || c == s.charAt(i - 1));
	                } else {
	                    dp[i][j] = (j >= 2 && dp[i][j - 2]) || (i > 0) && (j > 1) && dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
	                }
	            }
	        }
	        
	        return dp[m][n];
	    }
	}
	
	/*11. Container With Most Water   QuestionEditorial Solution
	Difficulty: Medium
	Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
	which together with x-axis forms a container, such that the container contains the most water.

	Note: You may not slant the container.*/
	
	public class Solution_11 {
	    public int maxArea(int[] height) {
	        if (height == null || height.length < 2)
	            return 0;
	         int max = 0;   
	         
	         int i = 0; 
	         int j = height.length - 1;
	         
	         while(i < j)
	            max = Math.max(max, (j - i) * (height[i] > height[j] ? height[j--] : height[i++] ));
	         
	         return max;
	    }
	}
	
	/*12. Integer to Roman   QuestionEditorial Solution 
	Difficulty: Medium
	Given an integer, convert it to a roman numeral.

	Input is guaranteed to be within the range from 1 to 3999.*/
	
	public class Solution_12 {
	    public String intToRoman(int num) {
	        String M[] = {"", "M", "MM", "MMM"};
		    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
	    }
	}
	
	/*13. Roman to Integer   QuestionEditorial Solution
	Difficulty: Easy
	Given a roman numeral, convert it to an integer.

	Input is guaranteed to be within the range from 1 to 3999*/
	
	public class Solution_13 {
	    public int romanToInt(String s) {
	        if (s == null || s.length() == 0)
	            return 0;
	       
	        int total = 0;
	        int last = 0;
	        int valCurr = 0;
	        for (int i = s.length() - 1; i >= 0; i--) {	            
	            switch (s.charAt(i)) {
	                case 'i':
	                case 'I':
	                    valCurr = 1;
	                    break;
	                case 'v':
	                case 'V':
	                    valCurr = 5;
	                    break;
	                case 'x':
	                case 'X':
	                    valCurr = 10;
	                    break;
	                case 'l':
	                case 'L':
	                    valCurr = 50;
	                    break;
	                case 'c':
	                case 'C':
	                    valCurr = 100;
	                    break;
	                case 'd':
	                case 'D':
	                    valCurr = 500;
	                    break;
	                case 'm':
	                case 'M':
	                    valCurr = 1000;
	            }
	            
	            if (valCurr < last)
	                total -= valCurr;
	            else {
	                total += valCurr;
	                last = valCurr;
	            }
	        }
	        
	        return total;
	    }
	}
	
	/*14. Longest Common Prefix   QuestionEditorial Solution 
	Difficulty: Easy
	Write a function to find the longest common prefix string amongst an array of strings.*/
	
	public class Solution_14 {
	    public String longestCommonPrefix(String[] strs) {
	        if (strs == null || strs.length == 0) return "";
	        
	        
	        String comm = strs[0];
	        for (int i = 1; i < strs.length; i++) {
	            int j = 0;
	            for (; j < comm.length() && j < strs[i].length(); j++){
	                if(comm.charAt(j) != strs[i].charAt(j))
	                    break;
	            }
	            
	            if (j > 0) comm = comm.substring(0, j);
	            else {
	                comm = "";
	                break;
	            }
	        }
	        
	        return comm;
	    }
	}
	
	
	/*71. Simplify Path QuestionEditorial Solution 
	Difficulty: Medium
	Given an absolute path for a file (Unix-style), simplify it.

	For example,
	path = "/home/", => "/home"
	path = "/a/./b/../../c/", => "/c"*/
	
	public class Solution_71 {
	   public String simplifyPath(String path) {
	        if (path == null || path.length() == 0) return path;
	        
	        String[] parts = path.split("/");
	        Stack<String> bk = new Stack<String>();
	        for (int i = 0; i < parts.length; i++) {
	            String s = parts[i];
	            if (s.equals("..")) {
	                if (!bk.isEmpty()) bk.pop();
	            } else if (!s.equals(".") && s.length() > 0)
	                bk.push(s);
	        }
	        Stack<String> rs = new Stack<String>();
	        while(!bk.isEmpty()) rs.push(bk.pop());
	            
	        StringBuffer sb = new StringBuffer();
	        while (!rs.isEmpty())
	            sb.append("/").append(rs.pop());
	            
	        if (sb.length() == 0) sb.append("/");
	        
	        return sb.toString();
	    }
	}
	
	
	/*72. Edit Distance
	Difficulty: Hard
	
	Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

	You have the following 3 operations permitted on a word:

	a) Insert a character
	b) Delete a character
	c) Replace a character*/
	
	public class Solution_72 {
	    public int minDistance(String word1, String word2) {
	        if (word1 == null && word2 == null) return 0;
	        if (word1 == null || word1.length() == 0) return word2 == null ? 0 : word2.length();
	        if (word2 == null || word2.length() == 0) return word1 == null ? 0 : word1.length();
	        
	        int m = word1.length();
	        int n = word2.length();
	        
	        int[][] dp = new int[m+1][n+1];
	        for (int i = 1; i <= m; i++) dp[i][0] = i;
	        for (int j = 1; j <= n; j++) dp[0][j] = j;
	        
	        for (int i = 1; i <= m; i++)
	            for(int j = 1; j <= n; j++) {
	                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i -1][j-1];
	                else  dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
	            }
	        return dp[m][n];
	    }
	}
	
	/*73. Set Matrix Zeroes 
	Difficulty: Medium
	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.*/
	
	public class Solution_73 {
		 public void setZeroes(int[][] matrix) {
		     if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
		     int m = matrix.length, n = matrix[0].length;
		     boolean row = false, col = false;
		     for (int i = 0; i < m; i++)
		         for (int j = 0; j < n; j++){
		             if (matrix[i][j] == 0) {
		                 matrix[0][j] = 0;
		                 matrix[i][0] = 0;
		                 if (i == 0) row = true;
		                 if (j == 0) col = true;
		             }
		         }
		     for (int i = 1; i < m; i++){
		         if (matrix[i][0] == 0){
		             for (int j = 1; j < n;j++)
		                 matrix[i][j] = 0;
		         }
		     }
		     for (int j = 1; j < n; j++){
		         if (matrix[0][j] == 0){
		             for (int i = 1; i < m; i++)
		                 matrix[i][j] = 0;
		         }
		     }
		     if (row){
		         for (int j = 0; j < n; j++)
		             matrix[0][j] = 0;
		     }
		     if (col){
		         for(int i = 0; i < m; i++)
		             matrix[i][0] = 0;
		     }
		 }
	}
	
	/*74. Search a 2D Matrix 
	Difficulty: Medium
	Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

	Integers in each row are sorted from left to right.
	The first integer of each row is greater than the last integer of the previous row.
	For example,

	Consider the following matrix:

	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
	Given target = 3, return true.*/
	
	public class Solution_74 {
	    public boolean searchMatrix(int[][] matrix, int target) {
	        int m = matrix.length;
	        int n = matrix[0].length;
	        
	        int l = 0;
	        int r = m * n - 1;
	        
	        while (l <= r) {
	            int mid = (l + r) / 2;
	            int v = matrix[mid/n][mid%n];
	            if (v == target) return true;
	            else if (v > target) r = mid -1;
	            else l = mid + 1;
	        }
	        
	        return false;
	    }
	}
	
	/*75. Sort Colors   
	Difficulty: Medium
	Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
	Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

	Note:
	You are not suppose to use the library's sort function for this problem.*/
	
	public class Solution_75 {
		public void sortColors(int[] nums) {
	        if (nums == null || nums.length < 2) return;
	        int s0 = 0;
	        int s2 = nums.length -1;
	        
	        for (int i = 0; i < nums.length && i <= s2;) {
	            if (nums[i] == 0) {
	                if (i != s0) {
	                   nums[i] = nums[s0];
	                   nums[s0] = 0;
	                }
	                    
	                i++;
	                s0++;
	            }
	            else if (nums[i] == 2) {
	                nums[i] = nums[s2];
	                nums[s2--] = 2;
	            }
	            else i++;
	        }
	    }
	}
	
	/*76. Minimum Window Substring 
	Difficulty: Hard
	Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

	For example,
	S = "ADOBECODEBANC"
	T = "ABC"
	Minimum window is "BANC".

	Note:
	If there is no such window in S that covers all characters in T, return the empty string "".

	If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.*/
	
	public class Solution_76 {
	    public String minWindow(String s, String t) {
	        if (t == null || t.length() == 0 || s== null || s.length() == 0) return "";
	        int[][] match = new int[256][2];
	        int count = 0;
	        for (int i = 0; i < t.length(); i++) {
	            int c = (int) t.charAt(i);
	            match[c][0]++;
	            count++;
	        }
	        
	        List<Integer> elements = new ArrayList<Integer>();        
	        int start = 0, end = Integer.MAX_VALUE;
	        
	        for (int i = 0; i < s.length(); i++) {
	            int c = (int) s.charAt(i);
	            if (match[c][0] > 0) { //needed char.
	                elements.add(i);                
	                if (match[c][0] > match[c][1]) count--;
	                match[c][1]++;
	                
	                while (!elements.isEmpty()) {
	                    int sc = (int) s.charAt(elements.get(0));
	                    if (match[sc][0] < match[sc][1]) { //try to remove from the tail.
	                        elements.remove(0);
	                        match[sc][1]--;
	                    } else break;   
	                }
	                
	                if (match[c][0] == match[c][1] && count == 0) {
	                    int sc = elements.get(0);
	                    if (i - sc < end - start) {
	                        start = sc;
	                        end = i;
	                    }
	                }
	            }
	        }
	        
	        if (end < s.length()) return s.substring(start, end + 1);
	        else  return "";
	    }
	}
	
	/*77. Combinations
	Difficulty: Medium
	Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

	For example,
	If n = 4 and k = 2, a solution is:

	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]*/
	
	public class Solution_77 {
		public List<List<Integer>> combine(int n, int k) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if (n < k || k <= 0) return result;
	        
	        int[] buff = new int[k];
	        combine(1, n, k, buff, result);
	        return result;
	    }
	    
	    void combine(int value, int n, int k, int[] buff,  List<List<Integer>> result) {
	    	if (0 == k) {
	        	List<Integer> list = new LinkedList<Integer>();
	        	for (int i : buff) list.add(i);
	            result.add(list);
	        } else {
	            for (int i = value; i <= n-k+1; i++) {
	        	    buff[buff.length - k] = i;
	        	    combine(i+1, n, k-1, buff, result);
	            }
	        }
	    }
	    
	    public List<List<Integer>> combine_good(int n, int k) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if (k > n || k < 0) {
	            return result;
	        }
	        if (k == 0) {
	            result.add(new ArrayList<Integer>());
	            return result;
	        }
	        result = combine(n - 1, k - 1);
	        for (List<Integer> list : result) {
	            list.add(n);
	        }
	        result.addAll(combine(n - 1, k));
	        return result;
	    }
	}
	
	/*78. Subsets 
	Difficulty: Medium
	Given a set of distinct integers, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.

	For example,
	If nums = [1,2,3], a solution is:

	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]*/
	
	public class Solution_78 {
	    public List<List<Integer>> subsets(int[] nums) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if (nums == null || nums.length == 0) return result;
	        
	        List<Integer> list = new ArrayList<Integer>();
	        
	        doGetSubSets(nums, 0, list, result);
	       return result; 
	    }
	    
	    void doGetSubSets(int[] nums, int si, List<Integer> list, List<List<Integer>> result) {
	        if (si >= nums.length) {
	            result.add(new ArrayList<Integer>(list));
	        } else {
	        	doGetSubSets(nums, si + 1, list, result);
	            list.add(nums[si]);
	            doGetSubSets(nums, si + 1, list, result);
	            list.remove(list.size() - 1);
	        }
	    }
	    
	    public List<List<Integer>> subsets_good(int[] S) {	    	
	    	int totalNumber = 1 << S.length;
	    	List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
	    	for (int i=0; i<totalNumber; i++) {
	    		List<Integer> set = new LinkedList<Integer>();
	    		for (int j=0; j<S.length; j++) {
	    			if ((i & (1<<j)) != 0) {
	    				set.add(S[j]);
	    			}
	    		}
	    		collection.add(set);
	    	}
	    	return collection;
	    }
	    
	    public List<List<Integer>> subsets_2(int[] nums) {
	        List<List<Integer>> list = new ArrayList<>();	        
	        backtrack(list, new ArrayList<>(), nums, 0);
	        return list;
	    }

	    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
	        list.add(new ArrayList<>(tempList));
	        for(int i = start; i < nums.length; i++){
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, i + 1);
	            tempList.remove(tempList.size() - 1);
	        }
	    }
	    
	    public class Solution {
	        public List<List<Integer>> subsets(int[] S) {
	            List<List<Integer>> res = new ArrayList<>();
	            res.add(new ArrayList<Integer>());
	            
	            Arrays.sort(S);
	            for(int i : S) {
	                List<List<Integer>> tmp = new ArrayList<>();
	                for(List<Integer> sub : res) {
	                    List<Integer> a = new ArrayList<>(sub);
	                    a.add(i);
	                    tmp.add(a);
	                }
	                res.addAll(tmp);
	            }
	            return res;
	        }
	    }
	}
	
	/*79. Word Search
	Difficulty: Medium
	Given a 2D board and a word, find if the word exists in the grid.

	The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
	The same letter cell may not be used more than once.

	For example,
	Given board =
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	word = "ABCCED", -> returns true,
	word = "SEE", -> returns true,
	word = "ABCB", -> returns false.*/
	
	public class Solution_79 {
	    public boolean exist(char[][] board, String word) {
	        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) return false;
	        char[] w  = word.toCharArray();
	        int m = board.length, n = board[0].length;
	        int len = word.length();
	        for (int i = 0; i < m; i++) 
	        	for (int j = 0; j < n; j++) 
	        		if (exist(board, m, n, i, j, w, len, 0)) return true;
	        
	        return false;
	    }
	    
	    boolean exist(char[][] board, int m, int n, int i, int j, char[] w, int len, int index) {
	        if (index == len) return true;
	        
	        if (i < 0 || i >= m || j < 0 || j >= n) return false;
	        if (board[i][j] == 0) return false;
	        boolean ret = false;
	        if (board[i][j] == w[index]) {
	            char c = board[i][j];
	            board[i][j] = 0;
	            ret =  exist(board, m, n, i + 1, j, w, len, index + 1) ||
	                   exist(board, m, n, i - 1, j, w, len, index + 1) ||
	                   exist(board, m, n, i, j + 1, w, len, index + 1) ||
	                   exist(board, m, n, i, j - 1, w, len, index + 1);
	            board[i][j] = c;
	        }
	        
	        return ret;
	    }
	}
	
	/*80. Remove Duplicates from Sorted Array II
	Difficulty: Medium
	Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?

	For example,
	Given sorted array nums = [1,1,1,2,2,3],

	Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
	It doesn't matter what you leave beyond the new length.*/	
	
	public class Solution_80 {
	    public int removeDuplicates(int[] nums) {
	        if (nums == null || nums.length < 2) return nums.length;
	        int pos = 0;
	        boolean dup = false;
	        for (int i = 1; i < nums.length; i++) {
	            if (nums[i] == nums[pos]){
	                if (dup) continue;
	                else {
	                    dup = true;
	                    nums[++pos] = nums[i];
	                }
	            } else {
	                nums[++pos] = nums[i];
	                dup = false;
	            }
	        }
	        
	        return pos + 1;
	    }
	}
	
	/*81. Search in Rotated Sorted Array II 
	Difficulty: Medium
	Follow up for "Search in Rotated Sorted Array":
	What if duplicates are allowed?

	Would this affect the run-time complexity? How and why?

	Write a function to determine if a given target is in the array.*/
	
	public class Solution_81 {
	    public boolean search(int[] nums, int target) {
	        if (nums == null || nums.length == 0) return false;
	        
	        int l = 0; 
	        int h = nums.length - 1;
	        while (l <= h) {
	            int mid = (l + h)/2;
	            if (target == nums[mid]) return true;
	            else if (nums[mid] > nums[l]){
	                if (target >= nums[l] && target < nums[mid]) h = mid - 1;
	                else l = mid + 1;
	            } else if (nums[mid] < nums[l]) {
	                if (target > nums[mid] && target <= nums[h]) l = mid + 1;
	                else h = mid - 1;
	            } else l++;
	        }
	        
	        return false;
	    }
	}
	
	/*82. Remove Duplicates from Sorted List II 
	Difficulty: Medium
	Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

	For example,
	Given 1->2->3->3->4->4->5, return 1->2->5.
	Given 1->1->1->2->3, return 2->3.*/
	
	public class Solution_82 {
	    public ListNode deleteDuplicates(ListNode head) {
	        if (head == null) return head;
	        ListNode dummy = new ListNode(0);
	        ListNode cur = dummy;
	        boolean dupped = false;
	        while (head != null) {
	            if (head.next == null) {
	                if (!dupped) {
	                    cur.next = head;
	                    cur = cur.next;
	                }
	                cur.next = null;
	            } else if (head.next.val == head.val) {
	                dupped = true;
	            } else {
	                if (!dupped) {
	                    cur.next = head;
	                    cur = cur.next;
	                }
	                dupped = false;
	            }
	            head = head.next;
	        }
	        
	        return dummy.next;
	    }
	    
	    public ListNode deleteDuplicates_better(ListNode head) {
	        if(head==null) return null;
	        ListNode FakeHead=new ListNode(0);
	        FakeHead.next=head;
	        ListNode pre=FakeHead;
	        ListNode cur=head;
	        while(cur!=null){
	            while(cur.next!=null&&cur.val==cur.next.val){
	                cur=cur.next;
	            }
	            if(pre.next==cur){
	                pre=pre.next;
	            }
	            else{
	                pre.next=cur.next;
	            }
	            cur=cur.next;
	        }
	        return FakeHead.next;
	    }
	}
	
	/*83. Remove Duplicates from Sorted List
	Difficulty: Easy
	Given a sorted linked list, delete all duplicates such that each element appear only once.

	For example,
	Given 1->1->2, return 1->2.
	Given 1->1->2->3->3, return 1->2->3.*/
	
	public class Solution_83 {
	    public ListNode deleteDuplicates(ListNode head) {
	        if (head == null || head.next == null) return head;
	        
	        ListNode prev = head;
	        ListNode cur = head.next;
	        
	        while (cur != null) {
	            if (prev.val != cur.val) {
	                prev.next = cur;
	                prev = prev.next;
	            }
	            cur = cur.next;
	        }
	        prev.next = null;
	        
	        return head;
	    }
	}
	
	/*85. Maximal Rectangle 
	Difficulty: Hard
	Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

	For example, given the following matrix:

	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0
	Return 6.*/
	
	public class Solution_85 {
	    public int maximalRectangle(char[][] matrix) {
	        
	        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
	        int m = matrix.length;
	        int n = matrix[0].length;
	        int[] left = new int[n];
	        int[] right = new int[n];
	        int[] height = new int[n];
	        Arrays.fill(right, n);
	        
	        int maxA = 0;
	        for(int i=0; i<m; i++) {
	            int cur_left=0, cur_right=n; 
	            for(int j=0; j<n; j++) { // compute height (can do this from either side)
	                if(matrix[i][j]=='1') {
	                    height[j]++; 
	                    
	                    left[j]=Math.max(left[j],cur_left); // compute left (from left to right)
	                }
	                else {
	                    height[j]=0;
	                    
	                    left[j]=0; 
	                    cur_left=j+1;
	                }
	            }
	            
	            // compute right (from right to left)
	            for(int j=n-1; j>=0; j--) {
	                if(matrix[i][j]=='1') right[j]=Math.min(right[j],cur_right);
	                else {right[j]=n; cur_right=j;}    
	            }
	            // compute the area of rectangle (can do this from either side)
	            for(int j=0; j<n; j++)
	                maxA = Math.max(maxA,(right[j]-left[j])*height[j]);
	        }
	        return maxA;
	    }
	}
	
	/*90. Subsets II 
	Difficulty: Medium
	
	Given a collection of integers that might contain duplicates, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.

	For example,
	If nums = [1,2,2], a solution is:

	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]*/
	
	public class Solution_90 {
	    public List<List<Integer>> subsetsWithDup(int[] nums) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        res.add(new ArrayList<Integer>());
	        Arrays.sort(nums);
	        int preStart = 0;
	        
	        for (int i  = 0;  i < nums.length; i++) {
	            int start = 0;
	            if (i > 0 && nums[i] == nums[i-1]) start = preStart;
	            
	            int end = res.size();
	            for (int j = start; j < end; j++) {
	                List<Integer> l = new ArrayList<Integer>(res.get(j));
	                l.add(nums[i]);
	                res.add(l);
	            }
	            preStart = end;
	        }
	       return res; 
	    }
	}
	
	/*91. Decode Ways 
	Difficulty: Medium
	A message containing letters from A-Z is being encoded to numbers using the following mapping:

	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
	Given an encoded message containing digits, determine the total number of ways to decode it.

	For example,
	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

	The number of ways decoding "12" is 2.*/
	
	public class Solution_91 {
	    public int numDecodings(String s) {
	        if (s == null || s.length() == 0) return 0;
	        char[] a = s.toCharArray();
	        int [] dp = new int[a.length + 1];
	        dp[0] = 1;
	        dp[1] = a[0] > '0' ? 1 : 0;
	        for (int i = 2; i <= a.length; i++) {
	            if (a[i-1] > '0') dp[i] = dp[i - 1];
	            
	        	int v = (a[i -2] - '0') * 10 + a[i-1] - '0';
	        	if (v >= 10 && v <= 26) dp[i] += dp[i - 2];
	        }
	        
	        return dp[dp.length-1];
	    }
	}
	
	/*92. Reverse Linked List II 
	Difficulty: Medium
	Reverse a linked list from position m to n. Do it in-place and in one-pass.

	For example:
	Given 1->2->3->4->5->NULL, m = 2 and n = 4,

	return 1->4->3->2->5->NULL.

	Note:
	Given m, n satisfy the following condition:
	1 ≤ m ≤ n ≤ length of list.*/
	
	public class Solution_92 {
	    public ListNode reverseBetween(ListNode head, int m, int n) {
	        ListNode dummy = new ListNode(0);
	        dummy.next = head;
	        ListNode pre = dummy;
	        for (int i = 1; i < m; i++)
	            pre = pre.next;
	        ListNode first = pre.next;
	        ListNode last = dummy;
	        for (int i = 1; i <= n; i++) 
	            last = last.next;
	        ListNode pos = last.next;
	        last.next = null;
	        
	        ListNode p = null;
	        ListNode c = first;
	        while (c != null) {
	            ListNode pn = c.next;
	            c.next = p;
	            p = c;
	            c = pn;
	        }
	        
	        pre.next = last;
	        first.next = pos;
	        
	        return dummy.next;
	    }
	}
	
	/*93. Restore IP Addresses
	Difficulty: Medium
	Given a string containing only digits, restore it by returning all possible valid IP address combinations.

	For example:
	Given "25525511135",

	return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)*/
	
	public class Solution_93 {
	    public List<String> restoreIpAddresses(String s) {
	        int[] buff = new int[4];
	        List<String> res = new LinkedList<String>();
	        doRestoreIpAddresses(s.toCharArray(), 0, buff, 0, res);
	       
	      	return res;        
	    }
	    
	    public void doRestoreIpAddresses(char[] a, int index, int[] buff, int count, List<String> res) {
	        if (count == 4) {
	            if (index == a.length) {
	                StringBuffer sb = new StringBuffer();
	                sb.append(buff[0]);
	                for (int i = 1; i < 4; i++) sb.append('.').append(buff[i]);
	                res.add(sb.toString());
	            }
	        }
	        
	        if (index < a.length && count < 4) {
	            for (int i = index; i <= index + 2 && i < a.length; i++) {	                
	                if (i == index || a[index] != '0') {
		                int value = 0;
		                for (int s = index;  s <= i; s++)
		                    value = value * 10 + a[s] - '0';
		                if (value <= 255) {
		                    buff[count] = value;
		                    doRestoreIpAddresses(a, i + 1, buff, count + 1, res);
		                }
	                }
	            }
	        }
	    }
	    
	    public class Solution {
	        List<String> restoreIpAddresses(String s) {
		    	List<String> ans = new ArrayList<String>();
		    	int len = s.length();
		    	for (int i = 1; i <=3; ++i){  // first cut
		    		if (len-i > 9) continue;    		
		    		for (int j = i+1; j<=i+3; ++j){  //second cut
		    			if (len-j > 6) continue;    			
		    			for (int k = j+1; k<=j+3 && k<len; ++k){  // third cut
		    				int a,b,c,d;                // the four int's seperated by "."
		    				a = Integer.parseInt(s.substring(0,i));  
		    				b = Integer.parseInt(s.substring(i,j)); // notice that "01" can be parsed into 1. Need to deal with that later.
		    				c = Integer.parseInt(s.substring(j,k));
		    				d = Integer.parseInt(s.substring(k));
		    				if (a>255 || b>255 || c>255 || d>255) continue; 
		    				String ip = a+"."+b+"."+c+"."+d;
		    				if (ip.length()<len+3) continue;  // this is to reject those int's parsed from "01" or "00"-like substrings
		    				ans.add(ip);
		    			}
		    		}
		    	}
		    	return ans;
		    }
	    }
	    
	    public class Solution2 {
	        public List<String> restoreIpAddresses(String s) {
	            List<String> res = new ArrayList<String>();
	            int len = s.length();
	            for(int i = 1; i<4 && i<len-2; i++){
	                for(int j = i+1; j<i+4 && j<len-1; j++){
	                    for(int k = j+1; k<j+4 && k<len; k++){
	                        String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
	                        if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
	                            res.add(s1+"."+s2+"."+s3+"."+s4);
	                        }
	                    }
	                }
	            }
	            return res;
	        }
	        public boolean isValid(String s){
	            if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
	                return false;
	            return true;
	        }
	    }
	}
	
	/*94. Binary Tree Inorder Traversal
	Difficulty: Medium
	Given a binary tree, return the inorder traversal of its nodes' values.

	For example:
	Given binary tree [1,null,2,3],
	   1
	    \
	     2
	    /
	   3
	return [1,3,2].*/
			
	public class Solution_94 {
	    public List<Integer> inorderTraversal(TreeNode root) {
	        List<Integer> res = new LinkedList<Integer>();
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        TreeNode node = root;
	        
	        while (node != null || !stack.isEmpty()) {
	            if (node != null) {
	                stack.push(node);
	                node = node.left;
	            } else {
	                node = stack.pop();
	                res.add(node.val);
	                node = node.right;
	            }
	        }
	        
	        return res;
	    }
	    
	    public class Solution {
	        public List<Integer> inorderTraversal(TreeNode root) {
	            List<Integer> res = new LinkedList<Integer>();
	            traversal(root, res);
	            return res;
	        }
	        
	        void traversal(TreeNode root, List<Integer> res) {
	            if (root == null) return;
	            traversal(root.left, res);
	            res.add(root.val);
	            traversal(root.right, res);
	        }
	    }
	}
	
	/*95. Unique Binary Search Trees II 
	Difficulty: Medium
	Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

	For example,
	Given n = 3, your program should return all 5 unique BST's shown below.

	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3*/
	
	public class Solution_95 {
		public List<TreeNode> generateTrees(int n) {
	        if (n < 1) return new LinkedList<TreeNode>();
	       return recursive(1, n);
	    }
	    
	    List<TreeNode> recursive(int l, int r) {
	        List<TreeNode> res = new LinkedList<TreeNode>();
	        if (l > r) res.add(null);
	        
	        for (int i = l; i <= r; i++) {
	            List<TreeNode> left = recursive(l, i - 1);
	            List<TreeNode> right = recursive(i + 1, r);
	            for (TreeNode tl : left) {
	                for(TreeNode tr : right) {
	                    TreeNode root = new TreeNode(i);
	                    root.left = tl;
	                    root.right = tr;
	                    res.add(root);
	                }
	            }
	        }
	        
	        return res;
	    }
	}
	
	/*97. Interleaving String   
	Difficulty: Hard
	
	Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

	For example,
	Given:
	s1 = "aabcc",
	s2 = "dbbca",

	When s3 = "aadbbcbcac", return true.
	When s3 = "aadbbbaccc", return false.*/
	
	public class Solution_97 {
	    public boolean isInterleave(String s1, String s2, String s3) {
	    	if (s3 == null || s3.length() == 0) return ((s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0));    	
	    	if (s1 == null || s1.length() == 0) return (s2.equals(s3));
	    	if (s2 == null || s2.length() == 0) return (s1.equals(s3));
	    	
	    	int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
	    	if (len1 + len2 != len3) return false;
	    	
	    	boolean[][] bp = new boolean[len1 + 1][len2 + 1];
	    	bp[0][0] = true;
	    	for (int i = 1; i <= len1; i++) bp[i][0] = bp[i -1][0] && s1.charAt(i - 1) == s3.charAt(i -1);
	    	for (int j = 1; j <= len2; j++) bp[0][j] = bp[0][j-1] && s2.charAt(j - 1) == s3.charAt(j - 1);
	    	
	    	for (int i = 1; i <= len1; i++) 
	    		for (int j = 1; j <= len2; j++) {
	    			if (s1.charAt(i -1) == s3.charAt(i + j -1)) 
	    				bp[i][j] |= bp[i-1][j];
	    			
	    			if (s2.charAt(j-1) == s3.charAt(i+j -1))
	    				bp[i][j] |= bp[i][j-1];    		
	    		}
	    	
	    	
	        return bp[len1][len2];
	    }
	}
	
	/*98. Validate Binary Search Tree 
	Difficulty: Medium
	Given a binary tree, determine if it is a valid binary search tree (BST).

	Assume a BST is defined as follows:

	The left subtree of a node contains only nodes with keys less than the node's key.
	The right subtree of a node contains only nodes with keys greater than the node's key.
	Both the left and right subtrees must also be binary search trees.
	Example 1:
	    2
	   / \
	  1   3
	Binary tree [2,1,3], return true.
	Example 2:
	    1
	   / \
	  2   3
	Binary tree [1,2,3], return false.*/
	
	public class Solution_98 {
	    public boolean isValidBST(TreeNode root) {
	        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	    }
	    
	    boolean isValidBST(TreeNode n, long min, long max) {
	       if (n == null) return true;
	       if (n.val <= min || n.val >= max) return false;
	        
	       return isValidBST(n.left, min, n.val) && isValidBST(n.right, n.val, max);
	    }
	    
	    public class Solution {
	        public boolean isValidBST(TreeNode root) {
	            return isValidBST(root, new TreeNode[1]);
	        }
	        
	        public boolean isValidBST(TreeNode cur, TreeNode[] prev) {
	            if (cur == null) return true;
	           
	            if (!isValidBST(cur.left, prev)) return false;
	            if (prev[0] != null && prev[0].val >= cur.val) return false;
	            prev[0] = cur;
	            
	            return isValidBST(cur.right, prev);
	        }
	    }
	    
	    public class Solution_better {
	    	public boolean isValidBST(TreeNode root) {
	    	    return isValid(root, null, null);
	    	}

	    	public boolean isValid(TreeNode root, Integer min, Integer max) {
	    	    if(root == null) return true;
	    	    if(min != null && root.val <= min) return false;
	    	    if(max != null && root.val >= max) return false;
	    	    
	    	    return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
	    	}}
		}
	
	/*99. Recover Binary Search Tree 
	Difficulty: Hard
	Two elements of a binary search tree (BST) are swapped by mistake.

	Recover the tree without changing its structure.

	Note:
	A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?*/
	
	public class Solution_99 {
	    public void recoverTree(TreeNode root) {
	       TreeNode[] save = new TreeNode[3];
	       travesal(root, save);
	       
	       if (save[0] != null) {
	           int v = save[0].val;
	           save[0].val = save[1].val;
	           save[1].val = v;
	       }
	    }
	    
	    public void travesal(TreeNode root, TreeNode[] save) {
	        if (root == null) return;
	        travesal(root.left, save);
	        if (save[2] != null) {
	           if (root.val < save[2].val) {
	               if (save[0] == null) {
	                   save[0] = save[2];
	                   save[1] = root;
	               } else if (root.val < save[1].val) save[1] = root;
	           }
	        }
	        save[2] = root;
	        travesal(root.right, save);
	    }	   
	}
	
	/*100. Same Tree
	Difficulty: Easy
	Given two binary trees, write a function to check if they are equal or not.

	Two binary trees are considered equal if they are structurally identical and the nodes have the same value.*/
	
	public class Solution_100 {
	    public boolean isSameTree(TreeNode p, TreeNode q) {
	        if (p == null && q == null) return true;
	        if ((p == null && q != null) ||(p != null && q == null)) return false;
	        if (p.val != q.val) return false;
	        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	    }
	}
	
	/*101. Symmetric Tree
	Difficulty: Easy
	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	But the following [1,2,2,null,3,null,3] is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
	Note:
	Bonus points if you could solve it both recursively and iteratively.*/
	
	public static class Solution_101 {
	    public boolean isSymmetric(TreeNode root) {
	        if (root == null) return true;
	        return isSymmetric(root.left, root.right);
	    }
	    
	    public boolean isSymmetric(TreeNode left, TreeNode right) {
	        if (left == null && right == null) return true;
	        if (left == null || right == null) return false;
	        
	        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	    }
	    
	    public static class Solution {
	    	public boolean isSymmetric_better(TreeNode root) {
	    	    if (root == null) return true;
	    	    Stack<TreeNode> stack = new Stack<>(); //stack can hold null!!!!!
	    	    stack.push(root.left);
	    	    stack.push(root.right);
	    	    while (!stack.empty()) {
	    	        TreeNode n1 = stack.pop(), n2 = stack.pop();
	    	        if (n1 == null && n2 == null) continue;
	    	        if (n1 == null || n2 == null || n1.val != n2.val) return false;
	    	        stack.push(n1.left);
	    	        stack.push(n2.right);
	    	        stack.push(n1.right);
	    	        stack.push(n2.left);
	    	    }
	    	    return true;
	    	}
	        public boolean isSymmetric(TreeNode root) {
	            if (root == null) return true;
	            
	            Stack<TreeNode> ls = new Stack<TreeNode>();
	            Stack<TreeNode> rs = new Stack<TreeNode>();
	            TreeNode ln = root.left;
	            TreeNode rn = root.right;
	            
	            while (ln != null || !ls.isEmpty()){
	                if (ln != null) {
	                    if (rn == null) return false;
	                    ls.push(ln);
	                    rs.push(rn);
	                    ln = ln.left;
	                    rn = rn.right;
	                } else {
	                    if (rn != null) return false;
	                    ln = ls.pop();
	                    if (rs.isEmpty()) return false;
	                    rn = rs.pop();
	                    if (ln.val != rn.val) return false;
	                    ln = ln.right;
	                    rn = rn.left;
	                }
	            }
	            
	            return rn == null && rs.isEmpty();
	        }
	    }
	}
	
	/*102. Binary Tree Level Order Traversal
	Difficulty: Easy
	Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

	For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its level order traversal as:
	[
	  [3],
	  [9,20],
	  [15,7]
	]*/
	
	public class Solution_102 {
	    public List<List<Integer>> levelOrder(TreeNode root) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        if (root == null) return res;
	        
	        Queue<TreeNode> queue = new LinkedList<TreeNode>();
	        queue.add(root);
	        
	        while (!queue.isEmpty()) {
	            List<Integer> list = new ArrayList<Integer>();
	            int size = queue.size();
	            for (int i = 0; i < size; i++) {
	            	TreeNode n = queue.poll();
	                list.add(0, n.val);	                
	                if (n.right != null) queue.offer(n.right);
	                if (n.left != null) queue.offer(n.left);
	            }
	            
	            res.add(list);
	        }
	        
	        return res;
	    }
	}
	
	/*106. Construct Binary Tree from Inorder and Postorder Traversal
	Difficulty: Medium
	Given inorder and postorder traversal of a tree, construct the binary tree.

	Note:
	You may assume that duplicates do not exist in the tree.*/
	
	public class Solution_106 {
	    public TreeNode buildTree(int[] inorder, int[] postorder) {
	        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length)
	            return null;
	        pos = postorder.length - 1;
	        return helper(inorder, postorder, 0, postorder.length - 1);
	    }
	    
	    int pos;
	    
	    TreeNode helper(int[] inorder, int[] postorder, int s, int e) {
	        if (s > e) return null;
	        TreeNode root = new TreeNode(postorder[pos]);
	        pos--;
	        
	        for (int i = s; i <= e; i++) {
	            if (inorder[i] == root.val) {
	                root.right = helper(inorder, postorder, i + 1, e);
	                root.left = helper(inorder, postorder, s, i - 1);
	                
	                break;
	            }
	        }
	        return root;
	    }
	}
	
	/*107. Binary Tree Level Order Traversal II 
	Difficulty: Easy
	Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

	For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its bottom-up level order traversal as:
	[
	  [15,7],
	  [9,20],
	  [3]
	]*/
	public class Solution_107 {
		public List<List<Integer>> levelOrderBottom(TreeNode root) {
	        List<List<Integer>> res = new LinkedList<List<Integer>>();
	        if (root == null) return res;
	        LinkedList<TreeNode> levelNodes = new LinkedList<TreeNode>();
	        levelNodes.offer(root);
	        levelNodes.offer(null);
	        List<Integer> list = new LinkedList<Integer>();
	        while (!levelNodes.isEmpty()) {
	            
	            TreeNode n = levelNodes.poll();
	            if (n == null) {
	                res.add(0, list);
	                if (!levelNodes.isEmpty()) 
	                    levelNodes.offer(null);
	                list = new LinkedList<Integer>();
	            } else {
	                list.add(n.val);
	                if (n.left != null) levelNodes.offer(n.left);
	                if (n.right != null) levelNodes.offer(n.right);
	            }
	        }
	        
	        return res;
	    }
	}
	
	/*108. Convert Sorted Array to Binary Search Tree
	Difficulty: Medium
	Given an array where elements are sorted in ascending order, convert it to a height balanced BST.*/
	
	public class Solution_108 {
	    public TreeNode sortedArrayToBST(int[] nums) {
	        if (nums == null || nums.length == 0) return null;
	        return helper(nums, 0, nums.length - 1);
	    }
	    
	    TreeNode helper(int[] nums, int l, int r) {
	        if (l > r) return null;
	        if (l == r) return new TreeNode(nums[l]);
	        
	        int m = l + (r - l) / 2;
	        TreeNode root = new TreeNode(nums[m]);
	        root.left = helper(nums, l, m - 1);
	        root.right = helper(nums, m + 1, r);
	        
	        return root;
	    }
	}
	
	/*109. Convert Sorted List to Binary Search Tree
	Difficulty: Medium
	Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.*/
	
	public class Solution_109 {
	    public TreeNode sortedListToBST(ListNode head) {
	        return helper(head, null);
	    }
	    
	    TreeNode helper(ListNode head, ListNode tail) {
	        if (head == tail) return null;
	        if (head.next == tail) return new TreeNode(head.val);
	        
	        ListNode s = head;
	        ListNode f = head;
	        while(f != tail && f.next != tail) {
	            s = s.next;
	            f = f.next.next;
	        }
	        
	        TreeNode root = new TreeNode(s.val);
	        root.left = helper(head, s);
	        root.right = helper(s.next, tail);
	        return root;
	    }	    
	}
	
	/*110. Balanced Binary Tree 
	Difficulty: Easy
	Given a binary tree, determine if it is height-balanced.

	For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/
	
	public class Solution_110 {
	    public boolean isBalanced(TreeNode root) {
	        return getHeight(root) >= 0;
	    }
	    
	    int getHeight(TreeNode root) {
	        if (root == null) return 0;
	        int l = getHeight(root.left);
	        if (l < 0) return l;
	        int r = getHeight(root.right);
	        if (r < 0) return r;
	        
	        int d = Math.abs(l-r);
	        if (d > 1) return -1;
	        return 1 + Math.max(l, r);
	    }	   
	}
	
	/*111. Minimum Depth of Binary Tree 
	Difficulty: Easy
	Given a binary tree, find its minimum depth.

	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.*/
	
	public class Solution_111 {
		public int minDepth(TreeNode root) {
		       if (root == null) return 0;
		       if (root.left == null && root.right == null) return 1;
		       if (root.left == null) return 1 + minDepth(root.right);
		       else if (root.right == null) return 1 + minDepth(root.left);
		       else {
		           return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
		       }
		   }
		}
	
	/*112. Path Sum
	Difficulty: Easy
	Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \      \
	        7    2      1
	return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.*/
	
	public class Solution_112 {
		public boolean hasPathSum(TreeNode root, int sum) {
	        if(root == null) return false;

	        if(root.left == null && root.right == null) return sum == root.val;

	        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	    }
	}
	
	/*113. Path Sum II
	Difficulty: Medium
	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \    / \
	        7    2  5   1
	return
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]*/
	
	public class Solution_113 {
	    public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        List<List<Integer>> res = new LinkedList<List<Integer>>();
	        helper(root, sum, new Stack<Integer>(), res);
	        return res;
	    }
	    
	    void helper(TreeNode root, int sum, Stack<Integer> stack, List<List<Integer>> res) {
	        if (root == null) return;
	        stack.push(root.val);
	        if (root.left == null && root.right == null) {
	            if (sum == root.val) {
	            	List<Integer> list = new LinkedList<Integer>();
	            	list.addAll(stack.subList(0, stack.size()));
	                res.add(list);
	            }
	        }
	        helper(root.left, sum - root.val, stack, res);
	        helper(root.right, sum - root.val, stack, res);
	        stack.pop();
	    }
	}
	
	/*114. Flatten Binary Tree to Linked List
	Difficulty: Medium
	Given a binary tree, flatten it to a linked list in-place.

	For example,
	Given

	         1
	        / \
	       2   5
	      / \   \
	     3   4   6
	The flattened tree should look like:
	   1
	    \
	     2
	      \
	       3
	        \
	         4
	          \
	           5
	            \
	             6*/
	
	public class Solution_114 {
	     public void flatten(TreeNode root) {
	          helper(root, new TreeNode[1]);
	     }
	     
	     public void helper(TreeNode root, TreeNode[] prev) {
	         if (root == null) return;
	         
	         TreeNode temp = root.left;
	         root.left = root.right;
	         root.right = temp;
	         if (prev[0] == null) prev[0] = root;
	         else prev[0].right = root;
	         prev[0] = root;
	         
	         helper(root.right, prev);
	         helper(root.left, prev);
	         
	         root.left = null;
	     }
	}
	
	public void testFun() {		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		Solution_113 s = new Solution_113();
		System.out.println(s.pathSum(root, 22));
		
	}
}
