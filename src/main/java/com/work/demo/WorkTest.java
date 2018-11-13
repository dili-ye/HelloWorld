package com.work.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkTest {
    public static void main(String[] args) throws IOException {
//        List<String> list=new ArrayList<>();
//        File file=new File("C:\\Users\\79013\\Desktop\\iot_query.txt");
//        BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
//        String line=bf.readLine();
//        while(line!=null){
//            list.add(line);
//            line=bf.readLine();
//        }
//        bf.close();
//        for (String str:list){
//            String [] strs=str.split("\t");
//            System.out.println(strs[0]);
//        }
//        System.out.println(list.size());
        int [] a1={1,2,5,6,3,2,8};
        int [] a2= Arrays.copyOf(a1,a1.length);
//        System.arraycopy(a1,0,a2,0,a1.length);
        int [] a3=a2.clone();
        for(int i=0;i<a2.length;i++){
            a2[i]++;
            a3[i]+=2;
        }
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
        System.out.println(Arrays.toString(a3));
    }

}
