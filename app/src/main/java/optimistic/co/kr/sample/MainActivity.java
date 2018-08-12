package optimistic.co.kr.sample;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import utils.LogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_contact_intent).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int resId = view.getId();

        switch (resId) {
            case R.id.bt_contact_intent:
                selectContact();
                break;
            default :
                break;

        }
    }


    // ---------------------- 인텐트 액션을 이용한 선택된 연락처 뽑아 놓은 예제. --------------------------
    // https://developer.android.com/guide/components/intents-common?hl=ko
    
    static final int REQUEST_SELECT_CONTACT = 1;

    public void selectContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
//        intent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d("requestCode : "+requestCode+" resultCode : "+resultCode);
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();

            LogUtil.d("contactUri : "+contactUri);
            // Do something with the selected contact at contactUri
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            LogUtil.d("projection length : "+projection.length);
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {

                // Do something with the phone number
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                LogUtil.d("number : " + number);
            }
        }
    }
}
