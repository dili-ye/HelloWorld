package 多线程;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class NumGenerator<claszs> {
    private volatile boolean cancel =false;
    public abstract int[] next();
    public void cancel(){
        cancel=true;
    }
    public boolean isCancel(){
        return cancel;
    }
}

class Numchecker implements Runnable{
    private NumGenerator generator;
    private final int id;

    public Numchecker(NumGenerator gr ,int id){
        this.generator=gr;
        this.id=id;
    }
    @Override
    public void run() {
        System.out.println("Testing......");
        while (!generator.isCancel()){
            int[] range=generator.next();
            if(range[0]>range[1]){
                System.out.println("ERROR ...id :" +id+" min: "+range[0]+"  > max: "+range[1]);
                generator.cancel();
            }
        }
    }

    public static void test(NumGenerator gen,int count){
        System.out.println("----------");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<count;i++){
            executorService.execute(new Numchecker(gen,i));
        }
        executorService.shutdown();
    }

    public static void test(NumGenerator gen){
        test(gen,10);
    }

}
public class ThreadDemo  extends NumGenerator{
    private int min=0;
    private int max=0;
    private int []range={min,max};
    private Random random=new Random();

    @Override
    public int[] next() {
        min=random.nextInt(100);
        max=random.nextInt(100);
        Thread.yield();
        if(min>max)
            max=min;
        int [] ia={min,max};
        return ia;
    }
//    public synchronized int[] next() {
//        min=random.nextInt(100);
//        max=random.nextInt(100);
//        Thread.yield();
//        if(min>max)
//            max=min;
//        int [] ia={min,max};
//        return ia;
//    }

    public static void main(String[] args) {
        Numchecker.test(new ThreadDemo());
    }


}
