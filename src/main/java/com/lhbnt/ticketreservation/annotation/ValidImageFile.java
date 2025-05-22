package com.lhbnt.ticketreservation.annotation;

import com.lhbnt.ticketreservation.config.Messages;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageFileValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageFile {
    String message() default Messages.INVALID_IMAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
