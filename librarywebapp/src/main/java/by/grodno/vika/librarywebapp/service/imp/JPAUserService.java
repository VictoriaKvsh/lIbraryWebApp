package by.grodno.vika.librarywebapp.service.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.domain.UserPicture;
import by.grodno.vika.librarywebapp.domain.UserRole;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.dto.UserRegistrationDTO;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.repo.UserCredentialsRepo;
import by.grodno.vika.librarywebapp.repo.UserPictureRepo;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.UserService;

@Service
public class JPAUserService implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserCredentialsRepo credRepo;
	@Autowired
	private UserPictureRepo pictureRepo;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<User> getUsers(int pageNum, String sortField) {
		int pageSize = 8;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(sortField).ascending());
		return userRepo.findAll(pageable);
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userRepo.save(user);
		if (user.getCredentials().getActive() == false) {
			emailSenderService.contextUserInfo(user);
		}

	}

	@Override
	public void updateUser(UserDTO userDTO, MultipartFile file) {
		User findById = userRepo.findById(userDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		findById.setFirstName(userDTO.getFirstName());
		findById.setLastName(userDTO.getLastName());
		findById.setPhoneNumber(userDTO.getPhoneNumber());
		findById.setAddress(userDTO.getAddress());
		if (file != null) {
			String string = UUID.randomUUID().toString();
			File file2 = new File(string);
			UserPicture picture = findById.getPicture();
			if (picture == null) {
				picture = new UserPicture();
			}
			picture.setFileName(file2.getName());
			picture.setFileLocation(file2.getAbsolutePath());

			findById.setPicture(picture);

			try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(file2)) {
				IOUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}

			pictureRepo.save(picture);
		}

		userRepo.save(findById);
	}

	@Override
	public void deleteUser(Integer number) {
		userRepo.deleteById(number);
	}

	@Override
	public List<User> findByExample(User userSample) {
		Example<User> exp = Example.of(userSample,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		return userRepo.findAll(exp);
	}

	@Override
	public List<User> findByLName(String lastName) {
		return userRepo.findByLastName(lastName);
	}

	@Override
	public User getUser(Integer id) {
		return userRepo.getOne(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
//	@Transactional
	public void activateUser(Integer id) {
		Optional<User> findById = userRepo.findById(id);

		findById.map(user -> {
			// UserCredentials cred = user.getCredentials();
			// cred.setActive(true);
			user.getCredentials().setActive(true);
			user.setUserRequestToken(null);
			// credRepo.save(cred);

			userRepo.save(user);
			return user;
		}).orElseThrow(() -> new ResourceNotFoundException(
				"There is no User registered with this email: " + findById.get().getEmail()));

	}

	@Override
	public void updateUserRequestToken(String token, String email) {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			user.setUserRequestToken(token);
			userRepo.save(user);
		} else {
			throw new ResourceNotFoundException("There is no User registered with this email: " + email);
		}
	}

	@Override
	public User getByUserRequestToken(String token) {
		return userRepo.findByUserRequestToken(token);
	}

	@Override
//	@Transactional
	public void updatePassword(User user, String newPassword) {
		user.getCredentials().setPassword(passwordEncoder.encode(newPassword));
		user.setUserRequestToken(null);
		userRepo.save(user);

	}

	@Override
	public void createNewUserAfterOAuthLoginSuccess(UserRegistrationDTO userDTO) {

		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setRole(UserRole.USER);
		UserCredentials creds = new UserCredentials(null, new Date(), true, null);
		user.setCredentials(creds);

		userRepo.save(user);
	}

}
