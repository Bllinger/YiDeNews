package com.wei.news.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wei.news.R;

/**
 * 作者：赵若位
 * 时间：2018/8/6 22:58
 * 邮箱：1070138445@qq.com
 * 功能：
 */
public class AboutFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View layout = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.dialog_about, null);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (dialog.isShowing())
                {
                    dialog.dismiss();
                }
            }
        });
        return dialog;
    }
}
