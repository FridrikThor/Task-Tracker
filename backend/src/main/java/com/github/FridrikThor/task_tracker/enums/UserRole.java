package com.github.FridrikThor.task_tracker.enums;

public enum UserRole {
    DEVELOPER("Developer", false),
    MANAGER("Manager", true),
    ADMIN("Admin", true);

    private final String displayString;
    private final boolean elevatedUser;


    UserRole(String displayString, boolean elevatedUser) {
        this.displayString = displayString;
        this.elevatedUser = elevatedUser;
    }
    public String getDisplayString()
    {
        return this.displayString;
    }

    public boolean isElevatedUser()
    {
        return this.elevatedUser;
    }

}
