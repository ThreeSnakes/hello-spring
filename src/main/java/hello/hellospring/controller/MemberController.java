package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import hello.hellospring.service.MemberService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private MemberService memberService;
    private MemberService2 memberService2;

    @Autowired
    public MemberController(MemberService memberService, MemberService2 memberService2) {
        this.memberService = memberService;
        this.memberService2 = memberService2;
    }
}
