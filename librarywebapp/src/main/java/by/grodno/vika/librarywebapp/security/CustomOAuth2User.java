package by.grodno.vika.librarywebapp.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User{

	private OAuth2User oauth2User;
	
	public CustomOAuth2User(OAuth2User oauth2User) {
		this.oauth2User = oauth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		oauth2User.getAuthorities();
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oauth2User.getAttribute("email");
	}
	public String getFirstName() {
		return oauth2User.getAttribute("given_name");
		
	}
	
	public String getLastName() {
		return oauth2User.getAttribute("family_name");
		
	}
	
		
}