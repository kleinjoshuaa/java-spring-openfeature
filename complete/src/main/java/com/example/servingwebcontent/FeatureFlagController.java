package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.servingwebcontent.OpenFeatureWrapper;

import java.nio.charset.Charset;
import java.util.Random;

import static com.example.servingwebcontent.PunGenerator.getMeme;
import static com.example.servingwebcontent.PunGenerator.getPun;


@Controller
public class FeatureFlagController {

    private boolean usePunImage = false;
    public RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z').build();

    // This block will be executed when the class is loaded
    static {
        OpenFeatureWrapper.initialize();

    }

    @GetMapping("/")
    public String joke(Model model) {
        // random string on each page hit
        String generatedString = generator.generate(20);
		String flagResult = OpenFeatureWrapper.getFeatureFlag("openfeature_demo_flag", generatedString);
		
        model.addAttribute("usePunImage", flagResult);
        if(flagResult.equals("on")) {
            String memeUrl = getMeme();
            System.out.println("Meme URL: "+memeUrl);
            model.addAttribute("memeUrl", memeUrl);
        } else if(flagResult.equals("off")) {
            String pun = getPun();
            System.out.println("Pun: "+pun);
            model.addAttribute("pun", pun);
        }
        System.out.println("Generated Hit for: "+generatedString);
        return "index";
    }
}