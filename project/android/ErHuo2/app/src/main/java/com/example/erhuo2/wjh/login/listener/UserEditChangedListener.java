package com.example.erhuo2.wjh.login.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erhuo2.R;

public class UserEditChangedListener implements TextWatcher {
    private CharSequence temp;//监听前的文本
    private int editStart;//光标开始位置
    private int editEnd;//光标结束位置
    private final int charMaxNum = 10;
    private ImageView iv_login_user_cancle;
    private EditText et_login_user;
    public UserEditChangedListener(ImageView iv, EditText et){
        this.iv_login_user_cancle = iv;
        this.et_login_user = et;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        temp = s;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(temp.length()==0){
            iv_login_user_cancle.setImageDrawable(null);
        }else{
            iv_login_user_cancle.setImageResource(R.drawable.cancel);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

        /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
        editStart = et_login_user.getSelectionStart();
        editEnd = et_login_user.getSelectionEnd();

        if (temp.length() >= charMaxNum) {
            s.delete(editStart - 1, editEnd);
            int tempSelection = editStart;
            et_login_user.setText(s);
            et_login_user.setSelection(tempSelection);
        }
    }
}
