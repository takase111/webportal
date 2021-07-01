package jp.ac.hcs.s3a319.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TaskController {
	@Autowired
	private  TaskService taskService;
	
	/**
	 * タスクを表示する
	 * @param model
	 * @return 結果画面 - タスク
	 */
	@PostMapping("/task")
	public String get(Principal principal, Model model){
		TaskEntity taskEntity = taskService.selectAll(principal.getName());
		model.addAttribute("taskEntity", taskEntity);
		
		log.info("[" + principal.getName() + "]タスク:" + "");
		
		return "task/task";
		
	}
}
