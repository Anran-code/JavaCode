package com.ranan.practice;

import java.util.Comparator;

public class PetScoreComparator implements Comparator<Pet> {
    @Override
    public int compare(Pet o1, Pet o2) {
        return Double.compare(o1.getScore(),o2.getScore());
    }
}
