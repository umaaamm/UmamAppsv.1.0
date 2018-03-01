package com.fun_corp.umamappsv10;


import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Beranda extends Fragment {
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    Firebase bacadata,bacadata1,bacadata2,bacadata3;
    public ImageView btnsetmakan,semprot,pompa,minum;
    public TextView smakan,ssemprot,suhu,kelembapan,stower,sjam;
    String makan,faksin,pompas,semprott,sstower,jam,detik;
    Double level;
    Double l1,l2,l3,l4;
    int year,month,day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_beranda, container, false);
        Firebase.setAndroidContext(this.getActivity());
        btnsetmakan = (ImageView) view.findViewById(R.id.makandong);
        semprot = (ImageView) view.findViewById(R.id.semprot);
        pompa = (ImageView) view.findViewById(R.id.pompa);
        kelembapan = (TextView) view.findViewById(R.id.kelembapan);
        suhu = (TextView) view.findViewById(R.id.suhu);
        smakan = (TextView) view.findViewById(R.id.smakan);
        minum =(ImageView) view.findViewById(R.id.minum);
        ssemprot = (TextView) view.findViewById(R.id.ssemprot);
        stower = (TextView) view.findViewById(R.id.stower);
        //sjam = (TextView) view.findViewById(R.id.sjam);

        bacadata = new Firebase("https://kandang-pintar.firebaseio.com/kendali");
        bacadata2 = new Firebase("https://kandang-pintar.firebaseio.com/rekam_data/kelembapan");
        bacadata1 = new Firebase("https://kandang-pintar.firebaseio.com/rekam_data/suhu");
        bacadata3 = new Firebase("https://kandang-pintar.firebaseio.com/rekam_data/level_air");



        bacadata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String makana="hidup";
                String f = "hidup";
                String t = "hidup";
                try{
                makan = dataSnapshot.child("makan").getValue().toString();
                //semprott = dataSnapshot.child("faksin").getValue().toString();
                faksin = dataSnapshot.child("faksin").getValue().toString();
                pompas = dataSnapshot.child("pompa_air").getValue().toString();
                if (makana.equals(makan)){
                    smakan.setText("Sedang Memberi Makan!");
                    btnsetmakan.setImageResource(R.mipmap.makan_p);
                }else {
                    smakan.setText("Tidak Memberi Makan!");
                    btnsetmakan.setImageResource(R.mipmap.makan);
                }
                if (f.equals(faksin)){
                    ssemprot.setText("Sedang Menyemprot");
                    semprot.setImageResource(R.mipmap.semprot_p);
                }else {
                    ssemprot.setText("Tidak Menyemprot");
                    semprot.setImageResource(R.mipmap.ic_blur_on_white_24dp);
                }
                if (t.equals(pompas)){
                    stower.setText("Sedang Mengisi");
                    pompa.setImageResource(R.mipmap.tower_p);
                }else {
                    stower.setText("Tidak Mengisi");
                    pompa.setImageResource(R.mipmap.ic_opacity_white_24dp);
                }
                }catch (Exception e) {
                    Snackbar.make(getView(), "Tidak Dapat Membaca Data, Periksa Koneksi Internet Anda!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnsetmakan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    bacadata.child("makan").setValue("hidup");
                    //Snackbar.make(getView(), "Hidupin Aku", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }catch (Exception e){
                    Snackbar.make(getView(), "Tidak Dapat Memproses, Periksa Koneksi Internet Anda!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
        semprot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               String sfaksin="hidup";
                try {
                    if (faksin.equals(sfaksin)) {
                        bacadata.child("faksin").setValue("mati");
                    } else {
                        bacadata.child("faksin").setValue("hidup");
                    }
                }catch (Exception e){
                    Snackbar.make(getView(), "Tidak Dapat Memproses, Periksa Koneksi Internet Anda!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                //Snackbar.make(getView(), "Hidupin Aku", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        pompa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pompaa="hidup";
                try {
                    if (pompas.equals(pompaa)) {
                        bacadata.child("pompa_air").setValue("mati");
                    } else {
                        bacadata.child("pompa_air").setValue("hidup");
                    }
                }catch (Exception e){
                    Snackbar.make(getView(), "Tidak Dapat Memproses, Periksa Koneksi Internet Anda!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                //Snackbar.make(getView(), "Hidupin Aku", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });



        bacadata3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    //String mam = String.valueOf(noteSnapshot.child("data").getValue().toString());
                    try {
                        String a = String.valueOf(noteSnapshot.child("data").getValue().toString());
                        double level = Double.parseDouble(a);

                        //Snackbar.make(getView(), ""+level, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        l1 = Double.valueOf(20);
                        l2 = Double.valueOf(50);
                        l3 = Double.valueOf(75);
                        l4 = Double.valueOf(100);

                        if (level >= 0 && level <= l1) {
                            minum.setImageResource(R.mipmap.battery);
                        }
                        if (level >= 21 && level <= l2) {
                            minum.setImageResource(R.mipmap.batteryy);
                        }
                        if (level >= 51 && level <= l3) {
                            minum.setImageResource(R.mipmap.batteryyy);
                        }
                        if (level >= 76 && level <= l4) {
                            minum.setImageResource(R.mipmap.batteryyyy);
                        }
                    }catch (Exception e){
                        Snackbar.make(getView(), "Tidak Dapat Memproses, Periksa Koneksi Internet Anda!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        bacadata1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot noteSnapshot : dataSnapshot.getChildren()){
//                    smakan.setText(noteSnapshot.child("status").getValue().toString());
                    suhu.setText(noteSnapshot.child("data").getValue().toString()+" °C");
                    //kelembapan.setText(noteSnapshot.child("data").getValue().toString());

                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        bacadata2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot noteSnapshot : dataSnapshot.getChildren()){
//                    smakan.setText(noteSnapshot.child("status").getValue().toString());
                    //suhu.setText(noteSnapshot.child("data").getValue().toString());
                    kelembapan.setText(noteSnapshot.child("data").getValue().toString()+" %");
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return view;
    }


//    public void notifkecepatan() {
//        Intent intent = new Intent(this.getActivity(), MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this.getActivity(), 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this.getActivity())
//                .setSmallIcon(R.mipmap.speedo1)
//                .setContentTitle("Kecepatan Melebihi batas")
//                .setContentText("Anak anda melebihi batas yang telah ditentukan")
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0, notificationBuilder.build());
//    }
//    public void notifkece() {
//        Intent intent = new Intent(this.getActivity(), MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this.getActivity(), 2, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this.getActivity())
//                .setSmallIcon(R.mipmap.ic_warning_black_48dp)
//                .setContentTitle("Kecelakaan")
//                .setContentText("Anak anda Kecelakaan")
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//        // Elfin Sanjaya kamu ganteng bgt saya ngevans sama kamu
//        NotificationManager notificationManager =
//                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(2, notificationBuilder.build());
//    }

}
