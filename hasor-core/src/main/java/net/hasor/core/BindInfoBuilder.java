/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.core;
/**
 * 用于配置 bean 信息。
 * @version : 2014年7月2日
 * @author 赵永春(zyc@hasor.net)
 */
public interface BindInfoBuilder<T> {
    /**为绑定设置ID。*/
    public void setBindID(String newID);
    /**为类型绑定一个名称。*/
    public void setBindName(String bindName);
    /**为类型绑定一个实现，当获取类型实例时其实获取的是实现对象。*/
    public void setSourceType(Class<? extends T> sourceType);
    /**设置元信息。*/
    public void setMetaData(String key, Object value);
    /**标记为单例。*/
    public void setSingleton(boolean singleton);
    /**为类型绑定一个{@link Provider}。*/
    public void setCustomerProvider(Provider<T> customerProvider);
    /**将类型发布到一个固定的命名空间内。*/
    public void setScopeProvider(Provider<Scope> scopeProvider);
    //
    /**设置构造参数。*/
    public void setInitParam(int index, Class<?> paramType, Provider<?> valueProvider);
    /**设置构造参数。*/
    public void setInitParam(int index, Class<?> paramType, BindInfo<?> valueInfo);
    /**添加依赖注入。*/
    public void addInject(String property, Provider<?> valueProvider);
    /**添加依赖注入。*/
    public void addInject(String property, BindInfo<?> valueInfo);
    //
    public BindInfo<T> toInfo();
}