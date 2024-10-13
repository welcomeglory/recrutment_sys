package com.sinjin.controller;

import com.sinjin.service.ApplicantService;
import com.sinjin.vo.ApplicantVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApplicantControllerTest {

    @InjectMocks
    private ApplicantController applicantController;

    @Mock
    private ApplicantService applicantService;

    @Mock
    private Model model;

    private ApplicantVO applicant1;
    private ApplicantVO applicant2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        applicant1 = new ApplicantVO(1, "김영희", 80, 90, 85, 70, 75);
        applicant2 = new ApplicantVO(2, "김철수", 70, 85, 90, 80, 75);
    }

    @Test
    public void testList_ReturnsListView() {
        List<ApplicantVO> applicants = Arrays.asList(applicant1, applicant2);
        when(applicantService.getList()).thenReturn(applicants);

        String viewName = applicantController.list(model);

        assertEquals("/applicant/list", viewName);
        verify(model).addAttribute("applicants", applicants);
    }

    @Test
    public void testRemove_RedirectsToList() {
        String viewName = applicantController.remove(1, model);

        assertEquals("redirect:/applicant/list", viewName);
        verify(applicantService).remove(1);
    }

    @Test
    public void testWriteView_ReturnsWriteView() {
        String viewName = applicantController.write_view();

        assertEquals("/applicant/inputForm", viewName);
    }

    @Test
    public void testWrite_RedirectsToList() {
        String viewName = applicantController.write(applicant1);

        assertEquals("redirect:/applicant/list", viewName);
        verify(applicantService).write(applicant1);
    }

    @Test
    public void testModify_ReturnsUpdateForm() {
        when(applicantService.get(1)).thenReturn(applicant1);

        String viewName = applicantController.modify(1, model);

        assertEquals("/applicant/updateForm", viewName);
        verify(model).addAttribute("applicantVO", applicant1);
    }

    @Test
    public void testModify_Post_RedirectsToList() {
        String viewName = applicantController.modify(applicant1);

        assertEquals("redirect:/applicant/list", viewName);
        verify(applicantService).modify(applicant1);
    }
}
