package com.coread.readingin.tool.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.coread.readingin.R;

/**
 * Created by tzduan on 23/10/17.
 */

public class CoReadToolBar extends Toolbar {
    private Context mContext;

    public CoReadToolBar(Context context) {
        super(context);
        mContext = context;
        initViews();
    }

    public CoReadToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
    }

    public CoReadToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    private void initViews(){
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.coread_tool_bar, this);
    }
}
