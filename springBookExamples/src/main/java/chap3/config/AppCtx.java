package chap3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap3.spring.ChangePasswordService;
import chap3.spring.MemberDao;
import chap3.spring.MemberInfoPrinter;
import chap3.spring.MemberListPrinter;
import chap3.spring.MemberPrint;
import chap3.spring.MemberRegisterService;

@Configuration // 스프링 설정 클래스를 의미하는 annotation
public class AppCtx {
	// 반드시 bean을 이용해 주입할 필요는 없다. 
	// 하지만 bean으로 주입하지 않는 경우는 스프링 컨테이너가 객체를 관리하지 않는다.
	
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
		// spring은 싱글톤 방식이 기본이기 때문에 위의 memberRegSvc랑 같은 memberDao 객체를 공유한다.
		// 이게 가능한 이유는, spring이 AcppCtx를 상속하는 새로운 설정 클래스를 알아서 만들어서 
		// 만든 memberDao를 보관했다가 이후에는 동일한 memberDao를 반환해주기 때문
		return pwdSvc;
	}
	
	@Bean
	public MemberPrint memberPrinter() {
		return new MemberPrint();
	}
	
	@Bean
	public MemberListPrinter listPrinter() { // 여러개 의존 class 있는 것도 당연히 가능
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		// setter를 사용한 주입
		return infoPrinter;
	}
}
