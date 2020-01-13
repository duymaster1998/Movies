package edu.xda.abc.dialogs;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertDialog {
    private Context context;

    public AlertDialog(Context context) {
        this.context = context;
    }

    public void alertDialogSuccess(String title,String message) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .show();
    }

    public void alertDialogError(String title, String message) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .show();
    }

    public void alertDialogLoading() {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading ...");
        pDialog.setCancelable(true);
        pDialog.show();
    }

//    public void showCustomDialog(String mTitle, String mDescription) {
//        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(
//                R.layout.fragment_ebooks_top_chat,
//                null
//        );
//
//        TextView title = view.findViewById(R.id.tv_title);
//        TextView description = view.findViewById(R.id.tv_description);
//        final Button buttonCancel = view.findViewById(R.id.btn_cancel);
//        final Button buttonAllow = view.findViewById(R.id.btn_allow);
//
//        title.setText(mTitle);
//        description.setText(mDescription);
//
//        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(context);
//        alert.setView(view);
//        alert.setCancelable(false);
//
//        android.support.v7.app.AlertDialog dialog = alert.create();
//        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//
//        buttonAllow.setOnClickListener(v -> {
//        });
//
//        buttonCancel.setOnClickListener(v -> {
//            if (dialog.isShowing()) {
//                dialog.dismiss();
//            }
//        });
//    }
}
