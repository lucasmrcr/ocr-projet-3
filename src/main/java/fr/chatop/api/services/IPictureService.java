package fr.chatop.api.services;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    String savePicture(MultipartFile picture);

    byte[] getPicture(String id);
}
