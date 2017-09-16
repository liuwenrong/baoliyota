/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.java.reflect;

import com.java.Print;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * des:
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class MethodDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class classPerson = Class.forName("com.java.reflect.Person");
        Constructor declaredPersonConstructor = classPerson.getDeclaredConstructor(new Class[]{int.class, String.class});

        Method[] declaredMethods = classPerson.getDeclaredMethods();

        Print.printArr(declaredMethods);

        Object personInstance = declaredPersonConstructor.newInstance(25, "小花花");

        Method getAgeMethod = classPerson.getDeclaredMethod("getAge", new Class[]{});

        Object getAgeReturn = getAgeMethod.invoke(personInstance, null);

        Method getNameMethod = classPerson.getDeclaredMethod("getName", null);
        Object getNameReturn = getNameMethod.invoke(personInstance, null);

        Method setNameMethod = classPerson.getMethod("setName", String.class);
        Object setNameReturn = setNameMethod.invoke(personInstance, "刘文荣");

        Object getNameReturnAfterModify = getNameMethod.invoke(personInstance, null);

        Print.out(getAgeReturn, getNameReturn, setNameReturn, getNameReturnAfterModify);


    }

}
