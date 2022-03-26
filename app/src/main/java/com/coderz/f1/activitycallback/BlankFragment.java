package com.coderz.f1.activitycallback;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    ImageView imageviewImage;
    Button buttonBrowse;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View content = inflater.inflate(R.layout.fragment_blank, container,false);

        buttonBrowse = content.findViewById(R.id.button_browse);
        imageviewImage = content.findViewById(R.id.imageview_image);

        buttonBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick(view);
            }
        });

        return content;
    }

    private void buttonClick(View view){
        final Intent browse = new Intent(Intent.ACTION_GET_CONTENT);
        browse.setType("image/*");
        browseResultLauncher.launch(browse);
    }

    ActivityResultLauncher<Intent> browseResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() != Activity.RESULT_OK) return;
                    Intent data = result.getData();
                    if (data.getData() == null) return;

                    Uri fileUri = data.getData();
                    imageviewImage.setImageURI(fileUri);
                }
            }
    );
}