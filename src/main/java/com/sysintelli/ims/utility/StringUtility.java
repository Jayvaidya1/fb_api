/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.utility;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author jay
 */
public class StringUtility {

    private static final Logger logger = Logger.getLogger(StringUtility.class.getName());

    /**
     * @Return TRUE if not valid
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * @Return TRUE if not valid
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String... str) {
        for (String s : str) {
            if (isNullOrEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static String emptyIfNull(String str) {
        return isNullOrEmpty(str) ? "" : str.trim();
    }

    /**
     * @Return true is valid
     * @param str
     * @return
     */
    public static boolean isValidString(String... str) {
        for (String s : str) {
            if (isNullOrEmpty(s) || "''".equals(s) || "' '".equals(s)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static boolean isStringLengthLessThan(Integer limit, String... str) {
        for (String s : str) {
            if (s.length() <= 1) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * for converting object to JSON string
     *
     * @param object
     * @return
     */
    public static String stringify(Object object) {
        ObjectMapper jackson = new ObjectMapper();
        jackson.setDateFormat(new SimpleDateFormat("dd MMM yyyy")); //This will convert long value(mili secs) to Date.
        jackson.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String json = null;
        try {
            json = jackson.writeValueAsString(object);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error while creating json: ", ex);
        }
        return json;
    }

}