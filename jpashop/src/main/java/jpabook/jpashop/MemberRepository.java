package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링 컨테이너 위에 있으면 자동으로 주입(부트가 해줌)
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);

        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
