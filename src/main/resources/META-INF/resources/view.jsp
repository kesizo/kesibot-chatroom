<%@ include file="./init.jsp" %>

<%
    boolean noConfig = Validator.isNull(chatbotProvider) || Validator.isNull(chatbotUrl); 

%>

<div id="app"></div>

<c:choose>
    <c:when test="<%= noConfig %>">
        <p>
            Please select your chatbot provider in the configuration
        </p>
    </c:when>

    <c:otherwise>
       
       Chatbot provider :<span id="chatbotProvider"><%= chatbotProvider %></span>
       Chatbot Url :<span id="chatbotUrl"><%= chatbotUrl %></span>
       Cookie :<span id="chatsession"><%= renderRequest.getPortletSession(true).getId() %></span>
       
       
    </c:otherwise>
</c:choose>


<script src="<%=request.getContextPath()%>/dist/app.js?<%= new java.util.Date().getTime()%>"></script>
