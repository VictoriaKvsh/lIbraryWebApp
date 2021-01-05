package by.grodno.vika.librarywebapp.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import by.grodno.vika.librarywebapp.domain.ReadersBook;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.ReadersBookService;

@Controller
public class ReadersBookController {
	
	@Autowired
	ReadersBookService repo;
	@Autowired
	UserRepo uRepo;
	
	@GetMapping("/reader")
	public String getReadersBook(Model model) {
		 List<ReadersBook> readersBook = repo.getReadersBook();
		 model.addAttribute("readersBook", readersBook);
		 return "bookInfo";
	}
	
	@PostMapping(path = "/reader/{catalogId}/add")
	public String saveReadersBook(@PathVariable ("catalogId") Integer discriptionId, @AuthenticationPrincipal UserDetails currentUser, ReadersBook readersBook) {
		
		Integer userId = uRepo.findByEmail(currentUser.getUsername()).getId();
		repo.addReadersBook(discriptionId, userId, readersBook);
		
		return "redirect:/catalog";
	}
	
	
	@PutMapping("/@@@@@/{catalogId}")
    public ReadersBook updateStatus(@PathVariable Integer readersBookId, @Valid @RequestBody ReadersBook readersBookRequest) {
        return repo.updateReadersBook(readersBookId, readersBookRequest);
    }
	
	
}
