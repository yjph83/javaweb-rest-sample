package com.ch.cloud.demo.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:
 * 时间:2018-03-13 09:11
 *
 * @author:yjph83
 */
public class BeanMapper {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public BeanMapper() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        ArrayList destinationList = new ArrayList();
        Iterator it = sourceList.iterator();

        while(it.hasNext()) {
            Object sourceObject = it.next();
            Object destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    public static <T> PageInfo<T> mapPageInfo(Collection sourceList, Class<T> destinationClass) {
        Page destinationPage = new Page();
        copy(sourceList, destinationPage);
        destinationPage.clear();
        Iterator it = sourceList.iterator();

        while(it.hasNext()) {
            Object sourceObject = it.next();
            Object destinationObject = dozer.map(sourceObject, destinationClass);
            destinationPage.add(destinationObject);
        }

        return new PageInfo(destinationPage);
    }

    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

}
