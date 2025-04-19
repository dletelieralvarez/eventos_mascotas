package com.microserviciosemana3.eventos_mascotas.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaValidaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaValida {
    String message() default "Fecha del Evento no es válida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
