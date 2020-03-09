package com.gcalsolaro.hsapp.idpsaml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcalsolaro.hsapp.idpsaml.stereotypes.CurrentUser;

/**
 * 
 * @since 1.0
 * @version 1.0
 */
@Controller
public class LandingController {

	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);

	/**
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/landing")
	public ModelAndView landing(@CurrentUser User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			logger.debug("Current authentication instance from security context is null");
		else
			logger.debug("Current authentication instance from security context: " + this.getClass().getSimpleName());
		model.addAttribute("username", user.getUsername());
		return new ModelAndView("landing");
	}

}
