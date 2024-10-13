package com.sinjin.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantVOTest {

    private ApplicantVO applicant;

    @BeforeEach
    public void setUp() {
        applicant = new ApplicantVO(1, "김엘레나라", 85, 90, 80, 75, 70);
    }

    @Test
    public void testCalculateTotalScore() {
        double expectedScore = (85 * 0.2) + (90 * 0.4) + ((80 + 75 + 70) / 3.0 * 0.4);
        assertEquals(expectedScore, applicant.getTotalScore(), 0.01);
    }

    @Test
    public void testSetD_scoreUpdatesTotalScore() {
        applicant.setD_score(90);
        double expectedScore = (90 * 0.2) + (90 * 0.4) + ((80 + 75 + 70) / 3.0 * 0.4);
        assertEquals(expectedScore, applicant.getTotalScore(), 0.01);
    }

    @Test
    public void testSetW_scoreUpdatesTotalScore() {
        applicant.setW_score(95);
        double expectedScore = (85 * 0.2) + (95 * 0.4) + ((80 + 75 + 70) / 3.0 * 0.4);
        assertEquals(expectedScore, applicant.getTotalScore(), 0.01);
    }

    @Test
    public void testSetI1_scoreUpdatesTotalScore() {
        applicant.setI1_score(85);
        double expectedScore = (85 * 0.2) + (90 * 0.4) + ((85 + 75 + 70) / 3.0 * 0.4);
        assertEquals(expectedScore, applicant.getTotalScore(), 0.01);
    }

    @Test
    public void testAssignRanks() {
        List<ApplicantVO> applicants = new ArrayList<>();
        ApplicantVO applicant1 = new ApplicantVO(1, "김수정", 85, 90, 80, 75, 70);
        ApplicantVO applicant2 = new ApplicantVO(2, "김민철", 90, 85, 80, 80, 90);
        applicants.add(applicant1);
        applicants.add(applicant2);

        // 총점 확인
        double totalScore1 = applicant1.getTotalScore();
        double totalScore2 = applicant2.getTotalScore();

        System.out.println("김수정 총점: " + totalScore1);
        System.out.println("김민철 총점: " + totalScore2);

        ApplicantVO.assignRanks(applicants);

        assertEquals(1, applicants.get(0).getRank()); 
        assertEquals(2, applicants.get(1).getRank()); 
    }

    @Test
    public void testSetRank() {
        applicant.setRank(1);
        assertEquals(1, applicant.getRank());
    }
}
