package com.example.multinotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder>
{
    private List<Note> noteList;
    private MainActivity mainActivity;

    public NoteAdapter(List<Note> noteList, MainActivity mainActivity)
    {
        this.noteList = noteList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType)
    {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_note, parent, false);

        viewItem.setOnClickListener(mainActivity);
        viewItem.setOnLongClickListener(mainActivity);
        return new NoteViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int pos)
    {
        Note n = noteList.get(pos);

        noteViewHolder.noteTitle.setText(n.getTitle());
        noteViewHolder.noteDesc.setText(trimText(n.getDesc()));
        noteViewHolder.noteDate.setText(dateToString(n.getDate()));
    }

    @Override
    public int getItemCount()
    {
        return noteList.size();
    }

    private String dateToString(Date date)
    {
            if (date != null)
            {
                SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();
                sdf.applyPattern("EEE MMM dd, hh:mm aaa");
                return sdf.format(date);
            }
            return null;
        }

  private String dateToString(LocalDateTime localDateTime)
    {
        if(localDateTime!=null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd, hh:mm aaa", Locale.US);
            return sdf.format(localDateTime);
        }
        return null;
    }

    private String trimText(String noteText)
    {
        if(noteText!=null)
        {
            if(noteText.length()>80)
            {
                return noteText.substring(0,80).concat("...");
            }
            else
                {
                return noteText;
            }
        }
        else
           {
            return "";
        }
    }
}
