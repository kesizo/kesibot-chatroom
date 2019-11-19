<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<%@ include file="./init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>"
                           var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>"
                           var="configurationRenderURL" />

	<aui:form action="<%= configurationActionURL %>" method="post" name="fm" id="submitParamsForm">
	
	    <aui:input name="<%= Constants.CMD %>" type="hidden"
	               value="<%= Constants.UPDATE %>" />
	
	    <aui:input name="redirect" type="hidden"
	               value="<%= configurationRenderURL %>" />
	               
	    <aui:fieldset>
	    	<div style="margin: 20px">
	        
		        <aui:select name="chatbotProvider" label="Chatbot Provider Platform"
		                    value="<%= chatbotProvider %>">
		            <aui:option value="rasa">RASA</aui:option>
		            <aui:option value="dialog flow">Dialog flow</aui:option>
		        </aui:select>
		    
		         <aui:input  name="chatbotUrl" label="Chatbot Url" type="text" 
		        	 value="<%= chatbotUrl==null || ((String)chatbotUrl).isEmpty() ? "http://localhost:5005/webhooks/rest/webhook" : chatbotUrl %>">
		        </aui:input>
		</div>
	    </aui:fieldset>
	    <aui:button-row>
	        <aui:button type="submit"></aui:button>
	    </aui:button-row>
	</aui:form>
