package JDBCDAO.models;

import java.util.Date;

public class Users {
    private int id;
    private String name;
    private String password;
    private int phone;
    private Date dateregedit;
    private int roleId;
    private Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getDateregedit() {
        return dateregedit;
    }

    public void setDateregedit(Date dateregedit) {
        this.dateregedit = dateregedit;
    }
}
