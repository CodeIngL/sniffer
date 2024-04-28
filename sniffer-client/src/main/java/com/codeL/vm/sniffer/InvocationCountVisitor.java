package com.codeL.vm.sniffer;

import sun.jvm.hotspot.memory.SystemDictionary;
import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.oops.Klass;
import sun.jvm.hotspot.oops.Method;
import sun.jvm.hotspot.utilities.MethodArray;

import java.io.File;
import java.util.Set;

public class InvocationCountVisitor implements SystemDictionary.ClassVisitor {

    private final Set<MethodModel> methodModels;

    public InvocationCountVisitor(Set<MethodModel> methodModels) {
        this.methodModels = methodModels;
    }

    public void visit(Klass klass) {
        if (klass instanceof InstanceKlass) {
            final MethodArray methods = ((InstanceKlass) klass).getMethods();
            for (int i = 0; i < methods.length(); i++) {
                final Method method = methods.at(i);
                if (method.getInvocationCount() > 0) {
                    MethodModel methodModel = MethodModel.covert(method);
                    if (methodModel != null) {
                        methodModels.add(methodModel);
                    }
                }
            }
        }
    }
}
