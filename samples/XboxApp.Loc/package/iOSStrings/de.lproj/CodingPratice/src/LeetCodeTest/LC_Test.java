package LeetCodeTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import junit.framework.TestCase;

//Given a [sorted] array of integers, return indices of 2 numbers such that they add up to a specific target.
//You may assume that each input would have exactly one solution. 
/**
 * @author chuliu
 *
 */
public class LC_Test extends TestCase {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	};

	public class Solution {
		public int[] twoSum(int[] nums, int target) {
			if (nums == null)
				return null;

			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
			for (int i = 0; i < nums.length; i++) {
				map.put(nums[i], i);
			}

			for (int i = 0; i < nums.length; i++) {
				int v = target - nums[i];
				if (map.containsKey(v)) {
					int j = (int) map.get(v);
					if (i != j) {
						return new int[] { i, j };
					}
				}
			}

			return null;
		}
	}

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
				if (i == 0)
					maxOfLeft = nums2[j - 1];
				else if (j == 0)
					maxOfLeft = nums1[i - 1];
				else
					maxOfLeft = Math.max(nums1[i - 1], nums2[j - 1]);

				if ((m + n) % 2 > 0) {
					return maxOfLeft;
				} else {
					int minOfRight;
					if (i == m)
						minOfRight = nums2[j];
					else if (j == n)
						minOfRight = nums1[i];
					else
						minOfRight = Math.min(nums1[i], nums2[j]);
					return (maxOfLeft + minOfRight) / 2.0;
				}
			}
		}

		return 0.0;
	}

	public String longestPalindrome(String s) {
		if (s == null)
			return s;
		int len = s.length();
		if (len == 1)
			return s;
		int maxLen = 0;
		int maxL = 0;
		for (int i = 0; i < len && len - i > maxLen / 2; i++) {
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

			if (index == 0)
				inc = 1;
			if (index == numRows - 1)
				inc = -1;
			index += inc;
		}

		StringBuffer str = new StringBuffer("");
		for (StringBuffer sb : sba)
			str.append(sb);

		return str.toString();
	}

	public int reverse(int x) {
		return reverseLong(x);
	}

	public int reverseLong(long x) {
		if (x < 0)
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
		else
			return (int) ret;
	}

	public int myAtoi(String str) {
		if (str == null || str.length() == 0)
			return 0;
		str = str.trim();

		long value = 0;
		long sign = 1;
		boolean setSign = false;
		boolean firstDigital = false;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c)) {
				if (firstDigital)
					break;
				else if (c == '+' && !setSign) {
					sign = 1;
					setSign = true;
				} else if (c == '-' && !setSign) {
					sign = -1;
					setSign = true;
				} else
					return 0;

			} else {
				if (!firstDigital) {
					firstDigital = true;
					value = c - '0';
				} else {
					value = value * 10 + c - '0';
					if (value > Integer.MAX_VALUE && sign == 1)
						return Integer.MAX_VALUE;
					else if (sign == -1 && -value < Integer.MIN_VALUE)
						return Integer.MIN_VALUE;
				}

			}
		}

		value *= sign;
		if (value > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (value < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		return (int) value;
	}

	public boolean isMatch(String s, String p) {
		if (s == null || p == null)
			return false;

		int m = s.length();
		int n = p.length();

		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;

		for (int i = 0; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				char c = p.charAt(j - 1);
				if (c != '*') {
					dp[i][j] = (i > 0) && dp[i - 1][j - 1] && (c == '.' || c == s.charAt(i - 1));
				} else {
					dp[i][j] = (j >= 2 && dp[i][j - 2]) || (i > 0) && (j > 1) && dp[i - 1][j]
							&& (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
				}
			}
		}

		return dp[m][n];
	}

	public int maxArea(int[] height) {
		if (height == null || height.length < 2)
			return 0;
		int max = 0;

		int i = 0;
		int j = height.length - 1;

		while (i < j)
			max = Math.max(max, (j - i) * (height[i] > height[j] ? height[j--] : height[i++]));

		return max;
	}

	public String intToRoman(int num) {
		String M[] = { "", "M", "MM", "MMM" };
		String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}

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

	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";

		String comm = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int j = 0;
			for (; j < comm.length() && j < strs[i].length(); j++) {
				if (comm.charAt(j) != strs[i].charAt(j))
					break;
			}

			if (j > 0)
				comm = comm.substring(0, j);
			else {
				comm = "";
				break;
			}
		}

		return comm;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 3)
			return result;

		java.util.Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			int j = i + 1;
			int k = nums.length - 1;
			int total = -nums[i];
			while (j < k) {
				int t = nums[j] + nums[k];
				if (t == total) {
					result.add(Arrays.asList(nums[i], nums[j], nums[k]));
					while (j < k && nums[j] == nums[j + 1])
						j++;
					j++;
				} else if (t < total) {
					while (j < k && nums[j] == nums[j + 1])
						j++;
					j++;
				} else
					k--;
			}
		}

		return result;
	}

	public int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3)
			return Integer.MAX_VALUE;

		int value = 0;
		int diff = Integer.MAX_VALUE;

		int len = nums.length;
		int total;

		Arrays.sort(nums);
		for (int i = 0; i < len; i++) {
			int j = i + 1;
			int k = len - 1;

			while (j < k) {
				total = nums[i] + nums[j] + nums[k];
				if (total == target)
					return target;
				else if (total > target) {
					k--;
					if (total - target < diff) {
						diff = total - target;
						value = total;
					}
				} else {
					j++;
					if (target - total < diff) {
						diff = target - total;
						value = total;
					}
				}
			}
		}

		return value;
	}

	public boolean isValid(String s) {
		if (s == null || s.length() == 0)
			return true;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int v = getParentheseValue(c);
			if (v == 0)
				continue;
			else if (v < 0)
				stack.push(v);
			else if (stack.isEmpty())
				return false;
			else if (stack.pop() + v != 0)
				return false;
		}

		return stack.isEmpty();
	}

	int getParentheseValue(char c) {
		switch (c) {
		case '(':
			return -1;
		case ')':
			return 1;
		case '{':
			return -2;
		case '}':
			return 2;
		case '[':
			return -3;
		case ']':
			return 3;
		default:
			return 0;
		}
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode head = new ListNode(0);
		ListNode curr = head;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}

		if (l1 != null)
			curr.next = l1;
		else if (l2 != null)
			curr.next = l2;

		return head.next;
	}

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		if (n <= 0)
			return list;

		char[] buff = new char[2 * n];
		addParenthesis(list, buff, n, n, 0, n);

		return list;
	}

	void addParenthesis(List<String> list, char[] buff, int left, int right, int count, int n) {
		if (count == 2 * n)
			list.add(String.valueOf(buff));

		if (left > 0) {
			buff[count] = '(';
			addParenthesis(list, buff, left - 1, right, count + 1, n);
		}

		if (right > left) {
			buff[count] = ')';
			addParenthesis(list, buff, left, right - 1, count + 1, n);
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				else if (o1.val == o2.val)
					return 0;
				else
					return 1;
			}
		});

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

	public ListNode swapPairs(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode curr = dummy;

		while (curr.next != null && curr.next.next != null) {
			ListNode n1 = curr.next;
			ListNode n2 = n1.next;

			curr.next = n2;
			n1.next = n2.next;
			n2.next = n1;
			curr = n1;
		}

		return dummy.next;
	}

	public ListNode swapPairs_recursive(ListNode head) {
		if ((head == null) || (head.next == null))
			return head;
		ListNode n = head.next;
		head.next = swapPairs(head.next.next);
		n.next = head;
		return n;
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k <= 1)
			return head;

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;

		while (cur != null && cur.next != null) {
			ListNode first = cur.next;
			ListNode last = first;
			for (int i = 0; i < k - 1; i++) {
				last = last.next;
				if (last == null)
					break;
			}

			if (last != null) {
				ListNode nextFirst = last.next;
				last.next = null;
				cur.next = reverseList(first);
				first.next = nextFirst;
				cur = first;
			}
		}

		return dummy.next;
	}

	ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode n = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return n;
	}

	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int j = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[j - 1])
				continue;
			if (i != j)
				nums[j++] = nums[i];
		}

		return j;
	}

	public int removeElement(int[] nums, int val) {
		int index = 0;
		boolean shifted = false;
		for (int n : nums) {
			if (val == n)
				shifted = true;
			else if (shifted) {
				nums[index++] = n;
			}
		}

		return index;
	}

	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null)
			return -1;
		int lenH = haystack.length();
		int lenN = needle.length();

		if (lenN > lenH)
			return -1;
		if (lenN == 0 || lenH == 0)
			return 0;

		for (int i = 0; i <= lenH - lenN; i++) {
			boolean match = true;
			for (int j = 0; j < lenN; j++) {
				if (haystack.charAt(j + i) != needle.charAt(j)) {
					match = false;
					break;
				}
			}

			if (match)
				return i;
		}

		return -1;
	}

	public int divide(int d1, int d2) {
		long dividend = d1;
		long divisor = d2;
		boolean positive = true;
		if (dividend < 0 && divisor < 0) {
			dividend = -dividend;
			divisor = -divisor;
		} else if (dividend < 0 || divisor < 0) {
			positive = false;
			if (dividend < 0)
				dividend = -dividend;
			if (divisor < 0)
				divisor = -divisor;
		}

		if (divisor == 0)
			return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;

		if (divisor == 1) {
			long res = positive ? dividend : -dividend;
			if (res > Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			if (res < Integer.MIN_VALUE)
				return Integer.MIN_VALUE;

			return (int) res;
		}

		long res = 0;
		while (dividend >= divisor) {
			long div = dividend;
			long d = divisor;
			long multi = 1;
			while (div > d << 1) {
				d <<= 1;
				multi <<= 1;
			}

			res += multi;
			dividend -= d;
		}

		res = positive ? res : -res;
		if (res > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (res < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		return (int) res;
	}

	public void nextPermutation(int[] A) {
		if (A == null || A.length <= 1)
			return;
		int i = A.length - 2;
		while (i >= 0 && A[i] >= A[i + 1])
			i--; // Find 1st id i that breaks descending order
		if (i >= 0) { // If not entirely descending
			int j = A.length - 1; // Start from the end
			while (A[j] <= A[i])
				j--; // Find rightmost first larger id j
			swap(A, i, j); // Switch i and j
		}
		reverse(A, i + 1, A.length - 1); // Reverse the descending sequence
	}

	public void swap(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	public void reverse(int[] A, int i, int j) {
		while (i < j)
			swap(A, i++, j--);
	}

	public int longestValidParentheses_my_initial_thought(String s) {
		if (s == null)
			return 0;
		int len = s.length();
		if (len < 2)
			return 0;
		int[] values = new int[len];
		for (int i = 0; i < len; i++)
			values[i] = s.charAt(i) == '(' ? -1 : 1;
		int max = 0;
		for (int i = 0; i < len - max; i++) {
			int total = 0;
			for (int j = i; j < len; j++) {
				total += values[j];
				if (total > 0 || total < -(len - j))
					break;
				else if (total == 0) {
					int v = j - i + 1;
					if (v > max)
						max = v;
				}
			}
		}

		return max;
	}

	public int longestValidParentheses(String s) {
		int res = 0;
		int tep = 0;
		Stack<Integer> s1 = new Stack<>();
		int data[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(')
				s1.push(i);
			else {
				if (!s1.empty()) {
					data[i] = 1;
					data[s1.pop()] = 1;
				}
			}
		}
		for (int i : data) {
			if (i == 1)
				tep++;
			else {
				res = Math.max(tep, res);
				tep = 0;
			}
		}
		return Math.max(tep, res);
	}

	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int lo = 0, hi = nums.length - 1;

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] == target)
				return mid;

			if (nums[mid] >= nums[lo]) {
				if (target >= nums[lo] && target < nums[mid])
					hi = mid - 1;
				else
					lo = mid + 1;
			} else {
				if (target > nums[mid] && target <= nums[hi])
					lo = mid + 1;
				else
					hi = mid - 1;
			}
		}

		return -1;
	}

	public int[] searchRange(int[] nums, int target) {
		int[] ret = { -1, -1 };
		if (nums == null || nums.length == 0)
			return ret;

		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] == target) {
				lo = hi = mid;
				while (lo > 0 && nums[lo - 1] == target)
					lo--;
				while (hi < nums.length - 1 && nums[hi + 1] == target)
					hi++;
				ret[0] = lo;
				ret[1] = hi;

				return ret;
			} else if (nums[mid] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}

		return ret;
	}

	public int searchInsert_my(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		int lo = 0, hi = nums.length - 1;

		while (true) {
			int mid = (lo + hi) / 2;
			if (nums[mid] == target)
				return mid;
			else if (nums[mid] < target) {
				if (mid + 1 < hi)
					lo = mid + 1;
				else
					break;
			} else {
				if (mid - 1 > lo)
					hi = mid - 1;
				else
					break;
			}
		}

		int diff = Integer.MAX_VALUE;
		int index = -1;
		for (int i = lo; i <= hi; i++) {
			int d = Math.abs(nums[i] - target);
			if (d < diff) {
				diff = d;
				index = target > nums[i] ? i + 1 : i;
			}
		}

		return index;
	}

	public int searchInsert(int[] A, int target) {
		int low = 0, high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			if (!isParticallyValid(board, i, 0, i, 8))
				return false;
			if (!isParticallyValid(board, 0, i, 8, i))
				return false;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!isParticallyValid(board, i * 3, j * 3, i * 3 + 2, j * 3 + 2))
					return false;
			}
		}
		return true;
	}

	private boolean isParticallyValid(char[][] board, int x1, int y1, int x2, int y2) {
		Set<Character> singleSet = new HashSet<Character>();
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if (board[i][j] != '.')
					if (!singleSet.add(board[i][j]))
						return false;
			}
		}
		return true;
	}

	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0)
			return;
		solve(board);
	}

	public boolean solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {// trial. Try 1 through 9
														// for each cell
						if (isValid(board, i, j, c)) {
							board[i][j] = c; // Put c for this cell

							if (solve(board))
								return true; // If it's the solution return true
							else
								board[i][j] = '.'; // Otherwise go back
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValid(char[][] board, int i, int j, char c) {
		// Check colum
		for (int row = 0; row < 9; row++)
			if (board[row][j] == c)
				return false;

		// Check row
		for (int col = 0; col < 9; col++)
			if (board[i][col] == c)
				return false;

		// Check 3 x 3 block
		for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
			for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
				if (board[row][col] == c)
					return false;
		return true;
	}

	public String countAndSay(int n) {
		if (n <= 0)
			return "01";

		String s = "1";
		for (int i = 0; i < n - 1; i++) {
			char[] array = s.toCharArray();
			StringBuffer sb = new StringBuffer();
			char c = ' ';
			int count = 0;
			for (char ch : array) {
				if (count == 0) {
					c = ch;
					count++;
				} else if (ch == c)
					count++;
				else {
					sb.append(count).append(c);
					c = ch;
					count = 1;
				}
			}

			sb.append(count).append(c);

			s = sb.toString();
		}

		return s;
	}

	public boolean isMatch_wild_card_string(String s, String p) {
		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
		match[s.length()][p.length()] = true;
		for (int i = p.length() - 1; i >= 0; i--) {
			if (p.charAt(i) != '*')
				break;
			else
				match[s.length()][i] = true;
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = p.length() - 1; j >= 0; j--) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
					match[i][j] = match[i + 1][j + 1];
				else if (p.charAt(j) == '*')
					match[i][j] = match[i + 1][j] || match[i][j + 1];
				else
					match[i][j] = false;
			}
		}
		return match[0][0];
	}

	public int jump(int[] nums) {
		if (nums == null || nums.length == 0)
			return -1;
		if (nums.length == 1)
			return 0;

		int steps = 1;
		int prevMax = nums[0];
		if (prevMax <= 0)
			return -1;

		int currMax = 0;
		for (int i = 1; i < nums.length - 1; i++) {
			currMax = Math.max(i + nums[i], currMax);
			if (i == prevMax) {
				if (currMax <= 0)
					return -1;
				prevMax = currMax;
				currMax = 0;
				steps++;
			}
		}

		return steps;
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return lists;
		doPermute(nums, 0, lists);

		return lists;
	}

	void doPermute(int[] nums, int index, List<List<Integer>> lists) {
		if (index == nums.length - 1) {
			List<Integer> list = new ArrayList<Integer>(nums.length);
			for (int i = 0; i < nums.length; i++)
				list.add(nums[i]);
			lists.add(list);
		} else {
			doPermute(nums, index + 1, lists);
			for (int i = index + 1; i < nums.length; i++) {
				exch(nums, i, index);
				doPermute(nums, index + 1, lists);
				exch(nums, i, index);
			}
		}
	}

	void exch(int[] nums, int i1, int i2) {
		int temp = nums[i2];
		nums[i2] = nums[i1];
		nums[i1] = temp;
	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> lists = new ArrayList<List<String>>();
		if (n <= 0)
			return lists;

		char[][] board = new char[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';
		doSolveNQueens(board, 0, n, lists);

		return lists;
	}

	void doSolveNQueens(char[][] b, int r, int n, List<List<String>> lists) {
		if (r == b.length) {
			List<String> l = new ArrayList<String>();
			for (char[] row : b)
				l.add(new String(row));
			lists.add(l);
		} else {
			for (int c = 0; c < n; c++) {
				if (isValid(r, c, b, n)) {
					b[r][c] = 'Q';
					doSolveNQueens(b, r + 1, n, lists);
					b[r][c] = '.';
				}
			}
		}
	}

	boolean isValid(int r, int c, char[][] b, int n) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < n; j++) {
				if (b[i][j] == 'Q' && (i + j == r + c || i + c == j + r || j == c)) {
					return false;
				}
			}
		}
		return true;
	}

	int totalNQueenSolution = 0;

	public int totalNQueens(int n) {
		if (n <= 0)
			return 0;
		if (n == 1)
			return 1;

		char[][] board = new char[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';
		totalNQueenSolution = 0;
		getTotalNQueens(board, 0, n);

		return totalNQueenSolution;
	}

	void getTotalNQueens(char[][] b, int r, int n) {
		if (r == b.length) {
			totalNQueenSolution++;
		} else {
			for (int c = 0; c < n; c++) {
				if (isValid(r, c, b, n)) {
					b[r][c] = 'Q';
					getTotalNQueens(b, r + 1, n);
					b[r][c] = '.';
				}
			}
		}
	}

	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int max = Integer.MIN_VALUE;
		int subtotal = 0;

		for (int i = 0; i < nums.length; i++) {
			subtotal += nums[i];
			if (subtotal > max)
				max = subtotal;
			if (subtotal < 0)
				subtotal = 0;
		}

		return max;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return list;
		int rLow = 0, rHi = matrix.length - 1, cLow = 0, cHi = matrix[0].length - 1;

		while (rLow <= rHi && cLow <= cHi) {
			for (int i = cLow; i <= cHi; i++)
				list.add(matrix[rLow][i]);
			rLow++;

			for (int i = rLow; i <= rHi; i++)
				list.add(matrix[i][cHi]);
			cHi--;

			for (int i = cHi; i >= cLow && rLow <= rHi; i--)
				list.add(matrix[rHi][i]);
			rHi--;

			for (int i = rHi; i >= rLow && cLow <= cHi; i--)
				list.add(matrix[i][cLow]);
			cLow++;
		}

		return list;
	}

	public boolean canJump(int[] nums) {
		if (nums == null || nums.length == 0)
			return false;
		if (nums.length == 1)
			return true;

		int max = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i > max)
				return false;
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
            if (intervals == null || intervals.size() < 2) return intervals;
            
            Stack<Integer> needToDelete = new Stack<Integer>();
            
            intervals.sort(new Comparator<Interval>() {
                public int compare(Interval i1, Interval i2) {
                    return i1.start - i2.start;
                }});
            
            Interval prev = intervals.get(0);
            for (int i = 1; i < intervals.size(); i++) {
                Interval curr = intervals.get(i);
                if (prev.end >= curr.start) {
                    needToDelete.push(i - 1);
                    curr.start = prev.start;
                }
                prev = curr;
            }
            
            while (!needToDelete.isEmpty())
                intervals.remove((int)needToDelete.pop());
            
            return intervals;
        }
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++)); 
        return result;
    }
	
	public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        
        int[][] dp = new int[m][n];
       
        for (int i = 0; i < n; i++)
           dp[0][i] = 1;
        for (int i = 0; i < m; i++)
           dp[i][0] = 1;
           
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                
        return dp[m-1][n-1];
    }
	
	 public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                obstacleGrid[i][j] = 1 - obstacleGrid[i][j];
                if (obstacleGrid[i][j] > 0) { //not obstacle
                    if (i != 0 || j!= 0)
                        obstacleGrid[i][j] = ((i > 0) ? obstacleGrid[i-1][j] : 0) + ((j > 0) ? obstacleGrid[i][j-1] : 0);
            }
        }
        
        return obstacleGrid[m-1][n-1];
    }
	 
	 public int minPathSum(int[][] grid) {
	        if (grid == null) return 0;
	        
	        int m = grid.length, n = grid[0].length;
	        if (m == 0 || n == 0) return 0;
	        
	        for (int i = 0; i < m; i++)
	            for (int j = 0; j < n; j++) {
	                if (i != 0 && j != 0)
	                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
	                else if (i != 0)
	                    grid[i][j] += grid[i-1][j];
	                else if (j != 0)
	                    grid[i][j] += grid[i][j-1];
	            }
	        
	        return grid[m-1][n-1];
	    }
	 
	 public int[] plusOne(int[] digits) {
	        if (digits == null || digits.length == 0)
	            return new int[] {1};
	        int carry = 0;
	        for (int i = digits.length - 1; i >= i; i++){
	            digits[i] += carry;
	            if (digits[i] > 9) {
	                carry = 1;
	                digits[i] -= 10;
	            } else carry = 0;
	        }
	        
	        if (carry > 0) {
	            int[] newDigits = new int[digits.length + 1];
	            newDigits[0] = carry;
	            
	            digits = newDigits;
	        }
	        
	        return digits;
	    }
	 
	 public String addBinary(String a, String b) {
	        if (a == null || a.length() == 0) return b;
	        if (b == null || b.length() == 0) return a;
	        
	        StringBuffer sb = new StringBuffer();
	        int carry = 0;
	        
	        int i = a.length() - 1;
	        int j = b.length() - 1;
	        
	        while (i >= 0 || j >= 0) {
	            int v = carry;
	            if (i >= 0) v += a.charAt(i--) - '0';
	            if (j >= 0) v += b.charAt(j--) - '0';
	            
	            if (v >= 2) {
	                v -= 2;
	                carry = 1;
	            }
	            else carry = 0;
	            
	            sb.insert(0, (char) (v + '0'));
	            
	            
	        }
	        
	        if (carry > 0)
	            sb.insert(0, '1');
	        
	        return sb.toString();
	        
	    }
	 
	
    public List<String> fullJustify(String[] words, int L) {
        List<String> lines = new ArrayList<String>();
        
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;
                count += words[last].length() + 1;
                last++;
            }
            
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < L; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff;
                int r = (L - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        
        
        return lines;
    }
    
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        
        long v = x;
        long low = 1, hi = x;
        while (low <= hi) {
            long mid = (low + hi) / 2;
            long t = mid * mid;
            if (t == v) return (int) mid;
            else if (t > v) hi = mid - 1;
            else low = mid + 1;
        }
        
        return (int) hi;
    }
    
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n < 2) return 1;
        int [] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i -2];
        }
        
        return dp[n];
    }
    
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
    
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n < k) return result;
        
        List<Integer> list = new ArrayList<Integer>();
        combine(1, n, k, list, result);
        return result;
    }
    
    void combine(int start, int n, int k, List<Integer> list,  List<List<Integer>> result) {
        if (0 == k) {
            result.add(new ArrayList<Integer>(list));
        } else {
            for (int i = start; i <= n - k + 1; i++) { 
                list.add(i);
                combine(i + 1, n, k -1, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <= nums.length; i++) {
            doGetSubSets(nums, 0, i, list, result);
        }
        
       return result; 
    }
    
    void doGetSubSets(int[] nums, int si, int k, List<Integer> list, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<Integer> (list));
        } else {
            for (int i = si; i <= nums.length - k ; i++) {
                list.add(nums[i]);
                doGetSubSets(nums, i + 1, k - 1, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) return false;
        char[] w  = word.toCharArray();
        int m = board.length, n = board[0].length;
        int len = word.length();
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (exist(board, m, n, i, j, w, len, 0)) return true;
        	}
        }
        
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
    
    public int removeDuplicates_2(int[] nums) {
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
    public class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    	  };
    
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    boolean isValidBST(TreeNode n, long min, long max) {
        if (n == null) return true;
        
        if (n.val <= min || n.val >= max) return false;
        
        return isValidBST(n.left, min, n.val) && isValidBST(n.right, n.val, max);
    }
    
    public class TreeNodePair{
        TreeNode first;
        TreeNode second;
    };
    
    TreeNode prev = null;
    TreeNodePair pair = new TreeNodePair();
    
    public void recoverTree(TreeNode root) {
        traversal(root);
       
        if (pair.first != null && pair.second != null) {
            int v = pair.first.val;
            pair.first.val = pair.second.val;
            pair.second.val = v;
        }
    }
    
    public void traversal(TreeNode node) {
        if (node == null) return;
        traversal(node.left);
        if (prev != null) {
            if (node.val < prev.val) {
                if (pair.first == null) {
                    pair.first = prev;
                    pair.second = node;
                }
                else pair.second = node;
            }
        }
        
        prev = node;
        
        traversal(node.right);
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
   
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        
        List<TreeNode> level = new ArrayList<TreeNode> ();
        level.add(root);
        
        while (!level.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            List<TreeNode> back = new ArrayList<TreeNode>();
            for (TreeNode n : level) {
                list.add(n.val);
                if (n.left != null) back.add(n.left);
                if (n.right != null) back.add(n.right);
            }
                
            res.add(list);
            level = back;
        }
        
        return res;
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        
        List<TreeNode> level = new ArrayList<TreeNode> ();
        level.add(root);
        boolean leftToRight = true;
        
        while (!level.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            List<TreeNode> back = new ArrayList<TreeNode>();
            for (TreeNode n : level) {
                if (leftToRight) list.add(n.val);
                else list.add(0, n.val);
                if (n.left != null) back.add(n.left);
                if (n.right != null) back.add(n.right);
            }
            
            leftToRight = !leftToRight;
                
            res.add(list);
            level = back;
        }
        
        return res;
    }
    
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) return null;
        
        return doBuildTree(preorder, inorder, 0, preorder.length - 1);
    }
    
    int preorderPos = 0;
    public TreeNode doBuildTree(int[] preorder, int[] inorder,int left, int right) {
        if (preorderPos >= inorder.length) return null;
        if (left > right) return null;
        
        TreeNode r = new TreeNode(preorder[preorderPos]);
        for (int i = left; i <= right; i++) {
            if (preorder[preorderPos] == inorder[i]) {
            	preorderPos++;
                 r.left = doBuildTree(preorder, inorder, left, i -1);
                 r.right = doBuildTree(preorder, inorder, i + 1, right);
                 break;   
            }
        }
               
        return r;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        List<TreeNode> array = new ArrayList<TreeNode>();
        while (head != null) {
            array.add(new TreeNode(head.val));
            head = head.next;
        }
        
        return sortedListToBST(array, 0, array.size() - 1);
    }
    
    TreeNode sortedListToBST(List<TreeNode> array, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode head = new TreeNode(array.get(mid).val);
        head.left = sortedListToBST(array, l, mid - 1);
        head.right = sortedListToBST(array, mid + 1, r);
        return head;
    }
    
    public boolean isBalanced(TreeNode root) {
        int height = getHeight(root);
        return height >= 0;
    }
    
    int getHeight(TreeNode root) {
        if (root == null) return 0;
        
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        
        if (left < 0 || right < 0 || Math.abs(left-right) > 1) return -1;
        
        return 1 + Math.max(left, right);
    }
    
    int mindepth = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        minDepth(root, 0);
        
        return mindepth;
    }
    
    public void minDepth(TreeNode node, int depth) {
        if (node.left == null && node.right == null) {
            if (1 + depth < mindepth) mindepth = depth + 1;
        } 
        if (node.left != null) minDepth(node.left, depth + 1);
        if (node.right != null) minDepth(node.right, depth + 1); 
    }
        
    public boolean hasPathSum(TreeNode root, int sum) {
        return preOrderTraversal(root, sum, 0);
     }
     
     public boolean preOrderTraversal(TreeNode root, int sum, int total){
         if (root == null) return false;
         if (root.left == null && root.right == null && total + root.val == sum) return true;
         
         return preOrderTraversal(root.left, sum, total + root.val) || preOrderTraversal(root.right, sum, total + root.val);
     }
     
     public List<List<Integer>> pathSum(TreeNode root, int sum) {
         Stack<TreeNode> stack = new Stack<TreeNode>();
         List<List<Integer>> paths = new ArrayList<List<Integer>>();
         getPathSum(root, sum, stack, paths);
         return paths;
     }
     
     public void getPathSum(TreeNode node, int sum, Stack<TreeNode> stack, List<List<Integer>> paths) {
         if (node == null) return;
         
         stack.push(node);
         sum -= node.val;
         if (node.left == null && node.right == null && sum == 0) {
             List<Integer> list = new ArrayList<Integer>();
             for (int i = 0; i < stack.size();i++) list.add(stack.get(i).val);
             paths.add(list);
         }
         
         getPathSum(node.left, sum, stack, paths);
         getPathSum(node.right, sum, stack, paths);
         
         sum += node.val;
         stack.pop();
     }
    
     TreeNode prevousNode;
     public void flatten(TreeNode root) {
          if (root == null) return;
         
         if (prevousNode != null)
        	 prevousNode.right = root;
         prevousNode = root;
         TreeNode right = root.right;
         
         flatten(root.left);
         root.left = null;
         flatten(right);
     }
     
     public int numDistinct(String s, String t) {
         if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) return 0;
         int sLen = s.length();
         int tLen = t.length();
         
         int[][] dp = new int[tLen +1][sLen + 1];
         dp[0][0] = 1;
         
         for (int j = 0; j < sLen; j++) dp[0][j+1] = 1;
       
         for (int i= 0; i < tLen; i++)
        	 for (int j = 0; j< sLen; j++) {
        		 char tc = t.charAt(i);
        		 char sc = s.charAt(j);
        		 if (sc == tc)
        			 dp[i+1][j+1] =  dp[i][j] + dp[i+1][j];
        		 else dp[i+1][j+1] = dp[i+1][j];
        	 }
         	
         
         return dp[tLen][sLen];
     }
     
     public class TreeLinkNode {
    	      int val;
    	     TreeLinkNode left, right, next;
    	     TreeLinkNode(int x) { val = x; }
    	  };
    	
     
     public void connect(TreeLinkNode root) {
         if (root == null) return;
         if (root.left != null) root.left.next = root.right;
         if (root.right != null && root.next != null)
             root.right.next = root.next.left;
         
         connect(root.left);
         connect(root.right);
     }
     
     public void connect2(TreeLinkNode root) {
         if (root == null) return;
         
         if (root.left != null) {
             if (root.right != null) root.left.next = root.right;
             else {
                 TreeLinkNode next = root.next;
                 while (next != null) {
                     if (next.left != null || next.right != null) {
                         root.left.next = next.left != null ? next.left : next.right;
                         break;
                     } else next = next.next;
                 }
             }
         }
         if (root.right != null) {
             TreeLinkNode next = root.next;
             while (next != null) {
                 if (next.left != null || next.right != null) {
                     root.right.next = next.left != null ? next.left : next.right;
                     break;
                 } else next = next.next;
             }
         }
         
         
         connect2(root.right);
         connect2(root.left);
     }
     
     public List<List<Integer>> generate(int numRows) {
         List<List<Integer>> res = new ArrayList<List<Integer>>(numRows);        
         List<Integer> list = new ArrayList<Integer>(numRows);
         for (int i = 0; i < numRows; i++) {
             list = new ArrayList<Integer>(list);
             list.add(1);
             for (int j = i-1; j >0; j--) 
                 list.set(j, list.get(j) + list.get(j -1));
             res.add(list);
         }         
         return res;
     }
     
     public List<Integer> getRow(int rowIndex) {
         List<Integer> list = new ArrayList<Integer>();
         if (rowIndex >= 0) {
             int[] a = new int[rowIndex + 1];
             for (int i = 0; i <= rowIndex; i++)
             {
                 a[i] = 1;
                 for (int j = i -1; j > 0; j--)
                     a[j] = a[j] + a[j -1];
             }
            
            for (int v : a)
             list.add(v);
         }
         
         return list;
     }
     
     public int minimumTotal(List<List<Integer>> triangle) {
	    int sz = triangle.size();
	    int[] results = new int[sz+1];
	    
	    for(int i=sz-1; i>=0; i--) {
	        List<Integer> tmp = triangle.get(i);
	        
	        for(int j=0; j<tmp.size(); j++) {
	            results[j] = Math.min(results[j], results[j+1]) + tmp.get(j);
	        }
	    }
	    return results[0];
	}
     
     public int maxProfit(int[] prices) {
         int ret = 0;
         for (int i = 1 ; i < prices.length ; i++)
             ret += Math.max(prices[i] - prices[i-1] , 0);
         return ret;
     }
     
     public int maxProfit2(int[] prices) {
    	 if (prices == null || prices.length < 2) return 0;
         
    	 int buy1 = Integer.MAX_VALUE;
         int sell1 = 0;
         int buy2 = Integer.MAX_VALUE;
         int sell2 = 0;
         
         for (int p : prices) {
             buy1 = Math.min(buy1, p);
             sell1 = Math.max(sell1, p - buy1);
             buy2 = Math.min(buy2, p - sell1);
             sell2 = Math.max(sell2, p - buy2);
         }
         
         return sell2;    
     }
     
     /*int maxProfit(Vector<Integer> prices) {
         if (prices.empty()) return 0;
         int times = 2;
         vector<vector<Integer>> f(times + 1, vector<int>(prices.size(), 0));
         for(int k = 1; k <= times; k++) {
             int temp = f[k - 1][0] - prices[0];
             //f[k][0] = 0; 
             for(int i = 1; i < prices.size(); i++) {
                 f[k][i] = max(f[k][i - 1], prices[i] + temp);
                 temp = max(temp, f[k - 1][i] - prices[i]);
             }
         }
         return f[times][prices.size() - 1];
     }*/
     
     int max = Integer.MIN_VALUE;
     public int maxPathSum(TreeNode root) {
        maxPathSumDown(root);
        return max;
     }
     
     public int maxPathSumDown(TreeNode root){
          if (root == null) return 0;
         int left = Math.max(0, maxPathSumDown(root.left));
         int right = Math.max(0, maxPathSumDown(root.right));
         
         max = Math.max(max, left + right + root.val);
         return  Math.max(left, right) + root.val;
     }
     
     public boolean isPalindrome(String s) {
         if(s == null || s.length() == 0) return true;
         int l = 0; 
         int r = s.length() - 1;
         while (l < r) {
             char cl = s.charAt(l);
             if (!Character.isDigit(cl) && !Character.isAlphabetic(cl)) {
                 l++;
                 continue;
             }
             
             char cr = s.charAt(r);
             if (!Character.isDigit(cr) && !Character.isAlphabetic(cr)) {
                 r--;
                 continue;
             }
             
             if (Character.toLowerCase(cl) != Character.toLowerCase(cr)) return false;
             else {
                 l++;
                 r--;
             }
         }
         
         return true;
     }
     public int singleNumber(int[] nums) {
         int x2 = 0;
         int x1 = 0;
         int mask = 0;
         
         for (int i : nums) {
             x2 ^= (x1 & i);
             x1 ^= i;
             mask = ~(x2 & x1);
             x2 &= mask;
             x1 &= mask;
         }
         
         return x1;
     }
    
     class RandomListNode {
    	     int label;
    	     RandomListNode next, random;
    	      RandomListNode(int x) { this.label = x; }
    	  };
     public RandomListNode copyRandomList_recursive(RandomListNode head) {
         if (head == null) return null;
         HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
         return copyRandomListNode_recursive(map, head);
     }
     
     RandomListNode copyRandomListNode_recursive(HashMap<RandomListNode, RandomListNode> map, RandomListNode node) {
         if (node == null) return null;
         
         if (map.containsKey(node)) return map.get(node);
         RandomListNode newNode = new RandomListNode(node.label);
         map.put(node, newNode);
         newNode.next = copyRandomListNode_recursive(map, node.next);
         newNode.random = copyRandomListNode_recursive(map, node.random);
         
         return newNode;
     }
     
     public RandomListNode copyRandomList(RandomListNode head) {
         if (head == null) return null;
         HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
         
         RandomListNode node = head;
         while (node != null) {
             RandomListNode clone = getCopy(map, node);
             clone.next = getCopy(map, node.next);
             clone.random = getCopy(map, node.random);
             node = node.next;
         }
         
         return getCopy(map, head);
     }
     
     RandomListNode getCopy(HashMap<RandomListNode, RandomListNode> map, RandomListNode node) {
         if (node == null) return null;
         
         if (map.containsKey(node)) return map.get(node);
         
         RandomListNode clone = new RandomListNode(node.label);
         map.put(node, clone);
         return clone;
     }
     
     public boolean wordBreak1(String s, Set<String> wordDict) {
         int len = s.length();
         if (wordDict.contains(s)) return true;
        
         for (int i = 1; i < len; i++) {
             String l = s.substring(0, i);
             if (wordDict.contains(l)) {
                 if (!wordDict.contains(1)) wordDict.add(l);
                 String r = s.substring(i);
                 if (wordBreak(r, wordDict)) {
                     if (!wordDict.contains(r)) wordDict.add(r);
                     return true;
                 }
             }
         }
         return false;
     }
     
     public boolean wordBreak(String s, Set<String> wordDict) {
         int len = s.length();
         boolean[] dp = new boolean[len+1];
         dp[0] = true;
         for (int i = 1; i <= len; i++)
             for (int j = 0; j < i; j++) 
                 if (dp[j] && wordDict.contains(s.substring(j, i))) dp[i] = true;
            
         return  dp[len];    
     }
     
     public List<String> wordBreak2(String s, Set<String> wordDict){
    	 String word = "";
    	 List<String> list = new ArrayList<String>();
    	 doWordBreak(s, wordDict, list, word);
    	 return list;
     }
     
     void doWordBreak(String s, Set<String> wordDict, List<String> res, String word) {
    	 if (wordDict.contains(s)) {
    		 StringBuffer sb = new StringBuffer(word);
    		 if (word.length() > 0) sb.append( " ");
    		 sb.append(s);
    		 res.add(sb.toString());
    	 }
    	 for (int i = 1; i < s.length(); i++) {
    		 String l = s.substring(0, i);
    		 if (wordDict.contains(l)){
    			 StringBuffer sb = new StringBuffer();
    			 if (word.length() > 0)
    				 sb.append(word).append(" ");
    			 sb.append(l);
    			 doWordBreak(s.substring(i), wordDict, res, sb.toString());   				 
    		 }
    	 }
     }
     
     public List<String> wordBreak3(String s, Set<String> wordDict) {
         LinkedList<String>[] dp = (LinkedList<String>[]) new LinkedList<?>[s.length()+1];
         for(int i = s.length()-1; i >= 0; i--){
             if(wordDict.contains(s.substring(i,s.length()))) break;
             if(i == 0) return new LinkedList<String>();
         }
         
         for(int i = 0; i < dp.length; i++) dp[i] = new LinkedList<String>();
        
         for(int i = 1; i <= s.length(); i++) {
             for(int j = 0; j < i; j++) {
                 if((j == 0 || dp[j].size() > 0) && wordDict.contains(s.substring(j,i))) {
                     if(j == 0) dp[i].add(s.substring(j,i));
                     else {
                         for(String c : dp[j]) {
                             dp[i].add(c + " " + s.substring(j,i));
                         }
                     }
                 } 
             }
         }
         return dp[s.length()];
     }
     
     public List<String> wordBreak_good(String s, Set<String> dict) {
         Map<Integer, List<String>> validMap = new HashMap<Integer, List<String>>();

         // initialize the valid values
         List<String> l = new ArrayList<String>();
         l.add("");
         validMap.put(s.length(), l);

         // generate solutions from the end
         for(int i = s.length() - 1; i >= 0; i--) {
             List<String> values = new ArrayList<String>();
             for(int j = i + 1; j <= s.length(); j++) {
                 if (dict.contains(s.substring(i, j))) {
                     for(String word : validMap.get(j)) {
                         values.add(s.substring(i, j) + (word.isEmpty() ? "" : " ") + word);
                     }
                 }
             }
             validMap.put(i, values);
         }
         return validMap.get(0);
     }
     
     private final Map<String, List<String>> cache = new HashMap<>();

     private boolean containsSuffix(Set<String> dict, String str) {
         for (int i = 0; i < str.length(); i++) {
             if (dict.contains(str.substring(i))) return true;
         }
         return false;
     }

     public List<String> wordBreak_pass(String s, Set<String> dict) {
         if (cache.containsKey(s)) return cache.get(s);
         List<String> result = new LinkedList<>();
         if (dict.contains(s)) result.add(s);
         for (int i = 1; i < s.length(); i++) {
             String left = s.substring(0,i), right = s.substring(i);
             if (dict.contains(left) && containsSuffix(dict, right)) {
                 for (String ss : wordBreak_pass(right, dict)) {
                     result.add(left + " " + ss);
                 }
             }
         }
         cache.put(s, result);
         return result;
     }
     
     public boolean hasCycle(ListNode head) {
    	    if(head==null||head.next==null) return false;
    	    ListNode oneStep = head.next;
    	    ListNode twoStep = head.next.next;
    	    
    	    while(oneStep!=twoStep){
    	        if(oneStep==null||twoStep==null) return false;
    	        if(twoStep.next==null) return false;
    	        else{
    	            oneStep = oneStep.next;
    	            twoStep = twoStep.next.next;
    	        }
    	    }
    	    return true;
    	}
     
     public ListNode detectCycle(ListNode head) {
         ListNode slow = head;
         ListNode fast = head;
         
         while(fast != null && fast.next != null){
             slow = slow.next;
             fast = fast.next.next;
             
             if (slow == fast) {
                 fast = head;
                 while (slow != fast) {
                     slow = slow.next;
                     fast = fast.next;
                 }
                 return slow;
             }
         }
         
         return null;
     }
     
     public void reorderList(ListNode head) {
         if (head == null || head.next == null || head.next.next == null) return;
         
         ListNode slow = head;
         ListNode fast = head;
         
         ListNode prev = null;
         while (fast != null && fast.next != null) {
        	 prev = slow;
             slow = slow.next;
             fast = fast.next.next;
         }
         
         if (fast != null) {
             ListNode next = slow.next;
             slow.next = null;
             slow = next;
         } else if (prev != null )prev.next = null;
         
         ListNode s = null;
        
         while (slow != null) {
             ListNode next = slow.next;
             slow.next = s;
             s = slow;
             slow = next;            
         }
         
         ListNode f = head;
         while (s != null) {
        	 ListNode fnext = f.next;
             f.next = s;
             ListNode snext = s.next;
             s.next = fnext;
             f = fnext;
             s = snext;
         }
        
     }
     
     public ListNode insertionSortList(ListNode head) {
    	 if (head == null || head.next == null) return head;
    	 
    	 ListNode dummy = new ListNode(Integer.MIN_VALUE);
    	 dummy.next = head;
    	 ListNode prev = head; 
    	 ListNode cur = head.next;
    	 
    	 while (cur != null) {
    		 if (cur.val >= prev.val) {
    			 prev = cur;
    			 cur = cur.next;
    		 } else {
    			 ListNode next = cur.next;
    			 ListNode p = dummy; //insertion search previous node;
    			 ListNode c = dummy.next; //insertion search current node;
    			 while (c.val < cur.val) {
    				 p = p.next;
    				 c = c.next;
    			 }
    			 //cur need to be inserted between p & c.
    			 p.next = cur;
    			 cur.next = c;
    			 cur = next; //move current to the next.
    			 prev.next = cur; //Don't move prev node, but link the next one to current node.
    		 }
    	 }
    	 
    	 return dummy.next;
     }
     
     public ListNode sortList(ListNode head) {
         if (head == null || head.next == null)
             return head;
         ListNode f = head.next.next;
         ListNode p = head;
         while (f != null && f.next != null) {
             p = p.next;
             f =  f.next.next;
         }
         ListNode h2 = sortList(p.next);
         p.next = null;
         return merge(sortList(head), h2);
     }
     public ListNode merge(ListNode h1, ListNode h2) {
         ListNode hn = new ListNode(Integer.MIN_VALUE);
         ListNode c = hn;
         while (h1 != null && h2 != null) {
             if (h1.val < h2.val) {
                 c.next = h1;
                 h1 = h1.next;
             }
             else {
                 c.next = h2;
                 h2 = h2.next;
             }
             c = c.next;
         }
         if (h1 != null)
             c.next = h1;
         if (h2 != null)
             c.next = h2;
         return hn.next;
     }
     
     class Point {
    	      int x;
    	      int y;
    	      Point() { x = 0; y = 0; }
    	      Point(int a, int b) { x = a; y = b; }
    	 }
     
     public int maxPoints(Point[] points){
    	  if(points == null || points.length == 0){
    	      return 0;
    	  }
    	  
    	  if(points.length <= 2){
    	      return points.length;
    	  }
    	  
    	  int ret = 0;
    	  
    	  int n = points.length;
    	  int count = 0; 
    	  int duplicates = 0;
    	  
    	  for(int i = 0; i < n; i++){
    	      Point p = points[i];
    	      count = 0;
    	      duplicates = 0;
    	      
    	      for(int j = i + 1; j < n; j++){
    	          Point q = points[j];
    	          if(q.x == p.x && q.y == p.y){
    	              duplicates++;
    	              ret = Math.max(ret, duplicates + 1);
    	              continue;
    	          }
    	          
    	          //count point q
    	          count = 1;
    	          
    	          for(int k = j + 1; k < n; k++){
    	              Point r = points[k];
    	              count += isCoLinear(p, q, r)? 1: 0;
    	          }
    	          
    	          //count point p
    	          ret = Math.max(ret, count + duplicates + 1);
    	      }
    	      
    	  }
    	  
    	  return ret;
    	}

    	 private boolean isCoLinear(Point p, Point q, Point r){
    	  int val = (q.y - p.y) *(r.x - q.x) - (r.y - q.y)*(q.x - p.x);
    	  return val == 0;
    	}
    	 
    	 public int evalRPN(String[] tokens) {
    	        if (tokens == null || tokens.length < 1) return 0;
    	        
    	        Stack<Integer> stack = new Stack<Integer>();
    	        for (String token : tokens) {
    	            if (token.equals("+")) stack.push(stack.pop() + stack.pop());
    	            else if (token.equals("-")) stack.push(-(stack.pop() - stack.pop()));
    	            else if (token.equals("*")) stack.push(stack.pop() * stack.pop()); 
    	            else if (token.equals("/")) {
    	                int d = stack.pop();
    	                stack.push(stack.pop() / d);
    	            }
    	            else {
    	                stack.push(Integer.valueOf(token));
    	            }
    	        }
    	        
    	        return stack.pop();
    	    }
    	 
    	 public String reverseWords(String s) {
    	        if (s == null) return s;
    	        char[] a = s.toCharArray();
    	        int l = 0; 
    	        int r = a.length - 1;
    	        reverseWords(a, l, r);
    	        for (int i = 0; i < r; i++) {
    	        	char c = a[i];
    	            if (c == ' ') {
    	                reverseWords(a, l, i - 1);
    	                l = i + 1;
    	            }
    	        }
    	        
    	        if (l < r) reverseWords(a, l, r);
    	        
    	        
    	        return cleanSpaces(a, a.length);
    	    }
    	    
    	    void reverseWords(char[] a, int l, int r) {
    	        while (l < r) {
    	            char c = a[l];
    	            a[l] = a[r];
    	            a[r] = c;
    	            l++;
    	            r--;
    	        }
    	    }
    	    
    	    // trim leading, trailing and multiple spaces
    	  String cleanSpaces(char[] a, int n) {
    	    int i = 0, j = 0;
    	      
    	    while (j < n) {
    	      while (j < n && a[j] == ' ') j++;             // skip spaces
    	      while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
    	      while (j < n && a[j] == ' ') j++;             // skip spaces
    	      if (j < n) a[i++] = ' ';                      // keep only one space
    	    }
    	  
    	    return new String(a).substring(0, i);
    	  }
    	  public int maxProduct(int[] nums) {
    	        int lmin=nums[0];
    	        int lmax=lmin;
    	        int gmax=lmax;
    	        
    	        for( int i=1;i<nums.length;i++)
    	        {
    	            int t1 = nums[i]*lmax;
    	            int t2 = nums[i]*lmin;
    	            
    	            lmax = Math.max( Math.max(t1,t2), nums[i] );
    	            lmin = Math.min( Math.min(t1,t2), nums[i]);
    	            gmax = Math.max(gmax, lmax);
    	        }
    	        return gmax; 
    	    }	
    	  
    	  public class MinStack {

    		    Stack<Integer> minStack = new Stack<Integer>();
    		    int minValue;
    		    /** initialize your data structure here. */
    		    public MinStack() {
    		        
    		    }
    		    
    		    public void push(int x) {
    		        if (minStack.isEmpty()) 
    		            minValue = x;
    		        else if (x <= minValue) {
    		            minStack.push(minValue);
    		            minValue = x;
    		        } 
    		        minStack.push(x);
    		    }
    		    
    		    public void pop() {
    		        if (!minStack.isEmpty()) {
    		            int v = minStack.pop();
    		            if (v == minValue) {
    		                if (!minStack.isEmpty())
    		                    minValue = minStack.pop();
    		            }
    		        }
    		    }
    		    
    		    public int top() {
    		        return minStack.peek();
    		    }
    		    
    		    public int getMin() {
    		        return minValue;
    		    }
    		}
    	  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	        if (headA == null || headB == null) return null;
    	        
    	        ListNode a = headA;
    	        ListNode b = headB;
    	        
    	        while (a != null && b != null) {
    	            a = a.next;
    	            b = b.next;
    	        }
    	        
    	        ListNode aa = b == null ? headA : headB;  //aa is the longer one.
    	        ListNode bb = b == null ? headB : headA;
    	        
    	        if (a != null || b != null) {
    	           ListNode left = a != null ? a : b;
    	           while (left != null){
    	               left = left.next;
    	               aa = aa.next;
    	           }
    	        }
    	        
    	        while (aa != null && bb != null){
    	            if (aa == bb) return aa;
    	            aa = aa.next; 
    	            bb = bb.next;
    	        }
    	        
    	        return null;
    	    }
    	  
    	  public ListNode getIntersectionNode_best(ListNode headA, ListNode headB) {
    		    //boundary check
    		    if(headA == null || headB == null) return null;
    		    
    		    ListNode a = headA;
    		    ListNode b = headB;
    		    
    		    //if a & b have different len, then we will stop the loop after second iteration
    		    while( a != b){
    		    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
    		        a = a == null? headB : a.next;
    		        b = b == null? headA : b.next;    
    		    }
    		    
    		    return a;
    		}
    	  
    	  public int findPeakElement(int[] nums) {
    	        if (nums == null || nums.length == 0) return -1;
    	        if (nums.length == 1) return 0;
    	        
    	        if (nums[0] > nums[1]) return 0;
    	        if (nums[nums.length -1] > nums[nums.length -2]) return nums.length -1;
    	        for (int i = 1; i < nums.length - 1; i++) {
    	            if (nums[i] > nums[i -1] && nums[i] > nums[i+1]) return i;
    	        }
    	        
    	        return -1;
    	    }
    	  
    	  public int findPeakElement_excellent(int[] a) {
    	        int low = 0, mid = 0, high = a.length - 1;
    	        while(low < high) {
    	            mid = low + (high-low)/2;
    	            if(a[mid] < a[mid+1]) low = mid+1;
    	            else high = mid;
    	        }
    	        return low;
    	    }
    	  public int compareVersion(String version1, String version2) {
    	        String[] a1 = version1.split("\\.");
    	        String[] a2 = version2.split("\\.");
    	        int i = 0; 
    	        while (i < a1.length && i < a2.length) {
    	            int v1 = Integer.valueOf(a1[i]);
    	            int v2 = Integer.valueOf(a2[i]);
    	            if (v1 > v2) return 1;
    	            if (v1 < v2) return -1;
    	            
    	            i++;
    	        }
    	        
    	        if (a1.length == a2.length) return 0;
    	        else if (a1.length > a2.length){
    	            for (i = a2.length; i < a1.length; i++) {
    	                if (Integer.valueOf(a1[i])!= 0) return 1;
    	            }
    	        }
    	        else {
    	            for (i = a1.length; i < a2.length; i++) {
    	                if (Integer.valueOf(a2[i]) != 0) return -1;
    	            }
    	        }
    	        
    	        return 0;
    	    }
    	  
      //Radix sort.
    	  public int maximumGap(int[] nums) {
    		    if (nums == null || nums.length < 2) {
    		        return 0;
    		    }
    		    
    		    int len = nums.length;
    		    int w = 8, r = 4;
    		    int m = 16;
    		    int[] aux = new int[len];
    		    int e = 1;
    		    for (int  i = 0; i < w; i++) {
    		    	
    		    	int[] counts = new int[1 << r];
    		    	for (int j = 0; j < len; j++)
    		    		counts[nums[j] / e % m]++;
    		    	for (int j = 1; j < 16; j++)
    		    		counts[j] += counts[j-1];
    		    	for (int j = len - 1; j >=0; j--)
    		    	{
    		    		int v = nums[j] / e % m;
    		    		System.out.println(v);
    		    		aux[--counts[nums[j] / e % m]] = nums[j];
    		    	}
    		    	
    		    	nums = aux;
    		    	e = (int) Math.pow(2, (i+1) * 4) - 1;
    		    }
    		    int max = 0;
    		    for (int i = 1; i < len; i++)
    		    	max = Math.max(max, nums[i] - nums[i-1]);
    		    
    		    return max;
    		}
    	  
    	  public int calculateMinimumHP(int[][] dungeon) {
    	        int[][] exp = dungeon.clone();
    	        int m = exp.length;
    	        int n = exp[0].length;
    	        
    	        for (int i = m -1; i >= 0; i--){
    	        	for (int j = n -1 ; j >=0; j--){
    	        		if (i == m -1 && j == n-1)
    	        			exp[i][j] = dungeon[m-1][n-1] <=0 ? -dungeon[m-1][n-1] + 1 : 1;
    	        		else {
    	        			int v = Integer.MAX_VALUE;
    	        			if (i < m - 1) {
    	        				v = Math.max(1, exp[i+1][j] - dungeon[i][j]);
    	        			}
    	        			
    	        			if (j < n -1) {
    	        				int v2 = Math.max(1,  exp[i][j+1] -dungeon[i][j]);
    	        				v = Math.min(v, v2);
    	        			}
    	        			exp[i][j] = v;
    	        		}
    	        	}
    	        }
    	        
    	        return exp[0][0];
    	  }
	  
    	  class StringComparator implements Comparator<String> {
    	        public int compare(String a, String b) {
    	            if (a.length() == b.length()) {
    	                return b.compareTo(a);
    	            } else {
    	                String ab = a + b;
    	                String ba = b + a;
    	                return ba.compareTo(ab);
    	            }
    	        }
    	    }
    	    
    	    public String largestNumber(int[] num) {
    	        StringBuffer sbuf = new StringBuffer();
    	        ArrayList<String> numstrings = new ArrayList<String>(num.length);
    	        
    	        for (int i : num) numstrings.add(String.valueOf(i));
    	        Collections.sort(numstrings,  new StringComparator());
    	        
    	        for (String s : numstrings) sbuf.append(s);
    	        
    	        String res = sbuf.toString();
    	        if (res.length() > 0 && res.charAt(0) == '0') return "0";
    	        
    	        return res;
    	    }
    	    public List<String> findRepeatedDnaSequences(String s) {
    	        Set seen = new HashSet(), repeated = new HashSet();
    	        for (int i = 0; i + 9 < s.length(); i++) {
    	            String ten = s.substring(i, i + 10);
    	            if (!seen.add(ten))
    	                repeated.add(ten);
    	        }
    	        return new ArrayList(repeated);
    	    }
    	    
    	    public int maxProfit(int k, int[] prices) {
    	        int len = prices.length;
    	        if (k >= len / 2) return quickSolve(prices);
    	        
    	        int[][] t = new int[k + 1][len];
    	        for (int i = 1; i <= k; i++) {
    	            int tmpMax =  -prices[0];
    	            for (int j = 1; j < len; j++) {
    	                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
    	                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
    	            }
    	        }
    	        return t[k][len - 1];
    	    }
    	    

    	    private int quickSolve(int[] prices) {
    	        int len = prices.length, profit = 0;
    	        for (int i = 1; i < len; i++)
    	            // as long as there is a price gap, we gain a profit.
    	            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
    	        return profit;
    	    }
    	    
    	    public void rotate(int[] nums, int k) {
    	        if (k <= 0) return;
    	        int n = nums.length;
    	        k = k % n;
    	        
    	        rotate(nums, 0, n - 1);
    	        rotate(nums, 0, k - 1);
    	        rotate(nums, k, n - 1);
    	        
    	    }
    	    
    	    void rotate(int[] nums, int l, int r) {
    	        while (l < r) {
    	            int temp = nums[l];
    	            nums[l] = nums[r];
    	            nums[r] = temp;
    	            l++;
    	            r--;
    	        }
    	    }
    	    
    	    public int reverseBits(int n) {
    	        int result = 0;
    	        for(int i=0; i<32; i++){
    	            result <<= 1;
    	            result += n&1;
    	            n >>= 1;
    	        }
    	        return result;
    	    }
    	    
    	    public int hammingWeight(int n) {
    	        int count = 0;
    	        for (int i = 0; i < 32; i++) {
    	            count += n & 1;
    	            n >>= 1;
    	        }
    	        
    	        return count;
    	    }
    	    
    	    public int rob(int[] nums) {
    	        if (nums == null || nums.length ==0) return 0;
    	        
    	        int len = nums.length;
    	        if (len == 1) return nums[0];
    	        
    	        int[] dp = new int[len];
    	        for (int i = 0; i < len; i++) {
    	            dp[i] = nums[i];
    	            int max = 0;
    	            if (i >= 2) max = dp[i-2];
    	            if (i >= 3) max = Math.max(max, dp[i-3]);
    	            dp[i] += max;
    	        }
    	        
    	        return Math.max(dp[len-1], dp[len -2]);
    	    }
    	    
    	    class Solution1 {
    	        int currrentLevel = 1;
    	        Stack<TreeNode> stack = new Stack<TreeNode>();
    	        List<Integer> res = new ArrayList<Integer>();
    	        
    	        public List<Integer> rightSideView(TreeNode root) {
    	            getRightSideView(root);
    	            return res;
    	        }
    	        
    	        void getRightSideView(TreeNode node) {
    	            if (node == null) return;
    	            stack.push(node);
    	            if (currrentLevel == stack.size()){
    	                res.add(node.val);
    	                currrentLevel++;
    	            }
    	            
    	            getRightSideView(node.right);
    	            getRightSideView(node.left);
    	            
    	            stack.pop();
    	        }
    	    }
    	    public int numIslands(char[][] grid) {
    	        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    	        
    	        int count = 0;
    	        int m = grid.length;
    	        int n = grid[0].length;
    	        for (int i = 0; i < m; i++)
    	            for (int j = 0; j < n; j++) {
    	                if (resetOne(grid, m, n, i, j))
    	                    count++;
    	            }
    	            
    	        return count;
    	    }
    	    
    	    boolean resetOne(char[][] grid, int m, int n, int r, int c) {
    	        if (r < 0 || r >= m || c < 0 || c >= n) return false;
    	        if (grid[r][c] == '1') {
    	            grid[r][c] = 'x';
    	            resetOne(grid, m, n, r-1, c);
    	            resetOne(grid, m, n, r+1, c);
    	            resetOne(grid, m, n, r, c-1);
    	            resetOne(grid, m, n, r, c+1);
    	            
    	            return true;
    	        }
    	        return false;
    	    }
    	    
    	    public int rangeBitwiseAnd(int m, int n) {
    	        if (m== 0 || n== 0) return 0;
    	        int bit = 1;
    	        while (m != n) {
    	            m >>= 1;
    	            n >>= 1;
    	            bit <<= 1;
    	        }
    	        
    	        return m * bit;
    	    }
    	    
    	    public boolean isHappy(int n) {
    	        if (n <= 0) return false;
    	        HashSet<Integer> set = new HashSet<Integer>();
    	        while (n != 1) {
    	            if (set.contains(n)) return false;
    	            set.add(n);
    	            int v = 0;
    	            while (n > 0){
    	               int e = n % 10;    	                
    	                v += e * e;
    	                n /=10;
    	            }
    	            n = v;
    	        }
    	        
    	        return true;
    	    }
    	    
    	    public ListNode removeElements(ListNode head, int val) {
    	        ListNode dummy = new ListNode(0);
    	        dummy.next = head;
    	        ListNode node = dummy;
    	        while (node.next != null){
    	            if (node.next.val == val) node.next = node.next.next;
    	            else node = node.next;
    	        }
    	        
    	        return dummy.next;
    	    }
    	    public int countPrimes(int n) {
    	        if (n < 2) return 0;
    	        
    	        boolean[] notPrime = new boolean[n+1];
    	        int count = 0;
    	        for (int i = 2; i < n; i++)
    	             if (!notPrime[i]) {
    	                 count++;
    	                 for (int j = 2; j*i <= n; j++)
    	                     notPrime[j*i] = true;
    	             }
    	             
    	        return count;
    	        
    	     }   
    	    
    	    public boolean isIsomorphic(String s, String t) {
    	        if (s == null && t == null) return true;
    	        if (s == null) return t.length()==0;
    	        if (t == null) return s.length() == 0;
    	        
    	        int len = s.length();
    	        if (len != t.length()) return false;
    	        
    	        HashMap<Character, Character>  map = new HashMap<Character, Character>();
    	        for (int i = 0; i < len; i++) {
    	            char sc = s.charAt(i);
    	            char tc = t.charAt(i);
    	            
    	            if (map.containsKey(sc)) {
    	                if (tc != map.get(sc)) return false;
    	            } else {
    	                if (map.containsValue(tc)) return false;
    	                map.put(sc, tc);
    	            }
    	        }
    	        
    	        return true;
    	    }
    	    
    	    public boolean isIsomorphic_better(String s1, String s2) {
    	        int[] m = new int[512];
    	        for (int i = 0; i < s1.length(); i++) {
    	            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
    	            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
    	        }
    	        return true;
    	    }
    	    
    	    public ListNode reverseList__(ListNode head) {
    	        if (head == null || head.next == null) return head;
    	        
    	        ListNode pre = null;
    	        ListNode cur = head;
    	        
    	        while (cur != null){
    	            ListNode next = cur.next;
    	            cur.next = pre;
    	            pre = cur;
    	            cur = next;
    	        }
    	        
    	        return pre;
    	    }
    	    public static boolean canFinish(int numCourses, int[][] prerequisites) {
    	    	if (numCourses <= 0)
    	    		return false;
    	    	Queue<Integer> queue = new LinkedList<>();
    	    	int[] inDegree = new int[numCourses];
    	    	for (int i = 0; i < prerequisites.length; i++) {
    	    		inDegree[prerequisites[i][1]]++;
    	    	}
    	    	for (int i = 0; i < inDegree.length; i++) {
    	    		if (inDegree[i] == 0)
    	    			queue.offer(i);
    	    	}
    	    	while (!queue.isEmpty()) {
    	    		int x = queue.poll();
    	    		for (int i = 0; i < prerequisites.length; i++) {
    	    			if (x == prerequisites[i][0]) {
    	    				inDegree[prerequisites[i][1]]--;
    	    				if (inDegree[prerequisites[i][1]] == 0)
    	    					queue.offer(prerequisites[i][1]);
    	    			}
    	    		}
    	    	}
    	    	for (int i = 0; i < inDegree.length; i++) {
    	    		if (inDegree[i] != 0)
    	    			return false;
    	    	}
    	    	return true;
    	    }
    	    
    	    class TrieNode {
    	        // Initialize your data structure here.
    	    	TrieNode[] children;
    	    	String item;
    	    	
    	        public TrieNode() {
    	            children = new TrieNode[26];
    	            item = "";
    	        }
    	    }

    	    public class Trie {
    	        private TrieNode root;

    	        public Trie() {
    	            root = new TrieNode();
    	        }

    	        // Inserts a word into the trie.
    	        public void insert(String word) {
    	            TrieNode node = root;
    	            
    	            for(char ch: word.toCharArray()){
    	                int index = ch - 'a';
    	                if(node.children[index] == null) node.children[index] = new TrieNode();
    	                node = node.children[index];
    	            }
    	            
    	            node.item = word;
    	        }

    	        // Returns if the word is in the trie.
    	        public boolean search(String word) {
    	            return match(root, word, 0, false);
    	        }
    	        
    	        // Returns if there is any word in the trie
    	        // that starts with the given prefix.
    	        public boolean startsWith(String prefix) {
    	            return match(root, prefix, 0, true);
    	        }
    	        
    	        private boolean match(TrieNode node, String word, int i, boolean searchPrefix){
    	            if(node==null) return false;
    	            if(i==word.length()){
    	                if(searchPrefix) return true;
    	                return node.item.equals(word);
    	            }
    	            
    	            int index = word.charAt(i)-'a';
    	            return match(node.children[index], word, i+1, searchPrefix);
    	        }
    	    }
    	    class su2{
	    	    class TrieNode {
	    	        public boolean isWord; 
	    	        public TrieNode[] children = new TrieNode[26];
	    	        public TrieNode() {}
	    	    }
	
	    	    public class Trie {
	    	        private TrieNode root;
	    	        public Trie() {
	    	            root = new TrieNode();
	    	        }
	
	    	        public void insert(String word) {
	    	            TrieNode ws = root;
	    	            for(int i = 0; i < word.length(); i++){
	    	                char c = word.charAt(i);
	    	                if(ws.children[c - 'a'] == null){
	    	                    ws.children[c - 'a'] = new TrieNode();
	    	                }
	    	                ws = ws.children[c - 'a'];
	    	            }
	    	            ws.isWord = true;
	    	        }
	
	    	        public boolean search(String word) {
	    	            TrieNode ws = root; 
	    	            for(int i = 0; i < word.length(); i++){
	    	                char c = word.charAt(i);
	    	                if(ws.children[c - 'a'] == null) return false;
	    	                ws = ws.children[c - 'a'];
	    	            }
	    	            return ws.isWord;
	    	        }
	
	    	        public boolean startsWith(String prefix) {
	    	            TrieNode ws = root; 
	    	            for(int i = 0; i < prefix.length(); i++){
	    	                char c = prefix.charAt(i);
	    	                if(ws.children[c - 'a'] == null) return false;
	    	                ws = ws.children[c - 'a'];
	    	            }
	    	            return true;
	    	        }
	    	    }
    	    }

    	    public class WordDictionary {
    	        class TrieNode {
    	            TrieNode[] child = new TrieNode[26];
    	            boolean isWord = false;
    	        }
    	        TrieNode root = new TrieNode();
    	        public void addWord(String word) {
    	            TrieNode p = root;
    	            for (char c : word.toCharArray()) {
    	                if (p.child[c - 'a'] == null) p.child[c - 'a'] = new TrieNode();
    	                p = p.child[c - 'a'];
    	            }
    	            p.isWord = true;
    	        }

    	        public boolean search(String word) {
    	            return helper(word, 0, root);
    	        }
    	        
    	        private boolean helper(String s, int index, TrieNode p) {
    	            if (index >= s.length()) return p.isWord;
    	            char c = s.charAt(index);
    	            if (c == '.') {
    	                for (int i = 0; i < p.child.length; i++)
    	                    if (p.child[i] != null && helper(s, index + 1, p.child[i]))
    	                        return true;
    	                return false;
    	            } else return (p.child[c - 'a'] != null && helper(s, index + 1, p.child[c - 'a']));
    	        }
    	    }
    	    
    	    
    	    public class Solution11 {
    	    	public List<String> findWords(char[][] board, String[] words) {
    	            List<String> res = new ArrayList<String>();
    	            TrieNode root = new TrieNode();
    	            root.add(words);
    	            for (int i = 0; i < board.length; i++) 
    	                for (int j = 0; j < board[0].length; j++)
    	                    findWord(board, i, j, root, res);
    	            return res;
    	        }
    	        
    	        void findWord(char[][] board, int i, int j, TrieNode n, List<String> res) {
    	            char c = board[i][j];
    	            if (c== '#' || n.all[c-'a'] == null) return;
    	            if (n.all[c-'a'].word != null) {
    	                res.add(n.all[c-'a'].word);
    	                n.all[c-'a'].word = null;
    	            } 
    	            
    	            board[i][j] = '#';
    	            if (i >0) findWord(board, i-1, j, n.all[c-'a'], res);
    	            if (j >0) findWord(board, i, j-1, n.all[c-'a'], res);
    	            if (i < board.length -1) findWord(board, i+1, j, n.all[c-'a'], res);
    	            if (j < board[0].length -1) findWord(board, i, j+1, n.all[c-'a'], res);
    	            
    	            board[i][j] = c;
    	            
    	        }
    	        
    	        class TrieNode {
    	            TrieNode[] all = new TrieNode[26];
    	            String word;
    	            
    	            void add(String[] words) {
    	                for (String word : words) {
    	                    char[] chars = word.toCharArray();
    	                    TrieNode node = this;
    	                    for (char c : chars) {
    	                        if (node.all[c-'a'] == null) node.all[c-'a'] = new TrieNode();
    	                        node = node.all[c-'a'];
    	                    }
    	                    node.word = word;
    	                }
    	            }
    	        }
    	    } 
    	    
    	    public class Solution213 {
    	    	public int rob(int[] nums) {
    	            if (nums == null || nums.length ==0) return 0;
    	            if (nums.length == 1) return nums[0];
    	            
    	            int v = nums[0];
    	            nums[0] = 0;
    	            int max = doRob(nums);
    	            nums[0] = v;
    	            v = nums[nums.length -1];
    	            nums[nums.length -1] = 0;
    	            max = Math.max(max, doRob(nums));
    	            nums[nums.length-1] = v;
    	            
    	            return max;
    	            
    	        }
    	        
    	        int doRob(int[] nums){
    	            int[] v = new int[nums.length];
    	            v[0] = nums[0]; 
    	            v[1] = Math.max(nums[1], v[0]);
    	            for (int i = 2; i < nums.length; i++)
    	                v[i] = Math.max(v[i-1], nums[i] + v[i-2]);
    	                
    	            return v[nums.length -1];
    	        }
    	    }
    	    
    	    public class Solution_214 {
    	    	
    	    		public String shortestPalindrome(String s) {
    	    	        if (s == null || s.length() < 2) return s;
    	    	        
    	    	        char[] a = s.toCharArray();
    	    	        int r;
    	    	        for (r = a.length -1; r >= 0; r--) {
    	    	            if (isPalindrome(a, 0, r)) break;
    	    	        }
    	    	       
    	    	        StringBuilder sb = new StringBuilder(s);
    	    	        for (int i = r + 1; i < a.length; i++) {
    	    	            sb.insert(0, s.charAt(i));
    	    	        }
    	    	        
    	    	        return sb.toString();
    	    	    }
    	    	    boolean isPalindrome(char[] str, int l, int r) {
    	    	    	while (l < r) {
    	    	    		if (str[l++] != str[r--]) return false;
    	    	    	}
    	    	    	
    	    	    	return true;
    	    	    }
    	    }
    	    
    public class Solution_214_2 {
        public String shortestPalindrome(String s) {
	        int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
	        while(i < j) {
	             if (chs[i] == chs[j]) {
	                 i++; j--;
	             } else { 
	                 i = 0; end--; j = end;
	             }
	        }
	        return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
	    }
    }
    
    public class Solution_215 {
        public int findKthLargest(int[] nums, int k) {
            if (k <= 0 || k > nums.length) return -1;
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
            for (int n : nums) {
            	queue.add(n);
            	if(queue.size() > k) queue.poll();
            }
            
            return queue.peek();
        }
    }
    
    public class Solution_215_2 {
        public int findKthLargest(int[] nums, int k) {
        	if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
            return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
        }    
        
        public int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
        	if (start > end) return Integer.MAX_VALUE;
        	
        	int pivot = nums[end];// Take A[end] as the pivot, 
        	int left = start;
        	for (int i = start; i < end; i++) {
        		if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
        			swap(nums, left++, i);			
        	}
        	swap(nums, left, end);// Finally, swap A[end] with A[left]
        	
        	if (left == k)// Found kth smallest number
        		return nums[left];
        	else if (left < k)// Check right part
        		return findKthLargest(nums, left + 1, end, k);
        	else // Check left part
        		return findKthLargest(nums, start, left - 1, k);
        } 
        
        void swap(int[] A, int i, int j) {
        	int tmp = A[i];
        	A[i] = A[j];
        	A[j] = tmp;				
        }
    }
    public class Solution_216 {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            getCombinationSum3(nums, 0, k, n,  res);
            
            return res;
        }
        
        void getCombinationSum3(int[] nums, int i, int count, int n, List<List<Integer>> res) {
            if (i >= 9 || count <= 0) return;
            int total = n - nums[i];
            
            if (count == 1) {
                if (total == 0) { //found it.
                    List<Integer> l = new ArrayList<Integer>();
                    for (int j = 0; j <= i; j++){
                        if (nums[j] != 0) l.add(nums[j]);
                    }
                    res.add(l);
                } else if (total < 0) return;
            }
            
            getCombinationSum3(nums, i + 1, count - 1, n - nums[i], res);
            
            nums[i] = 0;
            getCombinationSum3(nums, i + 1, count, n, res);
            nums[i] = i + 1;
        }
    }
    public class Solution_217 {
        public boolean containsDuplicate(int[] nums) {
            if (nums == null) return false;
            Set<Integer> set = new HashSet<Integer>();
            for (int v : nums) 
                if (!set.add(v)) return true;
           
            return false;
        }
    }
    
    public class Solution_228 {
    	public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<String>();
            if (nums == null || nums.length == 0) return res;
            
            int start = 0; 
            int prev = nums[0];
            int i;
            for (i = 1; i < nums.length; i++) {
                if (nums[i] == prev + 1){
                    prev = nums[i];
                } else {
                    if (start == i-1) {
                    	res.add(String.valueOf(nums[start]));
                    	start = i; 
                    	prev = nums[i];
                    }
                    else {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.valueOf(nums[start])).append("->").append(String.valueOf(nums[i-1]));
                        res.add(sb.toString());
                        start = i;
                        prev = nums[i];
                    }
                }
            }
            
            if (start == i-1) res.add(String.valueOf(nums[start]));
            else {
                StringBuffer sb = new StringBuffer();
                sb.append(String.valueOf(nums[start])).append("->").append(String.valueOf(nums[i-1]));
                res.add(sb.toString());
            }
        
            return res;
        }
    }
    
    public class Solution_229 {
    	public List<Integer> majorityElement(int[] nums) {
            List<Integer> res = new ArrayList<Integer>();
            if (nums == null || nums.length == 0) return res;
            
            int c1= 0, v1 = nums[0], c2 = 0, v2 = nums[0];
            for (int v : nums) {
                if (v == v1) c1++;
                else if (v == v2) c2++;
                else if(c1 == 0) {
                    c1++;
                    v1 = v;
                } else if (c2 == 0) {
                    c2++;
                    v2 = v;
                } else {
                    c1--;
                    c2--;
                }
            }
            
            c1 = 0; 
            c2 = 0;
            for (int v: nums){
                if (v == v1) c1++;
                if (v1 != v2 && v == v2) c2++;
            }
            
            if (c1 > nums.length/3) res.add(v1);
            if (v1 != v2 && c2 > nums.length/3) res.add(v2);
            
            return res;
        }
    }
    
    public class Solution_230 {
        int v = 0;
        int kth = 0;
        public int kthSmallest(TreeNode root, int k) {
           getKthSmallest(root, k);
            return v;
        }
        
        void getKthSmallest(TreeNode root, int k) {
             if (root == null) return;
            
            getKthSmallest(root.left, k);
            
            kth++;
            
            if (k == kth) {
                v = root.val;
                return;
            }
            
            getKthSmallest(root.right, k);
            
        }
    }
    
    public class Solution_231 {
    	public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            return (n & (n-1)) == 0;
        }
    }
    
    public class Solution_233 {
    	public int countDigitOne(int n) {
            if (n <= 0) return 0;
            
            long factor = 1;
            long low = 0;
            long high = 0;
            long curr = 0;
            
            int count = 0;
            
            while (n / factor > 0) {
                high = n / factor / 10;
                low = n % factor;
                curr = n / factor % 10;
                
                if (curr == 0) count += high * factor;
                else if (curr == 1) count += high * factor + low + 1;
                else count += (high +1) * factor;
                
                factor *= 10;
            }
            
            return count;
        }
    }
    
    public class Solution_234 {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            
            if (fast != null) slow = slow.next;
            
            ListNode prev = null;
            ListNode curr = slow;
            while(curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            
            while (prev != null && head != null){
                if (prev.val != head.val) return false;
                prev = prev.next;
                head = head.next;
            }
            
            return true;
        }
    }
    
    public class Solution_235 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (p.val > q.val) {
                TreeNode t = p;
                p = q;
                q = t;
            }
            
            if (p.val <= root.val && q.val >= root.val) {
                if (hasNode(root, p) && hasNode(root, q))
                    return root;
                else return null;
            }
            else if (p.val > root.val) 
                return lowestCommonAncestor(root.right, p, q);
            else if (q.val <root.val)
                return lowestCommonAncestor(root.left, p, q);
                
            return null;
        }
        
        boolean hasNode(TreeNode root, TreeNode n){
            if (root == null) return false;
            if (root == n) return true;
            
            if (root.val > n.val) return hasNode(root.left, n);
            else return hasNode(root.right, n);
        }
    }
    
    public class Solution_236 {
        
    	TreeNode ancestor = null;
        
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            TreeNode l = lowestCommonAncestor(root.left, p, q);
           
            TreeNode r = null;
            if (ancestor == null)
            	r = lowestCommonAncestor(root.right, p, q);
            
            if (ancestor != null) return ancestor;
            
            if (l != null && r != null) {
            	ancestor = root;
            	return ancestor;
            }
            
            if (l != null || r != null) {
            	if (root == p || root ==q) {
            		ancestor = root;
            		return ancestor;
            	} else return l!= null ? l : r;
            }
            
            if (root == p || root == q)
            	return root;
             return null;
        }
    }
    
    public class Solution_237 {
        public void deleteNode(ListNode node) {
           if (node.next != null) {
               node.val = node.next.val;
               node.next = node.next.next;
           }
        }
    }
    
    public class Solution_238 {
    	   public int[] productExceptSelf(int[] nums) {
    	        int n = nums.length;
    	        int[] res = new int[n];
    	        res[0] = 1;
    	        for (int i = 1; i < n; i++) {
    	            res[i] = res[i - 1] * nums[i - 1];
    	        }
    	        int right = 1;
    	        for (int i = n - 1; i >= 0; i--) {
    	            res[i] *= right;
    	            right *= nums[i];
    	        }
    	        return res;
    	    }
    	}
    
    public class Solution_239 {
    	public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length <= 0) return new int[]{};
            ArrayList<Integer> queue = new ArrayList<Integer>();
            int[] out = new int[nums.length - k + 1];
            for (int i = 0; i < k - 1; i++) updateQueue(nums, queue, k, i);
            
            for (int i = k - 1; i < nums.length; i++) {
            	updateQueue(nums, queue, k, i);
            	out[i - k + 1] = nums[queue.get(0)];
            }
            
            return out;
        }
        
        void updateQueue(int[]nums, ArrayList<Integer> queue, int k, int index) {
        	if (!queue.isEmpty()) {
        		int oldest = queue.get(0);
        		if (index - oldest > k -1) queue.remove(0);
        	}
        	
        	if (queue.isEmpty()) queue.add(index);
        	else {
        		while (!queue.isEmpty()) {
            		int last = queue.size() - 1;
            		int prev = queue.get(last);
            		if (nums[index] >= nums[prev]) {
            			queue.remove(last);
            		} else break;
        		}
        		queue.add(index);
        	}
        }
    }
    
    public class Solution_240 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
            
            int m = matrix.length;
            int n = matrix[0].length;
            int i = 0, j = n -1;
            
            while (i < m && j >= 0) {
                int v = matrix[i][j];
                if (v == target) return true;
                if (v > target) j--;
                else i++;
            }
            
            return false;
        }
    }
    
    public class Solution_241 {
        public List<Integer> diffWaysToCompute(String input) {
            List<Character> ops = new ArrayList<Character>();
            List<Integer> nums = new ArrayList<Integer>();
            int val = -1;
            for (int i = 0; i < input.length(); i++) {
            	char c = input.charAt(i);
            	if (c == '+' || c == '-' || c == '*') {
            		if (val >= 0) {
            			nums.add(val);
            			val = -1;
            		}
            		ops.add(c);
            	}
            	else {
            		if (val < 0) val = c - '0';
            		else val = val * 10 + c - '0';
            	}
            }
            if (val >= 0) nums.add(val);
            
            return calculate(ops, nums, 0, nums.size() - 1);
        }
        
        List<Integer> calculate(List<Character> ops, List<Integer> nums, int si, int ei){
        	List<Integer> res = new ArrayList<Integer> ();
        	if (si == ei) res.add(nums.get(si));
        	else if (ei - si == 1) res.add(getValue(nums.get(si), nums.get(ei), ops.get(si)));
        	else {
        		for (int i = si; i < ei; i++)
        		{
        			List<Integer> left = calculate(ops, nums, si, i);
        			List<Integer> right = calculate(ops, nums, i+1, ei);
        			for (int v1 : left)
        				for (int v2 : right) {
        					res.add(getValue(v1, v2, ops.get(i)));
        				}
        		}
        	}
        	return res;
        }
        
        int getValue(int a, int b, char op) {
        	if (op == '+') return a + b;
        	else if (op == '-') return a - b;
        	else return a * b;
        }
    }
    
    public class Solution_242 {
        public boolean isAnagram(String s, String t) {
            if (s == null) return t == null;
            int len = s.length();
            if (len != t.length()) return false;
            
            int[] res = new int[256];
            for (int i = 0; i < len; i++) {
                res[s.charAt(i)]++;
                res[t.charAt(i)]--;
            }
            
            for (int i = 0; i < 256; i++) {
                if (res[i] != 0) return false;
            }
            
            return true;
        }
    }
    
    public class Solution_260 {
        public int[] singleNumber(int[] nums) {
        	
            int xor = 0;
            for (int v : nums) xor ^= v;
            xor &= -xor;
            
            int[] r = new int[2];
            for (int v : nums) {
                if ((xor & v) > 0)
                    r[0] ^= v;
                else r[1] ^= v;
            }
            
            return r;
            
        }
    }
    
    public class Solution_263 {
        public boolean isUgly(int num) {
            if (num < 1) return false;
            if (num == 1) return true;
            
            while (num > 0) {
                if (num % 2 == 0) num /= 2;
                else if (num % 3 == 0) num /= 3;
                else if (num % 5 == 0) num /= 5;
                else break;
            }
            
            return num <= 1;
        }
    }
    
    public class Solution_264 {
    	int nthUglyNumber(int n) {
    	    Queue<Integer> q2 = new LinkedList<Integer>();
    	    Queue<Integer> q3 = new LinkedList<Integer>();
    	    Queue<Integer> q5 = new LinkedList<Integer>();
    	    q2.offer(1);
    	    q3.offer(1);
    	    q5.offer(1);
    	    
    	    int m = 0;
    	    for (int i = 0; i < n; ++i) {
    	        m = Math.min(q2.peek(), Math.min(q3.peek(), q5.peek()));
    	        if (m == q2.peek()) q2.poll();
    	        if (m == q3.peek()) q3.poll();
    	        if (m == q5.peek()) q5.poll();
    	       
    	        q2.offer(2*m);
    	        q3.offer(3*m);
    	        q5.offer(5*m);
    	    }
    	    
    	    return m;
    	}
    	
    	public int nthUglyNumber1(int n) {
            int[] ugly = new int[n];
            ugly[0] = 1;
            int index2 = 0, index3 = 0, index5 = 0;
            int factor2 = 2, factor3 = 3, factor5 = 5;
            for(int i=1;i<n;i++){
                int min = Math.min(Math.min(factor2,factor3),factor5);
                ugly[i] = min;
                if(factor2 == min)
                    factor2 = 2*ugly[++index2];
                if(factor3 == min)
                    factor3 = 3*ugly[++index3];
                if(factor5 == min)
                    factor5 = 5*ugly[++index5];
            }
            return ugly[n-1];
        }
    }
    
    public class Solution_268 {
        public int missingNumber(int[] nums) {
            int x = 0;
            for (int i = 0; i < nums.length; i++){
                x ^= nums[i];
                x ^= i;
            }
            
            x ^= nums.length;
            
            return x;
        }
    }
    
    public class Solution_282 {
    	public List<String> addOperators(String num, int target) {
    	    List<String> res = new LinkedList<>();
    	    char[] a = num.toCharArray();
    	    backtrack(a, res, "", 0, target, 0);
    	    return res;
    	}

    	private void backtrack(char[] nums, List<String> res, String str, int pos, long rem, long prevNum) {
    	    if(rem == prevNum && pos == nums.length) {
    	        res.add(str);
    	        return;
    	    }
    	    long val = 0;
    	    for(int i=pos; i<nums.length; i++) {
    	        val = val*10 + (nums[i]-'0');
    	        
    	        if(i>pos && nums[pos]=='0') break;
    	        if(pos==0) backtrack(nums, res, ""+val, i+1, rem, val);
    	        else {
    	            backtrack(nums, res, str+"+"+val, i+1, rem-prevNum, val);
    	            backtrack(nums, res, str+"-"+val, i+1, rem-prevNum, -val);
    	            backtrack(nums, res, str+"*"+val, i+1, rem, prevNum*val);
    	        }
    	    }
    	}
    }
    
    public class Solution_283 {
    	public void moveZeroes(int[] nums) {
            if (nums == null || nums.length < 2) return;
            int p0 = 0;
            int pn = 0;
            while (p0 < nums.length && pn < nums.length) {
                if (nums[p0] != 0) {
                    p0++;
                    pn = p0 + 1;
                }
                else if (nums[pn] == 0) pn++;
                else {
                    nums[p0++] = nums[pn];
                    nums[pn++] = 0;
                }
            }
    	}
    }
    
    /*class PeekingIterator implements Iterator<Integer> {
        Integer peekedValue = null;
        Iterator<Integer> iterator;
    	public PeekingIterator(Iterator<Integer> iterator) {
    	    // initialize any member here.
    	    this.iterator = iterator;
    	}

        // Returns the next element in the iteration without advancing the iterator.
    	public Integer peek() {
    	    if (peekedValue == null)
                peekedValue = next();
            return peekedValue;
    	}

    	// hasNext() and next() should behave the same as in the Iterator interface.
    	// Override them if needed.
    	@Override
    	public Integer next() {
    	    if (peekedValue != null) {
    	        Integer bak = peekedValue;
    	        peekedValue = null;
    	        return bak;
    	    } else return iterator.next();
    	}

    	@Override
    	public boolean hasNext() {
    	    return peekedValue != null || iterator.hasNext();
    	}
    }*/
    
    public class Solution_287 {
        public int findDuplicate(int[] nums) {
            int s = 0, f = 0;
            do {
                s = nums[s];
                f = nums[nums[f]];
            } while (s != f);
            
            s = 0; 
            while (s != f) {
                s = nums[s];
                f = nums[f];
            }
            
            return s;
        }
    }
    
    public class Solution_290 {
    	public boolean wordPattern(String pattern, String str) {
            if (pattern == null || str == null) return false;
           char[] p = pattern.toCharArray();
           String[] s = str.split(" ");
           
           int len = p.length;
           if (len == 0 || len != s.length) return false;
           
           HashMap<Character, String> map = new HashMap<Character, String>();
           for (int i = 0; i < len; i++){
               char c = p[i];
               if (!map.containsKey(c)) {
                    if (map.containsValue(s[i])) return false;
                    map.put(c, s[i]);
               }
               else{
                   if (!s[i].equals(map.get(c))) return false;
               }
           }
           
           return true;
            
        }
    }
    
    class Solution_301 {
    	    void DFS(String s, int curPos, int last, char[] pair)
    	    {
    	        for(int i = curPos, cnt=0; i < s.length(); i++)
    	        {
    	            if(s.charAt(i) == pair[0]) cnt++;
    	            else if(s.charAt(i) == pair[1]) cnt--;
    	            if(cnt >=0) continue;
    	            for(int j = last; j <= i; j++)
    	                if(s.charAt(j)== pair[1] && (j==last || s.charAt(j-1)!=pair[1]))
    	                    DFS(s.substring(0, j)+s.substring(j+1), i, j, pair);
    	            return;
    	        }
    	        s = new StringBuilder(s).reverse().toString();
    	        if(pair[0] == '(') DFS(s, 0, 0, new char[]{')','('});
    	        else result.add(s);
    	    }
    	    
    	    public List<String> removeInvalidParentheses(String s) {
    	        DFS(s, 0, 0, new char[]{'(',')'});
    	        return result;
    	    }
    	
    	    List<String> result = new ArrayList<String>();
    	};
    	
    	class Solution_301_2 {
    		public List<String> removeInvalidParentheses(String s) {
    		    List<String> ans = new ArrayList<>();
    		    remove(s, ans, 0, 0, new char[]{'(', ')'});
    		    return ans;
    		}

    		public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
    		    for (int stack = 0, i = last_i; i < s.length(); ++i) {
    		        if (s.charAt(i) == par[0]) stack++;
    		        if (s.charAt(i) == par[1]) stack--;
    		        if (stack >= 0) continue;
    		        for (int j = last_j; j <= i; ++j)
    		            if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
    		                remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
    		        return;
    		    }
    		    String reversed = new StringBuilder(s).reverse().toString();
    		    if (par[0] == '(') // finished left to right
    		        remove(reversed, ans, 0, 0, new char[]{')', '('});
    		    else // finished right to left
    		        ans.add(reversed);
    		}
    	};
    	
    	public class NumMatrix {

	   private int[][] dp;

	    public NumMatrix(int[][] matrix) {
	    if(   matrix           == null
	       || matrix.length    == 0
	       || matrix[0].length == 0   ){
	        return;   
	    }
	    
	    int m = matrix.length;
	    int n = matrix[0].length;
	    
	    dp = new int[m + 1][n + 1];
	    for(int i = 1; i <= m; i++){
	        for(int j = 1; j <= n; j++){
	            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] -dp[i - 1][j - 1] + matrix[i - 1][j - 1] ;
	        }
	    }
	    }
	    
	    public int sumRegion(int row1, int col1, int row2, int col2) {
	    int iMin = Math.min(row1, row2);
	    int iMax = Math.max(row1, row2);
	    
	    int jMin = Math.min(col1, col2);
	    int jMax = Math.max(col1, col2);
	    
	    return dp[iMax + 1][jMax + 1] - dp[iMax + 1][jMin] - dp[iMin][jMax + 1] + dp[iMin][jMin];    
	    }
	}
    	
    	public class Solution_306 {
    	    public boolean isAdditiveNumber(String num) {
    	        int n = num.length();
    	        for (int i = 1; i <= n / 2; ++i)
    	            for (int j = 1; Math.max(j, i) <= n - i - j; ++j)
    	                if (isValid(i, j, num)) return true;
    	        return false;
    	    }
    	    private boolean isValid(int i, int j, String num) {
    	        if (num.charAt(0) == '0' && i > 1) return false;
    	        if (num.charAt(i) == '0' && j > 1) return false;
    	        String sum;
    	        Long x1 = Long.parseLong(num.substring(0, i));
    	        Long x2 = Long.parseLong(num.substring(i, i + j));
    	        for (int start = i + j; start != num.length(); start += sum.length()) {
    	            x2 = x2 + x1;
    	            x1 = x2 - x1;
    	            sum = x2.toString();
    	            if (!num.startsWith(sum, start)) return false;
    	        }
    	        return true;
    	    }
    	}
    	public class NumArray {
    	    int[] nums;
    	    int[] btree;
    	    public NumArray(int[] nums) {
    	        this.nums = new int[nums.length];
    	        btree = new int[nums.length + 1];
    	        for (int i = 0; i < nums.length; i++) {
    	           update(i, nums[i]);
    	        }
    	    }

    	    void update(int i, int val) {
    	        int index = i + 1;
    	        int delta = val - nums[i];
    	        nums[i] = val;
    	        
    	        while(index <= nums.length) {
    	            btree[index] += delta;
    	            index = index + (index & (-index));
    	        }
    	        
    	    }

    	    public int sumRange(int i, int j) {
    	        return getSum(j) - getSum(i-1);
    	    }
    	    
    	    int getSum(int i) {
    	        int index = i + 1;
    	        int sum = 0;
    	        while (index > 0) {
    	            sum += btree[index];
    	            index -= index & (-index);
    	        }
    	        
    	        return sum;
    	    }
    	}
    	
    	public class Solution_316 {
    	    public String removeDuplicateLetters(String s) {
    	        int[] cnt = new int[26];
    	        int pos = 0; // the position for the smallest s[i]
    	        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
    	        for (int i = 0; i < s.length(); i++) {
    	            if (s.charAt(i) < s.charAt(pos)) pos = i;
    	            if (--cnt[s.charAt(i) - 'a'] == 0) break;
    	        }
    	        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    	    }
    	}
    	
    	public class Solution_318 {
    		   public  int maxProduct(String[] words) {
    		    	if (words == null || words.length == 0)
    		    		return 0;
    		    	int len = words.length;
    		    	int[] value = new int[len];
    		    	for (int i = 0; i < len; i++) {
    		    		String tmp = words[i];
    		    		value[i] = 0;
    		    		for (int j = 0; j < tmp.length(); j++) {
    		    			value[i] |= 1 << (tmp.charAt(j) - 'a');
    		    		}
    		    	}
    		    	int maxProduct = 0;
    		    	for (int i = 0; i < len; i++)
    		    		for (int j = i + 1; j < len; j++) {
    		    			if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
    		    				maxProduct = words[i].length() * words[j].length();
    		    		}
    		    	return maxProduct;
    		    }
    		}

    	public class Solution_319 {    		
		   int bulbSwitch(int n) {
		        int counts = 0;
		        
		        for (int i=1; i*i<=n; ++i) {
		            ++ counts;    
		        }
		        
		        return counts;
		    }    			
    	}
    	
    	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    	    int n = nums1.length;
    	    int m = nums2.length;
    	    int[] ans = new int[k];
    	    for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
    	        int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
    	        if (greater(candidate, 0, ans, 0)) ans = candidate;
    	    }
    	    return ans;
    	}
    	private int[] merge(int[] nums1, int[] nums2, int k) {
    	    int[] ans = new int[k];
    	    for (int i = 0, j = 0, r = 0; r < k; ++r)
    	        ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
    	    return ans;
    	}
    	public boolean greater(int[] nums1, int i, int[] nums2, int j) {
    	    while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
    	        i++;
    	        j++;
    	    }
    	    return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    	}
    	public int[] maxArray(int[] nums, int k) {
    	    int n = nums.length;
    	    int[] ans = new int[k];
    	    for (int i = 0, j = 0; i < n; ++i) {
    	        while (n - i + j > k && j > 0 && ans[j - 1] < nums[i]) j--;
    	        if (j < k) ans[j++] = nums[i];
    	    }
    	    return ans;
    	}
    	
    	public class Solution_322 {
    	    public int coinChange(int[] coins, int amount) {
    	        int[] dp = new int[amount +1];
    	        dp[0] = 0;
    	        for (int i = 1; i <= amount; i++) dp[i] = amount + 1;
    	        for (int i = 1; i <= amount; i++) {
    	        	for (int j = 0; j < coins.length; j++) {
    	        		if (i - coins[j] >= 0 ){
    	        			dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
    	        		}
    	        	}
    	        }
    	        if (dp[amount] > amount) dp[amount] = -1;
    	        return dp[amount];
    	    }
    	}
    	
    	public class Solution_324 {
    		public void wiggleSort(int[] nums) {
    		    int median = selectKth(nums, 0, nums.length-1, nums.length%2==0 ? nums.length/2 : nums.length/2+1);
    		    List<Integer> leftArr = new ArrayList<Integer>();
    		    for(int i=0; i<=median; i++)
    		    leftArr.add(nums[i]);
    		    List<Integer> rightArr = new ArrayList<Integer>();
    		    for(int i=median+1; i<nums.length; i++)
    		    	rightArr.add(nums[i]);
    		    for(int li=leftArr.size()-1,ri=rightArr.size()-1,i=0; ri>=0; li--,ri--,i+=2) { // right is same or shorter than left
    		    	nums[i] = leftArr.get(li);
    		    	nums[i+1] = rightArr.get(ri);
    		    }
    		    if(nums.length%2!=0)
    		    	nums[nums.length-1] = leftArr.get(0);
    		}

    		 private int selectKth(int[] nums, int start, int end, int k) {
    		     int[] res = partition(nums,start,end);
    		     int lb = res[0]; int hb = res[1];
    		     if(k-1<lb)
    		         return selectKth(nums,start,lb-1,k);
    		     else if (k-1>hb)
    		         return selectKth(nums,hb+1,end,k);
    		     else
    		         return k-1;
    		 }
    		 
    		 private int[] partition(int[] nums, int lb, int hb) {
    		     int pVal = nums[lb]; // use random genarater is better in performance
    		     int i = lb;
    		     while(i<=hb) {
    		         if(nums[i]==pVal)
    		             i++;
    		         else if(nums[i]<pVal)
    		             swap(nums,i++,lb++);
    		         else
    		             swap(nums,i,hb--);
    		     }
    		     int[] res = new int[2];
    		     res[0] = lb; res[1] = hb;
    		     return res;
    		 }
    		 
    		 private void swap(int[] nums, int i, int j) {
    		     int tmp = nums[i];
    		     nums[i] = nums[j];
    		     nums[j] = tmp;
    		 }
    		}
    	public class Solution_326 {
    	    public boolean isPowerOfThree(int n) {
    	        if (n > 1){
    	            while (n > 1) {
    	                if (n % 3 !=0) return false;
    	                n /=3;
    	            }
    	        }
    	        
    	        return n == 1;
    	    }
    	}	
    	
    	public class Solution_327 {
    	    public int countRangeSum(int[] nums, int lower, int upper) {
	    	    int n = nums.length;
	    	    long[] sums = new long[n + 1];
	    	    for (int i = 0; i < n; ++i)
	    	        sums[i + 1] = sums[i] + nums[i];
	    	    return countWhileMergeSort(sums, 0, n + 1, lower, upper);
	    	}
	
	    	private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
	    	    if (end - start <= 1) return 0;
	    	    int mid = (start + end) / 2;
	    	    int count = countWhileMergeSort(sums, start, mid, lower, upper) 
	    	              + countWhileMergeSort(sums, mid, end, lower, upper);
	    	    int j = mid, k = mid, t = mid;
	    	    long[] cache = new long[end - start];
	    	    for (int i = start, r = 0; i < mid; ++i, ++r) {
	    	        while (k < end && sums[k] - sums[i] < lower) k++;
	    	        while (j < end && sums[j] - sums[i] <= upper) j++;
	    	        while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
	    	        cache[r] = sums[i];
	    	        count += j - k;
	    	    }
	    	    System.arraycopy(cache, 0, sums, start, t - start);
	    	    return count;
	    	}
    	}
    	
    	public class Solution_328 {
    	    public ListNode oddEvenList(ListNode head) {
    	        ListNode odd = new ListNode(1);
    	        ListNode even = new ListNode(0);
    	        ListNode podd = odd;
    	        ListNode peven = even;
    	        
    	        while (head != null) {
    	        	podd.next = head;
    	        	podd = head;
    	        	head = head.next;
    	        	peven.next = head;
    	        	peven = head;
    	        	if (head == null) break;
    	        	head = head.next;    	        	
    	        }
    	        
    	        podd.next = even.next;
    	        return odd.next;    	        
    	    }
    	}
    	
    	public class Solution_329 {
    		int longest = 0;
    	    public int longestIncreasingPath(int[][] matrix) {
    	        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    	        int m = matrix.length;
    	        int n = matrix[0].length;
    	        for (int i = 0; i < m; i++)
    	        	for (int j = 0; j < n; j++)
    	        		getPath(matrix, m, n, i, j, 0, Integer.MIN_VALUE);
    	        
    	        return longest;
    	    }
    	    
    	    void getPath(int[][] a, int m, int n, int i, int j, int len, int pre) {
    	    	if (i >= m ||i <0 || j >=n || j <0) return;
    	    	if (a[i][j] > pre) {
    	    		len++; 
    	    		longest = Math.max(longest, len);
    	    		int save = a[i][j];
    	    		a[i][j] = Integer.MIN_VALUE;
    	    		getPath(a, m, n, i+1, j, len, save);
    	    		getPath(a, m, n, i-1, j, len, save);
    	    		getPath(a, m, n, i, j+1, len, save);
    	    		getPath(a, m, n, i, j-1, len, save);
    	    		a[i][j] = save;
    	    	}
    	    }
    	}
    	
    	public class Solution_329_2 {

    		public int longestIncreasingPath(int[][] matrix) {

    			if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
    				return 0;

    			int max = 0, n = matrix.length, m = matrix[0].length;

    			// create a cache matrix
    			int[][] cache = new int[n][m];

    			// dfs search on every element in matrix
    			for (int i = 0; i < n; i++) {
    				for (int j = 0; j < m; j++) {
    					max = Math.max(dfs(matrix, Integer.MIN_VALUE, i, j, n, m, cache), max);
    				}
    			}
    			return max;
    		}

    		int dfs(int[][] matrix, int min, int i, int j, int n, int m, int[][] cache) {

    			// check boundary limits
    			if (i < 0 || j < 0 || i >= n || j >= m)
    				return 0;

    			// check min condition
    			if (matrix[i][j] <= min)
    				return 0;

    			// check into cache
    			if (cache[i][j] != 0)
    				return cache[i][j];

    			// update min
    			min = matrix[i][j];

    			// run dfs in all four directions
    			int a = dfs(matrix, min, i - 1, j, n, m, cache) + 1;
    			int b = dfs(matrix, min, i + 1, j, n, m, cache) + 1;
    			int c = dfs(matrix, min, i, j - 1, n, m, cache) + 1;
    			int d = dfs(matrix, min, i, j + 1, n, m, cache) + 1;

    			// find max and update cache
    			int max = Math.max(a, Math.max(b, Math.max(c, d)));
    			cache[i][j] = max;

    			return max;
    		}
    	}
    	
    	public class Solution_330 {
    		public int minPatches(int[] nums, int n) {
    	    	long miss = 1;
    	    	int added = 0;
    	    	int i = 0;
    	        while (miss <= n) {
    	            if (i < nums.length && nums[i] <= miss) {
    	                miss += nums[i++];
    	            } else {
    	                miss += miss;
    	                added++;
    	            }
    	        }
    	        return added;
    	    }
    	}
    	
    	public class Solution_331 {
    		int index = 0;
    	    public boolean isValidSerialization(String preorder) {
    	    	String[] values = preorder.split(",");
    	    	int total = values.length;
    	    	
    	    	return isValid(values, total) && index == total;
    	    }
    	    
    	    boolean isValid(String[] values, int total) {
    	    	if (index >= total) return false;
    	    	if (values[index].equals("#")) {
    	    		index++;
    	    		return true;
    	    	}
    	    	index++;
    	    	return isValid(values, total) && isValid(values, total);
    	    	
    	    }
    	}
    	
    	public class Solution_332_poor {
    	    public List<String> findItinerary(String[][] tickets) {
    	        if (tickets == null || tickets.length == 0 || tickets[0].length == 0) return res;
    	        route = new String[tickets.length + 1];
    	        find(tickets, 0);
    	        
    	        return res;
    	    }
    	    String[] route; 
    	    List<String> res = new ArrayList<String>();
    	    
    	    void find(String[][] tickets, int pos) {
    	    	if (pos == tickets.length + 1) {
    	    		
    	    		boolean valid = res.isEmpty();
    	    		if (!valid) {
    	    			for (int i = 0; i < res.size(); i++) {
    	    				int c = route[i].compareTo(res.get(i));
    	    				
    	    				if (c < 0) {
    	    					valid = true;
    	    					break;
    	    				} else if (c > 0) break;
    	    			}
    	    		}
    	    		if (valid){
    	    			res.clear();
    	    			for (String v : route) 	res.add(v);    	    				
    	    		}
    	    		
    	    		return;
    	    	}
    	    	
    	    	int nextPos = 0;
    	    	for (int i = 0; i < tickets.length; i++) {
    	    		String[] ticket = tickets[i];
    	    		if (ticket == null) continue;
    	    		if (pos == 0) {
    	    			if (ticket[0].equals("JFK")){
	    	    			route[0] = ticket[0];
	    	    			route[1] = ticket[1];
	    	    			nextPos = pos + 2;
	    	    		} else continue;
    	    		} else {
    	    			if (route[pos-1].equals(ticket[0])) {
    	    				route[pos]  = ticket[1];
    	    				nextPos = pos + 1;
    	    			}
    	    			else continue;    	    			
    	    		}
    	    		
    	    		tickets[i] = null;
    	    		find(tickets, nextPos);
    	    		tickets[i] = ticket;
    	    	}
    	    }
    	}
    	
    	public class Solution_332 {
    	    public List<String> findItinerary(String[][] tickets) {
    	        List<String> ret = new ArrayList<String>();
    	        Map<String, PriorityQueue<String>> map = new HashMap<>();
    	        for(String[] ticket : tickets) {
    	            if(!map.containsKey(ticket[0])) {
    	                map.put(ticket[0], new PriorityQueue<String>());
    	            }
    	            map.get(ticket[0]).offer(ticket[1]);;
    	        }
    	        Stack<String> stack = new Stack<String>();
    	        stack.push("JFK");
    	        while(!stack.isEmpty()) {
    	            String next = stack.peek();
    	            if(map.containsKey(next) && !map.get(next).isEmpty()) {
    	                stack.push(map.get(next).poll());
    	            } else {
    	                ret.add(next);
    	                stack.pop();
    	            }
    	        }
    	        Collections.reverse(ret);
    	        return ret;
    	    }
    	}
    	
    	public class Solution_334 {
    		public boolean increasingTriplet(int[] nums) {
    	        if (nums == null || nums.length < 3) return false;
    	        int small = Integer.MAX_VALUE;
    	        int big = Integer.MAX_VALUE;
    	        
    	        for (int n : nums) {
    	            if (n <= small) small = n;
    	            else if (n <= big) big = n;
    	            else return true;
    	        }
    	        
    	        return false;
    	    }
    	}
    	
    	public class Solution_335 {
    	    public boolean isSelfCrossing(int[] x) {
    	        List<int[]> all = new ArrayList<int[]> ();
    	        
    	        int[] prev = {0, 0};
    	        for (int i = 0; i < x.length; i++) {
    	        	int mod = i % 4;
    	        	int[] line = new int[4];
    	        	line[0] = prev[0];
    	        	line[1] = prev[1];
    	        	switch( mod) {
    	        	case 0:
    	        		prev[1] -= x[i];
    	        		break;
    	        	case 1: 
    	        		prev[0] -= x[i];
    	        		break;
    	        	case 2:
    	        		prev[1] += x[i];
    	        		break;
    	        	case 3: 
    	        		prev[0] += x[i];
    	        		break;    	        	
    	        	}
    	        	
    	        	line[2] = prev[0];
    	        	line[3] = prev[1];
    	        	
    	        	if (i > 2) {
    	        		for (int j = all.size() -3;  j >= 0; j -= 1) {
    	        			if (isCrossing(line, all.get(j))) return true;
    	        		}
    	        	}
    	        	all.add(line);
    	        }
    	        
    	        return false;
    	    }
    	    
    	    boolean isCrossing(int[] line1, int[] line2) {
    	    	int[] v = (line1[0] == line1[2]) ? line1 : line2;
    	    	int[] h = (v == line1) ? line2 : line1;
    	    	
    	    	return ((v[0] >= h[0] && v[0] <= h[2]) || (v[0] >= h[2] && v[0] <= h[0])) && ((h[1] >= v[1] && h[1] <= v[3]) || (h[1] >= v[3] && h[1] <= v[1]));
    	    }
    	}
    	
    	public class Solution_335_g {
    	     public boolean isSelfCrossing(int[] x) {
    	        int l = x.length;
    	        if(l <= 3) return false;
    	        
    	        for(int i = 3; i < l; i++){
    	            if(x[i] >= x[i-2] && x[i-1] <= x[i-3]) return true;  //Fourth line crosses first line and onward
    	            if(i >=4)
    	            {
    	                if(x[i-1] == x[i-3] && x[i] + x[i-4] >= x[i-2]) return true; // Fifth line meets first line and onward
    	            }
    	            if(i >=5)
    	            {
    	                if(x[i-2] - x[i-4] >= 0 && x[i] >= x[i-2] - x[i-4] && x[i-1] >= x[i-3] - x[i-5] && x[i-1] <= x[i-3]) return true;  // Sixth line crosses first line and onward
    	            }
    	        }
    	        return false;
    	    }
    	}
    	
    	public class Solution_336 {
    		public List<List<Integer>> palindromePairs(String[] words) {
    		    List<List<Integer>> res = new ArrayList<List<Integer>>();
    		    if(words == null || words.length == 0){
    		        return res;
    		    }
    		    //build the map save the key-val pairs: String - idx
    		    HashMap<String, Integer> map = new HashMap<>();
    		    for(int i = 0; i < words.length; i++){
    		        map.put(words[i], i);
    		    }
    		    
    		    //special cases: "" can be combine with any palindrome string
    		    if(map.containsKey("")){
    		        int blankIdx = map.get("");
    		        for(int i = 0; i < words.length; i++){
    		            if(isPalindrome(words[i])){
    		                if(i == blankIdx) continue;
    		                res.add(Arrays.asList(blankIdx, i));
    		                res.add(Arrays.asList(i, blankIdx));
    		            }
    		        }
    		    }
    		    
    		    //find all string and reverse string pairs
    		    for(int i = 0; i < words.length; i++){
    		        String cur_r = reverseStr(words[i]);
    		        if(map.containsKey(cur_r)){
    		            int found = map.get(cur_r);
    		            if(found == i) continue;
    		            res.add(Arrays.asList(i, found));
    		        }
    		    }
    		    
    		    //find the pair s1, s2 that 
    		    //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
    		    //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
    		    for(int i = 0; i < words.length; i++){
    		        String cur = words[i];
    		        for(int cut = 1; cut < cur.length(); cut++){
    		            if(isPalindrome(cur.substring(0, cut))){
    		                String cut_r = reverseStr(cur.substring(cut));
    		                if(map.containsKey(cut_r)){
    		                    int found = map.get(cut_r);
    		                    if(found == i) continue;
    		                    res.add(Arrays.asList(found, i));
    		                }
    		            }
    		            if(isPalindrome(cur.substring(cut))){
    		                String cut_r = reverseStr(cur.substring(0, cut));
    		                if(map.containsKey(cut_r)){
    		                    int found = map.get(cut_r);
    		                    if(found == i) continue;
    		                    res.add(Arrays.asList(i, found));
    		                }
    		            }
    		        }
    		    }
    		    
    		    return res;
    		}

    		public String reverseStr(String str){
    		    StringBuilder sb= new StringBuilder(str);
    		    return sb.reverse().toString();
    		}

    		public boolean isPalindrome(String s){
    		    int i = 0;
    		    int j = s.length() - 1;
    		    while(i <= j){
    		        if(s.charAt(i) != s.charAt(j)){
    		            return false;
    		        }
    		        i++;
    		        j--;
    		    }
    		    return true;
    		}
    	}
    	
    	public class Solution_337 {
    		HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
    	    public int rob(TreeNode root) {
    	        if (root == null) return 0;
    	        
    	        if (map.containsKey(root)) return map.get(root);
    	        
    	        int valWith = root.val;
    	        int valWithout = 0;
    	        if (root.left != null)  {
    	        	valWith += (root.left.left == null ? 0 : rob(root.left.left)) + (root.left.right == null ? 0 : rob(root.left.right));
    	        	valWithout += rob(root.left);
    	        }
    	        if (root.right != null) {
    	        	valWith += (root.right.left == null ? 0 : rob(root.right.left)) + (root.right.right == null ? 0 : rob(root.right.right));
    	        	valWithout += rob(root.right);
    	        }   	        
    	        
    	        int val = Math.max(valWith, valWithout); 
    	        map.put(root, val);
    	        return val;
    	    }
    	}
    	
    	public class Solution_337_2 {
    		public int rob(TreeNode root) {
    	        return maxMoney(root)[1];
    	    }
    	    
    	    // return int[2]: maxMoney[0] = max Money avoiding root itself, maxMoney[1] = max Money allowing root to be stolen
    	    private int[] maxMoney(TreeNode root) {
    	        if (root == null) return new int[2];
    	        int[] ans = new int[2], 
    	                l = maxMoney(root.left), 
    	                r = maxMoney(root.right);
    	        ans[0] = l[1] + r[1];
    	        ans[1] = Math.max(root.val + l[0] + r[0], ans[0]);
    	        return ans;
    	    }
    	}
    	
    	public class Solution_338 {
    		public int[] countBits(int num) {
	    		int[] nums = new int[num + 1];
	    		for (int i = 1; i <= num; i++)
	    			nums[i] = nums[i >> 1] + (i & 1);
	    		return nums;
    		}
    	
		}
    	
    	/*public class NestedIterator implements Iterator<Integer> {
			    LinkedList<Integer> ll = new LinkedList<Integer>();
			    
			    public NestedIterator(List<NestedInteger> nestedList) {
			        flat(nestedList);
			    }
			    
			    void flat(List<NestedInteger> list){
			        for (NestedInteger ni : list) {
			            if (ni.isInteger()) ll.add(ni.getInteger());
			            else flat(ni.getList());
			        }
			    }
			
			    @Override
			    public Integer next() {
			        if (ll.isEmpty()) return null;
			        else return ll.poll();
			    }
			
			    @Override
			    public boolean hasNext() {
			        return !ll.isEmpty();
			    }
			}
    	}*/
    	
    	public class Solution_342 {
    	    public boolean isPowerOfFour(int num) {
    	        while (num > 1) {
    	            if (num % 4 != 0) return false;
    	            num /= 4;
    	        }
    	        
    	        return num == 1;
    	    }
    	}
    	
    	public class Solution_343 {
    	    public int integerBreak(int n) {
    	    	if (n < 2) return 0;
    	    	
    	        int[] dp = new int[n+1];
    	        dp[1] = 1;
    	        for (int i = 2; i <= n; i++) {
    	        	int max = 0;
    	        	for (int j = 1; j <= i/2; j++) 
    	        		max = Math.max(Math.max(dp[j], j) * Math.max(dp[i-j], i-j), max);   
    	        	dp[i] = max;
    	        }
    	        
    	        return dp[n];
    	    }
    	    
    	    public int integerBreak_good(int n) {
    	    	if(n==2||n==3) return n-1;
    	    	if(n==4) return 4;
    	    	int temp = n;
    	    	int sum = 1;
    	    	while(temp>4){
    	    		temp = temp -3;
    	    		sum = sum*3;
    	    	}
    	    	return sum*temp;
    	    }
    	}
    	
    	public class Solution_344 {
    	    public String reverseString(String s) {
    	        if (s == null || s.length() < 2) return s;
    	        char[] a = s.toCharArray();
    	        int l = 0, r = a.length - 1;
    	        while (l < r) {
    	            char c = a[l];
    	            a[l++] = a[r];
    	            a[r--] = c;
    	        }
    	        
    	        String str =  String.valueOf(a);
    	        return str;
    	    }
    	}
    	
    	public class Solution_345 {
    		public String reverseVowels(String s) {
    	        if (s == null || s.length() < 2) return s;
    	        
    	        char[] a = s.toCharArray();
    	        int l = 0, r = a.length - 1;
    	        int vl = -1; 
    	        int vr = -1;
    	        while (l < r) {
    	            if(vl < 0 ) {
    	            	char c = a[l];
    	            	if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c== 'I' ||c == 'o' || c == 'O' ||c == 'u' || c == 'U' ) 
    	            		vl = l;
    	            	else l++;  		            	  	
    	            }		            	
    	            else if(vr < 0 ) {
    	            	char c = a[r];
    	            	if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' ||c == 'o' || c == 'O' ||c == 'u' || c == 'U' ) 
    	            		vr = r;
    	            	else r--;   		            	  	
    	            } else{
    	            	char t = a[vl];
    	            	a[vl] = a[vr];
    	            	a[vr] = t;
    	            	l++;
    	            	r--;
    	            	vl = -1;
    	            	vr = -1;
    	            }
    	        }
    	        
    	        return  String.valueOf(a);
    	    }
    		
    		public String reverseVowels_good(String s) {
    			int i = 0, j = s.length() - 1;
    	        char[] arr = s.toCharArray();
    	        String vowels = "aeiouAEIOU";
    	        while(i < j) {
    	            while(i < j && vowels.indexOf(arr[i]) == -1) {
    	                i++;
    	            }
    	            while(i < j && vowels.indexOf(arr[j]) == -1) {
    	                j--;
    	            }
    	            if(i < j) {
    	                char temp = arr[i];
    	                arr[i] = arr[j];
    	                arr[j] = temp;
    	                
    	                i++;
    	                j--;
    	            }
    	        }
    	        return new String(arr);
    		}
    	}
    	
    	public class Solution_347 {
    	    public List<Integer> topKFrequent(int[] nums, int k) {
    	        Map<Integer, Integer> counterMap = new HashMap<>();
    	        for(int num : nums) {
    	            int count = counterMap.getOrDefault(num, 0);
    	            counterMap.put(num, count+1);
    	        }
    	        
    	        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
    	        for(Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
    	            pq.offer(entry);
    	            if(pq.size() > k) pq.poll();
    	        }
    	        
    	        List<Integer> res = new LinkedList<>();
    	        while(!pq.isEmpty()) {
    	            res.add(0, pq.poll().getKey());
    	        }
    	        return res;
    	    }
    	}
    	
    	public class Solution_349 {
    	    public int[] intersection(int[] nums1, int[] nums2) {
    	        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
    	        
    	        Set<Integer> first = new HashSet<Integer>();
    	        Set<Integer> common = new HashSet<Integer>();
    	        for (int n : nums1) first.add(n);
    	        
    	        for (int n : nums2) {
    	            if (first.contains(n)) common.add(n);
    	        }
    	        
    	        int[] ret = new int[common.size()];
    	        Iterator<Integer> iter = common.iterator();
    	        int i = 0;
    	        while (iter.hasNext()) ret[i++] = (int) iter.next();
    	        
    	        return ret;
    	    }
    	}
    	
    	public class Solution_350 {
    	    public int[] intersect(int[] nums1, int[] nums2) {
    	        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
    	        
    	        Map<Integer, Integer> first = new HashMap<Integer, Integer>();
    	        Map<Integer, Integer> common = new HashMap<Integer, Integer>();
    	        for (int n : nums1) {
    	        	int i = 0;
    	        	if (first.containsKey(n))
    	        		i = first.get(n);
    	        	first.put(n, ++i);
    	        }
    	        
    	        int total = 0;
    	        for (int n : nums2) {
    	            if (first.containsKey(n)) {
    	            	int i = 0;
    	            	if (common.containsKey(n)) i = common.get(n);
    	            	common.put(n, ++i);
    	            	total++;
    	            	
    	            	i = first.get(n);
    	            	i--;
    	            	if (i== 0) first.remove(n);
    	            	else first.put(n, i);
    	            }
    	        }
    	        
    	        int[] ret = new int[total];
    	        Set<java.util.Map.Entry<Integer, Integer>> set = common.entrySet();
    	        int pos = 0;
    	        for (java.util.Map.Entry<Integer, Integer> entry : set) {
    	        	int v = entry.getKey();
    	        	int count = entry.getValue();
    	        	for (int j = 0; j < count; j++) {
    	        		ret[pos++] = v;
    	        	}
    	        }
    	        return ret;
    	    }
    	}
    	
    	//public class Solution_352 {
    		
    		 
    		  
    		public class SummaryRanges {
    			
    			public class Interval {
      		      int start;
      		      int end;
      		      Interval() { start = 0; end = 0; }
      		      Interval(int s, int e) { start = s; end = e; }
      		  };
    			private List<Interval> intervals = new ArrayList<Interval>();
    		    /** Initialize your data structure here. */
    		    public SummaryRanges() {    		        
    		    }
    		    
    		    public void addNum(int val) {
    		        if (intervals.isEmpty()) intervals.add(new Interval(val, val));
    		        else {
    		        	for (int i = 0; i < intervals.size(); i++) {
    		        		Interval cur = intervals.get(i);    		        		
    		        		if (val == cur.start - 1) {
    		        			cur.start = val; 
    		        			return;
    		        		} else if (val < cur.start) {
    		        			intervals.add(i,new Interval(val, val));
    		        			return;
    		        		}	
    		        		else if (val >= cur.start && val <= cur.end) {  
    		        			return;
    		        		}
    		        		else if (val  == cur.end + 1) {
    		        			cur.end = val;
    		        			if (i + 1 < intervals.size()) {
    		        				Interval next = intervals.get(i + 1);
    		        				if (cur.end == next.start || cur.end == next.start -1) {
    		        					cur.end = next.end;
    		        					intervals.remove(i+1);    		        					
    		        				}    		        				
    		        			}
    		        			return;
    		        		}
    		        	}     	
    		        	
    		        	intervals.add(new Interval(val, val)); 	
    		        	
    		        }
    		    }
    		    
    		    public List<Interval> getIntervals() {
    		    	System.out.println();
    		    	for (Interval i : intervals) {
    		    		StringBuffer sb = new StringBuffer();
    		    		sb.append('[').append(i.start).append(',').append(i.end).append(']').append(' ');
    		    		System.out.print(sb.toString());
    		    	}
    		        return intervals;
    		    }
    		}
    	//}
	public class Solution_354 {
		public int maxEnvelopes(int[][] envelopes) {
	        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
	        int max = 0;
	        int dp [] = new int [envelopes.length];
	        for(int i = 0; i < envelopes.length; i++){
	            dp[i] = 1;
	            for(int j = 0; j < i; j++){
	                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
	                    dp[i] = Math.max(dp[i], dp[j] + 1);
	            }
	            max = Math.max(dp[i], max);
	        }
	        return max;
	   	}
	}
	
	static public class Twitter {
	    private static  int timeStamp=0;
	    
	    // easy to find if user exist
	    private Map<Integer, User> userMap;
	    
	    // Tweet link to next Tweet so that we can save a lot of time
	    // when we execute getNewsFeed(userId)
	    private class Tweet{
	        public int id;
	        public int time;
	        public Tweet next;
	        
	        public Tweet(int id){
	            this.id = id;
	            time = timeStamp++;
	            next=null;
	        }
	    }
	    
	    
	    // OO design so User can follow, unfollow and post itself
	    public class User{
	        public int id;
	        public Set<Integer> followed;
	        public Tweet tweet_head;
	        
	        public User(int id){
	            this.id=id;
	            followed = new HashSet<>();
	            follow(id); // first follow itself
	            tweet_head = null;
	        }
	        
	        public void follow(int id){
	            followed.add(id);
	        }
	        
	        public void unfollow(int id){
	            followed.remove(id);
	        }
	        
	        
	        // everytime user post a new tweet, add it to the head of tweet list.
	        public void post(int id){
	            Tweet t = new Tweet(id);
	            t.next=tweet_head;
	            tweet_head=t;
	        }
	    }
	    
	    
	    

	    /** Initialize your data structure here. */
	    public Twitter() {
	        userMap = new HashMap<Integer, User>();
	    }
	    
	    /** Compose a new tweet. */
	    public void postTweet(int userId, int tweetId) {
	        if(!userMap.containsKey(userId)){
	            User u = new User(userId);
	            userMap.put(userId, u);
	        }
	        userMap.get(userId).post(tweetId);
	            
	    }
	    

	    
	    // Best part of this.
	    // first get all tweets lists from one user including itself and all people it followed.
	    // Second add all heads into a max heap. Every time we poll a tweet with 
	    // largest time stamp from the heap, then we add its next tweet into the heap.
	    // So after adding all heads we only need to add 9 tweets at most into this 
	    // heap before we get the 10 most recent tweet.
	    public List<Integer> getNewsFeed(int userId) {
	        List<Integer> res = new LinkedList<>();

	        if(!userMap.containsKey(userId))   return res;
	        
	        Set<Integer> users = userMap.get(userId).followed;
	        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
	        for(int user: users){
	            Tweet t = userMap.get(user).tweet_head;
	            // very imporant! If we add null to the head we are screwed.
	            if(t!=null){
	                q.add(t);
	            }
	        }
	        int n=0;
	        while(!q.isEmpty() && n<10){
	          Tweet t = q.poll();
	          res.add(t.id);
	          n++;
	          if(t.next!=null)
	            q.add(t.next);
	        }
	        
	        return res;
	        
	    }
	    
	    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	    public void follow(int followerId, int followeeId) {
	        if(!userMap.containsKey(followerId)){
	            User u = new User(followerId);
	            userMap.put(followerId, u);
	        }
	        if(!userMap.containsKey(followeeId)){
	            User u = new User(followeeId);
	            userMap.put(followeeId, u);
	        }
	        userMap.get(followerId).follow(followeeId);
	    }
	    
	    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	    public void unfollow(int followerId, int followeeId) {
	        if(!userMap.containsKey(followerId) || followerId==followeeId)
	            return;
	        userMap.get(followerId).unfollow(followeeId);
	    }
	}
	
	public class Solution_357 {
	    public int countNumbersWithUniqueDigits(int n) {
		    if (n == 0) {
		        return 1;
		    }
		    int ans = 10, base = 9;
		    for (int i = 2; i <= n && i <= 10; i++) {
		        base = base * (9 - i + 2);
		        ans += base;
		    }
		    return ans;
		}
	}
	class Solution_365 {
		public boolean canMeasureWater(int x, int y, int z) {
		        return z == 0 || (z - x <= y && z % gcd(x, y) == 0);
		    }
		    private int gcd(int x, int y)
		    {
		        return y == 0 ? x : gcd(y, x % y);
		    }
		};
		public class Solution_367 {
		    boolean isPerfectSquare(int num) {
		      if (num < 1) return false;
		      if (num == 1) return true;
		      long t = num / 2;
		      while (t * t > num) {
		        t = (t + num / t) / 2;
		      }
		      return t * t == num;
		    }
		}
		
		public class Solution_368 {
		    public List<Integer> largestDivisibleSubset(int[] nums) {
		        List<Integer> result=new ArrayList<Integer>();
		        int n=nums.length;
		        if(n<1) return result;
		        Arrays.sort(nums);
		        int[] s=new int[n];
		        int[] parent=new int[n]; //used to build result from 
		        Arrays.fill(parent,-1);
		        s[0]=1;
		        int maxSize=0; //largest subset size
		        int maxSizelastIndex=0; //the index of largest subset size
		        for(int i=1;i<n;i++){
		            s[i]=1;
		            for(int j=i-1;j>=0;j--){
		                if(nums[i]%nums[j]==0){
		                    if(s[j]+1>s[i]){
		                        s[i]=s[j]+1;
		                        parent[i]=j;
		                    }
		                }
		            }
		            if(s[i]>maxSize){
		                maxSize=s[i];
		                maxSizelastIndex=i;
		            }
		        }
		        //populate result;
		        int i=maxSizelastIndex;
		        while(i>=0){
		            result.add(0,nums[i]);
		            i=parent[i];
		        }
		        return result;
		    }
		}
		
		public class Solution_371 {
		    
		    
		    public int getSum(int a, int b) {
		    	return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
		    }

		    // Recursive
		    public int getSubtract(int a, int b) {
		    	return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
		    }

		    // Get negative number
		    public int negate(int x) {
		    	return ~x + 1;
		    }
		}
		
		public class Solution_372 {
		    private int base = 1337;

		    public int superPow(int a, int[] b) {
		        
		        if(b.length==0)  return 1;
		        
		        int lastDigit = b[b.length-1];
		        int[] copyArr = Arrays.copyOf(b, b.length-1);
		        
		        return powMod(superPow(a,copyArr), 10) * powMod(a,lastDigit) % base;
		    }
		    
		    private int powMod(int a, int n){
		        
		        a %= base;
		        int res = 1;
		        
		        for(int i=0; i<n;i ++){
		            res = (res*a)%base;
		        }
		        
		        return res;
		    }
		}
		
		public class Solution_375 {
		    public int getMoneyAmount(int n) {
		        int[][] table = new int[n+1][n+1];
		        return DP(table, 1, n);
		    }
		    
		    int DP(int[][] t, int s, int e){
		        if(s >= e) return 0;
		        if(t[s][e] != 0) return t[s][e];
		        int res = Integer.MAX_VALUE;
		        for(int x=s; x<=e; x++){
		            int tmp = x + Math.max(DP(t, s, x-1), DP(t, x+1, e));
		            res = Math.min(res, tmp);
		        }
		        t[s][e] = res;
		        return res;
		    }
		}
		
		public class Solution_376 {
		    public int wiggleMaxLength(int[] nums) {
		        if (nums == null) return 0;
		        		        
		        int[][] dp = new int[nums.length][2];
		        int max = 1;
		        dp[0][0] = 1;
		        dp[0][1] = 1;
		        
		        for (int i = 1; i < nums.length; i++) {
		        	for (int j = i-1; j >= 0; j--){
		        		if (nums[i] > nums[j]) {
		        			dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
		        		} else if (nums[i] < nums[j]){
		        			dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
		        		}
		        		
		        		max = Math.max(max, Math.max(dp[i][0],  dp[i][1]));
		        	}
		        }
		        return max;
		    }
		}
		
		class Solution_376_good {
			public int wiggleMaxLength(int[] nums) {
			        int count1 = 1, count2 = 1;
			        for(int i = 1; i < nums.length; ++i) 
			            if(nums[i] > nums[i-1]) count1 = count2 + 1;
			            else if(nums[i] < nums[i-1]) count2 = count1 + 1;
			        return nums.length == 0 ? 0 : Math.max(count1, count2);
			    }
			}
		
		public class Solution_377 {
			public int combinationSum4(int[] nums, int target) {
			    if (target == 0) {
			        return 1;
			    }
			    int res = 0;
			    for (int i = 0; i < nums.length; i++) {
			        if (target >= nums[i]) {
			            res += combinationSum4(nums, target - nums[i]);
			        }
			    }
			    return res;
			}
		}
		
		public class Solution_396 {
			 public int maxRotateFunction(int[] A) {
		        if(A.length == 0){
		            return 0;
		        }
		        
		        int sum =0, iteration = 0, len = A.length;
		        
		        for(int i=0; i<len; i++){
		            sum += A[i];
		            iteration += (A[i] * i);
		        }
		        
		        int max = iteration;
		        for(int j=1; j<len; j++){
		            // for next iteration lets remove one entry value of each entry and the prev 0 * k
		            iteration = iteration - sum + A[j-1]*len;
		            max = Math.max(max, iteration);
		        }
		        
		        return max;
		    }
		}
		
		public class Solution_397 {
		    public int integerReplacement(int n) {
		        if (n == 1) return 0;
		        if (n % 2 == 0) 
		            return (1 + integerReplacement(n / 2));
		        else 
		            return (2 + Math.min(integerReplacement(n/2), integerReplacement(n/2 + 1)));
		    }
		}
		
		public class Solution_407 {
		    class Cell {
		        int x;
		        int y;
		        int h;
		        public Cell(int x, int y, int height) {
		            this.x = x;
		            this.y = y;
		            this.h = height;
		        }
		    }
		    /**
		     * @param heights: a matrix of integers
		     * @return: an integer
		     */
		    public int trapRainWater(int[][] heights) {
		        if (heights == null || heights.length == 0 || heights[0].length == 0) {
		            return 0;
		        }

		        PriorityQueue<Cell> queue = new PriorityQueue<Cell>(1, new Comparator<Cell>(){
		            public int compare(Cell A, Cell B) {
		                return A.h - B.h;
		            }
		        });
		        int n = heights.length;
		        int m = heights[0].length;
		        boolean[][] visited = new boolean[n][m];

		        //LEFT-RIGHT
		        for (int i = 0; i < n; i++) {
		            visited[i][0] = true;
		            visited[i][m - 1] = true;
		            queue.offer(new Cell(i, 0, heights[i][0]));
		            queue.offer(new Cell(i, m - 1, heights[i][m - 1]));
		        }
		        //TOP-BOTTOM
		        for (int i = 0; i < m; i++) {
		            visited[0][i] = true;
		            visited[n - 1][i] = true;
		            queue.offer(new Cell(0, i, heights[0][i]));
		            queue.offer(new Cell(n - 1, i, heights[n - 1][i]));
		        }

		        int[] xs = {0,  0, 1, -1};
		        int[] ys = {1, -1, 0,  0};
		        int sum = 0;
		        while (!queue.isEmpty()) {
		            Cell cell = queue.poll();
		            for (int i = 0; i < 4; i++) {
		                int nx = cell.x + xs[i];
		                int ny = cell.y + ys[i];
		                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
		                    visited[nx][ny] = true;
		                    sum += Math.max(0, cell.h - heights[nx][ny]);
		                    queue.offer(new Cell(nx, ny, Math.max(heights[nx][ny], cell.h)));
		                }
		            }
		        }//end while
		        return sum;
		    }
		}
		
		public class Solution_409 {
		    public int longestPalindrome(String s) {
		        if (s == null) return 0;
		        if (s.length() < 2) return s.length();
		        Set<Character> set = new HashSet<Character>();
		        int count = 0;
		        for (int i = 0; i < s.length(); i++) {
		            char c = s.charAt(i);
		            if (set.contains(c)) {
		            	count += 2;
		            	set.remove(c);
		            } else set.add(c);
		        }
		        
		        return count + (set.isEmpty() ? 0 : 1);
		    }
		}
		
		public class Solution_410 {
		    public int splitArray(int[] nums, int m) {
		        long sum = 0;
		        int max = 0;
		        for(int num: nums){
		            max = Math.max(max, num);
		            sum += num;
		        }
		        return (int)binary(nums, m, sum, max);
		    }
		    
		    private long binary(int[] nums, int m, long high, long low){
		        long mid = 0;
		        while(low < high){
		            mid = (high + low)/2;
		            if(valid(nums, m, mid)){
		                //System.out.println(mid);
		                high = mid;
		            }else{
		                low = mid + 1;
		            }
		        }
		        return high;
		    }
		    
		    private boolean valid(int[] nums, int m, long max){
		        int cur = 0;
		        int count = 1;
		        for(int num: nums){
		            cur += num;
		            if(cur > max){
		                cur = num;
		                count++;
		                if(count > m){
		                    return false;
		                }
		            }
		        }
		        return true;
		    }
		}
		
		public class Solution_415 {
		    public String addStrings(String num1, String num2) {
		        if (num1.length() == 0) return num2;
		        else if (num2.length() == 0) return num1;
		        
		        char[] n1 = num1.toCharArray();
		        char[] n2 = num2.toCharArray();
		        if (n1.length < n2.length) {
		        	char[] temp = n1;
		        	n1 = n2;
		        	n2 = temp;
		        }
		        
		        int c = 0;
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < n2.length; i++) {
		        	c += n1[n1.length - i - 1] + n2[n2.length - i - 1] - 2 * '0';
		        	sb.insert(0, (char) (c % 10 + '0'));
		        	c /= 10;
		        }
		        
		        if (n1.length != n2.length) {
		        	for (int i = n2.length; i < n1.length; i++) {
		        		c += n1[n1.length - i - 1] - '0';
		        		sb.insert(0,  (char) (c % 10 + '0'));
		        		c /= 10;
		        	}
		        }
		        
		        if (c > 0) sb.insert(0, (char) (c + '0'));
		        
		        return sb.toString();
		    }
		}
		
		public class Solution_416 {
		    public boolean canPartition(int[] nums) {
		    	if (nums == null || nums.length < 2) return false;
		        int total = 0;
		        for (int num : nums) total += num;
		        
		        if(total %2 != 0) return false;
		        total /= 2;
		        
		        Set<Integer> possibles = new HashSet<Integer> ();		        
		        possibles.add(0);
		        Set<Integer> backup = new HashSet<Integer>();
		        for (int num : nums) {
		        	backup.clear();
		        	backup.addAll(possibles);
		        	Iterator<Integer> iter =  possibles.iterator();
		        	while (iter.hasNext()) {
		        		int v = iter.next() + num;
		        		if (v == total) return true;
		        		if (v < total)
		        			backup.add(v);
		        	}
		        	Set<Integer> temp = possibles;
		        	possibles = backup;
		        	backup = temp;
		        }
		        return false;
		    }		    
		}
		
		public class Solution_417 {
		    int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
		    public List<int[]> pacificAtlantic(int[][] matrix) {
		        List<int[]> res = new LinkedList<>();
		        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
		            return res;
		        }
		        int n = matrix.length, m = matrix[0].length;
		        //One visited map for each ocean
		        boolean[][] pacific = new boolean[n][m];
		        boolean[][] atlantic = new boolean[n][m];
		        Queue<int[]> pQueue = new LinkedList<>();
		        Queue<int[]> aQueue = new LinkedList<>();
		        for(int i=0; i<n; i++){ //Vertical border
		            pQueue.offer(new int[]{i, 0});
		            aQueue.offer(new int[]{i, m-1});
		            pacific[i][0] = true;
		            atlantic[i][m-1] = true;
		        }
		        for(int i=0; i<m; i++){ //Horizontal border
		            pQueue.offer(new int[]{0, i});
		            aQueue.offer(new int[]{n-1, i});
		            pacific[0][i] = true;
		            atlantic[n-1][i] = true;
		        }
		        bfs(matrix, pQueue, pacific);
		        bfs(matrix, aQueue, atlantic);
		        for(int i=0; i<n; i++){
		            for(int j=0; j<m; j++){
		                if(pacific[i][j] && atlantic[i][j])
		                    res.add(new int[]{i,j});
		            }
		        }
		        return res;
		    }
		    public void bfs(int[][]matrix, Queue<int[]> queue, boolean[][]visited){
		        int n = matrix.length, m = matrix[0].length;
		        while(!queue.isEmpty()){
		            int[] cur = queue.poll();
		            for(int[] d:dir){
		                int x = cur[0]+d[0];
		                int y = cur[1]+d[1];
		                if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]){
		                    continue;
		                }
		                visited[x][y] = true;
		                queue.offer(new int[]{x, y});
		            } 
		        }
		    }
		}
		
		public class Solution_419 {
		    public int countBattleships(char[][] board) {
		        if (board == null || board.length == 0 || board[0].length == 0) return 0;
		        int count = 0;
		        for (int i = 0; i < board.length; i++) {
		        	for (int j = 0; j < board[0].length; j++)
		        		if (board[i][j] == 'X') {
		        			if (i > 0 && board[i-1][j] == 'X') continue;
		        			if (j > 0 && board[i][j-1] == 'X') continue;
		        			count++;
		        		}
		        }
		        return count;
		    }		    
		    
		}
		
		public class Solution_423 {
		    
	    	public String originalDigits(String s) {
	            if (s == null || s.length() == 0) return "";
	            int[] counts = new int[26];
	            for (int i = 0; i < s.length(); i++) {
	                char c = s.charAt(i);
	                counts[c-'a']++;
	            }
	            
	            int[] numCount = new int[10];
	            char[][] nums = {{'z', 'e', 'r', 'o'},
	            		         {'o', 'n', 'e'},		            
	            		         {'t', 'w', 'o'},
	            		         {'t', 'h', 'r', 'e', 'e'},
	            		         {'f', 'o', 'u', 'r'},
	            		         {'f', 'i', 'v', 'e'},
	            		         {'s', 'i', 'x'},
	            		         {'s', 'e', 'v', 'e', 'n'},
	            		         {'e', 'i', 'g', 'h', 't'},
	            		         {'n', 'i', 'n', 'e'}};
	            char[] group = new char[] {'z', 'w', 'u', 'x', 'g', 'o', 'r', 'f', 's', 'n'};
	            for (char c : group) {
	                int count = counts[c-'a'];
	                while (count-- > 0) {
	                    switch (c ) {
	                    case 'z':
	                    	remove(counts, nums, 0, numCount);
	                    	break;
	                    case 'w':
	                    	remove(counts, nums, 2, numCount);
	                    	break;
	                    case 'u':
	                    	remove(counts, nums, 4, numCount);
	                    	break;
	                    case 'x':
	                    	remove(counts, nums, 6, numCount);
	                    	break;
	                    case 'g':
	                    	remove(counts, nums, 8, numCount);
	                    	break;
	                    case 'o':
	                    	remove(counts, nums, 1, numCount);
	                    	break;
	                    case 'r':
	                    	remove(counts, nums, 3, numCount);
	                    	break;
	                    case 'f':
	                    	remove(counts, nums, 5, numCount);
	                    	break;
	                    case 's':
	                    	remove(counts, nums, 7, numCount);
	                    	break;
	                    case 'n':
	                    	remove(counts, nums, 9, numCount);
	                    	break;
	                    }
	                }
	            }
	            
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < numCount.length; i++) {
	            	if (numCount[i] > 0) {
	            		for (int j = 0; j < numCount[i]; j++) 
	            			sb.append((char) (i + '0'));
	            	}
	            }
	            
	            return sb.toString();
	        }	
	    	
	    	void remove(int[] counts, char[][] nums, int num, int[] numCount) {	    		
	    		for (char c: nums[num]) {
	    			if (counts[c - 'a'] > 0)
	    				counts[c - 'a']--;
	    			else  return ;	    				
	    		}
	    		numCount[num]++;
	    	}
	    }
		    
		public class Solution_424 {
			   
		    public int characterReplacement(String s, int k) {
		        int maxLen = 0;
		        for(int l = 0 ; l<26;l++){
		            char c = (char)('A' + l); //repeated char we are looking for
		            int i = 0, j = 0, count = 0;
		            while(j<s.length()){
		                char cur = s.charAt(j);
		                if(cur != c) count++;
		                
		                //make the substring valid again
		                while(count > k){
		                    if(s.charAt(i) != c) count--;
		                    i++;
		                }
		                
		                //update maximun len
		                maxLen = Math.max(maxLen,j-i+1);
		                j++;
		            }
		        }
		        return maxLen;
		    }
		    
		    public int characterReplacement_better871432
		    (String s, int k) {
		        int len = s.length();
		        int[] count = new int[26];
		        int start = 0, maxCount = 0, maxLength = 0;
		        for (int end = 0; end < len; end++) {
		            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
		            while (end - start + 1 - maxCount > k) {
		                count[s.charAt(start) - 'A']--;
		                start++;
		            }
		            maxLength = Math.max(maxLength, end - start + 1);
		        }
		        return maxLength;
		    }
		}   
	
		public class Solution_414 {
		    public int thirdMax(int[] nums) {
		        int f = Integer.MIN_VALUE;
		        int s = Integer.MIN_VALUE;
		        int t = Integer.MIN_VALUE;
		        boolean hasMin = false;
		        for (int v : nums) {	
		        	if (v == Integer.MIN_VALUE) hasMin = true;
		            if (v > f) {
		               t = s;
		               s = f;
		               f = v; 
		            } else if (v < f) {
		              if (v > s) {
		                  t = s;
		                  s = v;
		              } else if (v <s && v > t) {
		                 t = v; 
		              }
		            }
		        }
		        
		       if (t != Integer.MIN_VALUE) return t;
		       else {
		    	   if (s != Integer.MIN_VALUE && hasMin) return t;
		    	   else return f;
		       }
		    }
		}	
	public void testFun() {
		Solution_414 s = new Solution_414();
		System.out.println(s.thirdMax(new int[] {1,2,-2147483648}));
		
	}
}
