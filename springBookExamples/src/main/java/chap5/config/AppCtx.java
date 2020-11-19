package chap5.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import chap4.spring.ChangePasswordService;
import chap4.spring.MemberDao;
import chap4.spring.MemberInfoPrinter;
import chap4.spring.MemberListPrinter;
import chap4.spring.MemberPrint;
import chap4.spring.MemberRegisterService;
import chap4.spring.MemberSummaryPrinter;

@Configuration 
@ComponentScan(basePackages = {"spring"},
		excludeFilters = @Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao")) 
// 스캔해서 스프링 빈으로 등록하기 위해 설정 클래스에 적용
// "spring"은 스캔 대상 패키지 목록을 지정한다. 해당 패키지와 하위 패키지에 속한 클래스를 스캔한다
// FilterType.REGEX == 정규 표현식을 사용해 제외 대상을 지정하겠다는 의미. 
// 즉, spring으로 시작해 Dao로 끝나는 클래스를 제외(spring.MemberDao)
// 어노테이션을 검색해 해당 어노테이션을 붙인 클래스를 제외할 수도 있다.
public class AppCtx {
	
	// chap 3,4 에 비해 component 스캔을 통해 memberDao등을 bean으로 등록할 필요가 없어짐.
	// 코드가 간결해지는 효과
	
	@Bean
	// 이처럼 스캔도 하고, 수동 등록도 한 경우에는 수동 등록한 bean을 우선시한다.
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrint memberPrinter() {
		return new MemberPrint();
	}
	
	@Bean
	public MemberPrint memberPrinter1() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
}
