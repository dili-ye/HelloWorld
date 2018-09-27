package com.example.demo.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    /**
     * 1.两数之和
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     * */
    //双重遍历判断
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++) {
            for(int j=i+1;j<nums.length;j++) {
                if((nums[i]+nums[j])==target)
                    return new int[] {i,j};
            }
        }
        return null;
    }
    //运用map，单次循环遍历的同事进行查找比对
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> numsmap=new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int m=target-nums[i];
            if(numsmap.containsKey(m)) {
                return new int[] {numsmap.get(m),i};
            }
            numsmap.put(nums[i], i);
        }
        return null;
    }

    /**
     *7. 反转整数
     * 给定一个 32 位有符号整数，将整数中的数字进行反转。
     * */
    //转成字符串后进行反转
    public int reverse1(int x) {
        String xs=String.valueOf(x);
        String result="";
        for (char s:xs.toCharArray()) {
            result = s+result;
        }
        if(!Character.isDigit(result.charAt(result.length()-1))) {
            result=result.substring(result.length()-1)+result.substring(0, result.length()-1);
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
        int result=0;
        while(x!=0) {
            int yu=x%10;
            x=x/10;
            if(result>Integer.MAX_VALUE/10||((result ==Integer.MAX_VALUE/10)&&yu>7)) {
                return 0;
            }
            if(result<Integer.MIN_VALUE/10||((result==Integer.MAX_VALUE/10)&&yu<-8)) {
                return 0;
            }
            result =result*10+yu;
        }
        return result;
    }

    /**
     * 9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * */
    /*
        不转换成字符串进行反转（依旧是取余（取10）判断）
        循环判断到数值一半长度的时候就可以判断是否是回文数，
        即不断取余乘出来的数大于原数不断取商的数的时候
     */
    public boolean isPalindrome(int x) {
        if(x<0||(x%10==0&&x!=0))
            return false;
        int resnum=0;
        while(x>resnum) {
            int yu=x%10;
            x=x/10;
            resnum=resnum*10+yu;
        }
        return x==resnum||x==resnum/10;
    }
}
