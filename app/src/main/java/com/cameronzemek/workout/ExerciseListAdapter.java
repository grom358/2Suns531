package com.cameronzemek.workout;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ExerciseListAdapter extends BaseAdapter {
    private List<Exercise> workout;
    private LayoutInflater inflater;
    private boolean rebuild = true;
    private Object[] items;

    public ExerciseListAdapter(List<Exercise> workout, LayoutInflater inflater) {
        this.workout = workout;
        this.inflater = inflater;
    }

    private void build() {
        int itemCount = workout.size();
        for (Exercise exercise : workout) {
            itemCount += exercise.getSets().size();
        }
        items = new Object[itemCount];
        int pos = 0;
        for (Exercise exercise : workout) {
            items[pos++] = exercise;
            for (ExerciseSet set : exercise.getSets()) {
                items[pos++] = set;
            }
        }
        rebuild = false;
    }

    @Override
    public int getCount() {
        if (rebuild) {
            build();
        }
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        if (rebuild) {
            build();
        }
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        return item instanceof Exercise ? 0 : 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object item = getItem(position);
        if (item instanceof Exercise) {
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.list_header, null);
            }
            return getHeaderView(view, (Exercise) item);
        } else {
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.list_item, null);
            }
            return getItemView(view, (ExerciseSet) item);
        }
    }

    public View getHeaderView(View view, Exercise exercise) {
        TextView text = (TextView) view.findViewById(R.id.separator);
        text.setText(exercise.getName());
        return view;
    }

    public View getItemView(View view, ExerciseSet exerciseSet) {
        TextView text = (TextView) view.findViewById(R.id.list_content);
        text.setText(exerciseSet.getDetails());

        switch (exerciseSet.getState()) {
            case ExerciseSet.STATE_CURRENT:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorHighlightCurrent));
                break;
            case ExerciseSet.STATE_FAILED:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorHighlightFailure));
                break;
            case ExerciseSet.STATE_SUCCESS:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorHighlightSuccess));
                break;
            default:
                view.setBackgroundColor(Color.TRANSPARENT);
                break;
        }

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        rebuild = true;
    }
}
