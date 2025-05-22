package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.entity.Image;
import com.lhbnt.ticketreservation.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageService {
    List<Image> addMultipleImages(Long movieId, List<MultipartFile> files) throws IOException;
    Optional<Image> getImage(UUID imageId);
    List<Image> getMovieImages(Long movieId);
    Optional<Movie> getMovieWithImages(Long movieId);
}
