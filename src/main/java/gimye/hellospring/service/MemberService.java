package gimye.hellospring.service;
import gimye.hellospring.domain.Member;
import gimye.hellospring.repository.MemberRepository;
import gimye.hellospring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입

    public Long join(Member member) { // 회원 가입을 하면
        validateDuplicateMember(member); // 중복 회원 검증(같은 이름 회원이면 안 됨)
        memberRepository.save(member); // 해당 member가 중복 회원이 아니면 repo에 저장
        return member.getId(); // 임의로 id 반환하겠다고 설정
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}