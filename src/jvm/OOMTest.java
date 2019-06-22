package jvm;

import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static void main(String[] args) {
        try {
            URL url = new File("/Users/sherlockthao/Documents/ForWork/exercise/src/jvm").toURI().toURL();
            URL[] urls = {url};
            ClassLoadingMXBean loadingMXBean = ManagementFactory.getClassLoadingMXBean();
            List<ClassLoader> classLoaders = new ArrayList<>();
            while (true) {
                ClassLoader classLoader = new URLClassLoader(urls);
                classLoader.loadClass("ClassA");
                classLoaders.add(classLoader);
                System.out.println("total:" + loadingMXBean.getTotalLoadedClassCount());
                System.out.println("active:" + loadingMXBean.getLoadedClassCount());
                System.out.println("unloaded:" + loadingMXBean.getUnloadedClassCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
