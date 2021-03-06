package com.example.raeven.popularmovies.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raeven.popularmovies.Model.MovieModel;
import com.example.raeven.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private ArrayList<MovieModel> mMovieDataList = new ArrayList<MovieModel>();
    private Context mContext;
    private MovieOnClickListener mMovieOnCLickListener;

    public MovieAdapter(Context context, MovieOnClickListener listener){
        mContext = context;
        mMovieOnCLickListener = listener;
    }

    public interface MovieOnClickListener{
        void movieOnClick(MovieModel movieDetailsObject);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView iv_moviePoster;
        public TextView tv_movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            iv_moviePoster = (ImageView)itemView.findViewById(R.id.iv_moviePoster);
            tv_movieTitle = (TextView)itemView.findViewById(R.id.tv_mainTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MovieModel movieDetailsObject = mMovieDataList.get(position);
            mMovieOnCLickListener.movieOnClick(movieDetailsObject);

        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout = R.layout.movie_layout_list;
        boolean shouldAttach = false;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layout, null, shouldAttach);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String movieImageLink = mMovieDataList.get(position).getMainPosterLink();
        String movieTitle = mMovieDataList.get(position).getTitle();

        Picasso.with(mContext).load(movieImageLink).into(holder.iv_moviePoster);
        holder.tv_movieTitle.setText(movieTitle);
    }

    @Override
    public int getItemCount() {
        if (mMovieDataList != null){
            if (mMovieDataList.size() == 0){
                return 0;
            }

            else {
                return mMovieDataList.size();
            }
        }

        else
            return 0;

    }

    public void loadData(ArrayList<MovieModel> dataList){
        mMovieDataList = dataList;
        notifyDataSetChanged();
    }


}
