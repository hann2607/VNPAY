package com.vnpay.validator.enums;

import com.vnpay.constant.ErrorCodeConstant;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<ValidateEnumValue, Enum<?>> {

    private Class<? extends Enum<?>> enumClass;

    /**
     * Initializes the validator with the enum class specified in the annotation.
     *
     * @param annotation The annotation containing the enum class to be validated against.
     */
    @Override
    public void initialize(ValidateEnumValue annotation) {
        this.enumClass = annotation.enumClass();
    }

    /**
     * Validates whether the provided enum value is a valid constant of the specified enum class.
     *
     * @param value The enum value to be validated.
     * @param context The context in which the constraint is evaluated.
     * @return true if the value is valid; false otherwise.
     */
    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        // Disable the default constraint violation message
        context.disableDefaultConstraintViolation();

        // Define a custom constraint violation message
        context.buildConstraintViolationWithTemplate(ErrorCodeConstant.INVALID_VALIDATE)
                .addConstraintViolation();

        // If the value is null, it's not valid
        if (value == null) {
            return false;
        }

        // Check if the value is a valid enum constant
        return findEnumConstant(enumClass, value.name()) != null;
    }

    /**
     * Finds an enum constant by its name within the given enum class.
     *
     * @param enumClass The class of the enum to search in.
     * @param value The name of the enum constant to find.
     * @return The matching enum constant if found; null otherwise.
     */
    private static Enum<?> findEnumConstant(Class<? extends Enum<?>> enumClass, String value) {
        // Iterate over all enum constants and match by name
        for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equals(value)) {
                return enumConstant;
            }
        }
        return null;
    }
}

