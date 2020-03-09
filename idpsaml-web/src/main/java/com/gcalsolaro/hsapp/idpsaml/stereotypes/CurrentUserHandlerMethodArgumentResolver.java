package com.gcalsolaro.hsapp.idpsaml.stereotypes;

import java.security.Principal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 
 * @since 1.0
 * @version 1.0
 */
@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	 */
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(CurrentUser.class) != null && methodParameter.getParameterType().equals(User.class);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
	 */
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(methodParameter)) {
			Principal principal = (Principal) webRequest.getUserPrincipal();
			return (User) ((Authentication) principal).getPrincipal();
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}