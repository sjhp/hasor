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
package org.more.webui.components.ajaxform;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.more.core.event.Event;
import org.more.core.event.Event.Sequence;
import org.more.core.event.EventListener;
import org.more.webui.context.ViewContext;
import org.more.webui.support.UICom;
import org.more.webui.support.UIComponent;
import org.more.webui.support.UIInput;
import org.more.webui.support.values.MethodExpression;
/**
 * ajax方式请求的表单
 * @version : 2012-5-15
 * @author 赵永春 (zyc@byshell.org)
 */
@UICom(tagName = "ui_AjaxForm")
public class AjaxForm extends UIComponent {
    /**通用属性表*/
    public enum Propertys {
        /**Action动作（RW）*/
        submitEL,
    }
    @Override
    public String getComponentType() {
        return "ui_AjaxForm";
    }
    @Override
    protected void initUIComponent(ViewContext viewContext) {
        super.initUIComponent(viewContext);
        this.getEventManager().addEventListener(AjaxForm_Event_OnSubmit.SubmitEvent, new AjaxForm_Event_OnSubmit());
        this.setProperty(Propertys.submitEL.name(), null);
    }
    /**获取form EL字符串*/
    public String getSubmitEL() {
        return this.getProperty(Propertys.submitEL.name()).valueTo(String.class);
    }
    /**设置form EL字符串*/
    public void setSubmitEL(String submitEL) {
        this.getProperty(Propertys.submitEL.name()).value(submitEL);
    }
    public MethodExpression getSubmitExpression() {
        String actionString = this.getSubmitEL();
        if (actionString == null || actionString.equals("")) {} else
            return new MethodExpression(actionString);
        return null;
    }
    /**表单中的数据*/
    public Map<String, Object> getFormData() {
        Map<String, UIInput> comDataMap = this.getFormDataAsCom();
        Map<String, Object> formData = new HashMap<String, Object>();
        for (Entry<String, UIInput> ent : comDataMap.entrySet())
            formData.put(ent.getKey(), ent.getValue().getValue());
        return formData;
    }
    /**表单中的数据组建对象*/
    public Map<String, UIInput> getFormDataAsCom() {
        Map<String, UIInput> comDataMap = new HashMap<String, UIInput>();
        for (UIComponent com : this.getChildren())
            if (com instanceof UIInput == true) {
                UIInput input = (UIInput) com;
                comDataMap.put(input.getName(), input);
            }
        return comDataMap;
    }
}
/**负责处理OnSubmit事件的EL调用*/
class AjaxForm_Event_OnSubmit implements EventListener {
    public static Event SubmitEvent = Event.getEvent("OnSubmit");
    @Override
    public void onEvent(Event event, Sequence sequence) throws Throwable {
        AjaxForm component = (AjaxForm) sequence.getParams()[0];
        ViewContext viewContext = (ViewContext) sequence.getParams()[1];
        MethodExpression e = component.getSubmitExpression();
        if (e != null)
            viewContext.sendAjaxData(e.execute(component, viewContext));
    }
};