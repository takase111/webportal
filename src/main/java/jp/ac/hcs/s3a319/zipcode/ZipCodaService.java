package jp.ac.hcs.s3a319.zipcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 郵便番号情報を操作する
 * zipcloud社の郵便番号検索APIを活用する
 * - http://zipcloud. ibsnet.co.jp/doc/api
 */
public class ZipCodaService {
	
	@Autowired
	RestTemplate restTemplate;
	
	/** 郵便番号検索API リクエストURL */
	private static final String URL = "https://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";
	
	/**
	 * 指定した郵便番号に紐づく郵便番号情報を取得する
	 * @param zipcode 郵便番号(7桁、ハイフンなし)
	 * @return ZipCodeEntity
	 */
	public ZipCodeEntity getZip(String zipcode) {
		return ZipCodeEntity;
	}
}
