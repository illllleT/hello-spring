package gimye.hellospring.service;

import gimye.hellospring.domain.Member;
import gimye.hellospring.repository.MemberRepository;
import gimye.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){ // 각 테스트를 실행하기 전에
        memberRepository = new MemoryMemberRepository(); // MemoryMemberRepository를 만들고
        memberService = new MemberService(memberRepository); // MemberSerivce에 넣어줌
        // 그럼 같은 메모리를 사용하게 된다. 이런 것을 dependency injection, DI라고 한다.
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() { // 회원가입
        // given 무언가가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        // when 이것을 실행했을 때

         Long saveId = memberService.join(member);

        // then 결과가 이렇게 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given

        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*

        try{
            memberService.join(member2); // 예외 발생
            fail();
        } catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}