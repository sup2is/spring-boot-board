package me.sup2is.board.member;

import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;

    @GetMapping("/member/create")
    public String create(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/member/create";
    }

    @PostMapping("/member/create")
    public String createMember(@ModelAttribute @Valid MemberForm memberForm, BindingResult bindingResult) throws DuplicateMemberException {
        if(bindingResult.hasErrors()) {
            //todo 넘어온 field error 매핑해서 다시 /member/create 로 보내주는 코드 필요함
        }
        memberService.save(memberForm.createMember());
        return "redirect:/";
    }




}
