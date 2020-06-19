package com.example.ngakakajayuk.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ngakakajayuk.Data.JSON.DataAnswer;
import com.example.ngakakajayuk.R;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyviewHolder>{
    Context context;
    List<DataAnswer> myList;
    OnItemSelected onItemSelected;
    private Random random = new Random();
    private int randomz = random.nextInt(100);


    public AnswerAdapter(Context context, List<DataAnswer> myList,
                           OnItemSelected onItemSelected) {
        this.context = context;
        this.myList = myList;
        this.onItemSelected = onItemSelected;
    }
    public void setMovieList(List<DataAnswer> myList) {
        this.myList = myList;
        Collections.shuffle(myList);
        notifyDataSetChanged();
    }

    @Override
    public AnswerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.form_answer,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnswerAdapter.MyviewHolder holder, int position) {

        final Animation animSelected = AnimationUtils.loadAnimation(context, R.anim.anim_selected_card);
        Dialog dialogCard = new Dialog(context);
        dialogCard.setContentView(R.layout.dialog_answer_card);

        DataAnswer dataAnswer = myList.get(position);

        holder.tvAnswer.setText(dataAnswer.getJawaban());

        holder.itemView.setOnClickListener(v -> {
            onItemSelected.onSelected(dataAnswer);
//            v.startAnimation(animSelected);
//            dialogCard.show();
        });
    }

    @Override
    public int getItemCount() {
        if(myList != null){
            return 7;
        }
        return 0;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnItemSelected {
        void onSelected(DataAnswer dataTestSDG);
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvAnswer;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvAnswer = (TextView)itemView.findViewById(R.id.content_answer);
        }
    }
}
