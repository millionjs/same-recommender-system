package com.same.engine.platform.common.constant;

import java.util.HashSet;
import java.util.Set;

public class SuperUserConstant {


    public static final Set<String> SUPER_USER = new HashSet();

    static {
        SUPER_USER.add("lianjing@million.dev");
    }

    public static void main(String[] args) {
        System.out.println(SuperUserConstant.SUPER_USER);
    }

}
