package com.xiren.download.ui.base;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Activity管理
 */
public class RunningActivityManage {

    public static final String TAG = RunningActivityManage.class.getSimpleName();
    private static RunningActivityManage mRunningActivityManage;
    private ArrayList<Activity> activityList;

    /**私有化构造方法*/
    private RunningActivityManage(){
        activityList = new ArrayList<Activity>();
    }

    /**
     * 获取RunningActivityManage的实例
     */
    public static RunningActivityManage getInstance() {
        if (null == mRunningActivityManage) {
            mRunningActivityManage = new RunningActivityManage();
        }
        return mRunningActivityManage;
    }

    /**
     * 清除保存的Activity队列
     */
    public synchronized void clear() {
        if (activityList != null) {
            activityList.clear();
        }
        mRunningActivityManage = null;
    }

    /**
     * 结束队列中的全部Activity
     */
    public synchronized void finishAllActivity() {
        if (activityList != null) {
            Log.i(TAG, "activityList.size(): " + activityList.size());
            Iterator<Activity> iterator = activityList.iterator();
            while(iterator.hasNext()) {
                Activity activity = iterator.next();
                if (activity.isFinishing()){
                    iterator.remove();
                    Log.i(TAG, "activity: " + activity);
                } else {
                    iterator.remove();
                    activity.finish();
                    Log.i(TAG, "the activity is close: " + activity);
                }
            }
        }
    }

    /**
     * 结束掉其他Activity
     * @param exceptActivity 除了该Activity外都移除
     */
    public synchronized void finishOthersActivity(Activity exceptActivity) {
        if (activityList != null) {
            Log.i(TAG, "activityList.size(): " + activityList.size());
            Iterator<Activity> iterator = activityList.iterator();
            while(iterator.hasNext()) {
                Activity activity = iterator.next();
                if(activity == exceptActivity) {
                    continue;
                }

                if (activity.isFinishing()){
                    iterator.remove();
                    Log.i(TAG, "activity: " + activity);
                } else {
                    iterator.remove();
                    activity.finish();
                    Log.i(TAG, "the activity is close: " + activity);
                }
            }
        }
    }

    /**
     * 添加Activity到列表
     * @param activity
     */
    public synchronized void addActivity(Activity activity) {
        activityList.add(activity);
        Log.i(TAG, "add class name: " + activity.getClass().getName() + ", size: " + activityList.size());
    }

    /**
     * 移除指定的Activity
     * @param activity
     */
    public synchronized void removeActivity(Activity activity) {
        if (activityList != null && activityList.size() > 0) {
            int size = activityList.size();
            for (int index = size - 1; index >= 0; index --) {
                Activity item = activityList.get(index);
                if (item != null && item.getClass().getName().equalsIgnoreCase(activity.getClass().getName())) {
                    activityList.remove(index);
                    Log.i(TAG, "remove class name: " + activity.getClass().getName() + ", size: " + activityList.size());
                    break;
                }
            }
        }
    }


    /**
     * 移除指定的Activity
     * @param activityClass
     */
    public synchronized void removeActivity(Class activityClass) {

        if (activityList != null && activityList.size() > 0) {
            int size = activityList.size();
            for (int index = size - 1; index >= 0; index --) {
                Activity item = activityList.get(index);
                if (item != null && item.getClass().getName().equalsIgnoreCase(activityClass.getName())) {
                    activityList.remove(index);
                    Log.i(TAG, "remove class name: " + activityClass.getClass().getName() + ", size: " + activityList.size());
                    break;
                }
            }
        }
    }

    /**
     * 移除顶部的Activity
     */
    public synchronized void removeTopActivity() {
        if (activityList != null && activityList.size() > 0) {
            Log.i(TAG, "size: " + activityList.size());
            activityList.remove(activityList.size() - 1);
        }
        Log.i(TAG, "size: " + activityList.size());
    }

    /**
     * 获取顶部的Activity
     */
    public synchronized Activity getTopActivity() {
        Activity topActivity = null;
        if (activityList != null && activityList.size() > 0) {
            topActivity = activityList.get(activityList.size() - 1);
        }
        return topActivity;
    }

}
