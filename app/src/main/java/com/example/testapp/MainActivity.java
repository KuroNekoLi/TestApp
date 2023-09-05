package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    NumberAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // Initialize RecyclerView
        recyclerView = findViewById( R.id.recycler_view );
        recyclerView.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );
        adapter = new NumberAdapter( this );
        recyclerView.setAdapter( adapter );

        // Initialize Grid Layout
        GridLayout gridLayout = findViewById( R.id.gridLayout );

        for ( int i = 0; i < 100; i++ )
        {
            Button button = new Button( this );
            button.setText( String.valueOf( i + 1 ) );

            // 设置按钮的大小
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 180;  // 宽度为60像素
            params.height = 80; // 高度为60像素
            button.setLayoutParams(params);

            // 设置背景选择器
            button.setBackgroundResource(R.drawable.selector_item_background);

            final int position = i;
            button.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick( View v )
                {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

//                    // Scroll to the position when the button is clicked
//                    recyclerView.scrollToPosition( position );

                    // 滚动到指定位置，并设置偏移量为 0
                    if (layoutManager != null) {
                        layoutManager.scrollToPositionWithOffset(position, 0);}

                    // 延迟以等待滚动完成，然后设置焦点
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                viewHolder.itemView.requestFocus();
                            }
                        }
                    }, 100);  // 设置合适的延迟时间，这里用了100毫秒

                }
            } );
            gridLayout.addView( button );
        }
    }
    public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder>
    {
        private Context mContext;

        public NumberAdapter( Context context )
        {
            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType )
        {
            View view = LayoutInflater.from( mContext ).inflate( R.layout.item_number, parent, false );
            return new ViewHolder( view );
        }

        @Override
        public void onBindViewHolder( @NonNull ViewHolder holder, int position )
        {
            holder.textView.setText( String.valueOf( position + 1 ) );
        }

        @Override
        public int getItemCount()
        {
            return 100;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textView;

            public ViewHolder( @NonNull View itemView )
            {
                super( itemView );
                textView = itemView.findViewById( R.id.item_number_text );
            }
        }
    }
}

