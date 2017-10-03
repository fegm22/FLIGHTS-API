package com.nexthink.quiz.question7.dto;

import java.util.List;

/**
 * This is a helper class. Please do not modify it.
 */
public final class InventoryItem {
    private final String uid;
    private final String name;
    private final List<String> keywords;

    public InventoryItem(String uid, String name, List<String> keywords) {
        this.uid = uid;
        this.name = name;
        this.keywords = keywords;
    }

    public String getUid() {
        return uid;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
