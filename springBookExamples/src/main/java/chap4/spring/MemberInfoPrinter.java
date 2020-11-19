package chap4.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
	
	@Autowired // 메소드에도 autowired annotation을 붙일 수 있음
	public void setMemberDao(MemberDao memberDao) { 
		// setter로 객체 주입하는 방법
		this.memDao = memberDao;
	}
	
	@Autowired // 메소드에 autowired를 붙이면 스프링이 해당 메서드를 알아서 호출하고 빈 객체를 찾아 인자로 주입해준다.
	@Qualifier("printer")
	public void setPrinter(MemberPrint printer) {
		this.printer = printer;
	}
}
