package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository2;
import hello.hellospring.service.MemberService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService2 memberService2() {
        return new MemberService2(memberRepository2());
    }

    @Bean
    public MemberRepository memberRepository2() {
        return new MemoryMemberRepository2();
    }
}
