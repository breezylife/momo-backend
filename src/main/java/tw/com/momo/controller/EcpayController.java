package tw.com.momo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.payload.request.EcpayDto;
import tw.com.momo.utils.AllInOneUtils;

@RestController
@RequestMapping("/api")
public class EcpayController {
	
	@PostMapping("/checkOutOneTime")
	@CrossOrigin
	public ResponseEntity<?> checkOutOneTime(@RequestBody EcpayDto ecpay) {
		AllInOneUtils allInOneUtils = new AllInOneUtils();
		String form = allInOneUtils.genAioCheckOutOneTime(ecpay);

		return ResponseEntity.ok(form);
	}
	
	@PostMapping("/checkOutATM")
	@CrossOrigin
	public ResponseEntity<?> checkOutATM(@RequestBody EcpayDto ecpay) {
		AllInOneUtils allInOneUtils = new AllInOneUtils();
		String form = allInOneUtils.genAioCheckOutATM(ecpay);

		return ResponseEntity.ok(form);
	}
	
}
