package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new Slot1Fragment();

            case 1:
                return new Slot2Fragment();

            case 2:
                return new Slot3Fragment();

            case 3:
                return new Slot4Fragment();

            case 4:
                return new Slot5Fragment();

            case 5:
                return new Slot6Fragment();

            case 6:
                return new Slot7Fragment();

            case 7:
                return new Slot8Fragment();

            case 8:
                return new Slot9Fragment();

            case 9:
                return new Slot10Fragment();

            case 10:
                return new Slot11Fragment();

            case 11:
                return new Slot12Fragment();

            case 12:
                return new Slot13Fragment();

            case 13:
                return new Slot14Fragment();

            case 14:
                return new Slot15Fragment();

            case 15:
                return new Slot16Fragment();

            case 16:
                return new Slot17Fragment();

            case 17:
                return new Slot18Fragment();

            case 18:
                return new Slot19Fragment();

            case 19:
                return new Slot20Fragment();

            case 20:
                return new Slot21Fragment();

            case 21:
                return new Slot22Fragment();

            case 22:
                return new Slot23Fragment();

            case 23:
                return new Slot24Fragment();

            case 24:
                return new Slot25Fragment();

            case 25:
                return new Slot26Fragment();

            case 26:
                return new Slot27Fragment();

            case 27:
                return new Slot28Fragment();

            case 28:
                return new Slot29Fragment();

            case 29:
                return new Slot30Fragment();

            case 30:
                return new Slot31Fragment();

            case 31:
                return new Slot32Fragment();

            case 32:
                return new Slot33Fragment();

            case 33:
                return new Slot34Fragment();

            case 34:
                return new Slot35Fragment();

            case 35:
                return new Slot36Fragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
