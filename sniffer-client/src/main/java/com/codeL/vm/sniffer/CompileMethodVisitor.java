package com.codeL.vm.sniffer;

import sun.jvm.hotspot.code.CodeBlob;
import sun.jvm.hotspot.code.CodeCacheVisitor;
import sun.jvm.hotspot.code.NMethod;
import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.oops.Method;

import java.util.Set;

public class CompileMethodVisitor implements CodeCacheVisitor {

    private final Set<MethodModel> methodModels;

    public CompileMethodVisitor(Set<MethodModel> methodModels) {
        this.methodModels = methodModels;
    }

    public void prologue(Address address, Address address1) {

    }

    public void visit(CodeBlob codeBlob) {
        if (codeBlob == null) {
            return;
        }
        NMethod nMethod = codeBlob.asNMethodOrNull();
        if (nMethod == null){
            return;
        }
        final Method method = nMethod.getMethod();
        if (method == null || method.isNative()) {
            return;
        }
        if (method.getInvocationCount() > 0) {
            MethodModel methodModel = MethodModel.covert(method);
            if (methodModel != null) {
                methodModels.add(methodModel);
            }

        }
    }

    public void epilogue() {

    }
}
