package com.example.demo.leetcode.normal;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class LeetCodeHome {
    /**
     * 2,两数相加listnode
     * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result=new ListNode(0);
        ListNode temp=result;
        int chu=0;//除以10的商,看是否进位
        while(l1!=null||l2!=null){
            int x=(l1!=null)?l1.val:0;
            int y=(l2!=null)?l2.val:0;
            int sum=x+y+chu;
            chu=sum/10;
            temp.next=new ListNode(sum%10);
            temp=temp.next;
            if(l1!=null)
                l1=l1.next;
            if(l2!=null)
                l2=l2.next;
        }
        if(chu>0){
            temp.next=new ListNode(chu);
        }
        return result.next;
    }

    public static void main(String[] args) {
        LeetCodeHome leetCodeHome=new LeetCodeHome();
        ListNode l11=new ListNode(0);
        ListNode l1=l11;
        ListNode l22=new ListNode(7);
        ListNode l2=l22;
        l2=l2.next=new ListNode(3);
        ListNode listNode = leetCodeHome.addTwoNumbers(l11, l22);
        while (listNode!=null){
            int value=listNode.val;
            listNode=listNode.next;
            System.out.print(" "+value);
        }
    }
}