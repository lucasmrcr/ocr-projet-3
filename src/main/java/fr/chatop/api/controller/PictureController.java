package fr.chatop.api.controller;

import fr.chatop.api.services.IPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {

    private final IPictureService pictureService;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPicture(@PathVariable String id) {
        return pictureService.getPicture(id);
    }

}
