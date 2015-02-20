package wikiracers.wikiracers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/*
*This is the page after you've won the game, it will redirect you to this page.
*Tried to do an alert dialog instead of redirecting to another page but have some issues atm
*/
//Todo: get name, get counts

public class winnerPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        statisticbtn();
        menuButton();

    }

    //Redirects to stats page
    private void statisticbtn(){
        Button statsbtn = (Button) findViewById(R.id.statisticsbutton);
        View.OnClickListener statsYell = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), statisticsPage.class));
            }
        };
        statsbtn.setOnClickListener(statsYell);
    }
    //Redirects to menuActivity
    private void menuButton(){
        Button menuBtn = (Button) findViewById(R.id.menu_button);
        View.OnClickListener statsYell = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), menuActivity.class));
            }
        };
        menuBtn.setOnClickListener(statsYell);
    }
}

//this is an alert dialog might replace the winners page with this later if possible
//public void showAlertDialog(View v) {
//    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//    alertDialog.setTitle("Just a Setting Page");
//    alertDialog.setMessage("Settings Page");
//    alertDialog.setIcon(R.drawable.ic_launcher);
//    alertDialog.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            startActivity(new Intent(getApplicationContext(), statisticsPage.class));
//            Toast.makeText(getApplicationContext(), "Settings Page", Toast.LENGTH_SHORT).show();
//        }
//    });
//    alertDialog.show();
//}
