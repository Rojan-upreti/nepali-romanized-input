// REST controller exposing the JSON endpoint consumed by the Next.js page.
package com.lekhai.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class TransliterationController {
  private final TransliterationService service = new TransliterationService();

  @PostMapping("/transliterate")
  public ResponseEntity<Map<String, String>> transliterate(@Valid @RequestBody TransliterationRequest request) {
    return ResponseEntity.ok(Map.of("input", request.text(), "output", service.transliterate(request.text())));
  }

  public record TransliterationRequest(@NotBlank @Size(max = 500) String text) {}
}
