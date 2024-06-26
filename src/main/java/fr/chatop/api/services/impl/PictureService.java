package fr.chatop.api.services.impl;

import fr.chatop.api.exception.ResponseEntityException;
import fr.chatop.api.services.IPictureService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class PictureService implements IPictureService {

    private final static Logger logger = LoggerFactory.getLogger(PictureService.class);

    @Value("${application.pictures.path}")
    private String picturesPath;

    @Value("${application.pictures.url}")
    private String picturesUrl;

    @Override
    public String savePicture(MultipartFile picture) {
        File pictureFolder = new File(picturesPath);

        // If picture folder does not exist, create it
        if (!pictureFolder.exists() && pictureFolder.mkdirs() || pictureFolder.exists() && pictureFolder.isDirectory()) {
            // That is the new name of the picture, we use UUID to avoid conflicts
            String pictureFileName = UUID.randomUUID().toString();
            File pictureFile = new File(pictureFolder, pictureFileName);

            try {
                // Saving the picture to the file and returning the URL
                picture.transferTo(pictureFile);
                return picturesUrl + "/" + pictureFileName;
            } catch (Exception e) {
                logger.error("Failed to save picture", e);
            }
        }
        throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save picture");
    }

    @Override
    public byte[] getPicture(String id) {
        File pictureFile = new File(picturesPath, id);

        // When picture is not found, we throw an exception which will be caught by exception handler
        if (!pictureFile.exists()) {
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get picture");
        }

        // Reading the picture and returning it as byte array
        try (InputStream inputStream = new FileInputStream(pictureFile)) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            logger.error("Failed to get picture", e);
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get picture");
        }
    }
}
