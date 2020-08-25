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

import com.example.kkbox_assignment.JsonToGson.GetAlbumTracks;
import com.example.kkbox_assignment.JsonToGson.PlayLists;

import java.io.InputStream;

public class PlayListsView extends RecyclerView.Adapter<PlayListsView.ViewHolder> {
    private Context context;
    private PlayLists AlbumList;
    GetAlbumTracks AlbumList2;
    private String access_token, albumimageurl;

    PlayListsView(Context context, PlayLists AlbumList, String access_token)
    {
        this.context = context;
        this.AlbumList = AlbumList;
        this.access_token = access_token;
    }

    PlayListsView(Context context, GetAlbumTracks AlbumList2, String access_token, String albumimageurl)
    {
        this.context = context;
        this.AlbumList2 = AlbumList2;
        this.access_token = access_token;
        this.albumimageurl = albumimageurl;
    }

    @Override
    public PlayListsView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlistslayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(AlbumList2 == null)
        {return AlbumList.getTracks().getSummary().getTotal();}
        else
        {return AlbumList2.getSummary().getTotal();}
    }

    @Override
    public void onBindViewHolder(PlayListsView.ViewHolder holder, final int position) {
        //final Member member = memberList.get(position);
        //setImageURI(AlbumList.getData()[position].getImages()[0].getUrl());
        if(AlbumList2 == null) {
            new DownloadImageTask((ImageView) holder.imageId)
                    .execute(AlbumList.getTracks().getData()[position].getAlbum().getImages()[0].getUrl());
            holder.textName.setText(AlbumList.getTracks().getData()[position].getName());
            holder.textArtist.setText(AlbumList.getTracks().getData()[position].getAlbum().getArtist().getName());
            holder.textReleaseDate.setText(AlbumList.getTracks().getData()[position].getAlbum().getRelease_date());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongActivity.class);
                    intent.putExtra("id", AlbumList.getTracks().getData()[position].getId());
                    context.startActivity(intent);

                }
            });
        }else{
            new DownloadImageTask((ImageView) holder.imageId)
                    .execute(albumimageurl);
            holder.textName.setText(AlbumList2.getData()[position].getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongActivity.class);
                    intent.putExtra("id", AlbumList2.getData()[position].getId());
                    context.startActivity(intent);

                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageId;
        TextView textName, textArtist, textReleaseDate;
        ViewHolder(View itemView) {
            super(itemView);
            imageId = (ImageView) itemView.findViewById(R.id.coverimage);
            textName = (TextView) itemView.findViewById(R.id.playlistsname);
            textArtist = (TextView) itemView.findViewById(R.id.artistname);
            textReleaseDate = (TextView) itemView.findViewById(R.id.releasedate);
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
