
package com.example.SocialHelloWorld.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.SocialHelloWorld.service.FacebookService;

@Controller
public class DemoController {

	@Autowired
	FacebookService facebookService;


	@RequestMapping(path = "/")
	public ModelAndView landingPage(final HttpServletRequest request) {

		ModelAndView res = new ModelAndView("index.jsp");

		String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());

		String facebookUrl = this.facebookService.createFacebookAuthorizationURL(baseUrl);

		res.addObject("facebookUrl", facebookUrl);

		return res;
	}

	/*
	 *
	 * @GetMapping("/createFacebookAuthorization")
	 * public String createFacebookAuthorization(final HttpServletRequest request) {
	 *
	 * //String contextPath = servletContext.getContextPath();
	 *
	 * //System.out.println(contextPath);
	 *
	 * String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
	 *
	 * System.out.println("\n\nURL: " + baseUrl + "\n\n");
	 *
	 * String link = this.facebookService.createFacebookAuthorizationURL(baseUrl);
	 *
	 * return "redirect:" + link;
	 * }
	 *
	 */

	@GetMapping("/facebook")
	public String createFacebookAccessToken(@RequestParam("code") final String code, final HttpServletRequest request) {

		String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());

		this.facebookService.createFacebookAccessToken(code, baseUrl);
		return "connected.html";
	}

	@GetMapping("/getUser")
	public ModelAndView getUserResponse() {

		User user = this.facebookService.getUser();

		String userName = user.getName();

		String userId = user.getId();

		ModelAndView res = new ModelAndView("userPage.jsp");

		res.addObject("userName", userName);

		res.addObject("userId", userId);

		res.addObject("friendsList", this.facebookService.getFriends());

		return res;
	}

	/*
	 *
	 * @GetMapping("/createAPost")
	 * public ModelAndView getPostResponse() {
	 *
	 * ModelAndView res = new ModelAndView("createAPostPage.jsp");
	 *
	 * return res;
	 *
	 * }
	 *
	 */

	/*
	 * Posting on an user's wall has been disabled from the API, even though Spring Social still allows it.
	 *
	 * @RequestMapping("/post")
	 *
	 * @ResponseBody
	 * public String property(@ModelAttribute("facebookPost") final String post) {
	 *
	 * this.facebookService.publishAPost(post);
	 *
	 * return "redirect:/getUser";
	 * }
	 */
}
