package com.demo.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.model.ResponseDTO;
import com.demo.repo.UserRepo;

@RestController
@RequestMapping("/api")
public class CustomerAPI {
	@Autowired
	private UserRepo userRepo;

	// thêm customer
	@PostMapping("/customers")
	public ResponseEntity<ResponseDTO> add(@RequestBody User user) {
		user.setRoles(Arrays.asList("CUSTOMER"));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return ResponseEntity.status(200)
				.body(new ResponseDTO("Them thanh cong", 200, Arrays.asList(userRepo.save(user))));

	}

	// sửa customer
	@PutMapping("/customers")
	public ResponseEntity<ResponseDTO> put(@RequestBody User user) {
		user.setRoles(Arrays.asList("CUSTOMER"));
		return ResponseEntity.status(200)
				.body(new ResponseDTO("Sửa thành công", 200, Arrays.asList(userRepo.save(user))));

	}

	// tìm theo id
	@GetMapping("/customers/{id}")
	public ResponseEntity<ResponseDTO> get(@PathVariable("id") long id) {

		return ResponseEntity.status(200)
				.body(new ResponseDTO("Tìm thấy người dùng", 200, Arrays.asList(userRepo.findById(id))));

	}

	// tìm theo tên
	@GetMapping("/customers/findby/{name}")
	public ResponseEntity<ResponseDTO> get(@PathVariable("name") String name) {
		return ResponseEntity.status(200)
				.body(new ResponseDTO("Tìm thấy người dùng", 200, Arrays.asList(userRepo.findByName(name))));

	}

	// xóa customer
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable("id") long id) {
		userRepo.deleteById(id);
		return ResponseEntity.status(200).body(new ResponseDTO("Xóa thành công", 200, null));

	}

	// gọi ra list danh sachs
	@GetMapping("/customers")
	public ResponseEntity<ResponseDTO> findAll() {
		List<User> list = userRepo.findAll();
		List<Object> objectList = new ArrayList<>();
		for (User user : list) {
			for (String role : user.getRoles()) {
				if (role.contains("ROLE_CUSTOMER")) {
					objectList.add(user);
				}

			}
		}
		return ResponseEntity.status(200).body(new ResponseDTO("Tìm thấy người dùng", 200, objectList));

	}

}
