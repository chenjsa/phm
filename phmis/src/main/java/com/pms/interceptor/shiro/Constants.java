package com.pms.interceptor.shiro;

public class Constants {
	public static final String PARAM_DIGEST = "digest";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_ID = "id";

    public static final String LOGIN_TOKEN_CACHE_NAME = "logintoken";

    //摘要，用来加密生成token
    public static String KEY="qwertyasdfoimdfdk";

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
}
