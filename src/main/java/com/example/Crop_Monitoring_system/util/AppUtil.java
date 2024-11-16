package com.example.Crop_Monitoring_system.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String fieldImageToBase64(byte[] bytesFieldImage1) {
        return Base64.getEncoder().encodeToString(bytesFieldImage1);
    }

    public static String cropImageToBase64(byte[] bytesCropImage) {
        return Base64.getEncoder().encodeToString(bytesCropImage);
    }
    public static String generateVehicleId(){
        return "VEHICLE-" + UUID.randomUUID();
    }
    public static String generateEquipmentId(){

        return "EQUIP-" + UUID.randomUUID();
    }

}
