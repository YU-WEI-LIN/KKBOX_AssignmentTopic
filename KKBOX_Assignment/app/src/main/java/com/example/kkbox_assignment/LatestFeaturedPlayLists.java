package com.example.kkbox_assignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kkbox_assignment.JsonToGson.GetLatestFeaturedPlaylists;

import java.io.InputStream;
import java.util.List;

public class LatestFeaturedPlayLists extends RecyclerView.Adapter<LatestFeaturedPlayLists.ViewHolder> {
    private Context context;
    private GetLatestFeaturedPlaylists AlbumList;
    List<String[]> list;
    private String access_token;

    LatestFeaturedPlayLists(Context context, GetLatestFeaturedPlaylists AlbumList, List<String[]> list, String access_token)
    {
        this.context = context;
        this.AlbumList = AlbumList;
        this.list = list;
        this.access_token = access_token;
    }

    @Override
    public LatestFeaturedPlayLists.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.latestfeaturedplaylistslayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(LatestFeaturedPlayLists.ViewHolder holder, final int position) {
        //final Member member = memberList.get(position);
        //setImageURI(AlbumList.getData()[position].getImages()[0].getUrl());
        new DownloadImageTask((ImageView) holder.imageId)
                .execute(list.get(position)[1]);
        holder.textName.setText(list.get(position)[0]);
        holder.autuorName.setText(list.get(position)[3]);
        String updatetime[] = list.get(position)[4].split("T");
        holder.updatetime.setText(updatetime[0]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayListActivity.class);
                intent.putExtra("id", list.get(position)[2]);
                intent.putExtra("token", access_token);
                context.startActivity(intent);

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageId;
        TextView textId, textName, autuorName, updatetime;
        ViewHolder(View itemView) {
            super(itemView);
            imageId = (ImageView) itemView.findViewById(R.id.listimage);
            textName = (TextView) itemView.findViewById(R.id.listname);
            autuorName = (TextView) itemView.findViewById(R.id.author);
            updatetime = (TextView) itemView.findViewById(R.id.updatetime);
        }
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
