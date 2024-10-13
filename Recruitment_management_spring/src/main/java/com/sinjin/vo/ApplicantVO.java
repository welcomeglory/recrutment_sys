package com.sinjin.vo;

import java.util.List;

public class ApplicantVO {
    private int  a_no;               // 지원자 번호
    private String a_name;          // 성명
    private int d_score;         // 서류전형 점수
    private int w_score;         // 필기전형 점수
    private int i1_score;        // 면접관1 점수
    private int i2_score;        // 면접관2 점수
    private int i3_score;        // 면접관3 점수
    private double totalScore;      // 총점
    private int rank;               // 순위
    
    public ApplicantVO() {
    	
    }

    public ApplicantVO(int a_no, String a_name, int d_score, int w_score, int i1_score, int i2_score, int i3_score) {
        this.a_no = a_no;
        this.a_name = a_name;
        this.d_score = d_score;
        this.w_score = w_score;
        this.i1_score = i1_score;
        this.i2_score = i2_score;
        this.i3_score = i3_score;

        this.totalScore = calculateTotalScore();
    }

    private double calculateTotalScore() {
        double documentScore = d_score * 0.2;
        double writtenScore = w_score * 0.4;
        double interviewAverage = (i1_score + i2_score + i3_score) / 3.0;
        double interviewScore = interviewAverage * 0.4;

        return documentScore + writtenScore + interviewScore;
    }

    public static void assignRanks(List<ApplicantVO> applicants) {
        applicants.sort((a1, a2) -> Double.compare(a2.getTotalScore(), a1.getTotalScore()));
        
        for (int i = 0; i < applicants.size(); i++) {
            applicants.get(i).setRank(i + 1);
        }
    }

    public int getA_no() {
        return a_no;
    }

    public void setA_no(int a_no) {
        this.a_no = a_no;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public int getD_score() {
        return d_score;
    }

    public void setD_score(int d_score) {
        this.d_score = d_score;
        this.totalScore = calculateTotalScore();
    }

    public int getW_score() {
        return w_score;
    }

    public void setW_score(int w_score) {
        this.w_score = w_score;
        this.totalScore = calculateTotalScore(); 
    }

    public int getI1_score() {
        return i1_score;
    }

    public void setI1_score(int i1_score) {
        this.i1_score = i1_score;
        this.totalScore = calculateTotalScore(); 
    }

    public int getI2_score() {
        return i2_score;
    }

    public void setI2_score(int i2_score) {
        this.i2_score = i2_score;
        this.totalScore = calculateTotalScore(); 
    }

    public int getI3_score() {
        return i3_score;
    }

    public void setI3_score(int i3_score) {
        this.i3_score = i3_score;
        this.totalScore = calculateTotalScore(); 
    }

    public double getTotalScore() {
        return totalScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
} 