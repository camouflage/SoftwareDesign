package com.example.myticket.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myticket.R;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.entities.User;

public class ModifyPasswordActivity extends Activity {
	private EditText _phone_edt, _pw_edt, _pw_confirm_edt;
	private Button _ok_btn;
	private DataBaseHelper dbHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_password);
		initView();
		initEvent();
	}

	// init variables
	private void initView() {
		dbHandler = DataBaseHelper.getInstance(this);
		_phone_edt = (EditText)findViewById(R.id.mp_phone_edt);
		_pw_edt = (EditText)findViewById(R.id.mp_password_edt);
		_pw_confirm_edt = (EditText)findViewById(R.id.mp_confirm_password_edt);
		_ok_btn = (Button)findViewById(R.id.mp_ok_btn);
	}

	// set listeners
	private void initEvent() {
		_ok_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				modifyFunction();
			}
		});
	}

	// Signup
	private void modifyFunction() {
		String phone = _phone_edt.getText().toString();
		String pw1 = _pw_edt.getText().toString();
		String pw2 = _pw_confirm_edt.getText().toString();

		// Blank info
		if (phone.equals("") || pw1.equals("") || pw2.equals("")) {
			Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
			return;
		}

		// Two passwords are different
		if (!pw1.equals(pw2)) {
			Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
			_pw_edt.setText("");
			_pw_confirm_edt.setText("");
			return;
		}

		User temp = dbHandler.queryUser(phone);
		// Used phone number
		if (temp == null) {
			Toast.makeText(this, "用该手机号注册的用户不存在", Toast.LENGTH_SHORT).show();
			_phone_edt.setText("");
			_pw_edt.setText("");
			_pw_confirm_edt.setText("");
			return;
		}

		// DB opeartions
		temp.setPassword(pw1);
		dbHandler.updateUser(temp);
		Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
		
		finish();
	}
}
