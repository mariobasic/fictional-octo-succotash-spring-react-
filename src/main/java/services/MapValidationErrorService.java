package services;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MapValidationErrorService {

  public ResponseEntity<?> MapValidationService(BindingResult result) {
    if (result.hasErrors()) {

      Map<String, String> errorMap = result.getFieldErrors()
                                           .stream()
                                           .collect(getFieldErrorMapCollector());

      return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    return null;
  }

  private Collector<FieldError, ?, Map<String, String>> getFieldErrorMapCollector() {
    return Collectors.toMap(FieldError::getField,
        DefaultMessageSourceResolvable::getDefaultMessage, (s, s2) -> s2);
  }

}
