package com.mowitnow.webapp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mowitnow.mower.MowerApplication;
import com.mowitnow.mower.core.Mower;
import com.mowitnow.mower.core.MowerApplicationResult;
import com.mowitnow.mower.geom.Surface;

@Controller
public class HomeController {
	
	@Autowired
	private SessionFacade sessionFacade;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome(RedirectAttributes redirectAttrs) {
		
		if (sessionFacade.getApplicationResult() == null) {
			return executeApplication(null, redirectAttrs, "xebia.txt");
		}
		
		return "/home";
	}
	
	@RequestMapping(value = "/loadFile", method = RequestMethod.GET)
	public String loadFile(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		
		final String fileName = request.getParameter("file"); 
		return executeApplication(HomeController.class.getResourceAsStream("/" + fileName), redirectAttrs, fileName);
	}

	@RequestMapping(value = "/post_file", method = RequestMethod.POST)
	public String FormHandling(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttrs) throws IOException {

		if (file.isEmpty())
			return "redirect:/";
				
		return executeApplication(new ByteArrayInputStream(file.getBytes()), redirectAttrs, file.getOriginalFilename());
	}
	
	private String executeApplication(InputStream instructionStream, RedirectAttributes redirectAttrs, String fileName)  {
		
		if (instructionStream == null)
			instructionStream = HomeController.class.getResourceAsStream("/xebia.txt");

		final MowerApplication application = new MowerApplication();
		final MowerApplicationResult result = application.execute(instructionStream);
		sessionFacade.setApplicationResult(result);
		
		redirectAttrs.addFlashAttribute("message", "Loaded : " + fileName);
		return "redirect:/";
		
	}
	
	@ModelAttribute("map")
	public Surface getMap() {
		if (sessionFacade.getApplicationResult() == null)
			return null;
		return sessionFacade.getApplicationResult().getLawn();
	}

	
	@RequestMapping(value = "/get_moves", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Mower> getMoves() {
		return sessionFacade.getApplicationResult().getAllStates();
	}
}
