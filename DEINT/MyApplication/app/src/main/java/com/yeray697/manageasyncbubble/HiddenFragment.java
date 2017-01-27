package com.yeray697.manageasyncbubble;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * Created by usuario on 13/01/17.
 */

public class HiddenFragment extends Fragment {

    private TasksCallback mCallback;
    private int numbers[];
    private BubbleNumberTask bubbleNumberTask;

    public interface TasksCallback{
        void onPreExecute(); //Configurar la visibilidad de los botones
        void onProgressUpdate(int progress);
        void onCancelled();
        void onPostExecute(int time);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (TasksCallback) activity;
        } catch (Exception ex) {
            throw new ClassCastException(activity.getLocalClassName() + " must implement TasksCallback");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public void execute() {
        bubbleNumberTask = new BubbleNumberTask(numbers);
        bubbleNumberTask.execute();
    }

    public void cancel() {
        if (bubbleNumberTask != null)
            bubbleNumberTask.cancel(true);
    }

    private class BubbleNumberTask extends AsyncTask<Void,Integer,Integer>{

        private int[] numbers;

        public BubbleNumberTask(int[] numbers) {
            super();
            this.numbers = numbers;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return bubbleSort();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mCallback != null)
                mCallback.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if (mCallback != null)
                mCallback.onCancelled();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (mCallback != null)
                mCallback.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mCallback != null)
                mCallback.onProgressUpdate(values[0]);
        }

        private int bubbleSort() {
            long startTime = System.currentTimeMillis();
            int aux;
            for (int i = 0; i < numbers.length - 1; i++) {
                for (int j = 0; j < numbers.length -1; j++) {
                    if (numbers[j] > numbers[j+1])
                    {
                        aux          = numbers[j];
                        numbers[j]   = numbers[j+1];
                        numbers[j+1] = aux;
                    }
                    // Notifica a onProgressUpdate() del progreso actual
                    /*if(!isCancelled())
                        publishProgress((int)((numbers.length * i + j)/(float)(numbers.length * numbers.length))*100);
                    else
                        break;*/
                }
                if (!isCancelled())
                    publishProgress((int)(100*(i+1) / numbers.length));
                else
                    break;
            }
            return (int) ((System.currentTimeMillis() - startTime) / 1000);
        }

        public boolean isRunning() {
            return getStatus() == Status.RUNNING;
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public boolean isRunning(){
        return bubbleNumberTask != null && bubbleNumberTask.isRunning();
    }
}
