package com.work.demo;

class A{
    private D c=new D();
    public A(){
        System.out.println("A");
    }
}

class B{
    public B(){
        System.out.println("B");
    }
}

class C{
    public C(){
        System.out.println("C");
    }
}

class D{
    public D(){
        System.out.println("D");
    }
}

class DD extends D{
    
    public DD(){
        System.out.println("DD");
    }
}

class DDD extends DD{

    public DDD(){
        System.out.println("DDD");
    }
}
public class ConstructorDemo extends DDD{
    public ConstructorDemo(){
        System.out.println("ConstructorDemo");
    }
    private A a=new A();
    private B b=new B();
    private C c=new C();
    
    public static void main(String[] args) {
        new ConstructorDemo();
    }
    
}
