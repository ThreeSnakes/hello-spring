package hello.hellospring.controller;

import hello.hellospring.controller.dto.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // Component를 이용한 서비스
    private MemberService memberService;

    // Bean을 직접 등록해서 사용한 서비스
    private MemberService memberService2;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String memberForm() {
        return "member/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(MemberForm form) {
        Member newMember = new Member();
        newMember.setName(form.getName());

        memberService2.join(newMember);

        // Redirect를 한다.
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService2.fetchMembers();
        model.addAttribute("members", members);

        return "member/memberList";
    }
}
