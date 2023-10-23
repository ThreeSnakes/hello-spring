package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 예제에서는 간단한 구현이기 때문에 동시성 문제가 고려되지 않았다.
    // 나중에 동시성 이슈가 무엇인지 찾아보고 살펴보자.
    private static Map<Long, Member> store =  new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 아래가 자바 lambda 라고 한다. 보니깐 비슷하네~
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        // 아래 코드가 예제로 나온 코드이고 주석 처리한 코드도 되는것 같다.
        return new ArrayList<>(store.values());
        //  return store.values().stream().toList();
    }

    public void clearStore() {
        store.clear();
    }
}
