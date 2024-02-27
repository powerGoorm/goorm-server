package com.powerGoorm.member;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "git", nullable = false)
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
