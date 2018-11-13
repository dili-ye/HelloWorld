package com.work.demo.抽象类;

public class Test {
    public static void test1(DemoA a){
        ((CDemo1)a).print();
    }
    public static void test2(DemoA a){
        ((CDemo2)a).print();
    }
    public static void main(String[] args) {
        CDemo1 d1=new CDemo1();
        CDemo2 d2=new CDemo2();
        Test.test1(d1);
        Test.test2(d2);
    }

}
