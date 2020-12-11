package by.grodno.vika.librarywebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Integer>{

}
