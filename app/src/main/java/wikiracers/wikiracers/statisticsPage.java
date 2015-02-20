package wikiracers.wikiracers;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
/**
 * This will be the statistics page, it has a scrollview
 */
//Todo: record the stats of the players, their name, Wins
public class statisticsPage extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stats);
        }
}
