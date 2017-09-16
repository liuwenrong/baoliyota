/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.java.reflect;

import com.java.Print;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * des:
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class FieldDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class c = Class.forName("com.java.reflect.Person");
        Field[] fs = c.getDeclaredFields();

//                Constructor declaredPersonConstructor = c.getDeclaredConstructor(new Class[]{Integer.class, String.class});
        Constructor declaredPersonConstructor = c.getDeclaredConstructor(int.class, String.class);

        Object personInstance = declaredPersonConstructor.newInstance(25, "刘文荣");

        Object person = c.newInstance();

        Field age = c.getDeclaredField("age");
        age.setAccessible(true);
        age.set(person, 25);

        Field name = c.getDeclaredField("name");
//        age.setAccessible(true);
        name.set(person, "小花花");

        //定义可变长的字符串，用来存储属性
        StringBuffer sb = new StringBuffer();
        //通过追加的方法，将每个属性拼接到此字符串中
        //最外边的public定义
        sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");
        //里边的每一个属性
        for (Field field : fs) {
            sb.append("\t");//空格
            sb.append(Modifier.toString(field.getModifiers()) + " ");//获得属性的修饰符，例如public，static等等
            sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字
            sb.append(field.getName() + ";\n");//属性的名字+回车
        }

        sb.append("}");

        System.out.println(sb);

        Print.out(fs, fs, person, personInstance);


    }


}
