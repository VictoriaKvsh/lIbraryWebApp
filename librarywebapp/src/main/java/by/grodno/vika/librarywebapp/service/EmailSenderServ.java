package by.grodno.vika.librarywebapp.service;

import by.grodno.vika.librarywebapp.config.Mail;

public interface EmailSenderServ {
	String getHtmlContent(Mail mail);
}
