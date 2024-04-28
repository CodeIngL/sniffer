package com.codeL.vm.sniffer;

import org.objectweb.asm.Type;
import sun.jvm.hotspot.oops.Method;


public class MethodModel {

    private String cName;

    private String mName;

    private String parameters;

    long count;

    int lineNumber;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public static MethodModel covert(Method method) {
        if (method == null) {
            return null;
        }
        MethodModel methodModel = new MethodModel();
        String klassName = method.getMethodHolder().getName().asString();
        if (klassName.startsWith("sun") || klassName.startsWith("java") ||
                klassName.startsWith("com/i") || klassName.startsWith("jdk")) {
            return null;
        }
        klassName = klassName.replace('/', '.');
        methodModel.setcName(klassName);
        methodModel.setmName(method.getName().asString());
        methodModel.setParameters(covert(method.getSignature().asString()));
        if (method.hasLineNumberTable()) {
            methodModel.setLineNumber(method.getLineNumberTable()[0].getLineNumber());
        }
        methodModel.setCount(method.getInvocationCount() >> 3);
        return methodModel;
    }


    public static String covert(String sign) {
        if (sign == null || "".equals(sign)) {
            return null;
        }
        Type[] argumentTypes = Type.getArgumentTypes(sign);
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        if (argumentTypes != null && argumentTypes.length > 0) {
            for (int i = 0; i < argumentTypes.length; i++) {
                builder.append(argumentTypes[i].getClassName());
                if (i != (argumentTypes.length - 1)) {
                    builder.append(",");
                }

            }
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "MethodModel{" +
                "cName='" + cName + '\'' +
                ", mName='" + mName + '\'' +
                ", parameters='" + parameters + '\'' +
                ", count=" + count +
                ", lineNumber=" + lineNumber +
                '}';
    }

}
