package by.grodno.vika.librarywebapp.service.imp;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.service.UserService;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userFromBd = service.findByEmail(username);
		if (userFromBd == null || userFromBd.getCredentials().getActive().equals(false)) {
			throw new ResourceNotFoundException("There is no user registered with this email: " + userFromBd.getEmail());
		} else {
			return new org.springframework.security.core.userdetails.User(userFromBd.getEmail(),
					userFromBd.getCredentials().getPassword(), toAuthorities(userFromBd));
		}
	}

	public Collection<? extends GrantedAuthority> toAuthorities(User findByUserName) {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + findByUserName.getRole().name()));
	}

}