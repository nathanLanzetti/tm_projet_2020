package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyteaching.R;

import java.util.LinkedList;
import java.util.List;

import model.Cours;
import model.Professeurs;
import model.TestsComplet;

public class TestsCompletAdapter extends RecyclerView.Adapter<TestsCompletAdapter.TestsCompletViewHolder> {

    private final LinkedList<TestsComplet> testsComplets;
    private LayoutInflater layoutInflater;
    private final List<Professeurs> professeurs;
    private final List<Cours> cours;

    public TestsCompletAdapter(Context context, LinkedList<TestsComplet> testsComplets, List<Professeurs> professeurs, List<Cours> cours) {
        Log.wtf("LinkedList Size", testsComplets.size() + "");
        this.testsComplets = testsComplets;
        this.professeurs = professeurs;
        this.cours = cours;
        layoutInflater = LayoutInflater.from(context);
    }

    class TestsCompletViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final TextView tvCours;
        public final TextView tvAuthor;
        public final TextView tvMsg;
        final TestsCompletAdapter testsCompletAdapter;

        public TestsCompletViewHolder(@NonNull View itemView, TestsCompletAdapter testsCompletAdapter) {
            super(itemView);
            tvName = itemView.findViewById(R.id.testsCompletName);
            tvCours = itemView.findViewById(R.id.testsCompletCours);
            tvAuthor = itemView.findViewById(R.id.testsCompletAuthor);
            tvMsg = itemView.findViewById(R.id.testsCompletMsg);
            this.testsCompletAdapter = testsCompletAdapter;
        }
    }

    @NonNull
    @Override
    public TestsCompletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.tests_complet_item, parent, false);
        return new TestsCompletViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TestsCompletViewHolder holder, int position) {
        TestsComplet tests = testsComplets.get(position);
        Log.i("Linked List Items", getItemCount() + "");
        holder.tvName.setText("Name : " + tests.getTest().getName());
        holder.tvCours.setText("Subject : "+getCurrentAuthor(tests));
        holder.tvAuthor.setText("Created by "+getCurrentCours(tests));
        String msg = tests.getQuestionsComplets().size() == 0 ? "Start adding some Questions ! Click here !"
                : "This test contains "+tests.getQuestionsComplets().size() + " questions!";
        holder.tvMsg.setText(msg);
    }

    @Override
    public int getItemCount() {
        return testsComplets.size();
    }

    private String getCurrentAuthor(TestsComplet testsComplet){
        for (Professeurs professeur: professeurs) {
            if (professeur.getId() == testsComplet.getTest().getAuthor()){
                return professeur.getName();
            }
        }
        return null;
    }

    private String getCurrentCours(TestsComplet testsComplet){
        for (Cours coursSingle: cours) {
            if (coursSingle.getId() == testsComplet.getTest().getAuthor()){
                return coursSingle.getNom_cours();
            }
        }
        return null;
    }


}
