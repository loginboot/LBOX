package cn.ryan.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * @author ryan.cn
 * @creator ryan
 * @version 1.0.0
 * @description Ryan主要多語言实现类
 *
 */
public class RyanLangUtil {

    /**
     * language 资源
     */
    private static final ResourceBundle resource;

    static {
        resource = ResourceBundle.getBundle("i18n.msg", Locale.getDefault());
    }

    /**
     * 返回語言對象
     * @return
     */
    public static ResourceBundle getResBundle() {
        return resource;
    }

    /**
     * 私有构造函数
     */
    private RyanLangUtil() {

    }

    /**
     * Gets a string from the ResourceBundles.
     * <br> Convenience method to save casting.
     *
     * @param key the key of the properties.
     * @return the value of the property. Return the key if the value is not found.
     */
    public static String getMsgByCode(String key) {
        try {
            return resource.getString(key);
        } catch (Exception e) {
            return key;
        }
    }

    /**
     * Gets the integer from the properties.
     *
     * @param key the key of the property.
     *
     * @return the value of the key. return -1 if the value is not found.
     */
    public static Integer getIntegerValue(String key) {
        try {
            return Integer.valueOf(resource.getString(key));
        } catch (MissingResourceException e) {
            return new Integer(-1);
        }
    }

}
