package com.example.servingwebcontent;

import dev.openfeature.sdk.*;

import java.util.Map;

public class AfterHook implements StringHook {

    @Override
    public void after(HookContext<String> ctx, FlagEvaluationDetails<String> details, Map<String, Object> hints) {
        // code to run after successful flag evaluation
        System.out.println("Evaluation Context: "+ctx);
        System.out.println("Flag Evaluation Details: "+details);
    }


}
