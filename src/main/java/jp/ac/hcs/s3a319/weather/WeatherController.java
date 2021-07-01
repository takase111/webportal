package jp.ac.hcs.s3a319.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	
	/**
	 * 札幌の天気を表示する
	 * @param model
	 * @return 結果画面 - 天気情報
	 */
	@PostMapping("/weather")
	public String getCityCode(Principal principal, Model model){
		String cityCode = "016010";
		WeatherEntity weatherEntity = weatherService.getWeather(cityCode);
		model.addAttribute("weatherEntity", weatherEntity);
		
		log.info("[" + principal.getName() + "]天気検索:" + cityCode);
		return "weather/weather";
		
	}
}
