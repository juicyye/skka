package kozin.skka.web.controller;

import kozin.skka.domain.entity.Category;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ModelAttribute("categoryTypes")
    public Category[] categories(){
        return Category.values();
    }
}
