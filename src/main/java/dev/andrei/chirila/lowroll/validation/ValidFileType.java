package dev.andrei.chirila.lowroll.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.http.MediaType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFileType {
    String[] allowed() default {
            MediaType.APPLICATION_PDF_VALUE
    };

    String message() default "Invalid file type";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    long maxFileSize() default 3072 * 3072;
    String invalidFileTypeMessage() default "Invalid file type. File should be PDF";
    String invalidSizeMessage() default "File size is too big (3MB max)";
}
