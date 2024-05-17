package kozin.skka.web.controller;

import jakarta.validation.constraints.Size;
import kozin.skka.domain.entity.Roles;
import kozin.skka.domain.service.UserService;
import kozin.skka.domain.service.dto.UserForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @ModelAttribute("roleType")
    public Roles[] roles(){
        return Roles.values();
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("user") UserForm userForm) {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signupForm(@ModelAttribute("user") UserForm form) {
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        log.info("signup form: {}", form);
        userService.join(form);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "user/login";
    }
}
