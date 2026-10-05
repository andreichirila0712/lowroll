package dev.andrei.chirila.lowroll.security;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.commands.scan.result.ScanResult;

import java.io.IOException;
import java.io.InputStream;

public class MultipartSafetyValidator implements ConstraintValidator<SecureMultipartFile, MultipartFile> {
    private String failedToVerifySafetyMessage;
    private String virusDetectedMessage;
    private final ClamavClient clamavClient;

    public MultipartSafetyValidator(ClamavClient clamavClient) {
        this.clamavClient = clamavClient;
    }

    @Override
    public void initialize(SecureMultipartFile constraintAnnotation) {
        this.failedToVerifySafetyMessage = constraintAnnotation.failedToVerifySafetyMessage();
        this.virusDetectedMessage = constraintAnnotation.virusDetectedMessage();
    }

    @Override
    public boolean isValid(MultipartFile pdf, ConstraintValidatorContext context) {
        if (pdf == null) {
            return true;
        }
        context.disableDefaultConstraintViolation();

        try (InputStream input = pdf.getInputStream()) {
            ScanResult result = this.clamavClient.scan(input);

            if (result instanceof ScanResult.VirusFound) {
                return setValidationError(virusDetectedMessage, context);
            }
        } catch (IOException ex) {
            return setValidationError(failedToVerifySafetyMessage, context);
        }

        return true;
    }

    private static boolean setValidationError(String message, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
