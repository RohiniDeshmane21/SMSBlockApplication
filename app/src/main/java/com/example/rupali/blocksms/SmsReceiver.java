package com.example.rupali.blocksms;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.SmsMessage;
import java.util.ArrayList;

public class SmsReceiver extends WakefulBroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

       CommentsDataSource datasource = new CommentsDataSource(context);
       datasource.open();

       ArrayList<Comment> Numbers = (ArrayList<Comment>) datasource.getAllComments();

       Bundle bundle = intent.getExtras();
        SmsMessage msgs[] =  null;
        String number;
        if(bundle != null)
        {
          // . . .. get SMS message pass
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for(int i = 0; i < msgs.length; i++) {

                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                number = msgs[i].getOriginatingAddress();
                int sizeINComingNo = number.length();
                if( sizeINComingNo > 10)
                {
                    int Extra = sizeINComingNo - 10;
                    number = number.substring(Extra,sizeINComingNo);
                    System.out.println(" number = " + number);
                }

                for (int j = 0; j < Numbers.size(); j++) {

                    String INo =Numbers.get(j).toString();
                    int size1 = INo.length();
                    if(size1 > 10)
                    {
                        int ExtraSize = size1 - 10;
                        INo = INo.substring(ExtraSize,size1);
                        System.out.print(" Ino " + INo + " Number " + number);

                        if(INo.equals(number))
                        {
                            abortBroadcast();
                        }
                    }
                    else
                    if(INo.equals(number))
                    {
                        abortBroadcast();
                    }
                 }
            }

        }

    }
}
