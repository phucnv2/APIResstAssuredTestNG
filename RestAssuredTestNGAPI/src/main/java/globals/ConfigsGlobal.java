package globals;

import helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String URI = PropertiesHelper.getValue("URI");
    public static String PATH = PropertiesHelper.getValue("URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String accept = PropertiesHelper.getValue("accept");
    public static String contentType = PropertiesHelper.getValue("contentType");
    public static String messageWrongMethod = PropertiesHelper.getValue("messageWrongMethod");
}
