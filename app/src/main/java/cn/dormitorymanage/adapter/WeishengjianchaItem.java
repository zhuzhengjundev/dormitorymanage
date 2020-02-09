package cn.dormitorymanage.adapter;

public class WeishengjianchaItem {
//[{"hygiene_id":"1","hygiene_dormitoryid":"1-101","hygiene_grade":"差","hygiene_remark":"3131","hygiene_time":"2020年02月07日 06:27:21"}]
    private String hygiene_dormitoryid;
    private String hygiene_grade;
    private String hygiene_remark;
    private String hygiene_time;

    public WeishengjianchaItem(String hygiene_dormitoryid, String hygiene_grade, String hygiene_remark, String hygiene_time) {
        this.hygiene_dormitoryid = hygiene_dormitoryid;
        this.hygiene_grade = hygiene_grade;
        this.hygiene_remark = hygiene_remark;
        this.hygiene_time = hygiene_time;
    }

    public String getHygiene_dormitoryid() {
        return hygiene_dormitoryid;
    }

    public void setHygiene_dormitoryid(String hygiene_dormitoryid) {
        this.hygiene_dormitoryid = hygiene_dormitoryid;
    }

    public String getHygiene_grade() {
        return hygiene_grade;
    }

    public void setHygiene_grade(String hygiene_grade) {
        this.hygiene_grade = hygiene_grade;
    }

    public String getHygiene_remark() {
        return hygiene_remark;
    }

    public void setHygiene_remark(String hygiene_remark) {
        this.hygiene_remark = hygiene_remark;
    }

    public String getHygiene_time() {
        return hygiene_time;
    }

    public void setHygiene_time(String hygiene_time) {
        this.hygiene_time = hygiene_time;
    }
}
