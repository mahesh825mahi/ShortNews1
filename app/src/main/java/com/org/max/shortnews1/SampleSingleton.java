package com.org.max.shortnews1;
import android.content.Context;
/**
 * Created by Maheswaran on 9/1/2017.
 */
public class SampleSingleton {
private static  SampleSingleton sampleSingleton;
private Context context;
    private SampleSingleton (Context context)
    {
        this.context=context;
    }

    public static SampleSingleton getInstance(Context context)
    {
        if(sampleSingleton==null)
        {
            sampleSingleton = new SampleSingleton(context);
        }
        return sampleSingleton;
    }
}
