package com.example.demo.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    /**
     * 1.两数之和
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     */
    //双重遍历判断
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    //运用map，单次循环遍历的同事进行查找比对
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> numsmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int m = target - nums[i];
            if (numsmap.containsKey(m)) {
                return new int[]{numsmap.get(m), i};
            }
            numsmap.put(nums[i], i);
        }
        return null;
    }

    /**
     * 7. 反转整数
     * 给定一个 32 位有符号整数，将整数中的数字进行反转。
     */
    //转成字符串后进行反转
    public int reverse1(int x) {
        String xs = String.valueOf(x);
        String result = "";
        for (char s : xs.toCharArray()) {
            result = s + result;
        }
        if (!Character.isDigit(result.charAt(result.length() - 1))) {
            result = result.substring(result.length() - 1) + result.substring(0, result.length() - 1);
        }
        try {
            int parseInt = Integer.parseInt(result);
            return parseInt;
        } catch (Exception e) {
            return 0;
        }
    }

    //运用循环判断取余和商进行反转数字（取10）
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int yu = x % 10;
            x = x / 10;
            if (result > Integer.MAX_VALUE / 10 || ((result == Integer.MAX_VALUE / 10) && yu > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || ((result == Integer.MAX_VALUE / 10) && yu < -8)) {
                return 0;
            }
            result = result * 10 + yu;
        }
        return result;
    }

    /**
     * 9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 不转换成字符串进行反转（依旧是取余（取10）判断）
     * 循环判断到数值一半长度的时候就可以判断是否是回文数，
     * 即不断取余乘出来的数大于原数不断取商的数的时候
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int resnum = 0;
        while (x > resnum) {
            int yu = x % 10;
            x = x / 10;
            resnum = resnum * 10 + yu;
        }
        return x == resnum || x == resnum / 10;
    }

    /**
     * 26:删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        int len = nums.length;
        if (len == 0)
            return 0;
        int j = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * 27. 移除元素
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null)
            return 0;
        int len = nums.length;
        if (len == 0)
            return 0;
        int i = 0;
        for (int j = 0; j < len; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 28:实现 strStr() 函数。类似indexOf()
     */
    public int strStr(String haystack, String needle) {
        char[] haystacharray = haystack.toCharArray();
        char[] needlearray = needle.toCharArray();
        int len1 = haystacharray.length;
        int len2 = needlearray.length;
        if (len2 == 0)
            return 0;
        if (len1 == 0)
            return -1;
        if (len1 < len2)
            return -1;
        char first = needlearray[0];
        int max = len1 - len2;
        for (int i = 0; i <= max; i++) {
            if (first != haystacharray[i]) {
                //首先找到第一个字符出现的位置
                while (++i <= max &&first!=haystacharray[i]) ;
            }
            if(i<=max){
                int j=i+1;//从第二个字符开始继续比较
                int end=i+len2;//如果相同的话,比较的字符串最后的位置
                for(int k=1;(k<len2||j<end)&&needlearray[k]==haystacharray[j];j++,k++);
                if(j==end)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置
     * */
    public int searchInsert(int[] nums, int target) {
        if(nums==null)
            return 0;
        int len=nums.length;
        if(len==0)
            return 0;
        int min=0;
        int max=len-1;
        while(min<=max){
            int mid=(min+max)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                min=mid+1;
            else
                max=mid-1;
        }
        return min;
    }
}
