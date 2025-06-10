package com.same.engine.platform.common.constant;

import java.util.HashSet;
import java.util.Set;

public class Constant {
    public static final String SPLITTER_COLON = ":";
    public static final String SPLITTER_CHINESE_COLON = "：";
    public static final String SPLITTER_COMMA = ",";
    public static final String SPLITTER_CHINESE_COMMA = "，";
    public static final String SPLITTER_DUN_HAO = "、";
    public static final String SPLITTER_SEMICOLON = ";";
    public static final String SPLITTER_SLASH = "/";
    public static final String SPLITTER_UNDER_LINE = "_";
    public static final String SPLITTER_HYPHEN = "-";
    public static final String SPLITTER_EMPTY = "";

    public static final String INSERT_METHOD_NAME = "insert";
    public static final String UPDATE_METHOD_NAME = "update";
    public static final String DELETE_METHOD_NAME = "delete";

    public static final String USER_NAME_REG = "%s(%s)";
    public static final String DEFAULT_ORDER_BY_COLS = "id DESC";


    public static final String TENCENT_ZIP_BUCKET = "zip-1251524319";
    public static final String TENCENT_ZIP_HOST = "https://zip-qc.xhscdn.com";
    public static final int CSV_MAX_ROW = 200;


    public static final String ISO_LOCAL_DATE_HYPHEN = "yyyy-MM-dd";
    public static final String ISO_LOCAL_DATE_TIME_HYPHEN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO_LOCAL_DATE_TIME_HYPHEN_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    public static final String ISO_LOCAL_DATE_SLASH = "yyyy/MM/dd";
    public static final String ISO_LOCAL_DATE_TIME_SLASH = "yyyy/MM/dd HH:mm:ss";
    public static final String ISO_LOCAL_DATE_TIME_SLASH_WITHOUT_SECONDS = "yyyy/MM/dd HH:mm";

    public static String RCM_DIRECT_STATE = "DIRECT_ACCESS";
    public static String RCM_AUDIT_STATE = "AUDIT_ACCESS";


    public static final Set<String> ONLINE_CONFIG_DEFAULT = new HashSet();
    public static final Set<String> OFFLINE_CONFIG_DEFAULT = new HashSet();
    static {
        ONLINE_CONFIG_DEFAULT.add("online");
        OFFLINE_CONFIG_DEFAULT.add("offline");
    }


}
