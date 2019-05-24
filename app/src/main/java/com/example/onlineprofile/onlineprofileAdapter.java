package com.example.onlineprofile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class onlineprofileAdapter extends ArrayAdapter<profilecreation> {

    private Context Context ;
    private ArrayList<profilecreation>itemList;
    String id;
    String name;


    public  static final  String TAG = "OnlineProfile";
    AppDatabase mydb;

    public  onlineprofileAdapter(Context context, ArrayList<profilecreation> itemList, String id, String name){

        super(context,R.layout.profilecreation,itemList);
        this.Context = context;
        this.itemList = itemList;
        this.id = id;
        this.name = name;

    }

    customButtonListener customListner;
    public interface customButtonListener {
        public void onButtonClickListner(int position);
        public void onButtonClickListner2(int position);
    }
    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }


    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        final LayoutInflater  inflater = (LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.profilecreation, parent, false);
        final TextView labelView = rowView.findViewById(R.id.textbox1);
      //  final TextView labelView1=  rowView.findViewById(R.id.Adddetails);
        final TextView labelView2 = rowView.findViewById(R.id.textbox3);
        final ImageButton Edit = rowView.findViewById(R.id.Edit);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase mydb = new AppDatabase(parent.getContext());
                mydb.getEducationdata(id);
                Intent intent = new Intent(parent.getContext(),Editeducation.class);
                intent.putExtra("usernames",name);
                intent.putExtra("id",id);
                intent.putExtra("Ins",itemList.get(position).getText());


                parent.getContext().startActivity(intent);


            }
        });

        labelView.setText(itemList.get(position).getText());
//        labelView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(parent.getContext(),Educationdetails.class);
//                intent.putExtra("usernames",name);
//                intent.putExtra("id",id);
//
//                parent.getContext().startActivity(intent);
//
//            }
//        });



        labelView2.setText(itemList.get(position).getText3());

        Edit.setTag(itemList.get(position).getIButton1Id());

        return rowView;
    }
}
