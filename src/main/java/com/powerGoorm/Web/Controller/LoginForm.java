package com.powerGoorm.Web.Controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.powerGoorm.Web.repositroy.JpaMemeberRepository;
import com.powerGoorm.Web.service.EmailService;
import com.powerGoorm.Web.service.LoginMemberService;
import com.powerGoorm.domain.Data;
import com.powerGoorm.domain.Email.CheckUserInput;
import com.powerGoorm.domain.Email.Emails;
import com.powerGoorm.domain.Exception.Errors.CodeMistMatch.CodeMisMatch;
import com.powerGoorm.domain.Exception.Errors.CodeMistMatch.MailCodeMisMatchError;
import com.powerGoorm.domain.Exception.Errors.NoIdError.NotFoundIdError;
import com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror.NotPassword;
import com.powerGoorm.domain.Exception.Errors.UnCorrectPassworderror.UnCorrectPasswordError;
import com.powerGoorm.domain.Exception.Errors.idexisterror.NoId;
import com.powerGoorm.domain.Login.Login;
import com.powerGoorm.domain.Login.PasswordDto;
import com.powerGoorm.domain.Status;
import com.powerGoorm.domain.Sucess.MakeSucessResponseWithoutData;
import com.powerGoorm.domain.Sucess.SucessResp;
import com.powerGoorm.member.Member;
import com.powerGoorm.member.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginForm {
	private final EmailService emailService;

	private final LoginMemberService memberService;

	private final JpaMemeberRepository jpaMemeberRepository;

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<SucessResp<Null>> PostLoginPage(@ModelAttribute Login login, HttpServletRequest req) {

		String id = login.getId();

		Optional<Member> member = memberService.FindByUserId(id);

		if (!member.isPresent()) {
			log.info("{}", member.isPresent());
			throw new NotFoundIdError("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST, new NoId());
		} else if (memberService.CheckPassword(id, login.getPassword())) {

			throw new UnCorrectPasswordError("비밀번호가 일치하지않습니다.", HttpStatus.BAD_REQUEST, new NotPassword());
		}

		HttpSession session = req.getSession();
		session.setAttribute("id", id);
		MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData = new MakeSucessResponseWithoutData<>();
		return makeSucessResponseWithoutData.MakeSucessResp(
			new Status(String.valueOf(HttpStatus.OK.value()), "로그인 성공"), new Data<Null>(null));

	}

	@PostMapping("/logout")
	@ResponseBody
	public ResponseEntity<SucessResp<Null>> LogOut(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String id = (String)session.getAttribute("id");
		if (session != null) {
			session.invalidate();

		}
		MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData = new MakeSucessResponseWithoutData<>();
		return makeSucessResponseWithoutData.MakeSucessResp(
			new Status(String.valueOf(HttpStatus.OK.value()), "로그 아웃 성공"), new Data<Null>(null));
	}

	@PostMapping("/update")
	public ResponseEntity<SucessResp<Null>> PostUpdate(@ModelAttribute MemberDto memberDto, HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		String id = (String)session.getAttribute("id");

		memberDto.setId(id);
		memberService.UpdateData(id, memberDto);

		MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData = new MakeSucessResponseWithoutData<>();
		return makeSucessResponseWithoutData.MakeSucessResp(
			new Status(String.valueOf(HttpStatus.OK.value()), "업데이트 성공"), new Data<Null>(null));
	}

	@PostMapping("/mail")
	public ResponseEntity<SucessResp<Null>> SendingMail(HttpServletRequest req) {

		Random r = new Random();
		char[] sendCode = new char[10];
		for (int i = 0; i < 10; i++) {

			int k = r.nextInt(41, 88);
			sendCode[i] = (char)k;

		}
		HttpSession session = req.getSession(false);

		String sendCodes = String.valueOf(sendCode);
		emailService.SendMail((String) session.getAttribute("id"), "인증요청", sendCodes);


		session.setAttribute("answer_code", sendCodes);

		MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData = new MakeSucessResponseWithoutData<>();
		return makeSucessResponseWithoutData.MakeSucessResp(
			new Status(String.valueOf(HttpStatus.OK.value()), "인증 메일 전송성공"), new Data<Null>(null));
	}

	@PostMapping("/mail2")
	public ResponseEntity<SucessResp<Null>> Certification(@ModelAttribute CheckUserInput checkUserInput,
		HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String answer = (String)session.getAttribute("answer_code");

		if (answer.equals(checkUserInput.getUserInput())) {
			session.setAttribute("check", "true");
			MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData = new MakeSucessResponseWithoutData<>();

			return makeSucessResponseWithoutData.MakeSucessResp(
				new Status(String.valueOf(HttpStatus.OK.value()), "메일 인증 성공"), new Data<Null>(null));

		}
		throw new MailCodeMisMatchError("입력코드 불일치", HttpStatus.OK, new CodeMisMatch(checkUserInput.getUserInput()));

	}

	@PostMapping("/passwords")
	public ResponseEntity<SucessResp<Null>> ChangePassWord(@ModelAttribute PasswordDto passwordDto,
		HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		String id = (String)session.getAttribute("id");
		memberService.UpdatePassword(id, passwordDto.getPassword());
		session.setAttribute("answer", null);
		session.setAttribute("check", null);

		MakeSucessResponseWithoutData<Null> makeSucessResponseWithoutData = new MakeSucessResponseWithoutData<>();
		return makeSucessResponseWithoutData.MakeSucessResp(
			new Status(String.valueOf(HttpStatus.OK.value()), "비밀번호 변경 성공"), new Data<Null>(null));

	}

}
