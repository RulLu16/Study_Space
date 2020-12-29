package chap3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import chap3.spring.MemberDao;
import chap3.spring.MemberPrint;

@Configuration
@Import(AppConf2.class) // AppConf2의 클래스 설정도 같이 사용하게 하는 annotation
// @Import({AppConf1.class, AppConf2.class}) 로 두개 이상 설정 클래스를 지정할 수도 있음
public class AppConfImport {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrint memberPrinter() {
		return new MemberPrint();
	}
}
