
package com.example.SocialHelloWorld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {

	@Value("${spring.social.facebook.appId}")
	String	facebookAppId;
	@Value("${spring.social.facebook.appSecret}")
	String	facebookSecret;

	String	accessToken;


	public String createFacebookAuthorizationURL(final String baseUrl) {
		String result;
		FacebookConnectionFactory connectionFactory;
		OAuth2Operations oauthOperations;
		OAuth2Parameters params;

		connectionFactory = new FacebookConnectionFactory(this.facebookAppId, this.facebookSecret);
		oauthOperations = connectionFactory.getOAuthOperations();
		params = new OAuth2Parameters();

		String redirectUri = baseUrl + "/facebook";

		//String redirectUri = "http://localhost:8080/facebook";

		params.setRedirectUri(redirectUri);

		params.setScope("public_profile, email, user_birthday, user_friends");

		result = oauthOperations.buildAuthorizeUrl(params);

		return result;
	}

	public void createFacebookAccessToken(final String code, final String baseUrl) {

		FacebookConnectionFactory connectionFactory;
		AccessGrant accessGrant;

		connectionFactory = new FacebookConnectionFactory(this.facebookAppId, this.facebookSecret);

		//accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook", null);

		accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, baseUrl + "/facebook", null);

		this.accessToken = accessGrant.getAccessToken();
	}

	/*
	 *
	 * public String getName() {
	 *
	 * Facebook facebook;
	 *
	 * facebook = new FacebookTemplate(this.accessToken);
	 *
	 * String[] fields = {
	 * "id", "name"
	 * };
	 *
	 * return facebook.fetchObject("me", String.class, fields);
	 * }
	 *
	 */

	public User getUser() {

		Facebook facebook;

		facebook = new FacebookTemplate(this.accessToken);

		/*
		 * This crashes, as it's not supported by the newer Facebook API
		 * AKA Spring Social is outdated
		 *
		 *
		 * User user = facebook.userOperations().getUserProfile();
		 */

		//Workaround:
		//{ "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift", "website", "work"}

		String[] fields = {
			"id", "name"
		};

		User user;

		user = facebook.fetchObject("me", User.class, fields);

		return user;

	}

	public List<User> getFriends() {
		Facebook facebook;

		facebook = new FacebookTemplate(this.accessToken);

		List<User> friends;

		friends = facebook.friendOperations().getFriendProfiles();

		return friends;

	}
	/*
	 *
	 * public void publishAPost(final String post) {
	 *
	 * Facebook facebook = new FacebookTemplate(this.accessToken);
	 *
	 * facebook.feedOperations().post("me", post);
	 *
	 * }
	 *
	 */

}
