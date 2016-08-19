package com.example.xiaozhu.trafrie;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/19.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
