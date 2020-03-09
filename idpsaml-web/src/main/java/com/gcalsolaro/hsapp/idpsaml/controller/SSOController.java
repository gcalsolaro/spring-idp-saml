package com.gcalsolaro.hsapp.idpsaml.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/saml")
public class SSOController {

	private static final Logger logger = LoggerFactory.getLogger(SSOController.class);

	@Autowired
	private MetadataManager metadata;

	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	public String idpSelection(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			logger.debug("Current authentication instance from security context is null");
		else
			logger.debug("Current authentication instance from security context: " + this.getClass().getSimpleName());
		if (auth == null || (auth instanceof AnonymousAuthenticationToken)) {
			Set<String> idps = metadata.getIDPEntityNames();
			for (String idp : idps)
				logger.info("Configured Identity Provider for SSO: " + idp);
			model.addAttribute("idps", idps);
			return "pages/discovery";
		} else {
			logger.warn("The current user is already logged.");
			return "redirect:/landing";
		}
	}

}
