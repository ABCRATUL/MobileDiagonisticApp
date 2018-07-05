package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestActivities;

import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Constants;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper.Message;
import com.example.pronoymukherjee.hyperxchangediagnosticnew.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CameraRearTestActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceView mPreview;
    boolean isSafeToTakePicture = false;
    private String TAG_CLASS = CameraRearTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_rear_test);
        mPreview = findViewById(R.id.cameraSurface);
        mPreview.getHolder().addCallback(CameraRearTestActivity.this);
        mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        final Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                File hyperXchangeDir = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "HyperXchangeCamera");
                if (!hyperXchangeDir.exists()) {
                    hyperXchangeDir.mkdirs();
                }
                try {
                    if (bytes != null) {
                        File rearImage = new File(hyperXchangeDir, "Rear.jpg");
                        FileOutputStream fileOutputStream = new FileOutputStream(rearImage);
                        fileOutputStream.write(bytes);
                        completeActivity(true);
                    } else {
                        isSafeToTakePicture = true;
                    }
                } catch (Exception e) {
                    Message.logMessage(TAG_CLASS, e.toString());
                }
                isSafeToTakePicture = true;
            }
        };
        Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] bytes, final Camera camera) {
                try {
                    if (isSafeToTakePicture) {
                        try {
                            camera.takePicture(null, null, pictureCallback);
                        } catch (RuntimeException e) {
                        }
                        isSafeToTakePicture = false;
                        completeActivity(true);
                    } else {
                        Message.logMessage(TAG_CLASS, "Not able to take picture.");
                    }

                } catch (Exception e) {
                    Message.logMessage(TAG_CLASS, e.toString());
                }
            }
        };
        try {
            camera = Camera.open(0);    // For Back Camera
            camera.setDisplayOrientation(90);
            camera.setPreviewCallback(previewCallback);
            isSafeToTakePicture = true;
            Message.logMessage(TAG_CLASS, "Preview started");
            completeActivity(true);
        } catch (Exception e) {
            Message.logMessage(TAG_CLASS, e.toString());
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(mPreview.getHolder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = camera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width, selected.height);
        camera.setParameters(params);
        camera.startPreview();
        isSafeToTakePicture = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Message.logMessage(TAG_CLASS, "Surface Destroyed.");
    }

    private void completeActivity(final boolean status) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (status) {
                    setResult(RESULT_OK);
                } else
                    setResult(RESULT_CANCELED);
                finish();
            }
        }, Constants.TEST_TIMER);
    }
}
