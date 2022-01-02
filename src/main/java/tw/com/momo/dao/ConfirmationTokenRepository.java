package tw.com.momo.dao;

import org.springframework.data.repository.CrudRepository;

import tw.com.momo.domain.ConfirmationTokenBean;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenBean, String> {
	ConfirmationTokenBean findByConfirmationToken(String ConfirmationTokenBean);
}
