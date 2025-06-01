package com.lhbnt.ticketreservation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class ImageListValidator implements ConstraintValidator<ValidImageFile, List<MultipartFile>> {
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp");
    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext constraintValidatorContext) {
        if (files == null || files.isEmpty()) {
            return false;
        }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) return false;
            if (file.getSize() > MAX_FILE_SIZE) return false;
            String contentType = file.getContentType();
            if (!ALLOWED_TYPES.contains(contentType)) return false;
        }

        return true;
    }
}
