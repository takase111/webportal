package jp.ac.hcs.s3a319.task;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * タスク情報を操作する
 */
@Transactional
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	/**
	 * 指定したユーザIDのタスク情報を全件取得する
	 * @param userId ユーザID
	 * @return TaskEntity
	 */
	public TaskEntity selectAll(String userId) {
		TaskEntity taskEntity;
		try {
			taskEntity = taskRepository.selectAll(userId);
		} catch (DataAccessException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
			taskEntity = null;
		}
		
		return taskEntity;
	}
	
	/**
	 * タスク情報を1件追加する
	 * @param userId ユーザID
	 * @param limitday 
	 * @param comment 
	 * @param comment 
	 * @return TaskEntity
	 */
	public int insertOne(String userId, String comment, Date limitday) {
		int rowNumber = 0;
		TaskData data = new TaskData();
		data.setUser_id(userId);
		data.setComment(comment);
		data.setLimitday(limitday);
		
		try {
			rowNumber = taskRepository.insertOne(data);
		} catch (DataAccessException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		
		return rowNumber;
	}
	
	/**
	 * String型をDate型にする
	 * @param limitday String型の日付
	 * @return dateLimitday date型の日付
	 */
	public Date dateFormat(String limitday) {
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateLimitday = null;
		try {
			dateLimitday = smdf.parse(limitday);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return dateLimitday;
	}

	/**
	 * タスク情報を1件追加する
	 * @param userId ユーザID
	 * @param limitday 
	 * @param comment 
	 * @param comment 
	 * @return TaskEntity
	 */
	public boolean deleteOne(int id) {
		boolean deleteCheck = false;
		
		try {
			taskRepository.deleteOne(id);
			deleteCheck = true;
		}catch (DataAccessException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}
		
		return deleteCheck;
	}
	
	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskRepository.tasklistCsvOut(user_id);
	}

	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 */
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
	
}
