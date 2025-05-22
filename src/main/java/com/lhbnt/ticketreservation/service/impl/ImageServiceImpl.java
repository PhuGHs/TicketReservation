package com.lhbnt.ticketreservation.service.impl;

import com.lhbnt.ticketreservation.entity.Image;
import com.lhbnt.ticketreservation.entity.Movie;
import com.lhbnt.ticketreservation.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    @Override
    public List<Image> addMultipleImages(Long movieId, List<MultipartFile> files) throws IOException {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Image> getImage(UUID imageId) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> getMovieImages(Long movieId) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> getMovieWithImages(Long movieId) {
        return Optional.empty();
    }
}
