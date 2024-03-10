package org.aston.ems.admin_service.validation;

import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Validator;
import org.aston.ems.common_lib.exception.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoValidator {

    private final Validator validator;

    public <T> void validate(T dto){
        validator.validate(dto).stream().map(ConstraintViolation::getMessage)
            .reduce((m1, m2) -> String.join(", ", m1, m2))
            .ifPresent(m -> {
                throw new BadRequestException(m);
            });

    }

}
