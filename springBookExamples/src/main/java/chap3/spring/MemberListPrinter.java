package chap3.spring;

import java.util.Collection;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrint printer;
	
	public MemberListPrinter(MemberDao memberDao, MemberPrint printer) {
		// 생성자로 객체 주입하는 방법
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m->printer.print(m));
	}
}
