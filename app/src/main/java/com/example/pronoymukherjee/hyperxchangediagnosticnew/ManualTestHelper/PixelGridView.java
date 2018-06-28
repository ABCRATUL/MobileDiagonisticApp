package com.example.pronoymukherjee.hyperxchangediagnosticnew.ManualTestHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class PixelGridView extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private boolean[][] cellChecked ;

    int score=0;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        blackPaint.setColor(Color.CYAN);
    }



    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        cellChecked = new boolean[numColumns][numRows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        boolean completed = true;

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cellChecked[i][j]) {

                    canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            blackPaint);
                }
                else
                {
                    completed = false;
                }
            }
        }

        if(completed == true)
        {
            Toast.makeText(getContext(), "Touchscreen OK", Toast.LENGTH_SHORT).show();

            score =1;
            //new intent here
            Intent data = new Intent();
            String text = "Result to be returned....";
            //---set the data to pass back---
            data.setData(Uri.parse(String.valueOf(score)));
            data.putExtra("id",2);
            ((Activity)getContext()).setResult(RESULT_OK, data);
            //---close the activity---
            ((Activity)getContext()).finish();
        }

        for (int i = 1; i <= numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);

            Log.i(this.getClass().getName(), "column : " +column+" row: "+row);



            // cellChecked[column][row] = !cellChecked[column][row];
            if(row<getNumRows()&& column <getNumColumns()) {
                cellChecked[column][row] = true;
                invalidate();
            }
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);

            Log.i(this.getClass().getName(), "column : " +column+" row: "+row);


            //  cellChecked[column][row] = !cellChecked[column][row];
            if(row<getNumRows()&& column <getNumColumns()) {
                cellChecked[column][row] = true;
                invalidate();
            }
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int column = (int)(event.getX() / cellWidth);
            int row = (int)(event.getY() / cellHeight);

            Log.i(this.getClass().getName(), "column : " +column+" row: "+row);

            //System.out.print(cellChecked[column][row]+" cellChecked[column][row]");
            //cellChecked[column][row] = !cellChecked[column][row];
            if(row<getNumRows()&& column <getNumColumns()) {
                cellChecked[column][row] = true;
                invalidate();
            }
        }

        return true;
    }
}
