package com.sitech.cloudide.ssh;

/**
 * 
 * <p>Title: BaseConstant</p>
 * <p>Description: 常量</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 思特奇 </p>
 * @author heweia
 * @version 1.0
 * @createtime 2019-4-8 下午5:25:55
 *
 */
public class BaseConstant {

    /** mill seconds of one day */
    public static long MILLISECONDS_OF_ONE_SECOND = 1000;

    /** mill seconds of one day */
    public static long MILLISECONDS_OF_ONE_MINUTE = 1000 * 60;

    /** mill seconds of one day */
    public static long MILLISECONDS_OF_ONE_DAY = 1000 * 60 * 60 * 24;

    /** mill seconds of one hour */
    public static long MILLISECONDS_OF_ONE_HOUR = 1000 * 60 * 60;

    /**
     * WORD_SEPARATOR ( char )2
     */
    public static final String WORD_SEPARATOR = Character.toString((char) 2);

    public static final String SYSTEM_PROPERTY_CONFIG_FILE_PATH = "configFilePath";
    
	
    public static String DEFAULT_USERNAME = "idcs";
    public static String USERNAME = DEFAULT_USERNAME;

    public static String DEFAULT_PASSWORD = "idcs";
    public static String PASSWORD = DEFAULT_PASSWORD;

    public static int DEFAULT_SSH_PORT_DEFAULT = 22;
    public static int SSH_PORT_DEFAULT = DEFAULT_SSH_PORT_DEFAULT;

    public static final String COMMA = ",";

}
