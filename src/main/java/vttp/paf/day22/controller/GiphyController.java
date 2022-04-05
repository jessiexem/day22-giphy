package vttp.paf.day22.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vttp.paf.day22.model.Giphy;
import vttp.paf.day22.service.GiphyService;

import java.util.List;
import java.util.Optional;

@Controller
public class GiphyController {

    @Autowired
    private GiphyService giphySvc;

    @PostMapping("/search")
    public String searchGiphy(@RequestBody MultiValueMap<String,String> form, Model model) {
        System.out.println("In controller");

        String phrase = form.getFirst("phrase");
        String limit = form.getFirst("limit");
        String rating = form.getFirst("rating");

        System.out.println(">>>controller"+phrase+limit+rating);
        Optional<List<Giphy>> opt = giphySvc.getGiphys(phrase,limit,rating);

        if (opt.isEmpty()) {
            return "404";
        }

        List<Giphy> giphyList = opt.get();

//        giphyList.stream().forEach(g-> System.out.println(g.getImageUrl()));

        model.addAttribute("p",phrase);
        model.addAttribute("giphyList",giphyList);

        return "display";
    }
}
