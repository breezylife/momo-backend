package tw.com.momo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tw.com.momo.dao.ConfirmationTokenRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.ConfirmationTokenBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.LoginDto;
import tw.com.momo.payload.request.SignUpDto;
import tw.com.momo.payload.response.JwtResponse;
import tw.com.momo.service.EmailSenderService;
import tw.com.momo.service.UserDetailsImpl;
import tw.com.momo.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class UserRestApiController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		System.out.println("1234" + authentication.getPrincipal());
		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
	}

//	@RequestMapping(value = "/register", method = RequestMethod.GET)
//	public ResponseEntity<?> displayRegistration(ModelAndView modelAndView, UserBean user) {
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("register");
//		return modelAndView;
//	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		} else if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		} else {

			UserBean user = new UserBean();
			user.setEmail(signUpDto.getEmail());
			user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
			user.setBirthday(signUpDto.getBirthday());
			user.setPhone(signUpDto.getPhone());
			user.setAddress(signUpDto.getAddress());
			user.setUsername(signUpDto.getUsername());
			user.setUserphoto(signUpDto.getUserphoto());

			userRepository.save(user);

			ConfirmationTokenBean confirmationToken = new ConfirmationTokenBean(user);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("chand312902@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/api/auth/confirm-account?token="
					+ confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);
		}

		return new ResponseEntity<>("Plz check the Email to confim your account", HttpStatus.OK);
	}

	@GetMapping("/confirm-account")
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ConfirmationTokenBean token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		String content;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_HTML);
		if (token != null) {
			UserBean user = token.getUserBean();
			user.setEnabled(true);
			userRepository.save(user);

			content = "<h3>Congratulations! Your account has been activated and email is verified!</h3>";
//			Optional<UserBean> user = userRepository.findByEmail(token.getUserBean().getEmail());
//			user.get().setEnabled(true);
//			userRepository.save(user);
//			modelAndView.setViewName("accountVerified");
			return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
		} else {
//			modelAndView.addObject("message", "The link is invalid or broken!");
//			modelAndView.setViewName("error");
			content = "<h3><span>The link is invalid or broken</span></h3>";
			return new ResponseEntity<String>(content, responseHeaders, HttpStatus.NOT_FOUND);
		}

//		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
//		// add check for username exists in a DB
//		if (userRepository.existsByUsername(signUpDto.getUsername())) {
//			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
//		}
//
//		// add check for email exists in DB
//		if (userRepository.existsByEmail(signUpDto.getEmail())) {
//			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
//		}
//
//		// create user object
//		UserBean user = new UserBean();
//		user.setUsername(signUpDto.getUsername());
//		user.setEmail(signUpDto.getEmail());
//		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
//
//		userRepository.save(user);
//
//		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//
//	}
}
