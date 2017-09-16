package com.java;

/**
 * des: 泛型示例
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class Print<T> {
    private T data;

    public void printT(T t) {
        System.out.println(t.toString());
        this.data = t;
    }

    private static void print(Object object) {
        System.out.println(object);
    }

    public static <E> void printArr(E[] eArray) {

        for (int i=0; i<eArray.length; i++) {
            print(eArray[i]);
        }
    }

    public static <E> void out(E... args) {
        for (E e : args) {
            System.out.println(e);
        }
    }

    public void add(T t) {

    }

    public <E> void add(T t, E e) {

        System.out.println(t.toString());
        System.out.println(e.toString());

    }

    /**
     * 往数组写一个对象
     * @param e
     * @param eArray
     */
//    public void write(T e, T[] eArray) {
        public <E> void write(E e, E[] eArray, E[] newArray) {

       print(eArray);
        printArr(eArray);

//        E[] newArray = e.getClass().getType
//        E[] newArray = new E[eArray.length + 1];
        System.arraycopy(eArray, 0, newArray, 0, eArray.length);
        newArray[eArray.length] = e;

        print(newArray);
        printArr(newArray);

    }

    public T getData() {
        return data;
    }

    public <E> void printE(E e) {

        System.out.println(e.toString());

    }


    public static void main(String[] args) {

        Print<String> printStr = new Print<>();

        String[] eArray = {"1", "2"};
        printStr.write("write" , eArray, new String[eArray.length + 1]);

        printStr.out("哈哈", "呵呵", 123, "小花花");

        printStr.printT("hello World!");
        printStr.printE(Integer.valueOf("15"));


    }


}

class List<E> {
    public void add(E e) {

    }

    public <T> void addOthers(T t) {

    }
}
