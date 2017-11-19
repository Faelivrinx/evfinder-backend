package com.mypieceofcode.evfinder.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mypieceofcode.evfinder.command.TaskResponse;

public class PushNotificationHelper {
    public final static String AUTH_KEY_FCM = "AAAAzCYrSDE:APA91bEk2IOK46Cvircg0LTuOdAMtIJArNEpWKiHgDgFaqeQuueVTrmNdF0VdTKLJfJNmRVPsFCiXbf6-I8uyVp272dus9QKOD19-zlMmioGuawKxmir0wLxom31u3aOxCMdfZ_hxeKo";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    private static TaskResponse response = new TaskResponse("ADD_FRIEND", "FAILED");

    public static TaskResponse sendPushNotification(String deviceTokenList, String username) {
        Sender sender = new Sender(AUTH_KEY_FCM);
        Message msg = new Message.Builder().addData("message", username + " chce dodać Cię do znajomych!")
                .build();

        try {
            Result result = sender.send(msg, deviceTokenList, 5);
                if (result.getMessageId() != null) {
                    System.out.println("Push Notification Sent Successfully");
                    response.setSucces();
                } else {
                    System.out.println("ErrorCode " + result.getErrorCodeName());
                    response.setFailed();
                }
        } catch (IOException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
        return response;
    }
}