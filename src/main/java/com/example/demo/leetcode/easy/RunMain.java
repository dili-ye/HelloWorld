package com.example.demo.leetcode.easy;

import java.util.Arrays;

public class RunMain {
    public static void main(String[] args) {
        LeetCode leetcode=new LeetCode();
        LeetCodeHome LeetCodeHome=new LeetCodeHome();
        int [] nums={8,9,9,9};
        int [] result=leetcode.plusOne(nums);
        System.out.println(Arrays.toString(result));

    }
}
