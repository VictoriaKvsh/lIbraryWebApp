package by.grodno.vika.librarywebapp.service;

import java.util.List;

import by.grodno.vika.librarywebapp.domain.Subscription;

public interface SubscriptionService {
	List<Subscription> getSubscription();

	void addSubscription(Subscription subscription);

	void deleteSubscription(Integer number);
}
