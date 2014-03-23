package com.docfee.utils;

import com.docfee.controller.DoctorController;
import com.docfee.entity.DoctorEntity;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 下午10:10
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parseJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T extends Collection<E>, E> T parseCollectionJson(String json, Class<T> collectionClazz, Class<E> beanClazz) throws IOException {
        T res = null;
        try {
            res = collectionClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
        Collection<Map<String, ?>> col = parseJson(json, Collection.class);
        Map<String, ?> tmp = null;
        for (Map<String, ?> map : col) {
            E bean = null;
            try {
                bean = beanClazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            for (Map.Entry<String, ?> e : map.entrySet()) {
                String key = e.getKey();
                Object value = e.getValue();
                String ms = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                try {
                    Method method = beanClazz.getMethod(ms, e.getValue().getClass());
                    method.invoke(bean, value);
                    res.add(bean);
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException {
        String s = "[{\"id\":111,\"name\":\"tom\"},{\"id\":321,\"name\":\"jarvis\"}]";
        List<DoctorEntity> ds = null;
//        ds = parseCollectionJson(s, ArrayList.class, DoctorEntity.class);
//        Iterator it = ds.get(0)
//        it.next();
        System.out.println(ds);
        DoctorEntity doc = new DoctorEntity() ;
        System.out.println(DoctorEntity.class.getDeclaredMethod("setId"));
        System.out.println("abcd".substring(0, 1));
    }
}
