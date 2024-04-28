package com.codeL.vm.sniffer;

import sun.jvm.hotspot.code.CodeCache;
import sun.jvm.hotspot.debugger.AddressException;
import sun.jvm.hotspot.memory.SystemDictionary;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.Tool;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import static java.io.File.pathSeparator;

public class SnifferMain extends Tool {

    public static void main(String[] args) throws IOException {
        SnifferMain snifferMain = new SnifferMain();
        snifferMain.execute(args);
    }


    public void run() {
        Set<MethodModel> methodModels = new HashSet<MethodModel>();
        try {
            SystemDictionary dict = VM.getVM().getSystemDictionary();
            dict.allClassesDo(new InvocationCountVisitor(methodModels));
            Thread.sleep(1L);
            CodeCache codeCache = VM.getVM().getCodeCache();
            codeCache.iterate(new CompileMethodVisitor(methodModels));
        } catch (AddressException addressException) {
            System.err.println("Error accessing address 0x" + Long.toHexString(addressException.getAddress()));
            addressException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processorReulst(methodModels);
    }


    private void processorReulst(Set<MethodModel> methodModels) {
        for (MethodModel methodModel : methodModels) {
            System.out.println(methodModel);
        }
    }


}
