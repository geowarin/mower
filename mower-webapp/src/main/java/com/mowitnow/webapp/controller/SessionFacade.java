package com.mowitnow.webapp.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mowitnow.mower.core.MowerApplicationResult;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionFacade {

	private MowerApplicationResult applicationResult;

	public MowerApplicationResult getApplicationResult() {
		return applicationResult;
	}

	public void setApplicationResult(MowerApplicationResult applicationResult) {
		this.applicationResult = applicationResult;
	}
}
