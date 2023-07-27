package com.codecool.ehotel.service.logic;

import com.codecool.ehotel.model.Guest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface GuestGroupBuilder {
    public ArrayList<HashSet<Guest>> generateGroups(Set<Guest> guestSet);
}