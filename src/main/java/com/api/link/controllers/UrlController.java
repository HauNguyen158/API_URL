package com.api.link.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.link.handel.CreateURL;
import com.api.link.model.Url;
import com.api.link.repository.UrlRepository;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UrlController {

	@Autowired
	private UrlRepository repository;
	private CreateURL createURL = new CreateURL();

	@GetMapping("/all")
	public ResponseEntity<List<Url>> getUsers() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/add")
    public ResponseEntity<Url> addURL(@RequestBody String link){
		System.out.println(repository.count());
		String md = createURL.random(repository.count());

		Url _url = new Url(md, link);
		
		System.out.println(md);
		return new ResponseEntity<>(repository.save(_url), HttpStatus.CREATED);
    }

	@PostMapping("/user-add")
    public ResponseEntity<Object> userURL(@RequestBody Url url){
		try {
			repository.findById(url.getID()).get();
			return new ResponseEntity<>("URL already exist", HttpStatus.EXPECTATION_FAILED);
		
		} catch (Exception e) {
			String id = url.getID();
			
			if(id.length() > 0 && id.length() < 17){
				url.setTime();
				return new ResponseEntity<>(repository.save(url), HttpStatus.CREATED);
			}
			else
				return new ResponseEntity<>("URL length from 1 to 16 characters", HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	@DeleteMapping("del")
	public ResponseEntity<Url> delAll(){
		repository.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
