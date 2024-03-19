package com.powerGoorm.member;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberDto {
	@NotNull(message = "아이디을 입력해주세요")
	private String id;
	private String name;
	private String git;
	private String intro;


	public MemberDto(){};
	public MemberDto(String id, String name, String git, String intro) {
		this.id = id;
		this.name = name;
		this.git = git;
		this.intro = intro;
	}
}

