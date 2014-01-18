/*
 * Copyright 2008-2009 the original author or authors.
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
package net.test.simple._06_guice;
import com.google.inject.Binder;
import com.google.inject.Module;
/**
 * 这是一个简单的 Guice Module
 * @version : 2014-1-11
 * @author 赵永春 (zyc@byshell.org)
 */
public class GuiceMod2 implements Module {
    public void configure(Binder binder) {
        System.out.println("Hello guice , bind Integet to 123");
        binder.bind(String.class).toInstance("这是一个简单的测试程序，用来测试 Guice 原本的功能。");
    }
}