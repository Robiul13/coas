package com.asl.asl_rms.model;

import java.util.ArrayList;
import java.util.List;

public class CasbinRole {
    private String roleName;
    private List<String> urlName = new ArrayList<>();

    public CasbinRole() {
    }

    public CasbinRole(String roleName, List<String> urlName) {
        this.roleName = roleName;
        this.urlName = urlName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getUrlName() {
        return urlName;
    }

    public void setUrlName(List<String> urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return "CasbinRole{" +
                "roleName='" + roleName + '\'' +
                ", urlName=" + urlName +
                '}';
    }
}
