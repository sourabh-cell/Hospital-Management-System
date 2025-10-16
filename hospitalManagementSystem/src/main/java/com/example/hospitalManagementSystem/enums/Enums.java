package com.example.hospitalManagementSystem.enums;

public class Enums {


    public enum Gender {
        MALE, FEMALE, TRANSGENDER,OTHER
    }


    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED
    }

    public enum PatientStatus {
        ACTIVE, // taking treatment
        DISCHARGED,
        DECEASED
    }

    public enum BedStatus {
       VACCANT,
        OCCUPIED
    }




    public enum RoomStatus {
        AVAILABLE,
        UNAVAILABLE,
        UNDER_MAINTENANCE
    }

    public enum VisitType {
        OPD,
        IPD
    }

    public enum BloodGroup {
        A_POSITIVE,
        A_NEGATIVE,
        B_POSITIVE,
        B_NEGATIVE,
        AB_POSITIVE,
        AB_NEGATIVE,
        O_POSITIVE,
        O_NEGATIVE
    }
}
