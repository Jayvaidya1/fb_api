/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.utility;

import java.util.List;

/**
 *
 * @author jay
 */
public class ListUtility {

    /**
     * If null or empty then true O/W false
     *
     * @param list
     * @return
     */
    public  static boolean isNullOrEmpty(List list) {
        return (list == null || list.isEmpty());
    }

}
