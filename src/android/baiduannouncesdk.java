package com.heytz.baiduannouncesdk;


import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static java.lang.Thread.sleep;

/**
 * This class starts transmit to activation
 */
public class baiduannouncesdk extends CordovaPlugin {

    private static String TAG = "=====baiduannouncesdk.class====";
    private CallbackContext resultCallbackContext;
    private Context context;
    private String result = "";
    private SpeechSynthesizer mSpeechSynthesizer = null;
    protected String appId = "11194650";

    protected String appKey = "vtPSfelas3ycuEtW3uGmGl1B";

    protected String secretKey = "34f378211d11588f85576618ab22efd3";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = cordova.getActivity().getApplicationContext();

    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        resultCallbackContext = callbackContext;
        if (action.equals("start")) {
            try {


            } catch (Exception e) {
            }


            return true;
        }
        if (action.equals("stop")) {
            try {
                mSpeechSynthesizer.speak("我是小夏");
//                ArrayList<VoiceUIVariable> listVariables = new ArrayList<VoiceUIVariable>();
//                VoiceUIVariable variable = new VoiceUIVariable(ScenarioDefinitions.TAG_ACCOST, VoiceUIVariable.VariableType.STRING);
//                variable.setStringValue(ScenarioDefinitions.ACC_ACCOST);
//                listVariables.add(variable);
//                sleep(8000);
//                VoiceUIManagerUtil.updateAppInfo(vm, listVariables, true);
//                sleep(1000);
            } catch (Exception e) {

            }
//            wp.send(SpeechConstant.WAKEUP_STOP, null, null, 0, 0);
            return true;
        }
        if (action.equals("announce")) {
            try {
                String word = args.getString(0);
                if (word != null && word != "") {
                    mSpeechSynthesizer.speak(word);
                }
            } catch (Exception e) {

            }
            return true;
        }
        if (action.equals("init")) {
            try {
                if (mSpeechSynthesizer == null) {
                    mSpeechSynthesizer = SpeechSynthesizer.getInstance();
                    mSpeechSynthesizer.setContext(context);
                    int result = mSpeechSynthesizer.setAppId(appId);
                    result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
                    mSpeechSynthesizer.auth(TtsMode.ONLINE);
                    mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
                    // 设置合成的音量，0-9 ，默认 5
                    mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9");
                    // 设置合成的语速，0-9 ，默认 5
                    mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "4");
                    // 设置合成的语调，0-9 ，默认 5
                    mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");

                    mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
                    // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
                    // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
                    // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
                    // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
                    // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

                    mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL);
                    mSpeechSynthesizer.initTts(TtsMode.MIX);
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
//            String json = "{\"accept-audio-data\":false,\"disable-punctuation\":false,\"accept-audio-volume\":true,\"pid\":15361}";
//            asr.send(SpeechConstant.ASR_START, json, null, 0, 0);
            return true;
        }
        if (action.equals("voiceSpeech")) {
            try {
            } catch (Exception e) {

            }
//            String json = "{\"accept-audio-data\":false,\"disable-punctuation\":false,\"accept-audio-volume\":true,\"pid\":15361}";
//            asr.send(SpeechConstant.ASR_START, json, null, 0, 0);
            return true;
        }
//        if (action.equals("stopPlay")) {
//            asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
//            return true;
//        }
        return false;
    }



}
