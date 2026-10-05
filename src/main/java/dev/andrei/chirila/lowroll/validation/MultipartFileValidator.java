package dev.andrei.chirila.lowroll.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MultipartFileValidator implements ConstraintValidator<ValidFileType, MultipartFile> {
    private static final Tika tika = new Tika();

    private String[] allowedFileTypes;
    private long maxFileSize;
    private String invalidFileTypeMessage;
    private String invalidSizeMessage;

    @Override
    public void initialize(ValidFileType constraintAnnotation) {
        this.allowedFileTypes = constraintAnnotation.allowed();
        this.maxFileSize = constraintAnnotation.maxFileSize();
        this.invalidSizeMessage = constraintAnnotation.invalidSizeMessage();
        this.invalidFileTypeMessage = constraintAnnotation.invalidFileTypeMessage();
    }

    @Override
    public boolean isValid(MultipartFile pdf, ConstraintValidatorContext context) {
        if (pdf == null) {
            return true;
        }
        context.disableDefaultConstraintViolation();

        if(pdf.getSize() > maxFileSize) {
            return setValidationError(invalidSizeMessage, context);
        }

        try {
            String mimeType = tika.detect(pdf.getInputStream());

            final List<String> types = Arrays.stream(allowedFileTypes).toList();
            if (!types.contains(mimeType)) {
                return setValidationError(invalidFileTypeMessage, context);
            }
        } catch (IOException ex) {
            return setValidationError("Could not detect file type", context);
        }

        return true;
    }

    private static boolean setValidationError(String message, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
