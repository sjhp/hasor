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
import java.util.List;
/**
 * Hasor的核心接口，它为应用程序提供了一个统一的配置界面和运行环境。
 * @version : 2013-3-26
 * @author 赵永春 (zyc@hasor.net)
 */
public interface AppContext {
    /**获取环境接口。*/
    public Environment getEnvironment();
    /**模块启动通知*/
    public void start(Module... modules) throws Throwable;
    /**是否启动*/
    public boolean isStart();
    /**发送停止通知*/
    public void shutdown();
    //
    /*---------------------------------------------------------------------------------------Bean*/
    /**通过名获取Bean的类型。*/
    public Class<?> getBeanType(String bindID);
    /**获取已经注册的Bean名称。*/
    public String[] getBindIDs();
    /**如果存在目标类型的Bean则返回Bean的名称。*/
    public String[] getNames(Class<?> targetClass);
    /**判断是否存在某个ID的绑定。*/
    public boolean containsBindID(String bindID);
    /**创建Bean。*/
    public <T> Provider<T> getProvider(BindInfo<T> info);
    /**创建Bean。*/
    public <T> BindInfo<T> getBindInfo(String bindID);
    /**创建Bean。*/
    public <T> T getInstance(String bindID);
    /**创建Bean。*/
    public <T> T getInstance(Class<T> targetClass);
    /**创建Bean。*/
    public <T> T getInstance(BindInfo<T> info);
    //
    /*-------------------------------------------------------------------------------------Binder*/
    /**通过一个类型获取所有绑定到该类型的上的对象实例。*/
    public <T> List<T> findBindingBean(Class<T> bindType);
    /**通过一个类型获取所有绑定到该类型的上的对象实例。*/
    public <T> List<Provider<T>> findBindingProvider(Class<T> bindType);
    /**通过一个类型获取所有绑定到该类型的上的对象实例。*/
    public <T> T findBindingBean(String withName, Class<T> bindType);
    /**通过一个类型获取所有绑定到该类型的上的对象实例。*/
    public <T> Provider<T> findBindingProvider(String withName, Class<T> bindType);
    /**通过一个类型获取所有绑定到该类型的上的对象实例。*/
    public <T> List<BindInfo<T>> findBindingRegister(Class<T> bindType);
    /**通过一个类型获取所有绑定到该类型的上的对象实例。*/
    public <T> BindInfo<T> findBindingRegister(String withName, Class<T> bindType);
}