package cn.dormitorymanage.adapter;

public class WeixiuItem {
//[{"repair_id":"1","repair_dormitoryid":"1","repair_name":"1","repair_remark":"1","repair_time":"2020年02月07日 02:53:51"}]
    private String repair_id;
    private String repair_stuid;
    private String repair_dormitoryid;
    private String repair_name;
    private String repair_remark;
    private String repair_time;
    private String repair_stat;

    public WeixiuItem(String repair_id, String repair_stuid, String repair_dormitoryid, String repair_name, String repair_remark, String repair_time, String repair_stat) {
        this.repair_id = repair_id;
        this.repair_stuid = repair_stuid;
        this.repair_dormitoryid = repair_dormitoryid;
        this.repair_name = repair_name;
        this.repair_remark = repair_remark;
        this.repair_time = repair_time;
        this.repair_stat = repair_stat;
    }

    public String getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
    }

    public String getRepair_stuid() {
        return repair_stuid;
    }

    public void setRepair_stuid(String repair_stuid) {
        this.repair_stuid = repair_stuid;
    }

    public String getRepair_stat() {
        return repair_stat;
    }

    public void setRepair_stat(String repair_stat) {
        this.repair_stat = repair_stat;
    }

    public String getRepair_dormitoryid() {
        return repair_dormitoryid;
    }

    public void setRepair_dormitoryid(String repair_dormitoryid) {
        this.repair_dormitoryid = repair_dormitoryid;
    }

    public String getRepair_name() {
        return repair_name;
    }

    public void setRepair_name(String repair_name) {
        this.repair_name = repair_name;
    }

    public String getRepair_remark() {
        return repair_remark;
    }

    public void setRepair_remark(String repair_remark) {
        this.repair_remark = repair_remark;
    }

    public String getRepair_time() {
        return repair_time;
    }

    public void setRepair_time(String repair_time) {
        this.repair_time = repair_time;
    }
}
