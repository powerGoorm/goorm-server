package com.powerGoorm.member;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
	@Id
	@Column(name = "id", nullable = false)
	@Email(message="이메일 형식을 해주세요")
	@NotNull(message ="아이디를 입력해주세요")
	private String id;


	@Column(name = "name", nullable = false)
	@NotNull(message="이름을 입력해주세요")
	private String name;

	@Column(name = "password", nullable = false)
	@NotNull(message="비밀번호를 입력해주세요")
	private String password;

	@Column(name = "git", nullable = false)
	@NotNull(message="깃주소를 입력해주세요")
	private String git;
	@Column(name = "introduction")
	private String intro;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	public MemberDto toDto() {
		return new MemberDto(
			this.getId(),
			this.getName(),
			this.getGit(),
			this.getIntro()
		);
	}
}
