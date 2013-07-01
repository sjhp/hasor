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
package org.demo.webapps.safety;
import java.util.List;
import org.moreframework.context.AppContext;
import org.moreframework.security.Permission;
import org.moreframework.security.SecAccess;
import org.moreframework.security.SecurityAccess;
import org.moreframework.security.UserInfo;
/**
 * 
 * @version : 2013-4-28
 * @author ������ (zyc@byshell.org)
 */
@SecAccess(authSystem = "UserAuth")
public class UserSecurityAccess implements SecurityAccess {
    //
    @Override
    public void initAccess(AppContext appContext) {}
    @Override
    public List<Permission> loadPermission(UserInfo userInfo) {
        return null;
    }
    @Override
    public void destroyAccess(AppContext appContext) {}
}