package chap3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap3.spring.ChangePasswordService;
import chap3.spring.MemberDao;
import chap3.spring.MemberRegisterService;

@Configuration // 스프링 설정 클래스를 의미하는 annotation
public class AppCtx {
	
	@Bean // bean 객체 생성
	public MemberDao memberDao() { // 메소드의 이름 == 빈 객체의 이름이 된다.
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		
		return pwdSvc;
	}
}
