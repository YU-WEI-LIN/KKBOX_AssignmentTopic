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

import com.example.kkbox_assignment.JsonToGson.GetNewRealeaseAlbum;

import java.io.InputStream;

public class NewReleaseAlbumList extends RecyclerView.Adapter<NewReleaseAlbumList.ViewHolder> {
    private Context context;
    private GetNewRealeaseAlbum AlbumList;
    String access_token;

    NewReleaseAlbumList(Context context, GetNewRealeaseAlbum AlbumList, String access_token)
    {
        this.context = context;
        this.AlbumList = AlbumList;
        this.access_token = access_token;
    }

    @Override
    public NewReleaseAlbumList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newreleasealbumlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onBindViewHolder(NewReleaseAlbumList.ViewHolder holder, final int position) {
        //final Member member = memberList.get(position);
        //setImageURI(AlbumList.getData()[position].getImages()[0].getUrl());
        new DownloadImageTask((ImageView) holder.imageId)
                .execute(AlbumList.getData()[position].getImages()[0].getUrl());
        holder.textName.setText(AlbumList.getData()[position].getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayListActivity.class);
                intent.putExtra("id", AlbumList.getData()[position].getId());
                intent.putExtra("token", access_token);
                intent.putExtra("albumimageurl", AlbumList.getData()[position].getImages()[0].getUrl());
                context.startActivity(intent);

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageId;
        TextView textId, textName;
        ViewHolder(View itemView) {
            super(itemView);
            imageId = (ImageView) itemView.findViewById(R.id.albumimage);
            textName = (TextView) itemView.findViewById(R.id.albumname);
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
