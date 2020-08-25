package com.example.kkbox_assignment;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kkbox_assignment.JsonToGson.GetLatestFeaturedPlaylists;
import com.example.kkbox_assignment.JsonToGson.GetNewRealeaseAlbum;
import com.example.kkbox_assignment.JsonToGson.GetToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewReleasePagers extends RelativeLayout {
    String access_token;
    public NewReleasePagers(Context context, int PageNumber) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.newreleasepagers_layout, null);//連接頁面

        addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        GetkkboxToken(context);
    }

    private void GetkkboxToken(final Context context) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        FormBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .add("client_id", "41dfd0d2b3d7ea1f5d4b2b4ad81b1cc3")
                .add("client_secret", "4f2de64222b92ca4b517f2bc48807f19")
                .build();
        Request request = new Request.Builder()
                .url("https://account.kkbox.com/oauth2/token")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                //Log.d("Wayne",result);
                GetToken posts = new Gson().fromJson(result, GetToken.class);

                Log.d("Wayne", "access_token: " + posts.getAccess_token());
                Log.d("Wayne", "token_type: " + posts.getToken_type());
                Log.d("Wayne", "expires_in: " + posts.getExpires_in());

                access_token = posts.getAccess_token();

                GetNewReleaseAlbums(context);

                GetLatestFeaturedPlaylists(context);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Wayne", e.toString());
            }
        });


    }

    private void GetNewReleaseAlbums(final Context context) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.kkbox.com/v1.1/new-release-categories/4pVhRTE2XjCwnJfQRn/albums?territory=TW&limit=10")
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Bearer "+access_token)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d("Wayne",result);
                final GetNewRealeaseAlbum posts = new Gson().fromJson(result, GetNewRealeaseAlbum.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.newreleasealbumView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(new NewReleaseAlbumList(context, posts, access_token));
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Wayne", e.toString());
            }
        });
    }

    private void GetLatestFeaturedPlaylists(final Context context) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.kkbox.com/v1.1/featured-playlists?territory=TW")
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Bearer "+access_token)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d("Wayne",result);
                final GetLatestFeaturedPlaylists posts = new Gson().fromJson(result, GetLatestFeaturedPlaylists.class);
                final List<String[]> list = new ArrayList<String[]>();
                for (int i = 0; i <= 9; i++) {
                    String[] str = {posts.getData()[i].getTitle(), posts.getData()[i].getImages()[0].getUrl(), posts.getData()[i].getId(), posts.getData()[i].getOwner().getName(), posts.getData()[i].getUpdated_at()};
                    list.add(i, str);
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.latestfeaturedplaylistsView);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        recyclerView2.setLayoutManager(linearLayoutManager);
                        final LatestFeaturedPlayLists latestFeaturedPlayLists = new LatestFeaturedPlayLists(context, posts, list, access_token);
                        recyclerView2.setAdapter(latestFeaturedPlayLists);
                        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                            @Override
                            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                                Log.d("Wayne","onLoadMore");
                                if(totalItemsCount < posts.getSummary().getTotal()) {
                                    for (int i = totalItemsCount; i <= totalItemsCount+9 && i < posts.getSummary().getTotal(); i++) {
                                        String[] str = {posts.getData()[i].getTitle(), posts.getData()[i].getImages()[0].getUrl(), posts.getData()[i].getId(), posts.getData()[i].getOwner().getName(), posts.getData()[i].getUpdated_at()};
                                        list.add(i, str);
                                    }
                                    view.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            latestFeaturedPlayLists.notifyItemRangeInserted(latestFeaturedPlayLists.getItemCount(), 10);
                                        }
                                    });
                                }
                            }
                        };
                        recyclerView2.addOnScrollListener(scrollListener);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Wayne", e.toString());
            }
        });
    }




}
