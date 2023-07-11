package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewAuthor;
        private TextView textViewCategory;
        private TextView textViewId;
        private TextView textViewAvailability;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewAvailability = itemView.findViewById(R.id.textViewAvailability);
            textViewId = itemView.findViewById(R.id.textViewId);

        }

        public void bind(Book book) {
            textViewTitle.setText(book.getTitle());
            textViewAuthor.setText(book.getAuthor());
            textViewCategory.setText(book.getCategory());
            textViewAvailability.setText("Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
            textViewId.setText("ID: " + book.getId());

        }
    }
}
