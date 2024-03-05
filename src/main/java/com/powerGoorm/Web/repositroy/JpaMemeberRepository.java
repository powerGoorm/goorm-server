package com.powerGoorm.Web.repositroy;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.powerGoorm.member.Member;
import com.powerGoorm.member.MemberDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Repository
@Slf4j
public class JpaMemeberRepository implements com.powerGoorm.Web.repositroy.Repository {

	private final EntityManager em;

	@Override
		public void save(Member m) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode(m.getPassword());
			m.setPassword(password);

		em.persist(m);

	}

	@Override
	public void update(String id, MemberDto memberDto) {
		String query = "update Member m set m.git =:git,m.intro=:intro  where m.id=:id";
		em.createQuery(query)
			.setParameter("git", memberDto.getGit())
			.setParameter("intro", memberDto.getIntro())
			.setParameter("id", id)
			.executeUpdate();

	}

	@Override
	public Optional<Member> findById(String id) {
		try {
			String query = "select m from Member m where m.id=:id";
			Member m = em.createQuery(query, Member.class)
				.setParameter("id", id)
				.getSingleResult();
			return Optional.ofNullable(m);
		} catch (NoResultException e) {

			return Optional.ofNullable(null);
		}

	}

	public boolean CheckPassword(String id, String password) {
		try {
			String query = "select m from Member m where m.id=:id";
			Member member = em.createQuery(query, Member.class)
				.setParameter("id",id)
				.getSingleResult();

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			return !encoder.matches(password, member.getPassword());
		} catch (NoResultException e) {
			return true;
		}

	}

	public void UpdatePassword(String id, String password) {
		String query = "update Member m set m.password=:password where m.id=:id";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedpassword = encoder.encode(password);
		em.createQuery(query)
			.setParameter("password", hashedpassword)
			.setParameter("id", id)
			.executeUpdate();

	}
}
