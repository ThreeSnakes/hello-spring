package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService2 {

    private final MemberRepository memberRepository;

    public MemberService2(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private void checkDuplicateName(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(result -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }

    public Long join(Member member) {
        // 중복 이름 필터. 별도 메소드로 뽑아서 재작성.
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(m -> {
        //    throw new IllegalStateException("이미 존재하는 이름입니다.");
        // });
        checkDuplicateName(member);

        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> fetchMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
}
