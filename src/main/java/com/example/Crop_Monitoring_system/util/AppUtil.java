package com.example.Crop_Monitoring_system.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String fieldImageOneToBase64(byte[] bytesFieldImage1) {
        return Base64.getEncoder().encodeToString(bytesFieldImage1);
    }
    public static String fieldImageTwoToBase64(byte[] bytesFieldImage2) {
        return Base64.getEncoder().encodeToString(bytesFieldImage2);
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
    public static String generateStaffId(){
        return "STAFF-" + UUID.randomUUID();
    }
    public static String generateMonitoringId(){
        return "LOG-" + UUID.randomUUID();
    }
    public static String observedImageOneToBase64(byte [] observedImage){
        return Base64.getEncoder().encodeToString(observedImage);
    }

    public static String generateFieldId() {
        return "FIELD" + UUID.randomUUID();
    }
}
