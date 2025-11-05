package com.example.nero_clothing_backend.common;

public class RegexConstants {
    public static final String NAME = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŻżŹź]+(?:[\\s\\-'][A-Za-zĄąĆćĘęŁłŃńÓóŚśŻżŹź]+)*$";


    public static final String PHONE_NUMBER_REGEX = "^\\+?[0-9]{9,15}$";

    public static final String STREET = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŻżŹź0-9\\s\\-.]+$";
    public static final String CITY = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s\\-' ]+$";
    public static final String ZIP_CODE = "^\\d{2}-\\d{3}$";
    public static final String BUILDING = "^[0-9]+[A-Za-z0-9/\\-]{0,5}$";
    public static final String APARTMENT = "^[0-9]{0,5}$";
    public static final String COUNTRY = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s\\-']+$";

    public static final String PRODUCT_NAME = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ.,\\-\\s]+$";
    public static final String PRODUCT_DESCRIPTION = "^[A-Za-z0-9ąĄćĆęĘłŁńŃóÓśŚżŻźŹ.,\\-\\s\\\"'?!()]*$";


}
