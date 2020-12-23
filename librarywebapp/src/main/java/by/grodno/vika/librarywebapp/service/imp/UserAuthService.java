package by.grodno.vika.librarywebapp.service.imp;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;
import by.grodno.vika.librarywebapp.service.UserService;


@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return service.findByEmail(username).map(userFromBd -> {
			Optional<UserCredentials> findAny = userFromBd.getCredentials().stream().filter(UserCredentials::getActive)
					.findAny();
			String password = findAny.map(UserCredentials::getPassword).orElseThrow(() -> new UserNotFoundException());

			return new User(userFromBd.getEmail(), password, toAuthorities(userFromBd));
		}).orElse(null);
		
	}

	private Collection<? extends GrantedAuthority> toAuthorities(
			by.grodno.vika.librarywebapp.domain.User findByUserName) {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + findByUserName.getRole().name()));
	}

}