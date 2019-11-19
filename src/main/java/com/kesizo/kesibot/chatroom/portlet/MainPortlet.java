package com.kesizo.kesibot.chatroom.portlet;

import com.kesizo.kesibot.chatroom.constants.MainPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author mdoctor
 */
@Component(
	configurationPid = "MainPortletConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kesizo",
		"com.liferay.portlet.instanceable=false",
//		"com.liferay.portlet.header-portlet-css=/css/main.css",
//		"com.liferay.portlet.css-class-wrapper=kesibot-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.display-name=Kesibot Chatroom",
		"javax.portlet.name=" + MainPortletKeys.NAME_MAIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MainPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest,
					   RenderResponse renderResponse) throws IOException, PortletException {

		renderRequest.setAttribute(
				MainPortletConfiguration.class.getName(),
				_mainPortletConfiguration);

		super.doView(renderRequest, renderResponse);
	}

	public String getChatbotProvider(Map labels) {
		return (String) labels.get(_mainPortletConfiguration.chatbotProvider());
	}
	
	public String getChatbotUrl(Map labels) {
		return (String) labels.get(_mainPortletConfiguration.chatbotUrl());
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_mainPortletConfiguration = ConfigurableUtil.createConfigurable(
				MainPortletConfiguration.class, properties);
	}

	private volatile MainPortletConfiguration _mainPortletConfiguration;

}