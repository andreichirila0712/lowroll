package dev.andrei.chirila.lowroll.security;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartSafetyValidator.class)
public @interface SecureMultipartFile {
    String message() default "Failed to verify file safety";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String failedToVerifySafetyMessage() default "Failed to verify file safety";
    String virusDetectedMessage() default "Virus detected";
}
