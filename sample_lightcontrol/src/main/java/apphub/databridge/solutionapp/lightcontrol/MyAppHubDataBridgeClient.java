package apphub.databridge.solutionapp.lightcontrol;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.advantech.apphub.databridge.client.AppHubDataBridgeClient;
import com.advantech.apphub.databridge.constants.DataBridgeError;
import com.advantech.apphub.databridge.constants.ParameterData;
import com.advantech.apphub.databridge.constants.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * ClassName:   MyAppHubDataBridgeClient
 * Description: Extends from AppHubDataBridgeClient class, it implements setValue and getValue methods,
 * *           which are used for web applications to set and obtain the status of light bulbs
 * CreateDate   2021/09/02
 * Author:  fengchao.dai@advantech.com.cn
 */
public class MyAppHubDataBridgeClient extends AppHubDataBridgeClient {
    private String tag;
    private Handler handler;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    MyAppHubDataBridgeClient(String tagStr, Context ctx, Handler handler) {
        super(tagStr, ctx);
        this.tag = tagStr;
        this.handler = handler;
        sharedPreferences = ctx.getSharedPreferences("private_data", Context.MODE_PRIVATE);
    }

    @Override
    public ResponseData setValue(ParameterData param) {
        Log.d(tag, "setData: " + param.toString());

        String appName = param.getPkgName();
        String funcId = param.getFunctionId();
        String otherParams = param.getOtherParams();

        ResponseData responseData = new ResponseData();
        responseData.setPkgName(appName);
        responseData.setFunctionId(funcId);
        switch (funcId) {
            case "set_led_color":
                Log.d(tag, "#set_led_color# -- " + otherParams);
                try {
                    int color = Integer.parseInt(otherParams);
                    if (color < 5 && color > 0) {
                        editor = sharedPreferences.edit();
                        editor.putInt("bulb_color", color);
                        editor.apply();
                        responseData.setResult(0);
                        handler.sendEmptyMessage(MainActivity.UPDATE_BLUB_COLOR);
                    } else {
                        responseData.setResult(1);
                        responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_PARAMETER_ERROR);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    responseData.setResult(1);
                    responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_PARAMETER_ERROR);
                }
                break;
            default:
                responseData.setResult(1);
                responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_UNKNOWN_ERROR);

                break;
        }
        return responseData;
    }

    @Override
    public ResponseData getValue(ParameterData param) {
        Log.d(tag, "getData: " + param.toString());

        String appName = param.getPkgName();
        String funcId = param.getFunctionId();

        ResponseData responseData = new ResponseData();
        responseData.setPkgName(appName);
        responseData.setFunctionId(funcId);
        JSONObject subJsonObj = new JSONObject();
        try {
            switch (funcId) {
                case "get_led_status":
                    Log.d(tag, "#get_led_status#");
                    responseData.setResult(0);
                    responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_NO_ERROR);
                    for (int i = 0; i < 6; i++) {
                        subJsonObj.put("led" + i + "_status", sharedPreferences.getString("bulb" + i + "_status", "off"));
                    }
                    subJsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                    responseData.setOtherDatas(subJsonObj.toString());
                    break;
                case "get_led_color":
                    Log.d(tag, "#get_led_color#");
                    responseData.setResult(0);
                    responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_NO_ERROR);
                    subJsonObj.put("led_color", sharedPreferences.getInt("bulb_color", 1));
                    responseData.setOtherDatas(subJsonObj.toString());
                    break;
                default:
                    responseData.setResult(1);
                    responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_UNKNOWN_FUNCID);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            responseData.setResult(1);
            responseData.setErrorCode(DataBridgeError.DATABRIDGE_ERR_UNKNOWN_ERROR);
        }
        return responseData;
    }
}
