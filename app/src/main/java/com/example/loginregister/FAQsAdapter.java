package com.example.loginregister;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginregister.Swagger.FAQs;
import java.util.List;

public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.ViewHolder> {
    private List<FAQs> faqsList;
    private Context context;

    public FAQsAdapter(Context context, List<FAQs> faqsList) {
        this.context = context;
        this.faqsList = faqsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_faqs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FAQs faq = faqsList.get(position);

        Log.d("ADAPTER_DEBUG", "Pregunta: " + faq.getQuestion() + " - Respuesta: " + faq.getAnswer());
        holder.questionTextView.setText(faq.getQuestion() != null ? faq.getQuestion() : "Pregunta no disponible");
        holder.answerTextView.setText(faq.getAnswer() != null ? faq.getAnswer() : "Respuesta no disponible");
    }


    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView, answerTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.textViewQuestion);
            answerTextView = itemView.findViewById(R.id.textViewAnswer);
        }
    }
}