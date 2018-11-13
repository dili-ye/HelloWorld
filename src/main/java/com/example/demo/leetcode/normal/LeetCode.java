package com.example.demo.leetcode.normal;

import java.util.Calendar;
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

    public static double lilvs(double x,int days,int mouth,int year,int day){
        int [] mouths={31,28,31,30,31,30,31,31,30,31,30,31};
        if(year%4==0&&year%400!=0){
            mouths[1]=29;
        }
        if(days<=0){
            return x;
        }
        if(days<=(mouths[mouth]-day+1)){
            x=x+x*0.0005*days;
            System.out.println(year+" 年 "+(mouth+1)+" 月 "+day+" 日 到 "+(day+days-1)+" 日,共计"+days+"天利息,本利一共"+x+"元");
            return x;
        }else{
            int time=mouths[mouth]-day+1;
            x=x+x*0.0005*time;
            days -=time;
            System.out.println(year+" 年 "+(mouth+1)+" 月 "+day+" 日 到 "+(day+time-1)+" 日,共计"+time+"天利息,本利一共"+x+"元");
            mouth++;
            if(mouth==12){
                year++;
                mouth=0;
            }
            x=lilvs(x,days,mouth,year,1);
        }
        return x;
    }
    public static double lilv(double x,int sumDay){
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int mouth=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return lilvs(x,sumDay,mouth,year,day);
    }
    public static void main(String[] args) {
        System.out.println(lilv(10000,365));
    }
}
