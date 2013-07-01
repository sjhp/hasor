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
package org.moreframework.security.support.impl;
import static org.moreframework.security.SecurityConfig.Security_AuthSessionCache;
import static org.moreframework.security.SecurityConfig.Security_AuthSessionTimeout;
import org.moreframework.context.AppContext;
import org.moreframework.context.SettingListener;
import org.moreframework.context.Settings;
import org.moreframework.event.EventManager;
import org.moreframework.icache.Cache;
import org.moreframework.icache.CacheManager;
import org.moreframework.security.support.AbstractSecurityContext;
import org.moreframework.security.support.SessionData;
import com.google.inject.Singleton;
/**
 * ����SecurityContext��ʵ��
 * @version : 2013-4-20
 * @author ������ (zyc@byshell.org)
 */
@Singleton
public class InternalSecurityContext extends AbstractSecurityContext {
    private Cache<SessionData> authSessionCache = null;
    private SettingListener    settingListener  = new SessionDataCacheSettingListener();
    private long               sessionTimeOut   = 0;
    //
    //
    @Override
    public synchronized void initSecurity(AppContext appContext) {
        super.initSecurity(appContext);
        this.settingListener.loadConfig(appContext.getSettings());
        appContext.getSettings().addSettingsListener(settingListener);
    }
    @Override
    public synchronized void destroySecurity(AppContext appContext) {
        super.destroySecurity(appContext);
        appContext.getSettings().removeSettingsListener(settingListener);
    }
    @Override
    protected void removeSessionData(String sessionDataID) {
        this.authSessionCache.remove(sessionDataID);
    }
    @Override
    protected void updateSessionData(String sessionID) {
        this.authSessionCache.refreshCache(sessionID);
    }
    @Override
    protected void updateSessionData(String sessionDataID, SessionData newSessionData) {
        this.authSessionCache.toCache(sessionDataID, newSessionData, this.sessionTimeOut);
    }
    @Override
    protected SessionData getSessionData(String sessionDataID) {
        return this.authSessionCache.fromCache(sessionDataID);
    }
    private EventManager eventManager = null;
    @Override
    protected void throwEvent(String eventType, Object... objects) {
        if (this.eventManager == null)
            this.eventManager = this.getAppContext().getInstance(EventManager.class);
        this.eventManager.throwEvent(eventType, objects);
    }
    /**�������SessionDataCache���������ļ��Ķ�����Ч*/
    class SessionDataCacheSettingListener implements SettingListener {
        @Override
        public void loadConfig(Settings newConfig) {
            String cacheName = newConfig.getString(Security_AuthSessionCache);
            CacheManager cacheManager = getAppContext().getInstance(CacheManager.class);
            authSessionCache = cacheManager.getCache(cacheName);
            if (authSessionCache == null)
                throw new NullPointerException("not load AuthSessionCache ��" + cacheName + "��");
            sessionTimeOut = newConfig.getLong(Security_AuthSessionTimeout);
        }
    }
}