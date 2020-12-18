
package com.example.easyteaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import adapters.QuestionsCompletAdapter;
import model.QuestionsComplet;
import model.QuestionsTests;
import model.Solutions;
import model.TestsComplet;
import repository.QuestionsCompletRepository;
import repository.QuestionsTestsRepository;
import repository.TestsCompletRepository;

public class EditTestQuestionsActivity extends AppCompatActivity {

    private static final int STORAGE_CODE = 1000;
    private RecyclerView recyclerView;
    private QuestionsCompletAdapter questionsCompletAdapter;
    private Spinner spinner;
    private List<QuestionsComplet> questionsComplets;
    private TestsComplet testsComplet;
    private LifecycleOwner lifecycleOwner;
    private Context context;
    private List<QuestionsComplet> savedQuestions;
    private int testID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_test_questions);

        Bundle bundle = getIntent().getExtras();
        testID = bundle.getInt("testID");

        lifecycleOwner = this;
        context = this;

        EditTestQuestionsActivity that = this;

        recyclerView = findViewById(R.id.recyclerViewQuestionsComplet);

        /*
        initSpinnerWithRessources();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Selected " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */

        final TestsCompletRepository testsCompletRepository = new TestsCompletRepository();
        final QuestionsCompletRepository questionsCompletRepository = new QuestionsCompletRepository();

        testsCompletRepository.get(testID).observe(this, new Observer<TestsComplet>() {
            @Override
            public void onChanged(TestsComplet testsCompleChanged) {
                //Log.i("TEST GET ", testsComplet.getTest().getId() + "");
                testsComplet = testsCompleChanged;
                savedQuestions = new ArrayList<>();
                savedQuestions.addAll(testsComplet.getQuestionsComplets());

                // Trier en fonction du cours du TEST
                questionsCompletRepository.query().observe(lifecycleOwner, new Observer<List<QuestionsComplet>>() {
                    @Override
                    public void onChanged(List<QuestionsComplet> questionsComplets) {
                        questionsComplets = new ArrayList<>(questionsComplets);

                        filterList(questionsComplets);

                        questionsCompletAdapter = new QuestionsCompletAdapter(context, filterList(questionsComplets), testsCompleChanged);

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(questionsCompletAdapter);
                        questionsCompletAdapter.listQuestionsID.size();
                        Log.i("LIST QUESTIONS", "SIZE " + questionsCompletAdapter.listQuestionsID.size());
                    }
                });
            }
        });
    }

    private List<QuestionsComplet> filterList(List<QuestionsComplet> questionsComplets) {
        List<QuestionsComplet> filteredList = new ArrayList<>();
        for (QuestionsComplet questionsComplet: questionsComplets) {
            if (questionsComplet.getCours().getId() == testsComplet.getTest().getCours()){
                filteredList.add(questionsComplet);
            }
        }
        return filteredList;
    }

    private void initSpinnerWithRessources() {
        /*
        spinner =  findViewById(R.id.spinnerFilter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.questions_filter, android.R.layout.simple_dropdown_item_1line);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

         */

    }


    public void onClickSave(View view) {
        Log.i("SAVE ", questionsCompletAdapter.listQuestionsID.toString());
        /*
        List<Integer> idsToPost = new ArrayList<>();
        List<Integer> idsToDelete = new ArrayList<>();
        for (QuestionsComplet q: testsComplet.getQuestionsComplets()) {
            boolean in = false;
            for (Integer id: questionsCompletAdapter.listQuestionsID) {
                if (q.getQuestion().getId() == id){
                    Log.i("HERE ADD", id + "");
                    in = true;
                    break;
                }
                if (in) idsToPost.add(q.getQuestion().getId());
            }
        }
        for (Integer id: questionsCompletAdapter.listQuestionsID) {
            boolean in = false;
            for (QuestionsComplet q: testsComplet.getQuestionsComplets()) {
                if (q.getQuestion().getId() == id){
                    in = true;
                    break;
                }
            }
            if (in) idsToDelete.add(id);
        }
        //Log.i("IDS TO ADD", idsToPost.toString());
        Log.i("IDS TO ADD", idsToPost.toString());
        Log.i("IDS TO DELETE", idsToDelete.toString());
        */
        final LifecycleOwner lifecycleOwner = this;

        final QuestionsTestsRepository questionsTestsRepository = new QuestionsTestsRepository();
        questionsTestsRepository.deleteAll(testID);
        for (Integer id: questionsCompletAdapter.listQuestionsID) {
            questionsTestsRepository.post(new QuestionsTests(id, testID)).observe(this, new Observer<QuestionsTests>() {
                @Override
                public void onChanged(QuestionsTests questionsTests) {
                    savedQuestions.clear();

                    final QuestionsCompletRepository questionsCompletRepository = new QuestionsCompletRepository();
                    Log.wtf("LIST ID SIZE", questionsCompletAdapter.listQuestionsID.size() + "");
                        questionsCompletRepository.get(id).observe(lifecycleOwner, new Observer<QuestionsComplet>() {
                            @Override
                            public void onChanged(QuestionsComplet questionsComplet) {
                                savedQuestions.add(questionsComplet);
                            }
                        });
                }
            });

        }
    }

    public void onClickSavePDF(View view) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                // Request Permission
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, STORAGE_CODE);
            } else {
                // permission granted
                savedPDF();
            }
        } else {
            savedPDF();
        }
    }

    private void savedPDF(){
        // Create Doc
        Document doc = new Document();
        // PDF
        String fileName = "Correctif" + testsComplet.getTest().getName() + "_" + UUID.randomUUID();
        String filePath = Environment.getExternalStorageDirectory() + "/" + fileName + ".pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            // Open to Write
            doc.open();
            writeToPdf(doc);
            doc.close();
            Toast.makeText(this, "Saved as PDF", Toast.LENGTH_SHORT).show();

            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
            m.invoke(null);
            File file = new File(filePath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.createChooser(intent, "Open File");
            startActivity(intent);

            
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void writeToPdf(Document doc) {
        try {
            Paragraph title = new Paragraph("Correctif :" + testsComplet.getTest().getName());
            title.setAlignment(Element.ALIGN_CENTER);
            title.setLeading(24);
            doc.add(title);
            int i = 0;
            for (QuestionsComplet q: savedQuestions) {
                Paragraph questionParagraph = new Paragraph();
                questionParagraph.add(new Phrase(i+1 + ")" + q.getQuestion().getName()));
                String msgSolution = "";
                for (Solutions solution: q.getSolutions()) {
                    if (solution.getAnswer() == 1)
                        msgSolution += solution.getText();
                }
                questionParagraph.add(new Phrase( "Answer : " + msgSolution));
                i++;
                Log.i("Questions P", questionParagraph.toString());
                doc.add(questionParagraph);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case STORAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permission granted from popup
                    savedPDF();
                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}