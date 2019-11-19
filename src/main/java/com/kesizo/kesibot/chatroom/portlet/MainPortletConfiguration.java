package com.kesizo.kesibot.chatroom.portlet;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "MainPortletConfiguration")
public interface MainPortletConfiguration {
    @Meta.AD(
            deflt = "rasa",
            required = false
        )
    public String chatbotProvider();
    
    @Meta.AD(
            deflt = "http://localhost:5005/webhooks/rest/webhook",
            required = false
        )
    public String chatbotUrl();
}
