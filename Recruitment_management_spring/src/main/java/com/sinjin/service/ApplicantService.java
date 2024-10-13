package com.sinjin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinjin.mapper.ApplicantMapper;
import com.sinjin.vo.ApplicantVO;

import java.util.Comparator;
import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantMapper applicantMapper;

    public List<ApplicantVO> getList() {
        List<ApplicantVO> applicants = applicantMapper.getList();

        applicants.sort((a1, a2) -> Double.compare(a2.getTotalScore(), a1.getTotalScore())); 
        int currentRank = 1; 
        for (int i = 0; i < applicants.size(); i++) {
            if (i > 0 && applicants.get(i).getTotalScore() == applicants.get(i - 1).getTotalScore()) {
                applicants.get(i).setRank(applicants.get(i - 1).getRank());
            } else {
                applicants.get(i).setRank(currentRank);
            }
            currentRank++; 
        }
        applicants.sort(Comparator.comparingInt(ApplicantVO::getA_no));
        return applicants;
    }
    
    public void write(ApplicantVO applicant) {
        String uniqueName = generateUniqueName(applicant.getA_name());
        applicant.setA_name(uniqueName); 

        applicantMapper.insertApplicant(applicant);

        int lastNo = applicantMapper.getLastInsertedNo(); 
        applicant.setA_no(lastNo); 

        applicantMapper.insertInterviewScore(applicant.getA_no(), 1, applicant.getI1_score());
        applicantMapper.insertInterviewScore(applicant.getA_no(), 2, applicant.getI2_score());
        applicantMapper.insertInterviewScore(applicant.getA_no(), 3, applicant.getI3_score());        
    }

    public ApplicantVO get(int a_no) {
        return applicantMapper.get(a_no);
    }

    public void modify(ApplicantVO applicantVO) {
    	applicantMapper.updateApplicant(applicantVO);
        updateInterviewScores(applicantVO);   
    }

    public void remove(int a_no) {
    	applicantMapper.deleteInterviewScores(a_no); 
    	applicantMapper.deleteApplicantInfo(a_no); 
    }

    public void updateInterviewScores(ApplicantVO applicantVO) {
    	applicantMapper.updateInterviewScore(applicantVO.getA_no(), 1, applicantVO.getI1_score());
    	applicantMapper.updateInterviewScore(applicantVO.getA_no(), 2, applicantVO.getI2_score());
    	applicantMapper.updateInterviewScore(applicantVO.getA_no(), 3, applicantVO.getI3_score());
    }

    public String generateUniqueName(String name) {
        int count = applicantMapper.getNameCount(name); 
        
        if (count > 0) {
            return name + (count + 1); 
        }
        return name; 
    }

}
