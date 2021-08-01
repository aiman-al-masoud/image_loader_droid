package com.luxlunaris.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class ImportImageFragment extends Fragment {


    final int IMPORT_CODE = 1;

    Button importButton;

    public ImportImageFragment() {
        // Required empty public constructor
    }


    public static ImportImageFragment newInstance() {
        ImportImageFragment fragment = new ImportImageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_import_image, container, false);

        importButton = (Button) view.findViewById(R.id.importImgButton);

        importButton.setOnClickListener(new HandleImport());

        return view;
    }



    /**
     * Calls the SAF to let the user pick a file from \
     * which to import pages.
     */
    class HandleImport implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, IMPORT_CODE);
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //halt if result code is not "ok"
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        //halt if "data" is null
        if(data==null){
            return;
        }


        switch (requestCode){

            case IMPORT_CODE:
                Uri uri = data.getData();
                File file = getFileFromUri(uri);

                String html = "<img src=  \'"+file.getPath()+"\' />";


                Intent i = new Intent(this.getContext(), ReaderActivity.class);
                i.putExtra("HTML", html);
                startActivity(i);

                break;

        }

    }







    /**
     * Given the uri of an external file, make a copy of it
     * in app-internal storage and return it.
     * @param contentUri
     * @return
             */
    private File getFileFromUri(Uri contentUri) {
        //Use content Resolver to get the input stream that it holds the data and copy that in a temp file of your app file directory for your references
        File selectedFile = new File(getActivity().getFilesDir(), "import.zip"); //your app file dir or cache dir you can use

        try {

            InputStream in = getActivity().getContentResolver().openInputStream(contentUri);
            OutputStream out = new FileOutputStream(selectedFile);

            byte[] buf = new byte[1024];
            int len;

            if (in != null) {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectedFile;
    }










}