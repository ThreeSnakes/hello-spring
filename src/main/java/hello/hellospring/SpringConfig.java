package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepository2;
import hello.hellospring.service.MemberService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService2 memberService2() {
        return new MemberService2(memberRepository2());
    }

    @Bean
    public MemberRepository memberRepository2() {
//        return new MemoryMemberRepository2();
        return new JdbcMemberRepository(dataSource);
    }
}
