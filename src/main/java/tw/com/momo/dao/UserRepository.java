package tw.com.momo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.momo.domain.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long>{
	Optional<UserBean> findByEmail(String email);
	Optional<UserBean> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
	void save(Optional<UserBean> user);
}
