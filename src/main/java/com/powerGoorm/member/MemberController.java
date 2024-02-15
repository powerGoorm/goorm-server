package com.powerGoorm.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/member")
	public List<MemberDto> getMember() {
		return memberService.getMember();
	}

	@GetMapping("/member/search")
	public List<MemberDto> searchMember(@RequestParam String name) {
		return memberService.searchByName(name);
	}
}
