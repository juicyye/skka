package kozin.skka.web.controller;

import kozin.skka.domain.service.CommentService;
import kozin.skka.domain.service.PostService;
import kozin.skka.domain.service.dto.CommentForm;
import kozin.skka.domain.service.dto.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/new")
    public String createComment(@Validated CommentForm form, BindingResult bindingResult, RedirectAttributes rttr) {
        if(bindingResult.hasErrors()) {
            log.info("comment Error : {}", bindingResult.getAllErrors());
            return "redirect:/comment";
        }
        rttr.addAttribute("id", form.getPostId());
        commentService.save(form);
        return "redirect:/post/detail/{id}";
    }
}
