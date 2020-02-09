package cn.dormitorymanage.adapter;

public class SongshuiItem {
//    [{"water_id":"1","water_dormitoryid":"1-101","water_num":"2122","water_time":"2020年02月07日 05:05:52"}]
    private String water_dormitoryid;
    private String water_num;
    private String water_time;

    public SongshuiItem(String water_dormitoryid, String water_num, String water_time) {
        this.water_dormitoryid = water_dormitoryid;
        this.water_num = water_num;
        this.water_time = water_time;
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
