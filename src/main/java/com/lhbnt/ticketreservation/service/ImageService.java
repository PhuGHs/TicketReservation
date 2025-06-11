package com.lhbnt.ticketreservation.service;

import com.lhbnt.ticketreservation.entity.Image;
import com.lhbnt.ticketreservation.entity.enumeration.ResourceType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Service
public interface ImageService {
    List<String> uploadWithResourceImages(UUID resourceId, ResourceType resourceType, List<MultipartFile> files);
    Image getImage(UUID imageId);
    List<Image> getMovieImages(UUID movieId);
    String getUrl(UUID imageId);
    List<String> uploadImages(List<MultipartFile> files);
}
