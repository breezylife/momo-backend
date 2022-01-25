package tw.com.momo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.CommentRepository;
import tw.com.momo.dao.OrderDetailRepository;
import tw.com.momo.dao.ProductRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.CommentBean;
import tw.com.momo.domain.OrderDetailBean;
import tw.com.momo.domain.ProductBean;
import tw.com.momo.domain.UserBean;
import tw.com.momo.payload.request.CommentDto;
import tw.com.momo.service.UserDetailsImpl;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	
	@GetMapping("/comment/{id}")
	@CrossOrigin
	public ResponseEntity<?> select(@PathVariable("id") Integer id){
		
		Iterable<CommentBean> comments = commentRepository.findAllByProductsid(id);

		System.out.println(comments);
		return ResponseEntity.ok(comments);
	}	
	
	@PostMapping("/comment/{prid}/{oddid}")
	@CrossOrigin
	public ResponseEntity<?> comment(@PathVariable("prid") Integer prid ,@PathVariable("oddid") Integer oddid, @RequestBody CommentDto commentDto) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userRepository.findByUsername(userDetails.getUsername());
			
			CommentBean comment = new CommentBean(user);
			comment.setboard(commentDto.getBroad());
			comment.setStar(commentDto.getStar());
			comment.setProductsid(prid);
//			comment.setUserid(user.getId());
			
			commentRepository.save(comment);
			
			Optional<OrderDetailBean> setState =orderDetailRepository.findById(oddid);
			OrderDetailBean order=setState.get();
			order.setIscommented(1);
			orderDetailRepository.save(order);
			
			return ResponseEntity.ok(comment);
			
	}
	
	@DeleteMapping("/comment/{id}")
	@CrossOrigin
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		
		Optional<CommentBean> comm = commentRepository.findById(id);
		CommentBean comment = comm.get();
		commentRepository.deleteById(comment.getId());
		
		return new ResponseEntity<>("已刪除評論", HttpStatus.OK);
	}
	
}
