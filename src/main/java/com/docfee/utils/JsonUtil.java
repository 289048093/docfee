package com.docfee.utils;

import com.docfee.vo.RecordVO;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * User: Jarvis.Lee
 * Date: 14-3-23
 * Time: 下午10:10
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static StringWriter writer = new StringWriter();
    static JsonGenerator generator = null;

    static {
        try {
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            generator = objectMapper.getJsonFactory().createJsonGenerator(writer);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    if (writer != null) IOUtil.closeQuietly(writer);
                    if (generator != null && !generator.isClosed()) {
                        IOUtil.closeQuietly(generator);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static <T> T parseJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T extends Collection<E>, E> T parseCollectionJson(String json, Class<T> collectionClazz, Class<E> beanClazz) throws IOException {
        List<Map<String, ?>> list = objectMapper.readValue(json, List.class);
        T res = null;
        try {
            res = collectionClazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new IllegalArgumentException(e);
        }
        for (Map m : list) {
            generator.writeObject(m);
            String tmp = writer.getBuffer().toString();
            E doc = objectMapper.readValue(tmp, beanClazz);
            res.add(doc);
            clearWriter();
        }
        return res;
    }

    public static <T> ArrayList<T> parseCollectionJson(String json, Class<T> t) throws IOException {
        return parseCollectionJson(json, ArrayList.class, t);
    }

    public static String toJson(Object obj) {
        clearWriter();
        String json = null;
        try {
            generator.writeObject(obj);
            json = writer.getBuffer().toString();
            clearWriter();;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return json;
    }

    private static void clearWriter() {
        writer.getBuffer().delete(0, writer.getBuffer().length());
    }

    public static <T> void main(String[] args) throws IOException, NoSuchMethodException {
        clearWriter();
        String s = "[{productId:1,doctorId:0,price:100.0,num:44},{productId:2,doctorId:0,price:60.0,num:41}]";
//        s = s.substring(0,1);
//        s = s.substring(0,s.length()-1);
//        String[] ss = s.split(",");
//        for(String e:ss){
//            DoctorEntity doc = parseJson(e,DoctorEntity.class);
//            System.out.println(doc);
//        }
        ArrayList<String> clz = new ArrayList<String>();
        List<RecordVO> res = parseCollectionJson(s, RecordVO.class);
        System.out.println(res);
        System.out.println(toJson(res));
        System.out.println(String.format("aaa%sbbb","oooo"));

    }
}
