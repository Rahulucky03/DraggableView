package rahul.lucky.draggable;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import rahul.lucky.draggable.view.DraggableListener;
import rahul.lucky.draggable.view.DraggableView;

public class MainActivity extends AppCompatActivity {

    DraggableView draggableView;

    VideoView videoView;

    ImageView thumbnailImageView;

    RecyclerView rvMain;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        draggableView = findViewById(R.id.draggable_view);

        videoView = findViewById(R.id.video_view);

        thumbnailImageView = findViewById(R.id.iv_thumbnail);

        rvMain = findViewById(R.id.rv_main);

        initializeVideoView();
        initializePoster();
        hookDraggableViewListener();

    }

    public void onThubmnailClicked(View view) {
        Toast.makeText(this, "Video Title", Toast.LENGTH_SHORT).show();
    }


    public void onPosterClicked(View view) {
        draggableView.maximize();
    }

    /**
     * Hook DraggableListener to draggableView to pause or resume VideoView.
     */
    private void hookDraggableViewListener() {
        draggableView.setDraggableListener(new DraggableListener() {
            @Override public void onMaximized() {
                startVideo();
            }

            //Empty
            @Override public void onMinimized() {
                //Empty
            }

            @Override public void onClosedToLeft() {
                pauseVideo();
            }

            @Override public void onClosedToRight() {
                pauseVideo();
            }
        });
    }

    /**
     * Pause the VideoView content.
     */
    private void pauseVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    /**
     * Resume the VideoView content.
     */
    private void startVideo() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    /**
     * Initialize ViedeoView with a video by default.
     */
    private void initializeVideoView() {
        Uri uri = Uri.fromFile(Environment.getExternalStorageDirectory().listFiles()[35]);
        //Uri path = Uri.parse(Environment.getExternalStorageDirectory().listFiles()[34].listFiles()[3].listFiles()[1]);
        videoView.setVideoURI(uri);

        videoView.start();
    }

    /**
     * Initialize some ImageViews with a video poster and a video thumbnail.
     */
    private void initializePoster() {
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter(MainActivity.this);
        rvMain.setAdapter(adapter);
        adapter.setUserClickListener(new SearchAdapter.UserClickListener() {
            @Override
            public void OnUserClick() {
                draggableView.maximize();
            }
        });

        //posterImageView.setImageDrawable(getResources().getDrawable(R.drawable.spiderman_placeholder));
        /*Picasso.with(this)
                .load(VIDEO_POSTER)
                .placeholder()
                .into(posterImageView);*/
        thumbnailImageView.setImageDrawable(getResources().getDrawable(R.drawable.spiderman_placeholder));
        /*Picasso.with(this)
                .load(VIDEO_THUMBNAIL)
                .placeholder(R.drawable.spiderman_placeholder)
                .into(thumbnailImageView);*/
    }

}
