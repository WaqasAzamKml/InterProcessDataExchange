package com.approsoft.interprocessdataexchange;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String packageName = "com.approsoft.internalstorage";
    PackageManager packageManager;
    ApplicationInfo applicationInfo;
    FileInputStream fileInputStream;

    Button btnReadData;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Inter Process Data Exchange");

        btnReadData = (Button) findViewById(R.id.btnReadData);
        tvData = (TextView) findViewById(R.id.tvData);

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    packageManager = getPackageManager();
                    applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
                    String fileName = applicationInfo.dataDir+"/files/InternalStorage.txt";
                    fileInputStream = new FileInputStream(new File(fileName));
                    StringBuffer stringBuffer = new StringBuffer();
                    int read = -1;
                    while ((read = fileInputStream.read()) != -1) {
                        stringBuffer.append((char) read);
                    }
                    tvData.setText(stringBuffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                } finally{
                    try {
                        if(fileInputStream!=null) {
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
