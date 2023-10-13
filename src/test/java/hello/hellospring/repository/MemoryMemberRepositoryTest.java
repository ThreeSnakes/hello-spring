package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("test");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Assertions가 static import 되어서 아래 assertThat을 바로 사용할 수 있다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("jonathan");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("gabriel");
        repository.save(member2);

        // get()을 하면 Optional<Member>로 된것을 한번 깔 수 있다?
        Member jonathan = repository.findByName("jonathan").get();

        assertThat(member1).isEqualTo(jonathan);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("jonathan");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("gabriel");
        repository.save(member2);

        List<Member> all = repository.findAll();

        assertThat(all.size()).isEqualTo(2);
    }
}
