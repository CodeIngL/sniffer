package com.codeL.vm.spoon;

import com.codeL.vm.sniffer.MethodModel;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.*;

import java.util.*;

public class MethodModelProcessor extends AbstractProcessor<CtNamedElement> {

    private List<MethodModel> result = new ArrayList<>();

    public void process(final CtNamedElement ctNamedElement) {
        if (ctNamedElement instanceof CtType) {
            final CtType current = (CtType) ctNamedElement;
            CtPackage ctPackage = getaPackage((CtType) ctNamedElement);
            if (ctPackage == null){
                return;
            }
            String packageName = ctPackage.getQualifiedName();
            Set<CtMethod<?>> methods =  current.getAllMethods();
            for (CtMethod ctMethod:methods){
                CtElement ctElement = ctMethod.getParent();
                if (ctElement instanceof CtClass){
                    CtPackage ctPackage1 = getaPackage((CtType) ctElement);
                    if (ctPackage1 == null){
                        continue;
                    }
                    if (ctPackage1.getQualifiedName().startsWith("java")){
                        continue;
                    }
                }
                MethodModel methodModel = new MethodModel();
                methodModel.setcName(packageName+"."+ctNamedElement.getSimpleName());
                methodModel.setmName(ctMethod.getSimpleName());
                methodModel.setParameters(ctMethod.getSignature());
                result.add(methodModel);
            }
        }
    }

    private CtPackage getaPackage(CtType ctNamedElement) {
        return ctNamedElement.getPackage();
    }

    @Override
    public void processingDone() {
        super.processingDone();
        processingResult();
    }

    private void processingResult() {
        for (MethodModel methodModel:result){
            System.out.println(methodModel);
        }
    }


}
