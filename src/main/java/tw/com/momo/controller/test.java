package tw.com.momo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.utils.AllInOneUtils;

@RestController
@RequestMapping("/api")
public class test {
	
	@GetMapping("/ecpay")
	@CrossOrigin
	public ResponseEntity<?> pay() {
		AllInOneUtils allInOneUtils = new AllInOneUtils();
		String form = allInOneUtils.genAioCheckOutCVS();
//		System.out.println(form);
//		
		return ResponseEntity.ok(form);
	}
}
