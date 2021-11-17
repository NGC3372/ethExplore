package com.rainbowguo.ethexplore.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.rainbowguo.ethexplore.R;
import com.rainbowguo.ethexplore.Utils.TextUtils;
import com.rainbowguo.ethexplore.beans.proxy_transactionsInfoBean;

import java.util.Objects;

public class mDialogFragment extends DialogFragment {
    private static final String TAG = "mDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_search,null);
        EditText editText = view.findViewById(R.id.editText);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        builder.setView(view).setPositiveButton("Search", (dialog, which) -> {
            String query = editText.getText().toString();
            if (query.length() == 42){
                AddressFragment fragment = new AddressFragment();
                Bundle bundle = new Bundle();
                bundle.putString("address",query);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.fragmentBox,fragment).addToBackStack(null).commit();
            }else if (query.length() == 66){
                proxy_transactionsInfoBean.ResultDTO bean = new proxy_transactionsInfoBean.ResultDTO();
                bean.setHash(query);
                TransactionInfoFragment fragment = new TransactionInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("data",query);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.fragmentBox,fragment).addToBackStack(null).commit();
                //startActivity(new Intent(MainActivity.this, TransactionInfoActivity.class).putExtra("data",query));
            }else if(TextUtils.isNumber(query)){
                blockFragment fragment = new blockFragment();
                Bundle bundle = new Bundle();
                bundle.putString("blockNumber",query);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .add(R.id.fragmentBox,fragment).addToBackStack(null).commit();
            }
        });
        return builder.create();
    }
}
