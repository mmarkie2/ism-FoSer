package pollub.ism.foser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
public class MainActivity extends AppCompatActivity {

    private Button buttonStart, buttonStop, buttonRestart;
    private TextView textInfoService, textInfoSettings;
    private String message;
    private Boolean show_time, work, work_double;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStop = (Button)findViewById(R.id.buttonStop);
        buttonRestart = (Button)findViewById(R.id.buttonRestart);
        textInfoService = (TextView)findViewById(R.id.textInfoServiceState);
        textInfoSettings = (TextView) findViewById(R.id.textInfoSettings);
        this.updateUI();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSettings: startActivity(new Intent(this,SettingsActivity.class)); return true;
            case R.id.itemExit: finishAndRemoveTask(); return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void clickStart(View view) {
        Toast.makeText(this,"Start",Toast.LENGTH_SHORT).show();
    }

    public void clickStop(View view) {
        Toast.makeText(this,"Stop",Toast.LENGTH_SHORT).show();
    }

    public void clickRestart(View view) {
        clickStop(view);
        clickStart(view);
    }
    private String getPreferences(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        message = sharedPreferences.getString("message","ForSer");
        show_time = sharedPreferences.getBoolean("show_time", true);
        work = sharedPreferences.getBoolean("sync",true);
        work_double = sharedPreferences.getBoolean("double", false);

        return "Message: " + message + "\n"
                +"show_time: " + show_time.toString() +"\n"
                +"work: " + work.toString() + "\n"
                +"double: " + work_double.toString();
    }
    private void updateUI(){

        textInfoSettings.setText(getPreferences());
    }
}