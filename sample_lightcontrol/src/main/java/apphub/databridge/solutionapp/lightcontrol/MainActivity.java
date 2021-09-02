package apphub.databridge.solutionapp.lightcontrol;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.advantech.apphub.databridge.client.AppHubDataBridgeServiceConnection;
import com.advantech.apphub.databridge.constants.DataBridgeError;
import com.advantech.apphub.databridge.constants.ReportData;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Plugin-LightControl";
    public final static int UPDATE_BLUB_COLOR = 0;

    private ImageButton bulb0, bulb1, bulb2, bulb3, bulb4, bulb5;
    private Switch bulb0_switch, bulb1_switch, bulb2_switch, bulb3_switch, bulb4_switch, bulb5_switch;
    private CheckBox active_report_checkbox;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String[] blulb_status = {"off", "off", "off", "off", "off", "off"};
    private boolean active_report;
    private int[] blulb_color_id = {R.drawable.black_bulb, R.drawable.yellow_bulb, R.drawable.green_bulb, R.drawable.red_bulb, R.drawable.blue_bulb};

    MyAppHubDataBridgeClient myAppHubDataBridgeClient;
    private String pkgName = "apphub.databridge.solutionapp.lightcontrol";

    public Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_BLUB_COLOR:
                    updateBulbColor();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        myAppHubDataBridgeClient = new MyAppHubDataBridgeClient(TAG, getApplicationContext(), handler);
        myAppHubDataBridgeClient.bindDataBridgeService(dataBridgeServiceConnection);

        bulb0_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    if (b) {
                        bulb0.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb0_status", "on");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led0_status", "on");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bulb0.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb0_status", "off");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led0_status", "off");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (b) {
                        bulb0.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb0_status", "on");
                        editor.apply();
                    } else {
                        bulb0.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb0_status", "off");
                        editor.apply();
                    }
                }
            }
        });

        bulb1_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    if (b) {
                        bulb1.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb1_status", "on");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led1_status", "on");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bulb1.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb1_status", "off");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led1_status", "off");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (b) {
                        bulb1.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb1_status", "on");
                        editor.apply();
                    } else {
                        bulb1.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb1_status", "off");
                        editor.apply();
                    }
                }
            }
        });

        bulb2_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    if (b) {
                        bulb2.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb2_status", "on");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led2_status", "on");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bulb2.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb2_status", "off");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led2_status", "off");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (b) {
                        bulb2.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb2_status", "on");
                        editor.apply();
                    } else {
                        bulb2.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb2_status", "off");
                        editor.apply();
                    }
                }
            }
        });

        bulb3_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    if (b) {
                        bulb3.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb3_status", "on");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led3_status", "on");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bulb3.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb3_status", "off");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led3_status", "off");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (b) {
                        bulb3.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb3_status", "on");
                        editor.apply();
                    } else {
                        bulb3.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb3_status", "off");
                        editor.apply();
                    }
                }
            }
        });

        bulb4_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    if (b) {
                        bulb4.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb4_status", "on");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led4_status", "on");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bulb4.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb4_status", "off");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led4_status", "off");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (b) {
                        bulb4.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb4_status", "on");
                        editor.apply();
                    } else {
                        bulb4.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb4_status", "off");
                        editor.apply();
                    }
                }
            }
        });

        bulb5_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    if (b) {
                        bulb5.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb5_status", "on");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led5_status", "on");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        bulb5.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb5_status", "off");
                        editor.apply();
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("led5_status", "off");
                            jsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                            reportData(jsonObj.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (b) {
                        bulb5.setImageDrawable(getResources().getDrawable(blulb_color_id[sharedPreferences.getInt("bulb_color", 1)]));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb5_status", "on");
                        editor.apply();
                    } else {
                        bulb5.setImageDrawable(getResources().getDrawable(R.drawable.black_bulb));
                        editor = sharedPreferences.edit();
                        editor.putString("bulb5_status", "off");
                        editor.apply();
                    }
                }
            }
        });

        active_report_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (active_report_checkbox.isChecked()) {
                    editor = sharedPreferences.edit();
                    editor.putBoolean("active_report", true);
                    editor.apply();
                } else {
                    editor = sharedPreferences.edit();
                    editor.putBoolean("active_report", false);
                    editor.apply();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myAppHubDataBridgeClient != null) {
            myAppHubDataBridgeClient.unregisterDataBridgeClient(pkgName);
            myAppHubDataBridgeClient.unbindDataBridgeService();
        }
    }

    private void initView() {
        bulb0 = findViewById(R.id.bulb0);
        bulb1 = findViewById(R.id.bulb1);
        bulb2 = findViewById(R.id.bulb2);
        bulb3 = findViewById(R.id.bulb3);
        bulb4 = findViewById(R.id.bulb4);
        bulb5 = findViewById(R.id.bulb5);
        bulb0_switch = findViewById(R.id.bulb_switch0);
        bulb1_switch = findViewById(R.id.bulb_switch1);
        bulb2_switch = findViewById(R.id.bulb_switch2);
        bulb3_switch = findViewById(R.id.bulb_switch3);
        bulb4_switch = findViewById(R.id.bulb_switch4);
        bulb5_switch = findViewById(R.id.bulb_switch5);

        active_report_checkbox = findViewById(R.id.active_report);
        sharedPreferences = getSharedPreferences("private_data", MODE_PRIVATE);
        int bulb_color = sharedPreferences.getInt("bulb_color", 1);

        for (int i = 0; i < 6; i++) {
            blulb_status[i] = sharedPreferences.getString("bulb" + i + "_status", "off");
        }

        if (blulb_status[0].equals("on")) {
            bulb0.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
            bulb0_switch.setChecked(true);
        }
        if (blulb_status[1].equals("on")) {
            bulb1.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
            bulb1_switch.setChecked(true);
        }
        if (blulb_status[2].equals("on")) {
            bulb2.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
            bulb2_switch.setChecked(true);
        }
        if (blulb_status[3].equals("on")) {
            bulb3.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
            bulb3_switch.setChecked(true);
        }
        if (blulb_status[4].equals("on")) {
            bulb4.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
            bulb4_switch.setChecked(true);
        }
        if (blulb_status[5].equals("on")) {
            bulb5.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
            bulb5_switch.setChecked(true);
        }

        active_report = sharedPreferences.getBoolean("active_report", false);
        if (active_report) {
            active_report_checkbox.setChecked(true);
        }
    }

    private void updateBulbColor() {
        int bulb_color = sharedPreferences.getInt("bulb_color", 1);
        for (int i = 0; i < 6; i++) {
            blulb_status[i] = sharedPreferences.getString("bulb" + i + "_status", "off");
        }
        if (blulb_status[0].equals("on")) {
            bulb0.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
        }
        if (blulb_status[1].equals("on")) {
            bulb1.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
        }
        if (blulb_status[2].equals("on")) {
            bulb2.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
        }
        if (blulb_status[3].equals("on")) {
            bulb3.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
        }
        if (blulb_status[4].equals("on")) {
            bulb4.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
        }
        if (blulb_status[5].equals("on")) {
            bulb5.setImageDrawable(getResources().getDrawable(blulb_color_id[bulb_color]));
        }
    }

    private void reportData(final String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int ret = myAppHubDataBridgeClient.reportDataSync(new ReportData(pkgName, "report_data", data));
                if (ret == DataBridgeError.DATABRIDGE_ERR_NO_ERROR) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "report ok", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "report failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    AppHubDataBridgeServiceConnection dataBridgeServiceConnection = new AppHubDataBridgeServiceConnection(TAG) {
        @Override
        public void on_service_connected() {
            Log.d(TAG, "on_service_connected() is called");
            myAppHubDataBridgeClient.registerDataBridgeClient(pkgName);
            int ret = myAppHubDataBridgeClient.toggleDebugLog(true);
            if (ret == DataBridgeError.DATABRIDGE_ERR_NO_ERROR) {
                Log.d(TAG, "toggleDebugLog succeed!");
            } else {
                Log.d(TAG, "toggleDebugLog failed: " + ret);
            }
        }

        @Override
        public void on_service_disconnected() {
            Log.d(TAG, "on_service_disconnected() is called");
            //You can try to to re-connect to service
            Thread threadRetryServiceConnection = new Thread(new Runnable() {
                @Override
                public void run() {
                    int ret;
                    do {
                        try {
                            Log.d(TAG, "Retry to bind service after 10 sec");
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (!myAppHubDataBridgeClient.isDataBridgeServiceConnected()) {
                            Log.d(TAG, "Service is not connected. Retry to bind service ...");
                            ret = myAppHubDataBridgeClient.bindDataBridgeService(dataBridgeServiceConnection);
                        } else {
                            Log.d(TAG, "Service is connected. Stop retry.");
                            break;
                        }

                    } while (ret != DataBridgeError.DATABRIDGE_ERR_NO_ERROR);
                }
            });
            threadRetryServiceConnection.start();
        }
    };

}
