package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.grodno.vika.librarywebapp.domain.Subscription;
import by.grodno.vika.librarywebapp.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	SubscriptionService repo;

	@GetMapping("/subscription")
	public List<Subscription> getSubscription() {
		return repo.getSubscription();
	}
	
	@PostMapping(path = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveSubscription(@RequestBody Subscription subscription) {
		repo.addSubscription(subscription);
	}
}
