package com.lhbnt.ticketreservation.controller;

import com.lhbnt.ticketreservation.annotation.ValidImageFile;
import com.lhbnt.ticketreservation.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable UUID id) {
        var image = imageService.getImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getContentType()));
        headers.setContentLength(image.getFileSize());

        return ResponseEntity.ok()
                .headers(headers)
                .body(image.getImageData());
    }

    @PostMapping()
    public ResponseEntity<List<String>> uploadImage(@RequestPart(value = "files") @ValidImageFile List<MultipartFile> files) {
        var urls = imageService.uploadImages(files);
        return ResponseEntity.ok(urls);
    }
}
