package cn.dormitorymanage.adapter;

public class FangkedengjiItem {
    //[{"visitor_id":"1","visitor_dormitoryid":"1-101","visitor_name":"3123","visitor_identity":"1231","visitor_remark":"133",
//            "visitor_isagree":"是","visitor_time":"2020年02月07日05:41:02"}]
    private String visitor_dormitoryid;
    private String visitor_name;
    private String visitor_identity;
    private String visitor_remark;
    private String visitor_isagree;
    private String visitor_time;

    public FangkedengjiItem(String visitor_dormitoryid, String visitor_name, String visitor_identity, String visitor_remark, String visitor_isagree, String visitor_time) {
        this.visitor_dormitoryid = visitor_dormitoryid;
        this.visitor_name = visitor_name;
        this.visitor_identity = visitor_identity;
        this.visitor_remark = visitor_remark;
        this.visitor_isagree = visitor_isagree;
        this.visitor_time = visitor_time;
    }

    public String getVisitor_dormitoryid() {
        return visitor_dormitoryid;
    }

    public void setVisitor_dormitoryid(String visitor_dormitoryid) {
        this.visitor_dormitoryid = visitor_dormitoryid;
    }

    public String getVisitor_name() {
        return visitor_name;
    }

    public void setVisitor_name(String visitor_name) {
        this.visitor_name = visitor_name;
    }

    public String getVisitor_identity() {
        return visitor_identity;
    }

    public void setVisitor_identity(String visitor_identity) {
        this.visitor_identity = visitor_identity;
    }

    public String getVisitor_remark() {
        return visitor_remark;
    }

    public void setVisitor_remark(String visitor_remark) {
        this.visitor_remark = visitor_remark;
    }

    public String getVisitor_isagree() {
        return visitor_isagree;
    }

    public void setVisitor_isagree(String visitor_isagree) {
        this.visitor_isagree = visitor_isagree;
    }

    public String getVisitor_time() {
        return visitor_time;
    }

    public void setVisitor_time(String visitor_time) {
        this.visitor_time = visitor_time;
    }
}
