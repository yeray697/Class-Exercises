package com.ncatz.yeray.downloaderfile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by usuario on 16/02/17.
 */

public class DownloadFragment extends Fragment {
    private Button btDownload;
    private static final int REQUEST_WRITE_EXTERNAL = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewroot = inflater.inflate(R.layout.fragment_download,container,false);
        btDownload = (Button) viewroot.findViewById(R.id.button);
        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });
        return viewroot;
    }

    private void download() {
        if (checkWritePermission()){
            onDownload();
        }
    }

    private boolean checkWritePermission() {
        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),permission);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
            return true;
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),permission)){
            Snackbar.make(getView(),
                    R.string.permission_write_external,
                    Snackbar.LENGTH_INDEFINITE)
            .setAction(android.R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestPermissions(new String[]{permission},REQUEST_WRITE_EXTERNAL);
                }
            }).show();
        } else {
            requestPermissions(new String[]{permission},REQUEST_WRITE_EXTERNAL);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_EXTERNAL){
            if (grantResults.length>0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //okay
                onDownload();
            }
        }
    }

    private void onDownload() {
        btDownload.setEnabled(false);
        Intent intent = new Intent(getActivity(),Downloader.class);
        intent.setData(Uri.parse("https://commonsware.com/Android/Android_3-6-CC.pdf"));
        getActivity().startService(intent);
    }
}
