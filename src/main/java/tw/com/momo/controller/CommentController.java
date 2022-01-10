package tw.com.momo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.momo.dao.CommentRepository;
import tw.com.momo.dao.ProductRepository;
import tw.com.momo.dao.UserRepository;
import tw.com.momo.domain.CommentBean;
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
	
	
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		
		Optional<CommentBean> comm = commentRepository.findById(id);
		CommentBean comment = comm.get();
		commentRepository.deleteById(comment.getId());
		
		return new ResponseEntity<>("已刪除評論", HttpStatus.OK);
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<?> select(@PathVariable("id") Integer id){
	
		Iterable<CommentBean> comments = commentRepository.findAllByProductsid(id);
		
		System.out.println(comments);
		
		return ResponseEntity.ok(comments);
	}	
	
	@PostMapping("/comment/{id}")
	public ResponseEntity<?> comment(@PathVariable("id") Integer id , @RequestBody CommentDto commentDto) {
		
		UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer user = userDetails.getId();
	
			CommentBean comment = new CommentBean();
			comment.setIndexx(commentDto.getIndexx());
			comment.setProductsid(id);
			comment.setUserid(user);
			
			System.out.println("com:"+comment);

			commentRepository.save(comment);
		
			return new ResponseEntity<>("已送出評論", HttpStatus.OK);
	
	}
	
}
