package gimye.hellospring.repository;

import gimye.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        //given
        Member member = new Member(); //새로운 멤버 객체 생성
        member.setName("spring"); // 멤버 name 설정
        //when
        repository.save(member); // repo에 member 저장
        //then
        Member result = repository.findById(member.getId()).get();
        // findById 메소드로 잘 저장되었는지 확인
        // result가 내가 저장한 member와 같으면
        Assertions.assertEquals(member, result); // 을 통해 기댓값과 실제 값을 비교
        // Assertions.assertThat(member).isEqualTo(result); member가 result랑 같은지 비교
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        // 이름으로 찾기 테스트
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        // Optional로 묶어줬기 때문에 get()으로 꺼내주는 것
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}

