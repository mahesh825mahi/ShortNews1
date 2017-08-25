package com.org.max.shortnews1.ui.detail;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.org.max.shortnews1.R;
import com.org.max.shortnews1.db.NewsEntity;
import java.util.List;
public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    List<NewsEntity> movieList;

    public ViewPagerAdapter(Context context, List<NewsEntity> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtrank;
        TextView txtcountry;
        TextView txtpopulation;
        ImageView imgflag;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_vert_viewpager, container, false);

        // Locate the TextViews in viewpager_item.xml
        txtrank = (TextView) itemView.findViewById(R.id.rank);
        txtcountry = (TextView) itemView.findViewById(R.id.country);
        txtpopulation = (TextView) itemView.findViewById(R.id.population);

        // Capture position and set to the TextViews
        txtrank.setText("" + movieList.get(position).getId());
        txtcountry.setText(movieList.get(position).getNewsTitle());
        txtpopulation.setText(movieList.get(position).getNewsDescription());

        // Locate the ImageView in viewpager_item.xml
        //imgflag = (ImageView) itemView.findViewById(R.id.flag);
        // Capture position and set to the ImageView
        //imgflag.setImageResource(flag[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}