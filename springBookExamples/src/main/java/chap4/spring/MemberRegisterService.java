package chap4.spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		//this.memberDao = memberDao;
		// 자동 주입으로 생성자에서 주입해줄 필요가 없어짐.
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(),
									req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		
		return newMember.getId();
	}
}
