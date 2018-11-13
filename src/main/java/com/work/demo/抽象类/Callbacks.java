package com.work.demo.抽象类;


interface Incrementable{
    void inctement();
}

class Callee1 implements Incrementable{

    private int i=0;
    @Override
    public void inctement() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement{
    public void increment(){
        System.out.println("Other operation");
    }

    static void f(MyIncrement mi){
        mi.increment();
    }
}

class Callee2 extends MyIncrement{
    private int i=0;
    public void increment(){
        super.increment();
        i++;
        System.out.println(i);
    }

    private class  Closure implements Incrementable{

        @Override
        public void inctement() {
            Callee2.this.increment();
        }

    }

    Incrementable getCallbackReference(){
        return new Closure();
    }
}

class Caller{
    private Incrementable callbackReference;
    Caller(Incrementable incrementable){
        callbackReference=incrementable;
    }

    void go(){
        callbackReference.inctement();
    }
}
public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1=new Callee1();
        Callee2 c2=new Callee2();

        MyIncrement.f(c2);
        Caller caller1=new Caller(c1);
        Caller caller2=new Caller(c2.getCallbackReference());

        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }

}