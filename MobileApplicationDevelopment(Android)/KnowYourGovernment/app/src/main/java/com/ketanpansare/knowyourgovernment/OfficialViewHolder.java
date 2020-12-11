package com.ketanpansare.knowyourgovernment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ketan on 3/19/2020.
 */

public class OfficialViewHolder extends RecyclerView.ViewHolder {

    public TextView office;
    public TextView name; // party also displayed
    //private CardView cv;

    public OfficialViewHolder(View view){
        super(view);
        //cv = view.findViewById(R.id.cardView);
        office = view.findViewById(R.id.officeID);
        name = view.findViewById(R.id.nameID);

        //cv.setPreventCornerOverlap(false);
        //cv.setRadius(1f);
    }

}
