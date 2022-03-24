package com.demothefirstspring.the.first.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class UploadFileServiceImpl implements IUploadFileService{
	private final Logger log= LoggerFactory.getLogger(UploadFileServiceImpl.class);

	 private final static String DIRECTORY_UPLOAD="uploads";
	@Override
	public Resource uplodad(String photoName) throws MalformedURLException {
		Path filePath = getPath(photoName);
		log.info(filePath.toString());
		Resource recurso = new UrlResource(filePath.toUri());
		
			if(!recurso.exists()&& !recurso.isReadable()) {
				filePath = Paths.get("src/main/resources/static/images").resolve("default.jpg").toAbsolutePath();
	
			recurso = new UrlResource(filePath.toUri());
			
			log.error("Error, no se pudo cargar la imagen: "+ photoName);
			}
			return recurso;
	} 

	@Override
	public String copy(MultipartFile file) throws IOException {
		 String nameFile = UUID.randomUUID().toString()+"_"+file.getOriginalFilename().replace(" ", "");
		 Path rutaFile= getPath(nameFile);
				 
		 log.info(rutaFile.toString());
		 
		Files.copy(file.getInputStream(), rutaFile);
					
		return nameFile;
	}

	@Override
	public boolean deletePhoto(String photoName) {
		if(photoName !=null && photoName.length()>0) {
			 
			 Path rutaFotoAnterior = Paths.get("uploads").resolve(photoName).toAbsolutePath();
			
			 File previousPhotoFile= rutaFotoAnterior.toFile();
			 if(previousPhotoFile.exists()&& previousPhotoFile.canRead()) {
				 previousPhotoFile.delete();
				 return true;
			 }
		 }
		return false;
	}

	@Override
	public Path getPath(String photoName) {
		return Paths.get(DIRECTORY_UPLOAD).resolve(photoName).toAbsolutePath();
		
	}

}
