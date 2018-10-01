package com.example.demo.leetcode.normal;

public class RunMain {
    public static void main(String[] args) {
        LeetCode leetCode=new LeetCode();
        LeetCodeHome leetCodeHome=new LeetCodeHome();
        boolean flag=leetCode.containsNearbyAlmostDuplicate(new int[]{-1,2147483647},1,2147483647);
        System.out.println(flag);
    }
}