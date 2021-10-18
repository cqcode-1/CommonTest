package com.john.rxjava;

import org.junit.jupiter.api.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class ObserverTest {

   @Test
   public void test1(){
      //创建被观察者
      Observable<String> obs1 = Observable.create(new ObservableOnSubscribe<String>() {
         @Override
         public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
            e.onNext("我是1");
            e.onNext("我是2");
            e.onComplete();
         }
      });
      Observable<String> obs2 = Observable.create(new ObservableOnSubscribe<String>() {
         @Override
         public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
            e.onNext("我是A");
            e.onComplete();
         }
      });
      //创建观察者
      Consumer<String> consumer = new Consumer<String>() {
         @Override
         public void accept(String o) throws Exception {
            System.out.println("返回值" + o);
         }
      };
      //通过merge打包发送
      Observable.merge(obs1, obs2).subscribe(consumer);
   }


   @Test
   public void test02(){
      //创建被观察者
      Observable<String> obs1 = Observable.create(new ObservableOnSubscribe<String>() {
         @Override
         public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
            e.onNext("我是1");
            e.onNext("我是2");
            e.onComplete();
         }
      });
      Observable<String> obs2 = Observable.create(new ObservableOnSubscribe<String>() {
         @Override
         public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
            e.onNext("我是A");
            e.onNext("我是B");
            e.onNext("我是C");
            e.onComplete();
         }
      });
      //创建观察者
      Consumer<String> consumer = new Consumer<String>() {
         @Override
         public void accept(String o) throws Exception {
            System.out.println("返回值"+o);
         }
      };
      //通过zip打包
      Observable.zip(obs1, obs2, new BiFunction<String, String, String>() {
         @Override
         public String apply(@NonNull String s, @NonNull String s2) throws Exception {
            return s + s2;
         }
      }).subscribe(consumer);

   }

}
