package com.sinjin.mapper;

import com.sinjin.vo.ApplicantVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicantMapperTest {

    @Autowired
    private ApplicantMapper applicantMapper;

    private ApplicantVO applicant;

    @BeforeEach
    public void setUp() {
        applicant = new ApplicantVO();
        applicant.setA_name("이가은");
        applicant.setD_score(85);
        applicant.setW_score(90);
        applicant.setI1_score(80);
        applicant.setI2_score(75);
        applicant.setI3_score(70);
    }

    @Test
    public void testInsertApplicant() {
        applicantMapper.insertApplicant(applicant);
        int lastNo = applicantMapper.getLastInsertedNo();
        assertNotNull(lastNo);
        
        ApplicantVO insertedApplicant = applicantMapper.get(lastNo);
        assertNotNull(insertedApplicant);
        assertEquals("이가은", insertedApplicant.getA_name());
    }

    @Test
    public void testGetList() {
        List<ApplicantVO> applicants = applicantMapper.getList();
        assertNotNull(applicants);
        assertTrue(applicants.size() > 0);
    }

    @Test
    public void testGet() {
        applicantMapper.insertApplicant(applicant);
        int lastNo = applicantMapper.getLastInsertedNo();

        ApplicantVO retrievedApplicant = applicantMapper.get(lastNo);
        assertNotNull(retrievedApplicant);
        assertEquals(applicant.getA_name(), retrievedApplicant.getA_name());
    }

    @Test
    public void testUpdateApplicant() {
        applicantMapper.insertApplicant(applicant);
        int lastNo = applicantMapper.getLastInsertedNo();

        applicant.setA_no(lastNo);
        applicant.setA_name("이가은");
        applicantMapper.updateApplicant(applicant);

        ApplicantVO updatedApplicant = applicantMapper.get(lastNo);
        assertEquals("이가은", updatedApplicant.getA_name());
    }

    @Test
    public void testDeleteApplicantInfo() {
        applicantMapper.insertApplicant(applicant);
        int lastNo = applicantMapper.getLastInsertedNo();

        applicantMapper.deleteApplicantInfo(lastNo);
        ApplicantVO deletedApplicant = applicantMapper.get(lastNo);
        assertNull(deletedApplicant); 
    }

    @Test
    public void testGetNameCount() {
        applicantMapper.insertApplicant(applicant);
        int count = applicantMapper.getNameCount("이가은");
        assertTrue(count > 0);
    }
}
