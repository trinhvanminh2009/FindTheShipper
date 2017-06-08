package com.minh.findtheshipper.utils;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.minh.findtheshipper.R;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by trinh on 6/7/2017.
 */

public abstract class PermissionUtils {


    public static void requestPermission(AppCompatActivity activity, int requestID, String permission, boolean finishActivity)
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)){
            PermissionUtils.RationaleDialog.newInstance(requestID,finishActivity)
                    .show(activity.getSupportFragmentManager(),"dialog");
        }
        else {
            ActivityCompat.requestPermissions(activity, new String[]{permission},requestID);
        }
    }
    public static boolean isPermissionGranted(String[] grantPermissions, int[] grantResults,
                                              String permission) {
        for (int i = 0; i < grantPermissions.length; i++) {
            if (permission.equals(grantPermissions[i])) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }
/**
 * This class using for Rationale Dialog
 * */
    public static class RationaleDialog extends DialogFragment{
        private static final String ARGUMENT_PERMISSION_REQUEST_CODE = "request";
        private static final String ARGUMENT_FINISH_ACTIVITY = "finish";
        private boolean finishActivity = false;

        public static RationaleDialog newInstance(int requestCode, boolean finishActivity)
        {
            Bundle bundle = new Bundle();
            bundle.putInt(ARGUMENT_PERMISSION_REQUEST_CODE,requestCode);
            bundle.putBoolean(ARGUMENT_FINISH_ACTIVITY,finishActivity);
            RationaleDialog rationaleDialog = new RationaleDialog();
            rationaleDialog.setArguments(bundle);
            return rationaleDialog;
        }




        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle bundle = getArguments();
            final int requestCode = bundle.getInt(ARGUMENT_PERMISSION_REQUEST_CODE);
            finishActivity = bundle.getBoolean(ARGUMENT_FINISH_ACTIVITY);
            return new AlertDialog.Builder(getActivity()).setMessage(R.string.required_permission_location)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},requestCode);
                            //Don't finish the Activity while requesting permission
                            finishActivity = false;
                        }
                    }).setNegativeButton(R.string.cancel,null).create();
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            if(finishActivity)
            {
                TastyToast.makeText(getActivity(),getResources().getString(R.string.permission_required_toast)
                        ,TastyToast.LENGTH_LONG,TastyToast.INFO);
                getActivity().finish();
            }
        }
    }

    public static class PermissionDeniedDialog extends DialogFragment {

        private static final String ARGUMENT_FINISH_ACTIVITY = "finish";

        private boolean mFinishActivity = false;

        /**
         * Creates a new instance of this dialog and optionally finishes the calling Activity
         * when the 'Ok' button is clicked.
         */
        public static PermissionDeniedDialog newInstance(boolean finishActivity) {
            Bundle arguments = new Bundle();
            arguments.putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity);

            PermissionDeniedDialog dialog = new PermissionDeniedDialog();
            dialog.setArguments(arguments);
            return dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            mFinishActivity = getArguments().getBoolean(ARGUMENT_FINISH_ACTIVITY);

            return new android.app.AlertDialog.Builder(getActivity())
                    .setMessage("Permission denied")
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            if (mFinishActivity) {
                Toast.makeText(getActivity(), R.string.permission_required_toast,
                        Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }

}
