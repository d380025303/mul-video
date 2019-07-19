package com.daixinmini.video;

import com.daixinmini.video.util.InitUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URL;

@EnableScheduling
@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class VideoApplication {

	public static void main(String[] args) {
		InitUtil.setRunAsJar(isRunAsJar());
		SpringApplication.run(VideoApplication.class, args);
	}

	private static boolean isRunAsJar() {
		// 注意：由于fat jar方式打包，会有jar套jar。只能判断Application类是否在jar中
		URL url = VideoApplication.class.getResource("");
		String protocol = url.getProtocol();
		return "jar".equals(protocol);
	}
}
