package jpabook2.jpashop2.controller;

import jpabook2.jpashop2.domain.Address;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
    @PostMapping(value = "/members/new")    //포스트 전송된걸 등록
    public String create(@Valid MemberForm form, BindingResult result) {      //멤버폼에 오류 있으면 result를 통해 리턴.
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);    //저장
        return "redirect:/";    //첫페이지로
    }

    //회원목록조회
        @GetMapping(value = "/members")
        public String list(Model model) {
            List<Member> members = memberService.findMembers();
            model.addAttribute("members", members);
            return "members/memberList";
        }
    }