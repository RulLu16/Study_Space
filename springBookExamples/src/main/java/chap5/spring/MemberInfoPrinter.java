package chap5.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter") // 값을 주면 빈으로 등록할 때 사용할 이름이 지정됨.
// 지정하지 않는다면 클래스 이름의 첫글자를 소문자로 바꾼 이름을 bean이름으로 사용
public class MemberInfoPrinter {

	private MemberDao memDao;
	private MemberPrint printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member==null) {
			System.out.println("no data\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	@Autowired 
	public void setMemberDao(MemberDao memberDao) { 
		// setter로 객체 주입하는 방법
		this.memDao = memberDao;
	}
	
	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrint printer) {
		this.printer = printer;
	}
}
