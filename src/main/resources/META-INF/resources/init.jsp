<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.kesizo.kesibot.chatroom.portlet.MainPortletConfiguration" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
    MainPortletConfiguration chatroomConfiguration =
            (MainPortletConfiguration)
                    renderRequest.getAttribute(MainPortletConfiguration.class.getName());
    
    String chatbotProvider = StringPool.BLANK;
    String chatbotUrl = StringPool.BLANK;
    
    if (Validator.isNotNull(chatroomConfiguration)) {
    	chatbotProvider =
                portletPreferences.getValue(
                        "chatbotProvider", chatroomConfiguration.chatbotProvider());
    }
    
    if (Validator.isNotNull(chatroomConfiguration)) {
    	chatbotUrl =
                portletPreferences.getValue(
                        "chatbotUrl", chatroomConfiguration.chatbotUrl());
    }
    
%>