package kozin.skka.web.controller;

import kozin.skka.domain.entity.Category;
import kozin.skka.domain.service.PostService;
import kozin.skka.domain.service.dto.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostsController {
    private final PostService postService;


    @GetMapping
    public String post(Model model){
        List<PostForm> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "board/posts";
    }

    @GetMapping("/new")
    public String newPost(@ModelAttribute("post")PostForm form){
        return "board/postForm";

    }

    @PostMapping("/new")
    public String createPost(@Validated @ModelAttribute("post")PostForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("post error {} ", bindingResult.getAllErrors());
            return "board/postForm";
        }
        postService.save(form);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable Long id,Model model) {
        PostForm post = postService.getPost(id);
        model.addAttribute("post", post);
        return "board/post-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PostForm post = postService.getPost(id);
        model.addAttribute("post", post);
        return "board/post-update";
    }

    @PostMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, @ModelAttribute("post") PostForm form, BindingResult bindingResult, RedirectAttributes rttr) {
        if(bindingResult.hasErrors()){
            log.info("post error {} ", bindingResult.getAllErrors());
            return "board/postForm";
        }
        postService.update(id, form);
        return "redirect:/post/detail/{id}";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        postService.delete(id);
        rttr.addAttribute("delete",true);
        return "redirect:/post";
    }


}
