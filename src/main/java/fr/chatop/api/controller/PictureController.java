package fr.chatop.api.controller;

import fr.chatop.api.services.IPictureService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {

    private final IPictureService pictureService;

    @Operation(
        summary = "Get picture",
        description = "Retrieve picture by id"
    )
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPicture(@PathVariable String id) {
        return pictureService.getPicture(id);
    }

}
