package com.vnpay.validator.enums;

import com.vnpay.constant.ErrorCodeConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValueValidator.class)
public @interface ValidateEnumValue {

    /**
     * The error message to be returned when the field's value is not a valid enum constant.
     *
     * @return The default error message
     */
    String message() default ErrorCodeConstant.INVALID_VALIDATE;

    /**
     * Used to group constraints. Allows the annotation to be used in different validation groups.
     *
     * @return The groups to which the annotation belongs
     */
    Class<?>[] groups() default {};

    /**
     * Used to carry additional data to the validation framework.
     *
     * @return The payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * The enum class to validate against. This specifies which enum type the field's value must match.
     *
     * @return The class of the enum
     */
    Class<? extends Enum<?>> enumClass();
}
