package by.grodno.vika.librarywebapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import by.grodno.vika.librarywebapp.domain.ReadersBook;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.security.CustomOAuth2User;
import by.grodno.vika.librarywebapp.service.ReadersBookService;
import by.grodno.vika.librarywebapp.service.UserService;

@Controller
public class ReadersBookController {

	@Autowired
	ReadersBookService repo;
	@Autowired
	UserService userService;

	@GetMapping("/reader")
	public String getReadersBook(Model model) {
		List<ReadersBook> readersBook = repo.getReadersBook();
		model.addAttribute("readersBook", readersBook);
		return "bookInfo";
	}

	@PostMapping(path = "/reader/{catalogId}/add")
	public String saveReadersBook(@PathVariable("catalogId") Integer discriptionId,
			ReadersBook readersBook, Authentication authentication) {
		
		
		User user = userService.findByEmail(authentication.getName());
		Integer userId = user.getId();
		
		repo.addReadersBook(discriptionId, userId, readersBook);

		return "redirect:/catalog";
	}

}
