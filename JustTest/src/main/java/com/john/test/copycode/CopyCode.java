package com.john.test.copycode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class CopyCode {

   /**
    *    代码拷贝
    */
   
   public void  test04() throws Exception{
      //需要读取的文件，根路径
      String rootPath= "E:/wlb_new200731/Master/WingLungBank_app/src/";
      //需要读取的文件，文件中是每个文件的相对路径
      File file = new File("D://file.txt");
      System.out.println(file.length());
      //拷贝目的文件夹
      File fileDir = new File("D://change");
      final boolean delete = fileDir.delete();
      fileDir.mkdirs();
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      int count=0;
      int failCount= 0;
      while ((line  = bufferedReader.readLine()) != null){
         File file1 = new File(rootPath+line.trim());
         if(!file1.exists()){
            System.out.println(line);
            failCount ++;
            continue;
         }else{
            count ++;
         }
         System.out.println(file1.getName());
         FileInputStream fileInputStream = new FileInputStream(file1);
         String outputName = fileDir+"/" + file1.getName();
         FileOutputStream fileOutputStream = new FileOutputStream(outputName);
         byte[] bytes = new byte[2048];
         int read;
         while((read = fileInputStream.read(bytes)) > 0){
            fileOutputStream.write(bytes,0, read);
         }
      }
      System.out.println("成功拷贝文件： "+count);
      System.out.println("失败： "+failCount);
   }
}
