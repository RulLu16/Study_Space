package chap3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap3.spring.MemberDao;
import chap3.spring.MemberPrint;

@Configuration
public class AppConf1 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrint memberPrinter() {
		return new MemberPrint();
	}
}
