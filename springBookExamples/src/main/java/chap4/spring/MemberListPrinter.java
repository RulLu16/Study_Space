package chap4.spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrint printer;
	
	public MemberListPrinter() {
		// 자동주입으로 생성자에서 넣을 필요가 없어짐
		//this.memberDao = memberDao;
		//this.printer = printer;
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) { 
		this.memberDao = memberDao;
	}
	
	@Autowired 
	@Qualifier("printer") // 자동 주입할 빈을 한정한다. printer 값을 갖는 빈을 주입한다.
	public void setMemberPrinter(MemberPrint printer) {
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m->printer.print(m));
	}
}
