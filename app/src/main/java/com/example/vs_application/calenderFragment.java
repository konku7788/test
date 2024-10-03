package com.example.vs_application;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class calenderFragment extends Fragment {
    private CalendarView calendarView;
    private ListView todoListView;
    private Button buttonAddTodo;
    private TextView textViewTitle;
    private ArrayAdapter<String> adapter;
    private Map<String, List<String>> todoMap;
    private String currentDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender2, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        todoListView = view.findViewById(R.id.todoListView);
        buttonAddTodo = view.findViewById(R.id.buttonAddTodo);
        textViewTitle = view.findViewById(R.id.textViewTitle);

        todoMap = new HashMap<>();
        currentDate = getCurrentDateString();

        adapter = new ArrayAdapter<>(getContext(), R.layout.todo_item, R.id.todo_text, new ArrayList<>());
        todoListView.setAdapter(adapter);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            currentDate = getDateString(year, month, dayOfMonth);
            updateTodoList();
        });

        buttonAddTodo.setOnClickListener(v -> showAddTodoDialog());

        todoListView.setOnItemLongClickListener((parent, view1, position, id) -> {
            deleteTodo(position);
            return true;
        });

        updateTodoList();

        return view;

    }

    private void showAddTodoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_todo, null);
        final EditText editTextTodo = dialogView.findViewById(R.id.editTextTodo);

        builder.setView(dialogView)
                .setTitle("새 일정 추가")
                .setPositiveButton("추가", (dialog, id) -> {
                    String todo = editTextTodo.getText().toString().trim();
                    if (!todo.isEmpty()) {
                        addTodo(todo);
                    }
                })
                .setNegativeButton("취소", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addTodo(String todo) {
        List<String> todos = todoMap.getOrDefault(currentDate, new ArrayList<>());
        todos.add(todo);
        todoMap.put(currentDate, todos);
        updateTodoList();
    }

    private void deleteTodo(int position) {
        List<String> todos = todoMap.get(currentDate);
        if (todos != null && position < todos.size()) {
            todos.remove(position);
            updateTodoList();
        }
    }

    private void updateTodoList() {
        List<String> todos = todoMap.getOrDefault(currentDate, new ArrayList<>());
        adapter.clear();
        adapter.addAll(todos);
        adapter.notifyDataSetChanged();

        // 날짜에 따라 제목 업데이트
        String titleDate = currentDate.equals(getCurrentDateString()) ? "오늘" : currentDate;
        textViewTitle.setText(titleDate + "의 일정");
    }

    private String getCurrentDateString() {
        Calendar cal = Calendar.getInstance();
        return getDateString(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    private String getDateString(int year, int month, int day) {
        return year + "/" + (month + 1) + "/" + day;
    }
}