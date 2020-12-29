package chap3.spring;

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
	
	public void setMemberDao(MemberDao memberDao) { 
		// setter로 객체 주입하는 방법
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrint printer) {
		this.printer = printer;
	}
}
