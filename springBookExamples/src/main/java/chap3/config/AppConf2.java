package chap3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap3.spring.ChangePasswordService;
import chap3.spring.MemberDao;
import chap3.spring.MemberInfoPrinter;
import chap3.spring.MemberListPrinter;
import chap3.spring.MemberPrint;
import chap3.spring.MemberRegisterService;

@Configuration
public class AppConf2 {
	@Autowired // 해당 타입의 bean을 찾아 필드에 할당하는 annotaion
	private MemberDao memberDao; // AppConf1에서 memberDao bean을 설정했으니까 같은 bean이 할당됨
	@Autowired
	private MemberPrint memberPrinter;
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao);
		// 필드에 생성된 bean을 이용해 주입
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter);
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao);
		infoPrinter.setPrinter(memberPrinter);
		return infoPrinter;
	}
}
