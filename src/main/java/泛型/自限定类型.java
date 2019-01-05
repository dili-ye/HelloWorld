package 泛型;

class DemoType<T>{
    T data;
    void setData(T data){
        this.data=data;
    }
    T getData(){return data;}
    void f(){System.out.println(data.getClass().getSimpleName()+"--->"+data);}
}

class DemoTest extends DemoType<DemoTest>{}
public class 自限定类型 {
    public static void main(String[] args) {
        DemoTest t1=new DemoTest() ,t2=new DemoTest();
        t1.setData(t2);
        t1.f();
        t1.setData(t1);
        t1.f();
        DemoTest data = t1.getData();
        data.f();
        System.out.println(data==t1);
        System.out.println(data==t2);
    }
}
