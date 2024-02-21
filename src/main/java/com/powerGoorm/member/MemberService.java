package com.powerGoorm.member;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public List<MemberDto> getMember() {
		return memberRepository.findAll()
			.stream()
			.map(Member::toDto)
			.collect(Collectors.toList());
	}

	public List<MemberDto> searchByName(String name) {
		List<MemberDto> result = memberRepository.findAllByName(name)
			.stream()
			.map(Member::toDto)
			.collect(Collectors.toList());

		if (result.isEmpty()) {
			throw new NoSuchElementException("No member found with name:" + name);
		}
		return result;
	}
}
