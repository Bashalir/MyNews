package com.oc.bashalir.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oc.bashalir.mynews.Controllers.Utils.Utilities;
import com.oc.bashalir.mynews.Models.MostPopular;
import com.oc.bashalir.mynews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * set mostpopular attributes for the articles
 */
public class PopularHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_page_news_title)
    TextView mTitle;
    @BindView(R.id.fragment_page_news_ariane)
    TextView mAriane;
    @BindView(R.id.fragment_page_news_date)
    TextView mDate;
    @BindView(R.id.fragment_page_news_img)
    ImageView mImg;

    //load the view
    public PopularHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Set and Format the mostpopular attributes
     *
     * @param mostPopular
     */
    public void updateWithPopular(MostPopular.Result mostPopular) {

        String newsDate = new Utilities().DateShortFormatter(mostPopular.getPublishedDate(), "yyyy-MM-dd");

        Log.d("TAG", mostPopular.getTitle());

        mTitle.setText(mostPopular.getTitle());
        mAriane.setText(mostPopular.getSection());
        mDate.setText(newsDate);

        if (mostPopular.getMedia().isEmpty()) {
            mImg.setImageResource(R.drawable.logo);
        } else {
            String imgURL = mostPopular.getMedia().get(0).getMediaMetadata().get(6).getUrl();
            Picasso.get().load(imgURL).into(mImg);
        }

    }
}
