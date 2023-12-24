package com.school_management.support_entities;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberValidator {
    private PhoneNumberValidator(){}

    public static boolean isValidPhoneNumber(String phoneNumber, String countryCode) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            // Parse the phone number with the given country code
            phoneNumberUtil.parse(phoneNumber, countryCode);
            return true; // Valid phone number
        } catch (NumberParseException e) {
            return false; // Invalid phone number
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regex pattern for E.164 international phone number validation
        String e164Pattern = "^\\+?[1-9]\\d{1,14}$"; 
    
        return phoneNumber.matches(e164Pattern);
    }

    public static String formatToE164(String phoneNumber, String countryCode) throws NumberParseException {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            if(isValidPhoneNumber(phoneNumber, countryCode)){    
                PhoneNumber number = phoneNumberUtil.parse(phoneNumber, countryCode);
                return phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
            }
            throw new IllegalArgumentException();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

