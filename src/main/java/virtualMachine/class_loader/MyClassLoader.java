package virtualMachine.class_loader;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name)throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
        InputStream is = getClass().getResourceAsStream(fileName);
        if (null == is) {
            return super.loadClass(name);
        }
        try {
            byte[] b= new byte[is.available()];
            is.read(b);
            return defineClass(name,b,0,b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }

    }


}
