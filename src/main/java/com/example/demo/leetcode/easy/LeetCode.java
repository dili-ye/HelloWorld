package com.example.demo.leetcode.easy;

import sun.plugin2.main.server.ResultID;

import java.util.*;

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

    /**
     * 58. 最后一个单词的长度
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
     * 如果不存在最后一个单词，请返回 0 。
     * */
    public int lengthOfLastWord(String s) {
        int len=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' '){
                len++;
            }else if(s.charAt(i)==' '&&len!=0){
                return len;
            }
        }
        return len;
    }

    /**
     * 66. 加一
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * */
    public int[] plusOne(int[] digits) {
        int len=digits.length;
        if(digits[len-1]<9){
            digits[len-1]+=1;
            return digits;
        }else{
            int chu=1;
            for(int i=len-1;i>=0;i--){
                int sum=digits[i]+chu;
                if(sum<10){
                    digits[i]=sum;
                    return digits;
                }
                digits[i]=sum%10;
            }
            int [] result=new int[len+1];
            result[0]=1;
            return result;
        }
    }

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     */
    public String reverseWords(String s) {
        String[] splits=s.split(" ");
        StringBuilder sb=new StringBuilder();
        for(String split:splits){
            for(int i=split.length()-1;i>=0;i--){
                sb.append(split.charAt(i));
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。
     */
    public String reverseString(String s) {
//        StringBuilder sb=new StringBuilder(s);
//        sb.reverse();
//        return sb.toString();
        char [] schar=s.toCharArray();
        int len=schar.length;
        for(int i=0;i<=len/2;i++){
            swap(schar ,i,len-i-1);
        }
        return new String(schar);
    }

    private void swap(char[] schar, int i, int i1) {
        if(i<i1){
            char temp=schar[i1];
            schar[i1]=schar[i];
            schar[i]=temp;
        }

    }

    /**
     * 292. Nim游戏
     * 桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手
     * 每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     */
    public boolean canWinNim(int n) {
        return n%4!=0;
    }

    /**
     * 238. 除自身以外数组的乘积
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     */
    public int[] productExceptSelf(int[] nums) {
        int [] l1=new int[nums.length];
        l1[0]=1;
        for(int i=1;i<nums.length;i++){
            l1[i]=l1[i-1]*nums[i-1];
        }

        for(int i=nums.length-2,temp=nums[i+1];i>=0;i--){
            l1[i]=l1[i]*temp;
            temp=temp*nums[i];
        }
        return l1;
    }


    /**
     * 132. 分割回文串 II
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回符合要求的最少分割次数。
     *
     * public int minCut(String s) {
     *         if (s == null || s.length() == 0) {
     *             return 0;
     *         }
     *
     *         int len = s.length();
     *         int[] cut = new int[len];
     *         cut[0]=0;
     *         for (int i = 1; i < len; i++) {
     *             // int min = 1;
     *             cut[i]=is_palindrome(s.substring(0,i+1))?0:i;
     *             for (int j = i; j >= 1; j--) {
     *                 if(is_palindrome(s.substring(j,i+1))){
     *                     cut[i]=Math.min(cut[i],cut[j-1]+1);
     *                 }
     *             }
     *         }
     *         return  cut[s.length()-1];
     *     }
     *     public boolean is_palindrome(String s)
     *     {
     *         int begin=0;
     *         int end=s.length()-1;
     *         while(begin<end)
     *         {
     *             if(s.charAt(begin)!=s.charAt(end))
     *                 return false;
     *             begin++;
     *             end--;
     *         }
     *         return true;
     *     }
     */
    public int minCut(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        int len=s.length();
        int[] result=new int[len];
        result[0]=0;
        for(int i=1;i<len;i++){
            result[i]=isPalin(s.substring(0,i+1))?0:i;
            for(int j=i;j>=1;j--){
                if(isPalin(s.substring(j,i+1))){
                    result[i]= Math.min(result[i],result[j-1]+1);
                }
            }
        }
        return result[s.length()-1];
    }
    private boolean isPalin(String s){
        int start=0;
        int end=s.length()-1;
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     *
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回
     */

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<Integer ,List<Integer>> map=new HashMap<>();
        map_add_node(root,null,map);
        List<Integer> list=new ArrayList<>();
        if(map.isEmpty()){
            return list;
        }
        list.add(target.val);
        Set<Integer> set=new HashSet<>(list);
        for(int i=0;i<K;i++){
            List<Integer> temp = new ArrayList<>();
            for(Integer item:list){
                List<Integer> integers=map.get(item);
                for(Integer num:integers){
                    if(!set.contains(num)){
                        temp.add(num);
                    }
                }
            }
            list=new ArrayList<>(temp);
            set.addAll(temp);
        }
        return list;
    }
    private void map_add_node(TreeNode child,TreeNode parent,Map<Integer ,List<Integer>> map){
        if(child!=null && parent!=null){
            if(map.containsKey(child.val)){
                map.get(child.val).add(parent.val);
            }else{
                List<Integer> list=new ArrayList<>();
                list.add(parent.val);
                map.put(child.val,list);
            }

            if(map.containsKey(parent.val)){
                map.get(parent.val).add(child.val);
            }else{
                List<Integer> list=new ArrayList<>();
                list.add(child.val);
                map.put(parent.val,list);
            }
        }

        if(child.left!=null){
            map_add_node(child.left,child,map);
        }
        if(child.right!=null){
            map_add_node(child.right,child,map);
        }

    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
    public static void main(String[] args) {
        LeetCode l=new LeetCode();
        l.productExceptSelf(new int[]{1,2,3,4});
    }

}
