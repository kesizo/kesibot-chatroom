package com.kesizo.kesibot.chatroom.portlet;

import com.kesizo.kesibot.chatroom.constants.MainPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component(
    configurationPid = "MainPortletConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    immediate = true,
    property = {
        "javax.portlet.name=" +  MainPortletKeys.NAME_MAIN
    },
    service = ConfigurationAction.class
)
public class MainPortletConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
            throws Exception {
    	
    	 String chatbotProvider = ParamUtil.getString(actionRequest, "chatbotProvider");
         setPreference(actionRequest, "chatbotProvider", chatbotProvider);
        
        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
            PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
                MainPortletConfiguration.class.getName(),
                _mainPortletConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _mainPortletConfiguration = ConfigurableUtil.createConfigurable(
                MainPortletConfiguration.class, properties);
    }

    private volatile MainPortletConfiguration _mainPortletConfiguration;


}
