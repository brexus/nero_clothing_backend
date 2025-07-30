package com.example.nero_clothing_backend.common;

public class RegexConstants {
    public static final String STREET = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŻżŹź0-9\\s\\-.]{0,100}$";
    public static final String CITY = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s\\-]{0,80}$";
    public static final String ZIP_CODE = "^\\d{2}-\\d{3}$";
    public static final String BUILDING = "^[0-9]+[A-Za-z0-9/\\-]{0,5}$";
    public static final String APARTMENT = "^[0-9]{0,5}$";
    public static final String COUNTRY = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s\\-]{0,50}$";



    public static final String PRODUCT_NAME = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ.,\\-\\s]{0,50}$";
    public static final String PRODUCT_DESCRIPTION = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ.,\\-\\s]{0,50}$";
    public static final String PRODUCT_CATEGORY = "^(TSHIRTS|HOODIES|BOTTOMS|ACCESSORIES)?$";

    public static final String PRICE = "^(\\d+\\.\\d{1,2}|\\d+)?$";

//    public static final String PRODUCT_CATEGORY = "^(TSHIRTS|HOODIES|BOTTOMS|ACCESSORIES)?$";




    public static final String STOCK_QUANTITY = "^\\d+$";


}
