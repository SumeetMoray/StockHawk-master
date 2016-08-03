package com.sam_chordas.android.stockhawk.service;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.rest.Utils;

/**
 * Created by sumeet on 28/7/16.
 */



class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int mCount = 10;
//    private List<WidgetItem> mWidgetItems = new ArrayList<WidgetItem>();
    private Context mContext;
    private int mAppWidgetId;

    Cursor cursor;


    /*
        cursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                new String[] { "Distinct " + QuoteColumns.SYMBOL }, null,
                null, null);
    */


    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        cursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                new String[] {QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                        QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                QuoteColumns.ISCURRENT + " = ?",
                new String[]{"1"},
                null);
        //

    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {


        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        // Construct a remote views item based on the app widget item XML file,
        // and set the text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.list_item_quote);

        String message = "Position : " + String.valueOf(position);

//        rv.setTextViewText(R.id.stock_symbol, message);


        cursor.moveToPosition(position);

        rv.setTextViewText(R.id.stock_symbol,cursor.getString(cursor.getColumnIndex("symbol")));

        rv.setTextViewText(R.id.bid_price,cursor.getString(cursor.getColumnIndex("bid_price")));


        if(Utils.showPercent)
        {
            rv.setTextViewText(R.id.change,cursor.getString(cursor.getColumnIndex("percent_change")));
        }
        else
        {
            rv.setTextViewText(R.id.change,cursor.getString(cursor.getColumnIndex("change")));
        }

        if (cursor.getInt(cursor.getColumnIndex("is_up")) == 1){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                rv.setTextViewCompoundDrawables(R.id.change,
                        R.drawable.percent_change_pill_red,
                        R.drawable.percent_change_pill_red,
                        R.drawable.percent_change_pill_red,
                        R.drawable.percent_change_pill_red);
            }

        }
        else
        {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                rv.setTextViewCompoundDrawables(R.id.change,
                        R.drawable.percent_change_pill_green,
                        R.drawable.percent_change_pill_green,
                        R.drawable.percent_change_pill_green,
                        R.drawable.percent_change_pill_green);
            }
        }



        /*


        symbol.setText(cursor.getString(cursor.getColumnIndex("symbol")));
        bidPrice.setText(cursor.getString(cursor.getColumnIndex("bid_price")));
        int sdk = Build.VERSION.SDK_INT;
        if (cursor.getInt(cursor.getColumnIndex("is_up")) == 1){
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                viewHolder.change.setBackgroundDrawable(
                        mContext.getResources().getDrawable(R.drawable.percent_change_pill_green));
            }else {
                viewHolder.change.setBackground(
                        mContext.getResources().getDrawable(R.drawable.percent_change_pill_green));
            }
        } else{
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.change.setBackgroundDrawable(
                        mContext.getResources().getDrawable(R.drawable.percent_change_pill_red));
            } else{
                viewHolder.change.setBackground(
                        mContext.getResources().getDrawable(R.drawable.percent_change_pill_red));
            }
        }
        if (Utils.showPercent){
            viewHolder.change.setText(cursor.getString(cursor.getColumnIndex("percent_change")));
        } else{
            viewHolder.change.setText(cursor.getString(cursor.getColumnIndex("change")));
        }


        */


        // Return the remote views object.
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
