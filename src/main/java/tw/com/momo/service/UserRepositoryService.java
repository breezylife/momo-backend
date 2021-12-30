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
		UserBean userBean = userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User not found with email:" + email));
		return new org.springframework.security.core.userdetails.User(userBean.getEmail(), userBean.getPassword(),
				Collections.emptyList());
	}

//	public UserBean signup(UserBean bean) {
//		UserBean result = null;
//		if (bean != null && bean.getId() != null) {
//			boolean exist = userRepository.existsById(bean.getId());
//			if (!exist) {
//				return userRepository.save(bean);
//			}
//		}
//		return result;
//	}
}
