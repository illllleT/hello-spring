package gimye.hellospring.repository;
import gimye.hellospring.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장됨
    Optional<Member> findById(Long id); //저장된 이후로부터는 이 메소드를 통해
    Optional<Member> findByName(String name); // 저장소에서 회원을 찾아올 수 있다.
    List<Member> findAll(); //지금까지 저장된 모든 회원을 반환

}
