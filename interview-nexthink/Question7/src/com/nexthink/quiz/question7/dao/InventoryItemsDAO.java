package com.nexthink.quiz.question7.dao;

import java.util.*;

import com.nexthink.quiz.question7.dto.InventoryItem;

/**
 * This is a helper class. Please do not modify it
 */
public final class InventoryItemsDAO {

    public static Map<String, InventoryItem> getInventoryItems(int numberOfItems) {
        String[] countries = Locale.getISOCountries();
        Map<String, String> languages = getLanguageMap();
        Map<String, InventoryItem> items = new HashMap<>();
        Random random = new Random();
        int bound = countries.length;
        for (int i = 0; i < numberOfItems; i++) {
            String uid = "UID_" + i;
            String name = "DEVICE_" + i;
            String countryCode = countries[random.nextInt(bound)];
            Locale locale = new Locale(languages.containsKey(countryCode) ? languages.get(countryCode) : "",
                    countryCode);
            List<String> keywords = Arrays.asList(locale.getCountry(), locale.getDisplayCountry(Locale.ENGLISH),
                    locale.getLanguage(), locale.getDisplayLanguage(Locale.ENGLISH),
                    locale.getDisplayName(Locale.ENGLISH));
            items.put(uid, new InventoryItem(uid, name, keywords));
        }
        return items;
    }

    private static Map<String, String> getLanguageMap() {
        Map<String, String> languages = new HashMap<>();
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            if ((locale.getDisplayCountry() != null) && (!"".equals(locale.getDisplayCountry()))) {
                languages.put(locale.getCountry(), locale.getLanguage());
            }
        }
        return languages;
    }
}
