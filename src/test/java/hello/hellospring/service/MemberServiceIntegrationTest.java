package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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