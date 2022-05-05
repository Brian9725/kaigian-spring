package pers.brian.springframework.beans.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对FactoryBean的支持
 *
 * @author BrianHu
 * @create 2022-04-29 14:57
 **/
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * FactoryBean所创建的单例Bean缓存池
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);
}
