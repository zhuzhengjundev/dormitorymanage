package cn.dormitorymanage.adapter;

public class StudentListItem {
//    {"stu_id":"111111","stu_pwd":"1","stu_name":"小军","stu_gender":"男",
//            "stu_img":"http://172.20.10.2:8181/dormitoryManage/upload/studentImg/1581073941346_timg-1.jpg",
//            "stu_age":"242","stu_nativeplace":"41","stu_major":"4241","stu_hobby":"414","stu_dormitoryid":"1-101"}
    private String stu_id;
    private String stu_name;
    private String stu_gender;
    private String stu_img;
    private String stu_age;
    private String stu_nativeplace;
    private String stu_major;
    private String stu_hobby;
    private String stu_phone;
    private String stu_dormitoryid;

    public StudentListItem(String stu_id, String stu_name, String stu_gender, String stu_img, String stu_age, String stu_nativeplace, String stu_major, String stu_hobby, String stu_phone, String stu_dormitoryid) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_gender = stu_gender;
        this.stu_img = stu_img;
        this.stu_age = stu_age;
        this.stu_nativeplace = stu_nativeplace;
        this.stu_major = stu_major;
        this.stu_hobby = stu_hobby;
        this.stu_phone = stu_phone;
        this.stu_dormitoryid = stu_dormitoryid;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_gender() {
        return stu_gender;
    }

    public void setStu_gender(String stu_gender) {
        this.stu_gender = stu_gender;
    }

    public String getStu_img() {
        return stu_img;
    }

    public void setStu_img(String stu_img) {
        this.stu_img = stu_img;
    }

    public String getStu_age() {
        return stu_age;
    }

    public void setStu_age(String stu_age) {
        this.stu_age = stu_age;
    }

    public String getStu_nativeplace() {
        return stu_nativeplace;
    }

    public void setStu_nativeplace(String stu_nativeplace) {
        this.stu_nativeplace = stu_nativeplace;
    }

    public String getStu_major() {
        return stu_major;
    }

    public void setStu_major(String stu_major) {
        this.stu_major = stu_major;
    }

    public String getStu_hobby() {
        return stu_hobby;
    }

    public void setStu_hobby(String stu_hobby) {
        this.stu_hobby = stu_hobby;
    }

    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
    }

    public String getStu_dormitoryid() {
        return stu_dormitoryid;
    }

    public void setStu_dormitoryid(String stu_dormitoryid) {
        this.stu_dormitoryid = stu_dormitoryid;
    }
}
