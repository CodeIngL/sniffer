package com.codeL.vm.spoon;

import spoon.Launcher;

public class SMain {

    public static void main(String[] args) {
        String[] newArgs = new String[args.length + 4];
        for (int i = 0; i < args.length; i++) {
            newArgs[i] = args[i];
        }
        newArgs[args.length + 0] = "-p";
        newArgs[args.length + 1] = "com.codeL.vm.spoon.MethodModelProcessor";
        newArgs[args.length + 2] = "--output-type";
        newArgs[args.length + 3] = "nooutput";
        Launcher.main(newArgs);
    }
}
