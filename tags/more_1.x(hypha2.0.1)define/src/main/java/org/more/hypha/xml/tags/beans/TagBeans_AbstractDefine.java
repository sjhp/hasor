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
package org.more.hypha.xml.tags.beans;
import java.util.Map;
import org.more.core.xml.XmlElementHook;
import org.more.core.xml.XmlStackDecorator;
import org.more.core.xml.stream.EndElementEvent;
import org.more.core.xml.stream.StartElementEvent;
import org.more.hypha.xml.XmlDefineResource;
import org.more.util.BeanUtil;
/**
 * bases�����ռ�������Ļ��࣬�ڸ����ж�����һЩ�����Եķ�����
 * @version : 2011-8-17
 * @author ������ (zyc@byshell.org)
 */
public abstract class TagBeans_AbstractDefine<T> extends TagBeans_NS implements XmlElementHook {
    /**����{@link TagBeans_AbstractDefine}����*/
    public TagBeans_AbstractDefine(XmlDefineResource configuration) {
        super(configuration);
    };
    /**���isPutAttribute��������true�����õ�{@link XmlStackDecorator}���Է�Χ�е��������ɸ÷���ȷ����*/
    protected abstract String getAttributeName();
    /**�����������Ͷ���*/
    protected abstract T createDefine(XmlStackDecorator<Object> context);
    /**
     * ÿ����ǩ��beginElement��ʼִ��ʱ���ᴴ��һ���Լ��Ķѣ�������endElement��������ʱ�����������ѡ�
     * �÷����ķ���ֵ����ȷ���Ƿ��Խ{@link XmlStackDecorator}�ĵ�ǰ��ȥ��һ����Ѱ��{@link T},Ĭ��ֵ��true��
     */
    protected boolean isSpanStack() {
        return true;
    };
    /**��ȡһ�����壬���û�о͵���createDefine������������*/
    protected final T getDefine(XmlStackDecorator<Object> context) {
        String defineName = this.getAttributeName();
        boolean spanStack = this.isSpanStack();
        T define = null;
        if (spanStack == true)
            define = (T) context.getAttribute(defineName);
        else
            define = (T) context.getSource().getAttribute(defineName);
        //
        if (define == null) {
            define = this.createDefine(context);
            context.setAttribute(defineName, define);
        }
        return define;
    };
    /**�÷�������һ��Map��Map��key������Ƕ��������������ԣ���Value�б�����Ƕ�Ӧ��XMLԪ����������*/
    protected abstract Map<Enum<?>, String> getPropertyMappings();
    /**��ʼ������ǩ�����а���������ӦBean�ͽ����������ԡ�*/
    public void beginElement(XmlStackDecorator<Object> context, String xpath, StartElementEvent event) {
        //        context.createStack();
        //1.��ȡDefine
        T define = this.getDefine(context);
        //2.����BeanDefine��ֵ
        Map<Enum<?>, String> propertys = this.getPropertyMappings();
        if (propertys == null)
            return;
        for (Enum<?> att : propertys.keySet()) {
            String definePropertyName = att.name();
            String xmlPropertyName = propertys.get(att);
            //
            String xmlPropertyValue_s = event.getAttributeValue(xmlPropertyName);
            Object xmlPropertyValue_o = this.getPropertyValue(define, att, xmlPropertyValue_s);
            if (xmlPropertyValue_o == null)
                continue;
            BeanUtil.writePropertyOrField(define, definePropertyName, xmlPropertyValue_o);
        }
    };
    /**
     * ��ȡ��ת��ָ������ö�ٵ�����ֵ��
     * @param define ��������
     * @param propertyEnum ����ö�١�
     * @param xmlValue ������XML�ж��������ֵ��
     * @return ��������ֵ��
     */
    protected Object getPropertyValue(T define, Enum<?> propertyEnum, String xmlValue) {
        return xmlValue;
    };
    /**����������ǩ��*/
    public void endElement(XmlStackDecorator<Object> context, String xpath, EndElementEvent event) {
        //        context.dropStack();
    };
}