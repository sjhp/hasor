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
package org.test.more.submit;
import org.more.hypha.anno.define.Bean;
import org.more.submit.ActionContext;
import org.more.submit.ActionContextBuilder;
import org.more.submit.ActionInvoke;
import org.more.submit.acs.simple.ACBuilder;
import org.more.submit.impl.AbstractAC;
import org.more.util.Config;
/**
 * 
 * @version : 2011-7-14
 * @author ������ (zyc@byshell.org)
 */
@Bean
@ACBuilder
public class ACB_Bean implements ActionContextBuilder {
    public void init(Config<?> config) {
        // TODO Auto-generated method stub
    }
    public ActionContext builder() {
        return new AbstractAC() {
            public ActionInvoke getAction(String name, String userInfo) {
                System.out.println("ns=b , action=" + name);
                return super.getAction(name, userInfo);
            }
        };
    }
    public String getPrefix() {
        // TODO Auto-generated method stub
        return "b";
    }
}