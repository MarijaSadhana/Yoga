package com.sadhana.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadhana.yoga.R;
import com.sadhana.yoga.model.Programs;

public class DetailProgram extends AppCompatActivity {

    ImageView programCover, backArrow, videoPlay;
    TextView programTitle, programDescription;
    Programs program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_program);

        programCover = findViewById(R.id.program_cover_image);
        videoPlay = findViewById(R.id.video_play_image);
        backArrow = findViewById(R.id.backArrow);
        programTitle = findViewById(R.id.program_title);
        programDescription = findViewById(R.id.program_description);

        program = getIntent().getParcelableExtra("Programs");
        if (program != null) {
            programTitle.setText(program.getProgramTitle());
            programDescription.setText(program.getProgramDescription());
        }

        String imgName = null;
        if (program != null) {
            imgName = program.getProgramImage();
        }
        int resID = getResources().getIdentifier(imgName , "drawable", getPackageName());
        programCover.setImageResource(resID);

        videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoId = program.getProgramVideo();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId));
                intent.putExtra("force_fullscreen",true);
                startActivity(intent);
            }
        });
    }

    public void onBackClick(View view) {
        finish();
    }
}



