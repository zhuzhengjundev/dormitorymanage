package cn.dormitorymanage.function;

import android.content.Context;
import android.content.SharedPreferences;

public class User {

    Context context;

    public User(Context context) {
        this.context = context;
    }

    public Boolean getIsAdmin(){
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getBoolean("isAdmin", false);
    }

    public void setIsAdmin(Boolean isAdmin){
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putBoolean("isAdmin", isAdmin);
        editor.apply();
    }

    public String getManag_id() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("manag_id", "");
    }

    public void setManag_id(String manag_id) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("manag_id", manag_id);
        editor.apply();
    }

    public String getManag_name() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("manag_name", "");
    }

    public void setManag_name(String manag_name) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("manag_name", manag_name);
        editor.apply();
    }

    public String getManag_dormitory_num() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("manag_dormitory_num", "");
    }

    public void setManag_dormitory_num(String manag_dormitory_num) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("manag_dormitory_num", manag_dormitory_num);
        editor.apply();
    }

    public String getManag_manag_xb() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("manag_xb", "");
    }

    public void setManag_manag_xb(String manag_xb) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("manag_xb", manag_xb);
        editor.apply();
    }

    public String getStu_id() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_id", "");
    }

    public void setStu_id(String stu_id) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_id", stu_id);
        editor.apply();
    }

    public String getStu_pwd() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_pwd", "");
    }

    public void setStu_pwd(String stu_pwd) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_pwd", stu_pwd);
        editor.apply();
    }

    public String getStu_name() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_name", "");
    }

    public void setStu_name(String stu_name) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_name", stu_name);
        editor.apply();
    }

    public String getStu_gender() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_gender", "");
    }

    public void setStu_gender(String stu_gender) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_gender", stu_gender);
        editor.apply();
    }

    public String getStu_img() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_img", "");
    }

    public void setStu_img(String stu_img) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_img", stu_img);
        editor.apply();
    }

    public String getStu_age() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_age", "");
    }

    public void setStu_age(String stu_age) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_age", stu_age);
        editor.apply();
    }

    public String getStu_nativeplace() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_nativeplace", "");
    }

    public void setStu_nativeplace(String stu_nativeplace) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_nativeplace", stu_nativeplace);
        editor.apply();
    }

    public String getStu_major() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_major", "");
    }

    public void setStu_major(String stu_major) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_major", stu_major);
        editor.apply();
    }

    public String getStu_hobby() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_hobby", "");
    }

    public void setStu_hobby(String stu_hobby) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_hobby", stu_hobby);
        editor.apply();
    }

    public String getStu_dormitoryid() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_dormitoryid", "");
    }

    public void setStu_dormitoryid(String stu_dormitoryid) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_dormitoryid", stu_dormitoryid);
        editor.apply();
    }

    public String getStu_phone() {
        return context.getSharedPreferences("Users", Context.MODE_PRIVATE).getString("stu_phone", "");
    }

    public void setStu_phone(String stu_phone) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Users", Context.MODE_PRIVATE).edit();
        editor.putString("stu_phone", stu_phone);
        editor.apply();
    }
}
