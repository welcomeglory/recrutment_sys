package com.sinjin.service;

import com.sinjin.mapper.ApplicantMapper;
import com.sinjin.vo.ApplicantVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApplicantServiceTest {

    @InjectMocks
    private ApplicantService applicantService;

    @Mock
    private ApplicantMapper applicantMapper;

    private ApplicantVO applicant1;
    private ApplicantVO applicant2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        applicant1 = new ApplicantVO(1, "김민서", 80, 90, 85, 70, 75);
        applicant2 = new ApplicantVO(2, "김민정", 70, 85, 90, 80, 75);
    }

    @Test
    public void testGetList_ReturnsSortedApplicants() {
        when(applicantMapper.getList()).thenReturn(Arrays.asList(applicant1, applicant2));

        List<ApplicantVO> result = applicantService.getList();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getA_no()); 
        assertEquals(2, result.get(1).getA_no()); 
        assertEquals(1, result.get(0).getRank()); 
        assertEquals(2, result.get(1).getRank()); 
    }

    @Test
    public void testWrite_InsertsApplicantAndScores() {
        when(applicantMapper.getLastInsertedNo()).thenReturn(1);
        
        applicantService.write(applicant1);

        verify(applicantMapper).insertApplicant(any(ApplicantVO.class));
        verify(applicantMapper, times(3)).insertInterviewScore(eq(1), anyInt(), anyInt());
    }

    @Test
    public void testGet_ReturnsApplicant() {
        when(applicantMapper.get(1)).thenReturn(applicant1);

        ApplicantVO result = applicantService.get(1);
        
        assertEquals("김민서", result.getA_name());
    }

    @Test
    public void testModify_UpdatesApplicantAndScores() {
        applicantService.modify(applicant1);

        verify(applicantMapper).updateApplicant(applicant1);
        verify(applicantMapper).updateInterviewScore(1, 1, 85);
        verify(applicantMapper).updateInterviewScore(1, 2, 70);
        verify(applicantMapper).updateInterviewScore(1, 3, 75);
    }

    @Test
    public void testRemove_DeletesApplicantAndScores() {
        applicantService.remove(1);

        verify(applicantMapper).deleteInterviewScores(1);
        verify(applicantMapper).deleteApplicantInfo(1);
    }

    @Test
    public void testGenerateUniqueName_WhenNameExists_ReturnsUniqueName() {
        when(applicantMapper.getNameCount("김민서")).thenReturn(1);

        String uniqueName = applicantService.generateUniqueName("김민서");
        
        assertEquals("김민서2", uniqueName);
    }

    @Test
    public void testGenerateUniqueName_WhenNoDuplicates_ReturnsOriginalName() {
        when(applicantMapper.getNameCount("김민정")).thenReturn(0);

        String uniqueName = applicantService.generateUniqueName("김민정");
        
        assertEquals("김민정", uniqueName);
    }
}
