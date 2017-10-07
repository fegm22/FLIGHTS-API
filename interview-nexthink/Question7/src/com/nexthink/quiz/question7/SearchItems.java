package com.nexthink.quiz.question7;

import java.util.*;

import com.nexthink.quiz.question7.dao.InventoryItemsDAO;
import com.nexthink.quiz.question7.dto.InventoryItem;

/**
 * Copyright (C) 2015 by NEXThink SA, Switzerland. Any usage, copy or
 * partial copy of this code without the explicit agreement of NEXThink SA
 * is prohibited and will be legally pursued.
 */

/**
 * My comments:
    My idea behind the implementation of the searchForKeyword algorithm was to achieve linear time order complexity O(N).
    So, the best approach I thought was iterate through the Map only once.
    To do that, the idea was to transform the keywords into a single String and then search the substring.
    I implemented two algorithms. One that uses the String API and the other that use the Boyer Moore's algorithm for substring searching
    (just because I studied it once, I just wanted to compare them.)
    Most of the time the Java API was quite better.

 */

public class SearchItems {
    private static final String SEARCH = "ranc";
    private static final int NUMBER_OF_ITEMS = 1000000;
    private static Map<Character, Integer> mismatchShiftsTable = new HashMap<>();


    public static void main(String[] args) {
        System.out.println("Number of items: " + NUMBER_OF_ITEMS);
        System.out.println("Search string: " + SEARCH);

        Map<String, InventoryItem> items = InventoryItemsDAO.getInventoryItems(NUMBER_OF_ITEMS);
        long start = System.currentTimeMillis();
        Collection<String> uids = searchForKeywordMoore(SEARCH, items);
        long time = System.currentTimeMillis() - start;
        System.out.println("Time using Boyer Moore: " + time/1000F + " seconds");
        System.out.println("Results found: " + uids.size());

        System.out.println("---");

        start = System.currentTimeMillis();
        Collection<String> uids2 = searchForKeyword(SEARCH, items);
        time = System.currentTimeMillis() - start;
        System.out.println("Time using Java API: " +time/1000F + " seconds");

        System.out.println("Results found: " + uids2.size());
        //printResults(uids, items);
    }

    /**
     * Return a collection of string uids of inventory items which match the search criteria
     *
     * - Search should be performed on keywords of generated inventory items
     * - Search should be case insensitive and cover sub-strings (partial match)
     * - Results should not contain duplicate uids
     * - Using any data structure to create an intermediate data container to increase the search query performance is welcome
     *  (time to create/populate such data structure can be disregarded, size should be reasonable)
     * - Re-using existing algorithms/data structure implementations is totally welcome (not to re-invent the wheel)
     * - Classes in dao and dto packages should not be modified (even if the candidate really wants it :))
     * - Query time optimization is important! Basically, try to do it as fast as possible on given 1000000 items
     *
     * @param searchString a string to search for in inventory items keywords
     * @param items items map generated by the DAO
     * @return a collection of uids of inventory items which match the search criteria
     */
    private static Collection<String> searchForKeywordMoore(String searchString, Map<String, InventoryItem> items) {
        // TODO good luck!
        precomputeShifts(searchString.toLowerCase());
        HashSet<String> list = new HashSet<>();

        for( Map.Entry<String, InventoryItem> item : items.entrySet() ){

            if( search(item.getValue().getKeywords().toString().toLowerCase()
                    .replace("[","").replace("]","").replace(",","")) ) {
                list.add(item.getKey());
            }
        }

        return list;
    }

    private static Collection<String> searchForKeyword(String searchString, Map<String, InventoryItem> items) {

        HashSet<String> list = new HashSet<>();

        for( Map.Entry<String, InventoryItem> item : items.entrySet() ){

            if( item.getValue().getKeywords().toString().toLowerCase()
                    .replace("[","").replace("]","").replace(",","")
                    .contains(searchString.toLowerCase()) ) {
                list.add(item.getKey());
            }
        }

        return list;
    }

    private static void printResults(Collection<String> uids, Map<String, InventoryItem> items) {
        for (String uid : uids) {
            System.out.println(items.get(uid));
        }
    }

    private static void precomputeShifts(String searchString) {
        int lengthOfPattern = searchString.length();

        for (int index = 0; index < lengthOfPattern; index++) {
            char actualCharacter = searchString.charAt(index);
            int maxShift = Math.max(1, lengthOfPattern - index - 1);
            mismatchShiftsTable.put(actualCharacter, maxShift);
        }
    }

    private static boolean search(String text) {
        int lengthOfPattern = SEARCH.length();
        int lengthOfText = text.length();
        int numOfSkips;

        for (int i = 0; i <= lengthOfText - lengthOfPattern; i += numOfSkips) {
            numOfSkips = 0;
            for (int j = lengthOfPattern - 1; j >= 0; j--) {
                if (SEARCH.charAt(j) != text.charAt(i + j)) {

                    if ( mismatchShiftsTable.get(SEARCH.charAt(j)) != null ) {
                        numOfSkips = mismatchShiftsTable.get(SEARCH.charAt(j));
                        break;
                    } else {
                        numOfSkips = lengthOfPattern;
                        break;
                    }
                }
            }

            if (numOfSkips == 0) return true;
        }

        return false;
    }

}