package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button, button1, testIntent;
    EditText enterName, enterAge;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduleJob();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CancelJob();
            }
        });

        enterName = findViewById(R.id.enter_Name);
        enterAge = findViewById(R.id.enter_Age);
        testIntent = findViewById(R.id.test_intent);

        testIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent testingIntentWithBundles = new Intent(getApplicationContext(), DisplayActivity.class);
                testingIntentWithBundles.putExtra("nameChosen", enterName.getText().toString());

                int ageGiven = Integer.parseInt(enterAge.getText().toString());

                testingIntentWithBundles.putExtra("AgeGiven", ageGiven );
                startActivity(testingIntentWithBundles);

            }
        });
    }

    private void CancelJob() {
        JobScheduler scheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job Cancelled ");

    }

    private void ScheduleJob() {

        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo info = new JobInfo.Builder(123,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler scheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Scheduled ");
        }else {
            Log.d(TAG, "Job Scheduling failed");
        }




    }


}
