package com.jmp2017w.customwebsite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController
{
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String dispplayHomepage()
    {
        return "homepage";
    }
}