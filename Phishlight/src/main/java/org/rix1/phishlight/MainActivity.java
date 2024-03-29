package org.rix1.phishlight;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends Activity {


    private Button toggle;
    private ImageView flashlightIcon, light;
    private boolean isFlashlightOn = false;
    private Camera camera;
    private Context context = this;
    private PhishingHelper pHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlightIcon = (ImageView) findViewById(R.id.ic_flashlight);
        light = (ImageView) findViewById(R.id.ic_light);
        toggle = (Button)findViewById(R.id.btn_toggle);
        toggle.setText("ON");


        camera = Camera.open();
        final Camera.Parameters p = camera.getParameters();


        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("APP: ", "Button clicked");

                callHome();


                if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    Toast.makeText(context, "No camera found ", Toast.LENGTH_LONG).show();
                }
                if (isFlashlightOn) {
                    Log.d("APP: ", "Flashlight is off");
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(p);
                    toggle.setText("ON");
                    light.setVisibility(View.INVISIBLE);
                    isFlashlightOn = false;
                } else {
                    Log.d("APP: ", "Flashlight is ON!");
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    toggle.setText("OFF");
                    light.setVisibility(View.VISIBLE);
                    isFlashlightOn = true;
                }
            }
        });
    }

    public void callHome(){
        String data = "Hi, heres my address: " + getAddress();
        Toast.makeText(context, data, Toast.LENGTH_LONG);

        /* This code is currently not working...
        PhishingHelper phishingHelper = new PhishingHelper(context);
        phishingHelper.insert(getAddress());
        String extract = phishingHelper.getIP(phishingHelper.getAll());
        Toast.makeText(context, extract, Toast.LENGTH_LONG).show();
        */
    }


    public String getAddress(){
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                            Log.d("APP: ", "***** IP="+ ip);
                            return ip;
                        }
                    }
                }
            } catch (SocketException ex) {
                Log.d("APP: ", ex.toString());
            }
            return null;
        }

    public void databaseTest(){
        pHelper = new PhishingHelper(this);
        String insert = "129.04.329294";
        pHelper.insert(insert);
        Cursor c = pHelper.getAll();
        String extract = pHelper.getIP(c);
        Toast.makeText(this, extract, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void onStop(){
        super.onStop();
        if(camera != null){
            camera.release();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    /*    int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item); */
        return true;
    }
}
	