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
import java.util.ArrayList;
import java.util.List;

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
                    final List<String[]> list = new ArrayList<String[]>();
                    for (int i = 0; i <= 9 && i < posts.getTracks().getSummary().getTotal(); i++) {
                        String[] str = {posts.getTracks().getData()[i].getName(), posts.getTracks().getData()[i].getAlbum().getImages()[0].getUrl(), posts.getTracks().getData()[i].getId(), posts.getTracks().getData()[i].getAlbum().getArtist().getName(), posts.getTracks().getData()[i].getAlbum().getRelease_date()};
                        list.add(i, str);
                    }
                    PlayListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.playlistsView);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlayListActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            final PlayListsView playListsView = new PlayListsView(PlayListActivity.this, posts, list, access_token);
                            recyclerView.setAdapter(playListsView);
                            EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                                @Override
                                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                                    Log.d("Wayne","onLoadMore");
                                    if(totalItemsCount < posts.getTracks().getSummary().getTotal()) {
                                        for (int i = totalItemsCount; i <= totalItemsCount+9 && i < posts.getTracks().getSummary().getTotal(); i++) {
                                            String[] str = {posts.getTracks().getData()[i].getName(), posts.getTracks().getData()[i].getAlbum().getImages()[0].getUrl(), posts.getTracks().getData()[i].getId(), posts.getTracks().getData()[i].getAlbum().getArtist().getName(), posts.getTracks().getData()[i].getAlbum().getRelease_date()};
                                            list.add(i, str);
                                        }
                                        view.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                playListsView.notifyItemRangeInserted(playListsView.getItemCount(), 10);
                                            }
                                        });
                                    }
                                }
                            };
                            recyclerView.addOnScrollListener(scrollListener);
                        }
                    });
                }
                else
                {
                    final GetAlbumTracks posts = new Gson().fromJson(result, GetAlbumTracks.class);
                    new PlayListActivity.DownloadImageTask((ImageView) imageView)
                            .execute(albumimageurl);
                    final List<String[]> list = new ArrayList<String[]>();
                    for (int i = 0; i <= 9 && i < posts.getSummary().getTotal(); i++) {
                        String[] str = {posts.getData()[i].getName(), posts.getData()[i].getId()};
                        list.add(i, str);
                    }
                    PlayListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.playlistsView);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlayListActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            final PlayListsView playListsView = new PlayListsView(PlayListActivity.this, posts, list, access_token, albumimageurl);
                            recyclerView.setAdapter(playListsView);
                            EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                                @Override
                                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                                    Log.d("Wayne","onLoadMore");
                                    if(totalItemsCount < posts.getSummary().getTotal()) {
                                        for (int i = totalItemsCount; i <= totalItemsCount+9 && i < posts.getSummary().getTotal(); i++) {
                                            String[] str = {posts.getData()[i].getName(), posts.getData()[i].getId()};
                                            list.add(i, str);
                                        }
                                        view.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                playListsView.notifyItemRangeInserted(playListsView.getItemCount(), 10);
                                            }
                                        });
                                    }
                                }
                            };
                            recyclerView.addOnScrollListener(scrollListener);
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