package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.servingwebcontent.OpenFeatureWrapper;



@Controller
public class FeatureFlagController {

    private boolean usePunImage = false;
    // This block will be executed when the class is loaded
    static {
       // OpenFeatureWrapper.initialize();
    }

    @GetMapping("/")
    public String joke(Model model) {
		//String flagResult = OpenFeatureWrapper.getFeatureFlag("openfeature_demo_flag", "user6"); 
		String flagResult = "off";
		
        model.addAttribute("usePunImage", flagResult);
        return "index";
    }
}