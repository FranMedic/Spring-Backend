package com.demothefirstspring.the.first.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	
	 public Resource uplodad(String photoName) throws MalformedURLException;
	 
	 public String copy(MultipartFile file) throws IOException;
	 
	 public boolean deletePhoto(String photoName);
	 
	 public Path getPath(String photoName);
		 
	 
}
