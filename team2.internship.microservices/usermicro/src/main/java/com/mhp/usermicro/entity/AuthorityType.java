package com.mhp.usermicro.entity;

public enum AuthorityType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE"),
    ROLE_TEAM_COORD("ROLE_TEAM_COORD"),
    ROLE_TEAM_LEAD("ROLE_TEAM_LEAD");

    String name = "";

    private AuthorityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static String getString(String name){
        switch (name){
            case "ROLE_ADMIN":
                return "Admin";
            case "ROLE_EMPLOYEE":
                return "Employee";
            case "ROLE_TEAM_COORD":
                return "Team Coordinator";
            case "ROLE_TEAM_LEAD":
                return "Team Leader";
            default:
                return null;
        }
    }

    public static AuthorityType getAuthorityType(String name){
        switch (name){
            case "Admin":
                return AuthorityType.ROLE_ADMIN;
            case "Employee":
                return AuthorityType.ROLE_EMPLOYEE;
            case "Team Coordinator":
                return AuthorityType.ROLE_TEAM_COORD;
            case "Team Leader":
                return AuthorityType.ROLE_TEAM_LEAD;
            default:
                return null;
        }
    }
}