package jp.ac.hcs.s3a319.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * ユーザ一覧を表示する
	 * @param model
	 * @return 結果画面 - タスク
	 */
	@GetMapping("/user/list")
	public String getUserList(Principal principal, Model model){
		 UserEntity userEntity = userService.selectAll();
		
		//* タスクリスト表示 */
		model.addAttribute("userEntity", userEntity);
		
		/** ロガー */
		log.info("[" + principal.getName() + "]ユーザ:" + "表示");
		
		return "user/userList";
	}
}
