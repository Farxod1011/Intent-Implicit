package uz.farxod.intentimplicit;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import uz.farxod.intentimplicit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
        private static final int REQUEST_IMAGE_CAPTURE = 1;
        private static final int REQUEST_SELECT_IMAGE = 2;

        private ActivityMainBinding binding;

        @RequiresApi(api = Build.VERSION_CODES.S)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.website.setOnClickListener(w -> {
                //AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //saytga kirish uchun ssilka
                    String s = "ok.ru";
                    if (!s.isEmpty()) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www." + s)));
                    } else {
                        Toast.makeText(this, "Write something to open ‼", Toast.LENGTH_SHORT).show();
                    }
                });

            binding.call.setOnClickListener(c -> {
                //tel qilish uchun
                    String s = "998901661011";
                    if (!s.isEmpty()) {
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+" + s)));
                    } else {
                        Toast.makeText(this, "Write something to open ❗❗", Toast.LENGTH_SHORT).show();
                    }
                });

            binding.email.setOnClickListener(e -> {

                String to = "farxodSayfiddinov@gmail.com";
                String s1 = "Alibek Jurayev";
                String s2 = "Hello";

                if (!to.isEmpty() && !s1.isEmpty() && !s2.isEmpty()) {
                        startActivity(Intent.createChooser(new Intent(Intent.ACTION_SEND)
                                //mail adress si
                                        .putExtra(Intent.EXTRA_EMAIL, new String[]{to})
                                //mail mavzu (тема) si
                                        .putExtra(Intent.EXTRA_SUBJECT, s1)
                                //mail da jo'natiladigon text
                                        .putExtra(Intent.EXTRA_TEXT, s2)
                                //nima orqali jo'natasiz
                                        .setType("message/rfc822"),
                                //Выберите почтовый клиент, tanlash imkonini beradi
                                "Choose an email client"
                        ));
                    } else {
                        Toast.makeText(this, "Please enter something to open ❗❗", Toast.LENGTH_SHORT).show();
                    }

                });

            binding.map.setOnClickListener(m -> {
                //кордината долготы
                    String lat = "41.2646";
                //кордината широты
                    String lon = "69.2163";
                    if (!lat.isEmpty() && !lon.isEmpty()) {
                    //lokatsiyani intent qilamiz
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat + "," + lon)));
                    } else {
                        Toast.makeText(this, "Please enter something to open ❗❗", Toast.LENGTH_SHORT).show();
                    }
            });

            binding.camera.setOnClickListener(c -> {
                final int REQUEST_IMAGE_CAPTURE = 1;
                //cameraga o'tadi
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    } catch (ActivityNotFoundException e) {
                        // display error state to the user
                    }
                //startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),"take picture"), REQUEST_IMAGE_CAPTURE;
            });

            binding.openGallery.setOnClickListener(g -> {
                //galereyani ochish
                    startActivityForResult(new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore
                                            .Images.Media
                                            .EXTERNAL_CONTENT_URI),
                            REQUEST_SELECT_IMAGE);
            });

            binding.calendar.setOnClickListener(ca -> {
                    //tadbir nomi TITLE
                    String s = "Hello";
                    //tadbir lokatsiyasi EVENT_LOCATION
                    String s1 = "41.2646, 69.2163";
                    //tadbir boshlanish vaxti EXTRA_EVENT_BEGIN_TIME
                    String s2 = "09:10";
                    //tadbir tugash vaxti EXTRA_EVENT_END_TIME
                    String s3 = "10:36";

                    if (!s.isEmpty() && !s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()) {
                        //kalendarni bugungi sanasiga tadbir qo'shish joyini ochish
                        startActivity(new Intent(Intent.ACTION_INSERT)
                                .setType("vnd.android.cursor.item/event")
                                .putExtra(CalendarContract.Events.TITLE, s)
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, s1)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, s2)
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, s3)
                        );
                    } else {
                        Toast.makeText(this, "Please enter something to open ❗❗", Toast.LENGTH_SHORT).show();
                    }

            });

            binding.settings.setOnClickListener(s -> {
                binding.settingsLayout.setVisibility(View.VISIBLE);
                //wifi sozlamasiga kirish
                binding.wifi.setOnClickListener(a -> {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                });
                //bluetooh sozlamasiga kirish
                binding.bluet.setOnClickListener(a -> {
                    startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                });
                //display sozlamasiga kirish
                binding.display.setOnClickListener(a -> {
                    startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
                });
                //tushunmadim to'g'risi
                binding.rotate.setOnClickListener(a -> {
                    startActivity(new Intent(Settings.ACTION_AUTO_ROTATE_SETTINGS));
                });


            });

        }

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
//
//                // OPEN CAMERA
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                binding.cameraTV.setImageBitmap(imageBitmap);
//
//            }
//
//            if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
//                // OPEN GALLERY
//                Uri selectedImageUri = data.getData();
//                binding.gallery.setImageURI(selectedImageUri);
//            }
//        }

    }
