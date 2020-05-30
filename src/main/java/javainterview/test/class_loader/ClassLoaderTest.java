package javainterview.test.class_loader;

/**
 * JVM支持两种类型的类加载器
 * 1.引导类加载器(Bootstrap ClassLoader)    -----   C语言编写
 * 2.自定义加载器(继承与抽象类ClassLoader的类加载器)    -----   Java语言编写
 *      包括Extension CLassLoader(扩展类加载器)，System/App ClassLoader(系统/应用加载器)，
 *          User Defined ClassLoader(用户自定义加载器)
 *
 * 双亲委派机制
 * 优先委派给父类加载器去加载，如果父类加载器加载不了则依次给子类加载器加载
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        //系统类加载器  默认的类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d

        //加载/jre/lib/ext下的类
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//jdk.internal.loader.ClassLoaders$PlatformClassLoader@3551a94

        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);//null  加载java核心类库

        //classLoader为AppClassLoader@3fee733d
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        //classLoader为null  即bootStrapClassLoader
        ClassLoader stringClassLoader = String.class.getClassLoader();



    }


}
