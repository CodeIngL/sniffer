package com.codeL.vm.sniffer;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static java.io.File.pathSeparator;

public class SnifferSMain {
    public static void main(String[] args) throws MalformedURLException {
        String path = System.getProperty("sun.boot.class.path");
        String[] jars = path.split(pathSeparator);
        String goal = null;
        for (String str : jars) {
            if (str.endsWith(File.separator + "rt.jar")) {
                goal = str;
                break;
            }
        }
        String jarPath = null;
        if (goal != null) {
            File file = new File(goal);
            if (file.exists()) {
                File o = file.getParentFile();
                if (o.exists()) {
                    File t = o.getParentFile();
                    if (t.exists()) {
                        File th = t.getParentFile();
                        if (th.exists()) {
                            String a = th.getAbsolutePath();
                            a = a + File.separator + "lib" + File.separator + "sa-jdi.jar";
                            jarPath = a;
                        }
                    }
                }
            }
        }
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = null;
        if (jarPath != null) {
            File file = new File(jarPath);
            if (file.exists()) {
                File file1 = new File(SnifferSMain.class.getProtectionDomain().getCodeSource().getLocation().getPath());
                classLoader = new URLClassLoader(new URL[]{file.toURL(), file1.toURL()}, null);
            }
        }
        if (classLoader != null) {
            Thread.currentThread().setContextClassLoader(classLoader);
        }
        try {
            Class cls = classLoader.loadClass("com.codeL.vm.sniffer.SnifferMain");
            cls.getMethod("main", String[].class).invoke(null, (Object) args);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (classLoader != null) {
            Thread.currentThread().setContextClassLoader(old);
        }
    }
}
