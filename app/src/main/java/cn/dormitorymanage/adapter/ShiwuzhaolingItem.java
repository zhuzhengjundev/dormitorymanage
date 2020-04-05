package cn.dormitorymanage.adapter;

public class ShiwuzhaolingItem {
    //    [{"lostarticle_id":"2","lostarticle_title":"214","lostarticle_content":"1414",
//            "lostarticle_img":"http://172.20.10.2:8181/dormitoryManage/upload/shiwuImg/1581075716521_timg-1.jpg\n",
//            "lostarticle_phonenumber":"1414","lostarticle_time":"2020年02月07日 11:41:56"}]
    private String lostarticle_id;
    private String lostarticle_stuid;
    private String lostarticle_title;
    private String lostarticle_content;
    private String lostarticle_img;
    private String lostarticle_phonenumber;
    private String lostarticle_stat;
    private String lostarticle_time;

    public ShiwuzhaolingItem(String lostarticle_id,String lostarticle_stuid,String lostarticle_title, String lostarticle_content, String lostarticle_img, String lostarticle_phonenumber, String lostarticle_stat, String lostarticle_time) {
        this.lostarticle_id = lostarticle_id;
        this.lostarticle_stuid = lostarticle_stuid;
        this.lostarticle_title = lostarticle_title;
        this.lostarticle_content = lostarticle_content;
        this.lostarticle_img = lostarticle_img;
        this.lostarticle_phonenumber = lostarticle_phonenumber;
        this.lostarticle_stat = lostarticle_stat;
        this.lostarticle_time = lostarticle_time;
    }

    public String getLostarticle_id() {
        return lostarticle_id;
    }

    public void setLostarticle_id(String lostarticle_id) {
        this.lostarticle_id = lostarticle_id;
    }

    public String getLostarticle_stuid() {
        return lostarticle_stuid;
    }

    public void setLostarticle_stuid(String lostarticle_stuid) {
        this.lostarticle_stuid = lostarticle_stuid;
    }

    public String getLostarticle_title() {
        return lostarticle_title;
    }

    public void setLostarticle_title(String lostarticle_title) {
        this.lostarticle_title = lostarticle_title;
    }

    public String getLostarticle_content() {
        return lostarticle_content;
    }

    public void setLostarticle_content(String lostarticle_content) {
        this.lostarticle_content = lostarticle_content;
    }

    public String getLostarticle_img() {
        return lostarticle_img;
    }

    public void setLostarticle_img(String lostarticle_img) {
        this.lostarticle_img = lostarticle_img;
    }

    public String getLostarticle_phonenumber() {
        return lostarticle_phonenumber;
    }

    public void setLostarticle_phonenumber(String lostarticle_phonenumber) {
        this.lostarticle_phonenumber = lostarticle_phonenumber;
    }

    public String getLostarticle_stat() {
        return lostarticle_stat;
    }

    public void setLostarticle_stat(String lostarticle_stat) {
        this.lostarticle_stat = lostarticle_stat;
    }

    public String getLostarticle_time() {
        return lostarticle_time;
    }

    public void setLostarticle_time(String lostarticle_time) {
        this.lostarticle_time = lostarticle_time;
    }
}
