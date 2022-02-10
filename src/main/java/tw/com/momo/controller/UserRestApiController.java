package tw.com.momo.controller;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.ConfirmationTokenRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.ConfirmationTokenBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.LoginDto;
import tw.com.momo.payload.request.OauthRequestDto;
import tw.com.momo.payload.request.PasswordDto;
import tw.com.momo.payload.request.SignUpDto;
import tw.com.momo.payload.request.UserDto;
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
	@CrossOrigin
	public JwtResponse authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		if(userRepository.findByEmail(userDetails.getEmail()).isenabled()) {
			String jwt = jwtUtils.generateJwtToken(authentication);
			return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
		}else {
			return new JwtResponse(null, null, null, null);
		}
//		System.out.println(jwt);
		
	}

	@GetMapping("/user")
	@CrossOrigin
	public ResponseEntity<?> readuser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());
		// System.out.println("123123asdf"+user);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/user")
	@CrossOrigin
	public String registerUser(@RequestBody SignUpDto signUpDto) {

		if (userRepository.existsByUsername(signUpDto.getUsername())) {
//			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);\
			return "{\"error\":1}";
		} else if (userRepository.existsByEmail(signUpDto.getEmail())) {
//			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
			return "{\"error\":1}";
		} else {

			UserBean user = new UserBean();
			user.setEmail(signUpDto.getEmail());
			user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
			user.setBirthday(signUpDto.getBirthday());
			user.setPhone(signUpDto.getPhone());
			user.setGender(signUpDto.getGender());
			user.setAddress(signUpDto.getAddress());
			user.setUsername(signUpDto.getUsername());
			user.setUserphoto(signUpDto.getUserphoto());

			userRepository.save(user);

			ConfirmationTokenBean confirmationToken = new ConfirmationTokenBean(user);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("您好！ 歡迎來到哞哞購物!");
			mailMessage.setFrom("chand312902@gmail.com");
			mailMessage.setText("您好 ! " + user.getUsername() + " , 請驗證信中連結，繼續完成註冊步驟 : "
					+ "http://localhost:8080/api/auth/confirm-account?token="
					+ confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);
		}

//		return new ResponseEntity<>("Plz check the Email to confim your account", HttpStatus.OK);
		return "{\"success\":1}";
	}

	// 修改會員資料
	@PutMapping("/user")
	@CrossOrigin
	public ResponseEntity<?> update(@RequestBody UserDto userDto) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean userBean = userRepository.findByUsername(userDetails.getUsername());

//				userBean.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userBean.setGender(userDto.getGender());
		userBean.setBirthday(userDto.getBirthday());
		userBean.setPhone(userDto.getPhone());
		userBean.setAddress(userDto.getAddress());
		userBean.setUserphoto(userDto.getUserphoto());

		userRepository.save(userBean);

//		return new ResponseEntity<>("會員資料修改成功", HttpStatus.OK);
		return ResponseEntity.ok(userBean);
	}

	// 修改密碼
	@PostMapping("/userpassword")
	@CrossOrigin
	public String changePassword(@RequestBody PasswordDto passwordDto) {
		System.out.println(passwordDto);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());

		if (passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
			if (passwordDto.getPassword().equals(passwordDto.getPassword2())) {
				user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
				userRepository.save(user);
				return "{\"success\":1}";
			} else {
				System.out.println("新密碼不一致");
				return "{\"error\":2}";
			}

		} else {
			System.out.println("密碼輸入錯誤" + passwordEncoder.matches(user.getPassword(),
					passwordEncoder.encode(passwordDto.getOldPassword())));
			System.out.println(user.getPassword() + passwordDto.getOldPassword());
			return "{\"error\":1}";
		}

	}

	@PostMapping("/Oauth")
	@CrossOrigin
	public ResponseEntity<?> oauthRegister(@RequestBody OauthRequestDto oauthRequestDto) {
		UserBean user = new UserBean();
		
		if (userRepository.existsByEmail(oauthRequestDto.getEmail())) {
			user = userRepository.findByEmail(oauthRequestDto.getEmail());
			return ResponseEntity.ok().body(user);
		} else {
			user.setEmail(oauthRequestDto.getEmail());
			user.setPassword(passwordEncoder.encode(oauthRequestDto.getUid()));
			user.setUsername(oauthRequestDto.getDisplayName());
			user.setUserphoto(oauthRequestDto.getPhotoURL());
			user.setEnabled(true);
			userRepository.save(user);
		}
		return ResponseEntity.created(null).body(user);
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

			content = "<h3>Hello ! You have successfully registered!</h3> \r\n"
					+ "<img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f389/72.png\">  \r\n"
					+ "<img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f389/72.png\">  \r\n"
					+ "<img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f389/72.png\">  \r\n"
					+ "<img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f389/72.png\">  \r\n"
					+ "<img src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/1f389/72.png\">  \r\n"
					+ "<script language=\"javascript\">  \r\n"
					+ " setTimeout(\"location.href='http://localhost:3000';\", 1000);  \r\n"
					+ "</script>  ";
//			Optional<UserBean> user = userRepository.findByEmail(token.getUserBean().getEmail());
//			user.get().setEnabled(true);
//			userRepository.save(user);
//			modelAndView.setViewName("accountVerified");
			return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
		} else {
//			modelAndView.addObject("message", "The link is invalid or broken!");
//			modelAndView.setViewName("error");
			content = "<h3><span>The link is invalid or broken!</span></h3>";
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
