package com.example.demo.test;

public class StringChange {
    public String name(){
        return getClass().getSimpleName();
    }
    public String change(String str){
        char[] chars = str.toCharArray();

        int length = chars.length - 1;
        for (int i = 0; i < length/2; i++) {
            char a = chars[i];
            chars[i] = chars[length -i];
            chars[length - i] = a;
        }
        StringBuilder builder = new StringBuilder();
        for (char a :
                chars) {
            builder.append(a);
        }
        return builder.toString();
    }

}

interface Processor{
    String name();
    Object process(Object input);
}
class StringAdapter implements Processor{
    StringChange sc;
    public StringAdapter(StringChange stringChange){
        sc = stringChange;
    }
    @Override
    public String name() {
        return sc.name();
    }
    @Override
    public Object process(Object input) {//关键部分，连接你的接口与提供的接口
        return sc.change((String) input);
    }
}
