package tw.com.momo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.UserBean;

@Service
public class UserRepositoryService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserBean user = userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User not found with email:" + email));
		return UserDetailsImpl.build(user);
	}

}
