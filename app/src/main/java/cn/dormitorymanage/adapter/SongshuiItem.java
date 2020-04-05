package cn.dormitorymanage.adapter;

public class SongshuiItem {
//    [{"water_id":"1","water_dormitoryid":"1-101","water_num":"2122","water_time":"2020年02月07日 05:05:52"}]
    private String water_id;
    private String water_stuid;
    private String water_dormitoryid;
    private String water_num;
    private String water_time;
    private String water_stat;

    public SongshuiItem(String water_id, String water_stuid, String water_dormitoryid, String water_num, String water_time, String water_stat) {
        this.water_id = water_id;
        this.water_stuid = water_stuid;
        this.water_dormitoryid = water_dormitoryid;
        this.water_num = water_num;
        this.water_time = water_time;
        this.water_stat = water_stat;
    }

    public String getWater_id() {
        return water_id;
    }

    public void setWater_id(String water_id) {
        this.water_id = water_id;
    }

    public String getWater_stuid() {
        return water_stuid;
    }

    public void setWater_stuid(String water_stuid) {
        this.water_stuid = water_stuid;
    }

    public String getWater_stat() {
        return water_stat;
    }

    public void setWater_stat(String water_stat) {
        this.water_stat = water_stat;
    }

    public String getWater_dormitoryid() {
        return water_dormitoryid;
    }

    public void setWater_dormitoryid(String water_dormitoryid) {
        this.water_dormitoryid = water_dormitoryid;
    }

    public String getWater_num() {
        return water_num;
    }

    public void setWater_num(String water_num) {
        this.water_num = water_num;
    }

    public String getWater_time() {
        return water_time;
    }

    public void setWater_time(String water_time) {
        this.water_time = water_time;
    }
}
