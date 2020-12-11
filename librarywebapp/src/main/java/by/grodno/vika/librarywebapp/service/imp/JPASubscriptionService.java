package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.Subscription;
import by.grodno.vika.librarywebapp.repo.SubscriptionRepo;
import by.grodno.vika.librarywebapp.service.SubscriptionService;

@Service
public class JPASubscriptionService implements SubscriptionService {

	@Autowired
	SubscriptionRepo repo;

	@Override
	public List<Subscription> getSubscription() {

		return repo.findAll();
	}

	@Override
	public void addSubscription(Subscription subscription) {
		repo.save(subscription);

	}

	@Override
	public void deleteSubscription(Integer number) {
		repo.deleteById(number);

	}

}
