package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private MemoryMemberRepository memoryMemberRepository;
    private MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member target = new Member();
        target.setName("jonathan");

        // when
        Long saveId = memberService.join(target);
        Member findedMember = memberService.findMember(saveId).get();

        // then
        assertThat(target.getName()).isEqualTo(findedMember.getName());
    }

    @Test
    void 회원가입_예외() {
        // given
        Member target1 = new Member();
        target1.setName("jonathan");

        Member target2 = new Member();
        target2.setName("jonathan");

        // then
        /*
        // try catch를 이용한 테스트 방법
        try {
            memberService.join(target1);
            memberService.join(target2);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }
         */

        // assertThrow를 이용한 방법
        // memberService.join(target1);
        // assertThrows(IllegalStateException.class, () -> memberService.join(target2));

        // assertThrow를 이용한뒤 메세지를 검증하는 방법
        memberService.join(target1);
        IllegalStateException error = assertThrows(IllegalStateException.class, () -> memberService.join(target2));
        assertThat(error.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }

    @Test
    void fetchMembers() {
        // given
        Member member1 = new Member();
        member1.setName("test1");

        Member member2 = new Member();
        member2.setName("test2");

        // when
        memberService.join(member1);
        memberService.join(member2);

        List<Member> memberList = memberService.fetchMembers();
        List<Member> resultList = new ArrayList<Member>();
        resultList.add(member1);
        resultList.add(member2);

        // then
        assertThat(memberList).isEqualTo(resultList);
    }

    @Test
    void findMember() {
        // given
        Member member1 = new Member();
        member1.setName("test");

        // when
        Long saveId = memberService.join(member1);

        // then
        assertThat(saveId).isEqualTo(memberService.findMember(saveId).get().getId());
    }
}