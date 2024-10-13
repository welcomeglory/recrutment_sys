package com.sinjin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinjin.service.ApplicantService;
import com.sinjin.vo.ApplicantVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/applicant/*")
public class ApplicantController {

    @Autowired 
    private ApplicantService applicantService;

    @GetMapping("/list")
    public String list(Model model) {    
        model.addAttribute("applicants", applicantService.getList());        
        return "/applicant/list";
    }    

    @GetMapping("/delete")
    public String remove(@RequestParam("a_no") int a_no, Model model) {
    	applicantService.remove(a_no);
        return "redirect:/applicant/list";
    }

    @GetMapping("/write_view")
    public String write_view() {
        return "/applicant/inputForm";
    }
    
    @PostMapping("/write")
    public String write(@ModelAttribute ApplicantVO applicantVO) {
    	applicantService.write(applicantVO); 
        return "redirect:/applicant/list";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("a_no") int a_no, Model model) {
    	ApplicantVO applicantVO = applicantService.get(a_no); 
        model.addAttribute("applicantVO", applicantVO); 
        return "/applicant/updateForm"; 
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute ApplicantVO applicantVO) {
    	applicantService.modify(applicantVO); 
        return "redirect:/applicant/list"; 
    }
}
