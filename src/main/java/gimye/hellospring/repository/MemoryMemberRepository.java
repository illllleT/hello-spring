package gimye.hellospring.repository;

import gimye.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0 1 2 이렇게 키 값 생성해 줌



    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id setting
        store.put(member.getId(), member); // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null값이 변환될 수 있으므로 optional로 감싸서 return
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 루프를 돌면서 map에서 값이 찾아지면 반환함.
        // 끝까지 돌렸는데 없으면 Optional로 감싸져서 null로 반환됨.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 멤버가 다 반환됨
    }

    public void clearStore(){
        store.clear();
    }
}
