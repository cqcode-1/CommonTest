package com.john.test.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class BDTest {

   public void test1(){
      //持仓数量
      BigDecimal quantityBD = null;
      //一手数量
      BigDecimal lotSizeBD = null;
      try {
         quantityBD = new BigDecimal(30);
         lotSizeBD = new BigDecimal(30);
      } catch (Exception e) {
         e.printStackTrace();
      }

      if(quantityBD != null && lotSizeBD != null){

         // 1/2 手 取四舍五入数
         BigDecimal centerBD = (lotSizeBD.compareTo(BigDecimal.ONE) == 0)
                 ? BigDecimal.ONE :
                 lotSizeBD.divide(new BigDecimal(2),0, RoundingMode.HALF_UP);

         String[] quantities = new String[4];
         for (int i = 0; i < 4; i++) {
            quantities[i] = calcuateQuantity(quantityBD, lotSizeBD, centerBD, i+1).toString();
         }

         for (String quantity : quantities) {
            System.out.println(quantity);
         }
      }
   }

   /**
    * 根据股数，一手股数，半手股数，和仓位比例来计算仓位比例的值。
    * 计算比例时余数如果大于半手数，则向上取整。小于则省略余数
    * @param quantityBD 股数
    * @param lotSizeBD 一手股数 。 美股没有一手数，最小为1
    * @param centerBD 一手股数的一半，四舍五入
    * @param num 仓位比例 2/3/4 分别代表"1/2","1/3", "1/4"
    * @return 返回 对应仓位比例需要返回的值
    */
   private BigDecimal calcuateQuantity(BigDecimal quantityBD, BigDecimal lotSizeBD, BigDecimal centerBD, int num) {
      BigDecimal resultBD = null;
      //股数除以num 取整
      BigDecimal bigDec = quantityBD.divide(new BigDecimal(num), 0, RoundingMode.FLOOR);
      //取整后，乘以一手数量，去到整数和余数
      BigDecimal[] bigDecS = bigDec.divideAndRemainder(lotSizeBD);
      //余数如果小于中位数，或者num==1（表示全仓时）省略余数
      if(bigDecS[1].compareTo(centerBD) <1 || num ==1){
         resultBD = bigDecS[0].multiply(lotSizeBD);
      }else{
         resultBD = bigDecS[0].multiply(lotSizeBD).add(lotSizeBD);
      }
      return resultBD;
   }

   
   public void test2(){
      String v = "0.283%";
      final BigDecimal divide = new BigDecimal(v.replace("%", "")).divide(new BigDecimal(100));
      System.out.println(divide);
   }
   
   public void test3(){
      DecimalFormat df = new DecimalFormat("###,###.###");
      try{
         String str = "10.010";
         System.out.println(df.format(new BigDecimal(str)));
      }catch(Exception e){
         e.printStackTrace();
      }
   }
   
   public void test4(){
      final BigDecimal add = new BigDecimal("46,100".replace(",","")).
              add(new BigDecimal("149,137.40".replace(",", "")));

      DecimalFormat df = new DecimalFormat("###,##0.00");
      try{
         System.out.println(df.format(add));
      }catch(Exception e){
         e.printStackTrace();
      }
      System.out.println(add);
   }
   
   public void test5(){
      CharSequence charSequence = "";
      final String replace = charSequence.toString().replace(",", "");
      System.out.println("replace : "+ replace);

      String str = null;
      String strRe = str.toString().replace(",", "");
      System.out.println("strRe: " + strRe);

   }
   
   public void test6(){
      final BigDecimal multiply = new BigDecimal("0.05");
      final BigDecimal multiply1 =  new BigDecimal("135.410");
      BigDecimal[] bigDecimals = multiply1
              .divideAndRemainder(multiply);
      System.out.println(Arrays.toString(bigDecimals));
      System.out.println(bigDecimals[1].compareTo(BigDecimal.ZERO) == 0);
   }
   
   public void test7(){
      final BigDecimal bigDecimal = new BigDecimal(1);
      System.out.println(bigDecimal);
      final BigDecimal add = bigDecimal.add(null);
      System.out.println(add);
   }
}
