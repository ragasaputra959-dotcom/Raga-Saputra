package com.example.siakad_ui.models;

public class MenuItem {
    private String title;
    private int icon;
    private int actionId;

    public MenuItem(String title, int icon, int actionId) {
        this.title = title;
        this.icon = icon;
        this.actionId = actionId;
    }

    public String getTitle() { return title; }
    public int getIcon() { return icon; }
    public int getActionId() { return actionId; }
}