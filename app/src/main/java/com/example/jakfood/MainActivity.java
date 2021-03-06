package com.example.jakfood;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.jakfood.adapter.FoodAdapter;
import com.example.jakfood.helper.SessionManager;
import com.example.jakfood.model.DataKategoriItem;
import com.example.jakfood.model.DataMakananItem;
import com.example.jakfood.model.ResponseKategori;
import com.example.jakfood.model.ResponseMakanan;
import com.example.jakfood.model.ResponseUser;
import com.example.jakfood.network.ConfigRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends SessionManager implements FoodAdapter.OnFoodClick, SwipeRefreshLayout.OnRefreshListener {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final int REQ_FILE_CODE = 1;

    List<DataKategoriItem> dataKategori = new ArrayList<>();
    List<DataMakananItem> dataMakanan = new ArrayList<>();

    @BindView(R.id.spin_kategori_utama)
    Spinner spinKategoriUtama;
    @BindView(R.id.list_food)
    RecyclerView listFood;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    Dialog dialog, dialog2;
    TextInputEditText edtInsertNameFood, edtInsertUpdateId, edtUpdateNameFood, edtUpdateId;
    Button btnUploadImageFood, btnUpload, btnCancel, getBtnUpload, btnDelete, btnUpdateImages, btnUpdate;
    ImageView ivPreviewInsert;
    Spinner spInsert, spUpdate;
    String idMakanan, strIdUser, kategori, path, time, namaMakanan;
    Target target;
    Uri filePath;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_utama);
        ButterKnife.bind(this);
        refresh.setOnRefreshListener(this);

        requestPermission();
        insertFoodData();
        fetchDataKategori(spinKategoriUtama);

    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

        }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

    }

    private void insertFoodData() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.item_add_food);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);

                edtInsertNameFood = dialog.findViewById(R.id.edt_input_makanan);
                btnUploadImageFood = dialog.findViewById(R.id.btn_upload_images);
                spInsert = dialog.findViewById(R.id.spin_kategori);
                ivPreviewInsert = dialog.findViewById(R.id.image_preview_insert);
                btnUpload = dialog.findViewById(R.id.btn_upload);
                btnCancel = dialog.findViewById(R.id.btn_cancel);

                btnUploadImageFood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFileChooser();

                    }
                });

                fetchDataKategori(spInsert);

                btnUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        namaMakanan = edtInsertNameFood.getText().toString().trim();

                        if (TextUtils.isEmpty(namaMakanan)){
                            edtInsertNameFood.setError(getString(R.string.isEmpyField));

                        }
                        else if (ivPreviewInsert.getDrawable() == null){
                            shortToast("Gambar Harus Ada");

                        }
                        else {
                            fetchInsertData(kategori);
                            shortToast("Berhasil update");
                            dialog.dismiss();

                        }

                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });

    }

    private void fetchDataMakanan(String kategoriMakanan) {
        String idUSer = sessionManager.getIdUser();
        ConfigRetrofit.getInstance().getDataMakanan(idUSer, kategoriMakanan).enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {
                if (response.isSuccessful()){
                    dataMakanan = response.body().getDataMakanan();
                    String id[] = new String[dataMakanan.size()];
                    String name[] = new String[dataMakanan.size()];
                    String time[] = new String[dataMakanan.size()];
                    String images[] = new String[dataMakanan.size()];

                    for (int index = 0; index < dataMakanan.size(); index++) {
                        id[index] = dataMakanan.get(index).getIdMakanan();
                        name[index] = dataMakanan.get(index).getNama();
                        time[index] = dataMakanan.get(index).getInsertTime();
                        images[index] = dataMakanan.get(index).getFotoMakanan();
                        strIdUser = id[index];

                    }

                    FoodAdapter adapter = new FoodAdapter(MainActivity.this, dataMakanan);
                    listFood.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    listFood.setHasFixedSize(true);
                    listFood.setAdapter(adapter);
                    adapter.setOnclickListener(MainActivity.this);

                }
            }

            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {

            }
        });

    }

    private void fetchInsertData(String kategoriMakanan) {
        try {
            path = getPath(filePath);
            strIdUser = sessionManager.getIdUser();

        } catch (Exception e) {
            longToast("Gambar terlalu besar \n silakan pilih gambar yang lebih kecil");
            e.printStackTrace();

        }

        time = getCurentDate();
        try {
            new MultipartUploadRequest(c, BuildConfig.BASE_UPLOAD)
                    .addFileToUpload(path, "image")
                    .addParameter("vsiduser", strIdUser)
                    .addParameter("vsnamamakanan", namaMakanan)
                    .addParameter("vstimeinsert", time)
                    .addParameter("vskategori", kategoriMakanan)
                    //.setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            shortToast(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            shortToast(e.getMessage());
        }

    }

    private void fetchDataKategori(Spinner spInsert) {
        ConfigRetrofit.getInstance().getKategoriMakanan().enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                if (response.isSuccessful() && response.body().getDataKategori() !=null){
                    dataKategori = response.body().getDataKategori();
                    String id[] = new String[dataKategori.size()];
                    String nameKategori[] = new String[dataKategori.size()];

                    for (int index = 0; index < dataKategori.size(); index++) {
                        id[index] = dataKategori.get(index).getIdKategori();
                        nameKategori[index] = dataKategori.get(index).getNamaKategori();
                    }

                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, nameKategori);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spInsert.setAdapter(adapter);
                    spInsert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                            kategori = parent.getItemAtPosition(i).toString();
                            fetchDataMakanan(kategori);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {

            }
        });

    }

    private void showFileChooser() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK);
        intentGallery.setType("image/*");
        intentGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGallery, "Select Pictures"), MainActivity.REQ_FILE_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_FILE_CODE && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivPreviewInsert.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            sessionManager.logout();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getPath(Uri filepath) {
        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                MediaStore.Images.Media._ID + " = ? ",
                new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @Override
    public void onItemClick(int position) {
        dialog2 = new Dialog(MainActivity.this);
        dialog2.setContentView(R.layout.item_update_food);
        dialog2.setTitle("Data Food");
        dialog2.setCancelable(true);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.show();

        edtUpdateId = dialog2.findViewById(R.id.edt_id_makanan);
        edtUpdateNameFood = dialog2.findViewById(R.id.edt_input_makanan_update);
        btnUpdateImages = dialog2.findViewById(R.id.btn_update_images);
        spUpdate = dialog2.findViewById(R.id.spin_kategori_update);
        ivPreviewInsert = dialog2.findViewById(R.id.image_preview_update);
        btnUpdate = dialog2.findViewById(R.id.btn_update);
        btnDelete = dialog2.findViewById(R.id.btn_delete);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ivPreviewInsert.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                longToast(e.getMessage());

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.get().load(BuildConfig.BASE_URL + "uploads/" + dataMakanan.get(position).getFotoMakanan())
                .into(target);

        fetchDataKategori(spUpdate);
        edtUpdateNameFood.setText(dataMakanan.get(position).getMakanan());
        edtUpdateId.setText(dataMakanan.get(position).getIdMakanan());

        btnUpdateImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                fetchUpdate();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idMakanan = edtUpdateId.getText().toString();
                deleteMakanan();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idMakanan = edtUpdateId.getText().toString().trim();
                fetchUpdate();
            }
        });


    }

    private void fetchUpdate() {
        try {
            path = getPath(filePath);
            strIdUser = sessionManager.getIdUser();
        } catch (Exception e){
            e.printStackTrace();

        }

        namaMakanan = edtUpdateNameFood.getText().toString().trim();
        idMakanan = edtUpdateId.getText().toString().trim();

        if (TextUtils.isEmpty(namaMakanan)) {
            edtUpdateNameFood.setError("Nama Makanan harus berbeda");
        }
        else if (ivPreviewInsert.getDrawable() == null) {
            shortToast("Gambar harus dipilih");

        }
        else if (path == null) {
            shortToast("gambar harus diganti");

        }
        else {
            try {
                new MultipartUploadRequest(c, BuildConfig.BASE_UPDATE)
                        .addFileToUpload(path, "image")
                        .addParameter("vsiduser", strIdUser)
                        .addParameter("vsnamamakanan", namaMakanan)
                        .addParameter("vstimeinsert", time)
                        .addParameter("vsidkategori", kategori)
                        .addParameter("vsidmakanan", idMakanan)
                        //.setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                shortToast(e.getMessage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                shortToast(e.getMessage());
            }

            dialog2.dismiss();

        }

    }

    private void deleteMakanan() {
        ConfigRetrofit.getInstance().deleteData(idMakanan).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.body().getResult().equals("1")) {
                    dialog2.dismiss();
                    longToast(idMakanan);
                    fetchDataMakanan(kategori);

                }
                else {
                    shortToast(response.body().getMsg());
                    dialog2.setCancelable(true);

                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });

    }

    @Override
    public void onRefresh() {
        refresh.setRefreshing(false);
        fetchDataMakanan(kategori);

    }
}
