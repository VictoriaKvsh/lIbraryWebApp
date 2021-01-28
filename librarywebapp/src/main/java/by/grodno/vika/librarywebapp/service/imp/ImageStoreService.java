package by.grodno.vika.librarywebapp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.Avatar;
import by.grodno.vika.librarywebapp.service.StorageService;
import by.grodno.vika.librarywebapp.service.UserService;


@Service
public class ImageStoreService implements StorageService {

	@Autowired
	private UserService service;
	
	@Override
	public Avatar getFile(Integer id) {
		User user = service.getUser(id);
		if (user.getPicture() != null && user.getPicture().getFileLocation() != null) {
			Avatar avatar = new Avatar();
			avatar.setFullFilePath(user.getPicture().getFileLocation());
			return avatar;
		}
		return null;
	}

}