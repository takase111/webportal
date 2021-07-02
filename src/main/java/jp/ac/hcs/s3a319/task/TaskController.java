package jp.ac.hcs.s3a319.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getTaskList(Principal principal, Model model){
		TaskEntity taskEntity = taskService.selectAll(principal.getName());
		//* タスクリスト表示 */
		model.addAttribute("taskEntity", taskEntity);
		
		log.info("[" + principal.getName() + "]タスク:" + "表示");
		
		return "task/task";
	}
	
	/**
	 * タスクを追加する
	 * @param model
	 * @return 結果画面 - タスク
	 */
	@PostMapping("/task/insert")
	public String insertTask(@RequestParam("comment") String comment,
			@RequestParam("limitday") String limitday, Principal principal,
			Model model){
		
		//* タスクを追加 */
		taskService.insertOne(principal.getName(),comment,taskService.dateFormat(limitday));
		
		log.info("[" + principal.getName() + "]タスク:" + "追加");
		
		return getTaskList(principal, model);
	}
}
