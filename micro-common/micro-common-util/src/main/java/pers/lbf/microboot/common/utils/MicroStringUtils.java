/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package pers.lbf.microboot.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * 字符串工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/8 23:32
 */
public class MicroStringUtils extends StringUtils {

    private MicroStringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(Collection<?> strList) {
        return isNull(strList) || strList.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     * empty
     *
     * @param str     指定字符串
     * @param strList 指定字符串列表
     * @return flag
     */
    public static boolean matches(String str, Collection<String> strList) {

        if (isEmpty(str) || isEmpty(strList)) {
            return false;
        }

        for (String s : strList) {
            if (str.matches(s)) {
                return true;
            }
        }

        return false;

    }


}
