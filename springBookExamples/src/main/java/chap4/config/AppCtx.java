package chap4.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap4.spring.ChangePasswordService;
import chap4.spring.MemberDao;
import chap4.spring.MemberInfoPrinter;
import chap4.spring.MemberListPrinter;
import chap4.spring.MemberPrint;
import chap4.spring.MemberRegisterService;
import chap4.spring.MemberSummaryPrinter;

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
		// 자동 주입했기 때문에 해당 코드만 작성해도 알아서 주입이 된다
		return pwdSvc;
	}
	
	@Bean
	@Qualifier("printer") // 같은 타입의 빈이 여러개이면 어떤 것을 자동주입할지 알지 못해 익셉션이 발생한다
	// 이를 방지하기 위해 qualifier를 사용해 자동 주입할 빈을 지정할 수 있다.
	public MemberPrint memberPrinter() {
		return new MemberPrint();
	}
	
	@Bean
	public MemberPrint memberPrinter1() {
		return new MemberSummaryPrinter();
	}
	
	@Bean 
	// Qualifier가 없을 경우, 해당 메소드의 이름이 한정자가 된다. 
	public MemberListPrinter listPrinter() { 
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		// autowired로 자동주입 했기 때문에 해당 코드로도 주입이 가능
		infoPrinter.setPrinter(memberPrinter());
		// 설정 클래스에서 세터 메소드를 통해 의존 주입을 하더라도, 만약 해당 세터 메소드에 
		// Autowired가 붙어있으면 자동 주입을 통해 일치하는 빈을 주입한다. 
		// 즉, 여기서 넣은 주입은 사용하지 않는다.
		return infoPrinter;
	}
}
