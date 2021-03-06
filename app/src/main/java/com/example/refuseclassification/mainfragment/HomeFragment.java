package com.example.refuseclassification.mainfragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.EventManager;
import com.baidu.speech.asr.SpeechConstant;
import com.example.refuseclassification.ASRresponse;
import com.example.refuseclassification.CommonActivity;
import com.example.refuseclassification.DryActivity;
import com.example.refuseclassification.ErrorProneActivity;
import com.example.refuseclassification.ExerciseActivity;
import com.example.refuseclassification.HarmfulActivity;
import com.example.refuseclassification.KnowledgeDatabase;
import com.example.refuseclassification.R;
import com.example.refuseclassification.RecyclableActivity;
import com.example.refuseclassification.SearchActivity;
import com.example.refuseclassification.SpecialActivity;
import com.example.refuseclassification.TestActivity;
import com.example.refuseclassification.WetActivity;
import com.example.refuseclassification.setTitleCenter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements EventListener{

    private Toolbar toolbar;
    private ImageButton recyclable_button;
    private ImageButton harmful_button;
    private ImageButton wet_button;
    private ImageButton dry_button;
    private ImageButton test_button;
    private ImageButton exercise_button;
    private ImageButton errorProne_button;
    private ImageButton common_button;
    private ImageButton special_button;
    private EditText search;
    private ImageButton recording_button;
    private EventManager asr;//?????????????????????
    private String result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.home_toolbar);
        toolbar.setTitle("??????");
        new setTitleCenter().setTitleCenter(toolbar);// ?????????ToolBar
        new KnowledgeDatabase().setKnowledgeDatabase();// ??????????????????
        // ????????????????????????
        recyclable_button = (ImageButton) view.findViewById(R.id.recyclable_button);
        recyclable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecyclableActivity.class);
                startActivity(intent);
            }
        });
        harmful_button = (ImageButton) view.findViewById(R.id.harmful_button);
        harmful_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HarmfulActivity.class);
                startActivity(intent);
            }
        });
        wet_button = (ImageButton) view.findViewById(R.id.wet_button);
        wet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WetActivity.class);
                startActivity(intent);
            }
        });
        dry_button = (ImageButton) view.findViewById(R.id.dry_button);
        dry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DryActivity.class);
                startActivity(intent);
            }
        });
        test_button = (ImageButton) view.findViewById(R.id.test_button);
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
            }
        });
        exercise_button = (ImageButton) view.findViewById(R.id.exercise_button);
        exercise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExerciseActivity.class);
                startActivity(intent);
            }
        });
        errorProne_button = (ImageButton) view.findViewById(R.id.errorProne_button);
        errorProne_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ErrorProneActivity.class);
                startActivity(intent);
            }
        });
        common_button = (ImageButton) view.findViewById(R.id.common_button);
        common_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommonActivity.class);
                startActivity(intent);
            }
        });
        special_button = (ImageButton) view.findViewById(R.id.special_button);
        special_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SpecialActivity.class);
                startActivity(intent);
            }
        });
        search = (EditText) view.findViewById(R.id.searchHome);
        search.setFocusable(false);//????????????
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // ???????????????
        initPermission();
        recording_button = (ImageButton) view.findViewById(R.id.recording_button);
        recording_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    // ?????? ??????????????????
                    asr.send(SpeechConstant.ASR_START, "{}", null, 0, 0);
                } else if (action == MotionEvent.ACTION_UP) {
                    // ?????? ??????????????????
                    asr.send(SpeechConstant.ASR_STOP, "{}", null, 0, 0);
                }
                return false;
            }
        });

        //?????????EventManager??????
        asr = EventManagerFactory.create(getContext(), "asr");
        //??????????????????????????????
        asr.registerListener(this); // EventListener ??? onEvent??????

        return view;
    }

    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), perm)) {
                toApplyList.add(perm);
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), toApplyList.toArray(tmpList), 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // ?????????android 6.0???????????????????????????????????????????????????
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // ?????????????????????????????????
            if (params == null || params.isEmpty()) {
                return;
            }
            if (params.contains("\"final_result\"")) {
                // ??????????????????????????????
                Gson gson = new Gson();
                ASRresponse asRresponse = gson.fromJson(params, ASRresponse.class);//?????????????????????bean
                if(asRresponse.getBest_result().contains("???")){
                    // ????????????  ???????????????????????????
                    // ??????????????????????????????trim??????????????????????????????
                    setResult(asRresponse.getBest_result().replace('???',' ').trim());
                }else {// ?????????
                    setResult(asRresponse.getBest_result().trim());
                }
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                if (result.contains("???")) {
                    setResult(result.replaceAll("???", ""));
                }
                intent.putExtra("record", result);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //??????????????????
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        //?????????????????????
        // ?????????registerListener?????????????????????????????????????????????
        asr.unregisterListener(this);
    }

    public void setResult(String result) {
        this.result = result;
    }
}
