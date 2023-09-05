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
    private RecyclerView recyclerView1, recyclerView2;
    private Adapter1 adapter1;
    private Adapter2 adapter2;


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

        recyclerView1 = findViewById( R.id.recycler_view1 );
        recyclerView2 = findViewById( R.id.recycler_view2 );
        adapter1 = new Adapter1( new OnItemClickListener()
        {
            @Override
            public void onItemClick( int start, int end )
            {
                adapter2.updateData( start, end );
            }
        } );
        adapter2 = new Adapter2( new OnItemClickListener()
        {
            @Override
            public void onItemClick( int start, int end )
            {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

//                    // Scroll to the position when the button is clicked
//                    recyclerView.scrollToPosition( position );

                // 滚动到指定位置，并设置偏移量为 0
                if ( layoutManager != null )
                {
                    layoutManager.scrollToPositionWithOffset( start, 0 );
                }

                // 延迟以等待滚动完成，然后设置焦点
                recyclerView.postDelayed( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition( start );
                        if ( viewHolder != null )
                        {
                            viewHolder.itemView.requestFocus();
                        }
                    }
                }, 100 );  // 设置合适的延迟时间，这里用了100毫秒
            }
        } );

        recyclerView1.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );
        recyclerView1.setAdapter( adapter1 );

        recyclerView2.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );
        recyclerView2.setAdapter( adapter2 );

//            button.setOnClickListener( new View.OnClickListener()
//            {
//                @Override
//                public void onClick( View v )
//                {
//                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
////                    // Scroll to the position when the button is clicked
////                    recyclerView.scrollToPosition( position );
//
//                    // 滚动到指定位置，并设置偏移量为 0
//                    if (layoutManager != null) {
//                        layoutManager.scrollToPositionWithOffset(position, 0);}
//
//                    // 延迟以等待滚动完成，然后设置焦点
//                    recyclerView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
//                            if (viewHolder != null) {
//                                viewHolder.itemView.requestFocus();
//                            }
//                        }
//                    }, 100);  // 设置合适的延迟时间，这里用了100毫秒
//
//                }
//            } );
//        }
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

