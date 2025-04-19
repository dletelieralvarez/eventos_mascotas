package com.microserviciosemana3.eventos_mascotas.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

public class FechaValidaValidator implements ConstraintValidator<FechaValida, String> {
    @Override
    public boolean isValid(String fecha, ConstraintValidatorContext context) {
        if (fecha == null || fecha.isEmpty()) return false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // No permite fechas como 32/01/2024

        try {
            sdf.parse(fecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}