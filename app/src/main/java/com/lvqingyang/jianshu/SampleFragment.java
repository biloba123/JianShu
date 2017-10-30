package com.lvqingyang.jianshu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/9/18
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class SampleFragment extends Fragment {

    private static final String TAG = "SampleFragment";
    private android.widget.TextView tv;
    private static final String KEY_INDEX = "INDEX";
    private int mIndex;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onCreateView: ");
        mIndex=getArguments().getInt(KEY_INDEX);

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        this.tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(mIndex+"");
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onViewStateRestored: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onDetach: ");
    }

    public static SampleFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX,index);
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (BuildConfig.DEBUG) Log.d(TAG,mIndex+ " onHiddenChanged: " + hidden);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BuildConfig.DEBUG) Log.d(TAG, mIndex+" onSaveInstanceState: ");
    }

}
