package adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyteaching.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import model.QuestionsComplet;
import model.Solutions;
import model.Tests;
import model.TestsComplet;

public class QuestionsCompletAdapter extends RecyclerView.Adapter<QuestionsCompletAdapter.QuestionsCompletViewHolder> {
    private List<QuestionsComplet> questionsComplets;
    private TestsComplet testsComplet;
    private LayoutInflater inflater;
    public List<Integer> listQuestionsID = new ArrayList<>();
    private List<Boolean> listSelected = new ArrayList<>();

    public QuestionsCompletAdapter(Context context, List<QuestionsComplet> questionsComplets, TestsComplet testsComplet){
        this.questionsComplets = new ArrayList<>(questionsComplets);
        this.testsComplet = testsComplet;
        inflater = LayoutInflater.from(context);
    }

    public class QuestionsCompletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView questionsDetailTV;
        final TextView questionTypeTV;
        final TextView solutionTV;
        final ImageButton actionButton;
        final ConstraintLayout constraintLayout;
        final QuestionsCompletAdapter adapter;

        public QuestionsCompletViewHolder(@NonNull View itemView, QuestionsCompletAdapter questionsCompletAdapter) {
            super(itemView);
            this.questionsDetailTV = itemView.findViewById(R.id.questionDetail);
            this.questionTypeTV = itemView.findViewById(R.id.questionType);
            this.actionButton = itemView.findViewById(R.id.actionButton);
            this.solutionTV = itemView.findViewById(R.id.solution);
            this.constraintLayout = itemView.findViewById(R.id.question_layout);
            this.adapter = questionsCompletAdapter;
            actionButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            QuestionsComplet currentQuestion = questionsComplets.get(position);
            /* Toast.makeText(inflater.getContext(),
                    "Clicked " + currentQuestion.getQuestion().getId() + " Selected : " + listSelected.get(position),
                    Toast.LENGTH_SHORT).show(); */
            boolean currentSelectedValue = listSelected.get(position);
            listSelected.set(position, !currentSelectedValue);
            if (!listSelected.get(position)){
                // ITEM HAS BEEN REMOVED
                constraintLayout.getBackground().setColorFilter(Color.parseColor("#C4EAE9E9"), PorterDuff.Mode.SRC_IN);
                actionButton.setImageResource(R.drawable.ic_baseline_add_24);
                actionButton.getBackground().setColorFilter(Color.parseColor("#49C365"), PorterDuff.Mode.SRC_IN);
                int posToDelete = listQuestionsID.indexOf(currentQuestion.getQuestion().getId());
                Log.i("DELETE ID TEST", "ID : " + posToDelete);
                if (listQuestionsID.contains(currentQuestion.getQuestion().getId()))
                listQuestionsID.remove((Integer) currentQuestion.getQuestion().getId());
            } else {
                // ITEM HAS BEEN ADDED
                constraintLayout.getBackground().setColorFilter(Color.parseColor("#49C365"), PorterDuff.Mode.SRC_IN);
                actionButton.setImageResource(R.drawable.minus_24);
                actionButton.getBackground().setColorFilter(Color.parseColor("#C4EAE9E9"), PorterDuff.Mode.SRC_IN);
                listQuestionsID.add(currentQuestion.getQuestion().getId());
            }
            Log.i("LIST ID", listQuestionsID.toString());
            //Toast.makeText(inflater.getContext(), "Clicked " , Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public QuestionsCompletAdapter.QuestionsCompletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.questions_complet_item, parent, false);
        return new QuestionsCompletViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsCompletAdapter.QuestionsCompletViewHolder holder, int position) {
        listSelected.add(false);
        QuestionsComplet currentQuestion = questionsComplets.get(position);
        String textDetail = currentQuestion.getQuestion().getName() + " / " + currentQuestion.getCours().getNom_cours() + " / " +
                currentQuestion.getProfesseur().getName();
        String type = currentQuestion.getSolutions().size() > 1 ? "QCM" : "Open";
        String solutionText = "";
        for (Solutions solution: currentQuestion.getSolutions()) {
            if (solution.getAnswer() == 1){
                solutionText += solution.getText();
            }
        }
        holder.questionsDetailTV.setText("Q : " + textDetail);
        holder.questionTypeTV.setText("Type : " + type);
        holder.solutionTV.setText(solutionText);
        for (QuestionsComplet questionsComplet: testsComplet.getQuestionsComplets()) {
            // holder.constraintLayout.getBackground().setColorFilter(Color.parseColor("#626265"), PorterDuff.Mode.SRC_IN);
            if (questionsComplet.getQuestion().getId() == currentQuestion.getQuestion().getId()) {
                holder.constraintLayout.getBackground().setColorFilter(Color.parseColor("#49C365"), PorterDuff.Mode.SRC_IN);
                holder.actionButton.setImageResource(R.drawable.minus_24);
                holder.actionButton.getBackground().setColorFilter(Color.parseColor("#C4EAE9E9"), PorterDuff.Mode.SRC_IN);
                listQuestionsID.add(currentQuestion.getQuestion().getId());
                listSelected.set(position, true);
            }/*
            else {
                holder.constraintLayout.getBackground().setColorFilter(Color.parseColor("#626265"), PorterDuff.Mode.SRC_IN);
            }
            */
        }
    }

    @Override
    public int getItemCount() {
        return questionsComplets.size();
    }
}
