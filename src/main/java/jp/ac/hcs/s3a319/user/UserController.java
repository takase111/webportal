package jp.ac.hcs.s3a319.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	/**
	 * ユーザ登録画面(管理者用)を表示する.
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面(管理者用)
	 */
	@GetMapping("/user/insert")
	public String getUserInsert(UserForm from,Model model) {
		return "user/insert";
	}
	
	/**
	 * 1件分のユーザ情報をデータベースに登録する.
	 * @param form 登録するユーザ情報(パスワードは平文)
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザー一覧画面
	 */
	@PostMapping("/user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
		
		// 入力チェックに引っかかった場合、前の画面に戻る
		if(bindingResult.hasErrors()) {
			return getUserInsert(form, model);
		}
		
		//追加処理を実装する
		
		return getUserList(principal,model);
	}
}
