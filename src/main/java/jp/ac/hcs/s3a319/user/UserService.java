package jp.ac.hcs.s3a319.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * user情報を操作する
 */
@Transactional
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	/**
	 * 指定したユーザIDのタスク情報を全件取得する
	 * @return UserEntity
	 */
	public UserEntity selectAll() {
		UserEntity userEntity;
		try {
			userEntity = userRepository.selectAll();
		} catch (DataAccessException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
			userEntity = null;
		}
		
		return userEntity;
	}
}
