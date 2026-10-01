// MVC page controller that renders the JSP view at the application root.
package com.lekhai.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  @GetMapping("/")
  public String home() {
    return "index";
  }
}
