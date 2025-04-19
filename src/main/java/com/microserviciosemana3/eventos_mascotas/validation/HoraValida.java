package com.microserviciosemana3.eventos_mascotas.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HoraValidaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HoraValida {
    String message() default "Hora no válida (formato esperado: HH:mm)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
