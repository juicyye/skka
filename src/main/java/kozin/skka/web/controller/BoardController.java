package kozin.skka.web.controller;

import kozin.skka.domain.repository.condition.CategoryCon;
import kozin.skka.domain.repository.custom.PostCustomRepository;
import kozin.skka.domain.service.PostService;
import kozin.skka.domain.service.dto.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final PostService postService;
    private final PostCustomRepository postCustomRepository;

    @GetMapping("/{category}")
    public String humor(@PathVariable("category") String con, Model model) {
        CategoryCon categoryCon = new CategoryCon();
        categoryCon.setCategory(con);
        List<PostForm> postForms = postCustomRepository.findAllByHumor(categoryCon);
        model.addAttribute("posts", postForms);
        return "board/category/humor";
    }

}
