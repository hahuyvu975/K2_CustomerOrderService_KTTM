package com.demo.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Order;
import com.demo.entity.User;
import com.demo.model.ResponseDTO;
import com.demo.repo.OrderRepo;
import com.demo.repo.UserRepo;
@RestController
@RequestMapping("/api")
public class OrderAPI {
	@Autowired
	private OrderRepo orderRepo;

	@PostMapping("/orders")
	public ResponseEntity<ResponseDTO> add(@RequestBody Order order) {
		
		order.setDate(new Date());
		return ResponseEntity.status(200)
				.body(new ResponseDTO("Them thanh cong", 200, Arrays.asList(orderRepo.save(order))));
 
	}

	@PutMapping("/orders")
	public ResponseEntity<ResponseDTO> put(@RequestBody Order order) {
		order.setDate(new Date());
		return ResponseEntity.status(200)
				.body(new ResponseDTO("Sửa thành công", 200, Arrays.asList(orderRepo.save(order))));

	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<ResponseDTO> get(@PathVariable("id") long id) {

		return ResponseEntity.status(200)
				.body(new ResponseDTO("Tìm thấy order", 200, Arrays.asList(orderRepo.findById(id))));

	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable("id") long id) {
		orderRepo.deleteById(id);
		return ResponseEntity.status(200).body(new ResponseDTO("Xóa thành công", 200, null));

	}

	@GetMapping("/orders")
	public ResponseEntity<ResponseDTO> findAll() {
		List<Object> list  = (List)orderRepo.findAll();
	
		return ResponseEntity.status(200).body(new ResponseDTO("Tìm thấy danh sách order", 200,list));

	}
}
