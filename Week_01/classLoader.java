package work;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> aClass = new HelloClassLoader().findClass("Hello");
            Object obj = aClass.newInstance();
            Method method = aClass.getMethod("hello");
            method.invoke(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        File f = new File(this.getClass().getResource("/work/Hello.xlass").getPath());
        int length = (int) f.length();
        byte[] bytes = new byte[length];
        try {
            new FileInputStream(f).read(bytes);
        }catch (IOException e){
            e.printStackTrace();
            return super.findClass(name);
        }
        for(int i=0;i< bytes.length; i++){
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name,bytes,0,bytes.length);
    }

}