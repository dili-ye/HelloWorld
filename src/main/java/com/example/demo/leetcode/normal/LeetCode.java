package com.example.demo.leetcode.normal;

import java.util.SortedSet;
import java.util.TreeSet;

public class LeetCode {
    /**
     * 220. 存在重复元素 III
     * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
     * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
     * */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /*int len=nums.length;
        if(k<=0||t<0)
            return false;
        for(int i=0;i<len;i++){
            int first=nums[i];
            for(int j=i+1;j<=i+k&&j<len;j++){
                long re=Math.abs((long) nums[i]-nums[j]);
                if(re<=t)
                    return true;
            }
        }
        return false;
            效率太低
        */
        if(k<=0||t<0)
            return false;
        TreeSet<Long> ts=new TreeSet<Long>();
        for(int i=0;i<nums.length;i++){
            SortedSet<Long> subSt=ts.subSet((long)nums[i]-t,(long)nums[i]+t+1);
            if(!subSt.isEmpty())
                return true;
            if(i>=k)
                ts.remove((long)nums[i-k]);
            ts.add((long)nums[i]);
        }
        return false;
    }
}
