package com.sinjin.mapper;

import com.sinjin.vo.ApplicantVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApplicantMapper {

    @Select("SELECT a.a_no, a.a_name, a.d_score, a.w_score, "
            + "i1.i_score AS i1_score, "
            + "i2.i_score AS i2_score, "
            + "i3.i_score AS i3_score "
            + "FROM Applicant_INFO a "
            + "LEFT JOIN Interview_Scores i1 ON a.a_no = i1.a_no AND i1.interviewer_no = 1 "
            + "LEFT JOIN Interview_Scores i2 ON a.a_no = i2.a_no AND i2.interviewer_no = 2 "
            + "LEFT JOIN Interview_Scores i3 ON a.a_no = i3.a_no AND i3.interviewer_no = 3")
    List<ApplicantVO> getList();

    @Insert("INSERT INTO Applicant_INFO (a_no, a_name, d_score, w_score) VALUES (a_seq_no.NEXTVAL, #{a_name}, #{d_score}, #{w_score})")
    void insertApplicant(ApplicantVO applicantVO);
    
    @Select("SELECT MAX(a_no) FROM Applicant_INFO")
    int getLastInsertedNo();
    
    @Insert("INSERT INTO Interview_Scores (a_no, interviewer_no, i_score) VALUES (#{a_no}, #{interviewer_no}, #{i_score})")
    void insertInterviewScore(int a_no, int interviewer_no, int i_score);

    @Select("SELECT a.a_no, a.a_name, a.d_score, a.w_score, "
            + "i1.i_score AS i1_score, "
            + "i2.i_score AS i2_score, "
            + "i3.i_score AS i3_score "
            + "FROM Applicant_INFO a "
            + "LEFT JOIN Interview_Scores i1 ON a.a_no = i1.a_no AND i1.interviewer_no = 1 "
            + "LEFT JOIN Interview_Scores i2 ON a.a_no = i2.a_no AND i2.interviewer_no = 2 "
            + "LEFT JOIN Interview_Scores i3 ON a.a_no = i3.a_no AND i3.interviewer_no = 3 "
            + "WHERE a.a_no = #{a_no}")
    ApplicantVO get(int a_no);
    
    @Update("UPDATE Applicant_INFO SET a_name = #{a_name}, d_score = #{d_score}, w_score = #{w_score} WHERE a_no = #{a_no}")
    void updateApplicant(ApplicantVO applicantVO);
    
    @Update("UPDATE Interview_Scores SET i_score = #{i_score} WHERE a_no = #{a_no} AND interviewer_no = #{interviewer_no}")
    void updateInterviewScore(int a_no, int interviewer_no, int i_score);

    @Delete("DELETE FROM Interview_Scores WHERE a_no = #{a_no}")
    void deleteInterviewScores(int a_no);

    @Delete("DELETE FROM Applicant_INFO WHERE a_no = #{a_no}")
    void deleteApplicantInfo(int a_no);

    @Select("SELECT COUNT(*) FROM Applicant_INFO WHERE a_name LIKE #{name} || '%'")
    int getNameCount(String name);

}
