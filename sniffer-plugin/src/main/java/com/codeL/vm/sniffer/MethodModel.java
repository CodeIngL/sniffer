package com.codeL.vm.sniffer;



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
