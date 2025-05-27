package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.config.Messages;
import com.lhbnt.ticketreservation.entity.Image;
import com.lhbnt.ticketreservation.exception.ResourceNotFoundException;
import com.lhbnt.ticketreservation.repository.ImageRepository;
import com.lhbnt.ticketreservation.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Value("${server.url}")
    private String serverUrl;

    @Value(("${api.prefix}"))
    private String apiPrefix;

    @Override
    public List<Image> addMultipleImages(UUID movieId, List<MultipartFile> files) {
        if (files.isEmpty()) {
            return List.of();
        }
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            var file = files.get(i);
            Image image = null;
            try {
                image = Image.builder()
                        .resourceId(movieId)
                        .imageData(file.getBytes())
                        .imageOrder(i)
                        .contentType(file.getContentType())
                        .fileSize(file.getSize())
                        .fileName(file.getName())
                        .build();
            } catch (IOException e) {
                log.warn("Skipping file at index {} due to error: {}", i, file.getOriginalFilename(), e);
                continue;
            }
            images.add(image);
        }
        return imageRepository.saveAll(images);
    }

    @Override
    @Transactional(readOnly = true)
    public Image getImage(UUID imageId) {
        Image image = imageRepository.findById(imageId).orElse(null);
        if (Objects.isNull(image))
            throw new ResourceNotFoundException(Messages.IMAGE_NOT_FOUND);
        return image;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> getMovieImages(UUID movieId) {
        return imageRepository.findByResourceIdOrderByImageOrderAsc(movieId);
    }

    @Override
    public String getUrl(UUID imageId) {
        return serverUrl + apiPrefix + "/images/" + imageId;
    }
}
