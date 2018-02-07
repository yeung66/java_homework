package vo;

import java.io.Serializable;

public class Program implements Serializable{

    // 唯一标识id,32位随机字母数字
    private String id;

    // 国家
    private String country;

    // 学校
    private String university;

    // 学院
    private String school;

    // 项目名称
    private String program_Name;

    // 项目主页
    private String homepage;

    // 地址
    private String location;

    // 项目申请咨询邮箱
    private String email;

    // 联系方式
    private String phone_Number;

    // 学位
    private String degree;

    // 申请截止时间（奖学金）
    private String deadline_With_Aid;

    // 申请截止时间（无奖学金）
    private String deadline_Without_Aid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProgram_name() {
        return program_Name;
    }

    public void setProgram_Name(String programName) {
        this.program_Name = programName;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phoneNumber) {
        this.phone_Number = phoneNumber;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDeadline_With_Aid() {
        return deadline_With_Aid;
    }

    public void setDeadline_With_Aid(String deadlineWithAid) {
        this.deadline_With_Aid = deadlineWithAid;
    }

    public String getDeadline_Without_Aid() {
        return deadline_Without_Aid;
    }

    public void setDeadline_Without_Aid(String deadlineWithoutAid) {
        this.deadline_Without_Aid = deadlineWithoutAid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
