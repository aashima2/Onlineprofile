package com.example.onlineprofile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class onlineprofileAdapter2 extends ArrayAdapter<profilecreation>
{
    private android.content.Context Context ;
    private ArrayList<profilecreation> itemList;
    String id;
    String name;

    public  static final  String TAG = "OnlineProfile";
    AppDatabase mydb;

    public  onlineprofileAdapter2(Context context, ArrayList<profilecreation> itemList, String id, String name){

        super(context,R.layout.profilecreation,itemList);
        this.Context = context;
        this.itemList = itemList;
        this.id = id;
        this.name = name;
    }

    onlineprofileAdapter.customButtonListener customListner;
    public interface customButtonListener {
        public void onButtonClickListner(int position);
        public void onButtonClickListner2(int position);
    }
    public void setCustomButtonListner(onlineprofileAdapter.customButtonListener listener) {
        this.customListner = listener;
    }


    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.profilecreation, parent, false);
        final TextView labelView = rowView.findViewById(R.id.textbox1);
//        final TextView labelView1=  rowView.findViewById(R.id.Adddetails);
        final TextView labelView2 = rowView.findViewById(R.id.textbox3);
        final ImageButton Edit = rowView.findViewById(R.id.Edit);

        labelView.setText(itemList.get(position).getText());

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(),editemployementdata.class);
                intent.putExtra("usernames",name);
                intent.putExtra("id",id);
                intent.putExtra("des",itemList.get(position).getText());
                parent.getContext().startActivity(intent);

            }
        });
//        labelView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(parent.getContext(),Employmentdetails.class);
//                intent.putExtra("usernames",name);
//                intent.putExtra("id",id);
//
//                parent.getContext().startActivity(intent);
//            }
//        });

        labelView2.setText(itemList.get(position).getText3());

        Edit.setTag(itemList.get(position).getIButton1Id());

        return rowView;
    }
}
