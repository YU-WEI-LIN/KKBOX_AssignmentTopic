package com.example.kkbox_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.kkbox_assignment.JsonToGson.GetAlbumTracks;
import com.example.kkbox_assignment.JsonToGson.PlayLists;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayListActivity extends AppCompatActivity {
    String id, access_token, albumimageurl, url;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        access_token = intent.getStringExtra("token");
        albumimageurl = intent.getStringExtra("albumimageurl");
        Log.d("Wayne", id);

        GetPlaylists();
    }

    private void GetPlaylists() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        if(albumimageurl != null)
        {
            url = "https://api.kkbox.com/v1.1/albums/"+id+"/tracks?territory=TW";
        }else{
            url = "https://api.kkbox.com/v1.1/featured-playlists/"+id+"?territory=TW";
        }
        Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "application/json")
                    .addHeader("authorization", "Bearer " + access_token)
                    .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d("Wayne",result);
                ImageView imageView = (ImageView) findViewById(R.id.coverimage);
                if(albumimageurl == null)
                {
                    final PlayLists posts = new Gson().fromJson(result, PlayLists.class);
                    new PlayListActivity.DownloadImageTask((ImageView) imageView)
                            .execute(posts.getImages()[0].getUrl());
                    PlayListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.playlistsView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(PlayListActivity.this, LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(new PlayListsView(PlayListActivity.this, posts, access_token));
                        }
                    });
                }
                else
                {
                    final GetAlbumTracks posts = new Gson().fromJson(result, GetAlbumTracks.class);
                    new PlayListActivity.DownloadImageTask((ImageView) imageView)
                            .execute(albumimageurl);
                    PlayListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.playlistsView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(PlayListActivity.this, LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(new PlayListsView(PlayListActivity.this, posts, access_token, albumimageurl));
                        }
                    });
                }





            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Wayne", e.toString());
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }





}