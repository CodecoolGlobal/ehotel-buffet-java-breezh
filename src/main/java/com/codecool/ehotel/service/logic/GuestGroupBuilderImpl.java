package com.codecool.ehotel.service.logic;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestService;
import jdk.dynalink.linker.GuardingDynamicLinkerExporter;

import java.util.*;

public class GuestGroupBuilderImpl implements GuestGroupBuilder{

    public ArrayList<HashSet<Guest>> generateGroups(Set<Guest> guestSet) {
        ArrayList<HashSet<Guest>> guests = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            guests.add(new HashSet<>());
        }

        Random random = new Random();

        for (Guest guest : guestSet) {
            int randomGroupNumber = random.nextInt(8);
            guests.get(randomGroupNumber).add(guest);
        }
        return guests;
    }
}
