package com.powerGoorm.member;

import lombok.Data;

@Data
public class MemberDto {
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

