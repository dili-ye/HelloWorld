package 容器_17;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

class MyCollection<T> extends ArrayList<T>{
    public MyCollection(Generator<T> generator,int size){
        for(int i=0;i<size;i++){
            add(generator.next());
        }
    }

    public static <T> MyCollection<T> list(Generator<T> gen,int size){
        return new MyCollection<T>(gen,size);
    }
}

interface Generator<T>{T next();}
class GovermentDemo implements Generator<String>{

    String[] strs=("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15").split(" ");

    private int index;

    @Override
    public String next() {
        return strs[index++];
    }
}
class pair<K,V>{
    public K key;
    public V value;
    public pair(K k,V v){
        key=k;
        value=v;
    }

    @Override
    public String toString() {
        return key+":"+value;
    }
}

public class Goverment{
    public static void main(String[] args) {
//        Set<String> set=new LinkedHashSet<String>(new MyCollection<String>(new GovermentDemo(),15));
//        set.addAll(MyCollection.list(new GovermentDemo(),15));
//        System.out.println(set);
//        char a='A';
//        pair<Integer, String> pair = new pair<>(1, a+++"");
//        System.out.println(pair);
//        System.out.println(a);
        Random rand=new Random(47);
        int i=7;
        while (i>=0){
            int i1 = rand.nextInt();
            System.out.println(i1);
            System.out.println((byte)i1);
            byte b=(byte)i1;
            System.out.println((1<<i)&b);
            i--;
        }
    }
}


