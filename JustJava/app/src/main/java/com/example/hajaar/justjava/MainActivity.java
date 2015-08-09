package com.example.hajaar.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

;
import java.text.NumberFormat;

public class MainActivity extends ActionBarActivity {

    CoffeeShopOrder newOrder = new CoffeeShopOrder(0,5,1,2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        displayPrice(createInvoice(view));
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayPrice(String priceMessage) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(priceMessage);
    }

    public void increment(View view) {
        if (newOrder.getNumberOfCoffees()<10) {
            displayQuantity(newOrder.incrementNumberOfCoffees());
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"You cannot order more than 10 cups of coffee",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void decrement(View view) {
        if (newOrder.getNumberOfCoffees()>0) {
            displayQuantity(newOrder.decrementNumberOfCoffees());
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"You cant have negative cups of coffee",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void changeCreamPref(View view) {
        CheckBox creamCheckBox = (CheckBox) findViewById(R.id.cream_check_box);
        newOrder.setHasWhippedCream(creamCheckBox.isChecked());
    }

    public void changeChocolatePref(View view) {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        newOrder.setmHasChocolate(chocolateCheckBox.isChecked());
    }

    public void emailOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","",null));
        intent.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for " + nameEditText.getText());
        intent.putExtra(Intent.EXTRA_TEXT, createInvoice(view));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"You dont have an email app",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public String createInvoice(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String invoice = "Name: " + nameEditText.getText() + "\n";
        if (newOrder.getHasWhippedCream()) {
            invoice +=  "Added whipped cream " + "\n";
        }
        if (newOrder.getHasChocolate()) {
            invoice += "Added chocolate " + "\n";
        }
        invoice += "Quantity: " + newOrder.getNumberOfCoffees() + "\n" + "Total: " + NumberFormat.getCurrencyInstance().format(newOrder.getOrderTotal()) + "\n" + "Thank you!";
        return invoice;
    }
}