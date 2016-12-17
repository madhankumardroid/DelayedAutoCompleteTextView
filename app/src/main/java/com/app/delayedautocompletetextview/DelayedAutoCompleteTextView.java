package com.app.delayedautocompletetextview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

public class DelayedAutoCompleteTextView extends AutoCompleteTextView {
    private static final int HANDLER_KEY_USER_TYPED = 102;
    private static final int AUTOCOMPLETE_DELAY_DURATION = 2000; //Set for 2 secs
    private int mAutoCompleteDelay = AUTOCOMPLETE_DELAY_DURATION;
    private ProgressBar mLoadingIndicator;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            DelayedAutoCompleteTextView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };
    public DelayedAutoCompleteTextView(Context context) {
        super(context);
    }

    public DelayedAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DelayedAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLoadingIndicator(ProgressBar progressBar) {
        this.mLoadingIndicator = progressBar;
    }

    public void setAutoCompleteDelay(int autoCompleteDelay) {
        this.mAutoCompleteDelay = autoCompleteDelay;
    }

    @Override
    protected void performFiltering(CharSequence typedText, int keyCode) {
        if (mLoadingIndicator != null) {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        mHandler.removeMessages(HANDLER_KEY_USER_TYPED);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(HANDLER_KEY_USER_TYPED, typedText), this.mAutoCompleteDelay);
    }

    @Override
    public void onFilterComplete(int count) {
        if (mLoadingIndicator != null) {
            mLoadingIndicator.setVisibility(View.GONE);
        }
        super.onFilterComplete(count);
    }
}
