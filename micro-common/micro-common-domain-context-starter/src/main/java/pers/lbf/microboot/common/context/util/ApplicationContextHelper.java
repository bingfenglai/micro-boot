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
package pers.lbf.microboot.common.context.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


/**
 * spring 上下文工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/23 12:12
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Spring在bean初始化后会判断是不是ApplicationContextAware的子类
     * 如果该类是,setApplicationContext()方法,会将容器中ApplicationContext作为参数传入进去
     *
     * @param applicationContext spring上下文
     * @return void
     * @throws BeansException e
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2020/12/23 12:14
     */
    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    /**
     * 通过Name返回指定的Bean
     *
     * @param targetBeanClass
     * @param <T>
     * @return bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> targetBeanClass) {
        T beanInstance = null;
        //优先按type查
        try {
            beanInstance = applicationContext.getBean(targetBeanClass);
        } catch (Exception ignored) {
        }
        //按name查
        if (beanInstance == null) {
            String simpleName = targetBeanClass.getSimpleName();
            //首字母小写
            simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            try {
                beanInstance = (T) ApplicationContextHelper.applicationContext.getBean(simpleName);
            } catch (Exception ignored) {
            }


        }
        return beanInstance;

    }

    public static Object getBean(String beanName) {

        return ApplicationContextHelper.applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return ApplicationContextHelper.applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType, Object... params) {
        return ApplicationContextHelper.applicationContext.getBean(requiredType, params);
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void publishEvent(ApplicationEvent event) {
        ApplicationContextHelper.applicationContext.publishEvent(event);
    }

}
