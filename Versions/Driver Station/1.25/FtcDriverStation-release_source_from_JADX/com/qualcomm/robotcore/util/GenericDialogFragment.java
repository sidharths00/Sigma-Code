package com.qualcomm.robotcore.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class GenericDialogFragment extends DialogFragment {

    /* renamed from: com.qualcomm.robotcore.util.GenericDialogFragment.1 */
    class C01201 implements OnClickListener {
        final /* synthetic */ GenericDialogFragment f535a;

        C01201(GenericDialogFragment genericDialogFragment) {
            this.f535a = genericDialogFragment;
        }

        public void onClick(DialogInterface dialog, int id) {
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String str = "App error condition!";
        CharSequence string = getArguments().getString("dialogMsg", "App error condition!");
        Builder builder = new Builder(getActivity());
        builder.setMessage(string).setPositiveButton("OK", new C01201(this));
        return builder.create();
    }
}
