package com.example.hajaar.justjava;

/**
 * Created by hajaar on 08-Aug-15.
 */
public class CoffeeShopOrder {
    private int mNumberOfCoffees;
    private int mPriceOfCoffee;
    private int mPriceOfCream;
    private int mPriceOfChocolate;
    private boolean mHasWhippedCream;
    private boolean mHasChocolate;

    public CoffeeShopOrder(int tempnumberofcoffees, int temppriceofcofee, int temppriceofcream, int temppriceofchocolate) {
        mNumberOfCoffees=tempnumberofcoffees;
        mPriceOfCoffee=temppriceofcofee;
        mPriceOfCream=temppriceofcream;
        mPriceOfChocolate=temppriceofchocolate;
    }
    public void setNumberOfCoffees(int tempnumberofcoffees) {mNumberOfCoffees=tempnumberofcoffees;}
    public void setPriceOfCoffee(int temppriceofcoffee) {mPriceOfCoffee=temppriceofcoffee;}
    public void setPriceOfCream(int temppriceofcream) {mPriceOfCream=temppriceofcream;}
    public void setPriceOfChocolate(int temppriceofchocolate) {mPriceOfChocolate=temppriceofchocolate;}
    public void setHasWhippedCream(boolean temphaswhippedcream) {mHasWhippedCream=temphaswhippedcream;}
    public void setmHasChocolate(boolean temphaschocolate) {mHasChocolate=temphaschocolate;}
    public int getNumberOfCoffees() {return mNumberOfCoffees;}
    public int getPriceOfCoffee() {return mPriceOfCoffee;}
    public int getOrderTotal() {
        int tempsum = mPriceOfCoffee*mNumberOfCoffees;
        if (mHasChocolate) {tempsum += mPriceOfChocolate*mNumberOfCoffees;}
        if (mHasWhippedCream) { tempsum += mPriceOfCream*mNumberOfCoffees;}
        return tempsum;
    }
    public boolean getHasWhippedCream() {return mHasWhippedCream;}
    public boolean getHasChocolate() {return mHasChocolate;}
    public int incrementNumberOfCoffees() {
        mNumberOfCoffees += 1;
        return mNumberOfCoffees;
    }
    public int decrementNumberOfCoffees() {
        mNumberOfCoffees -= 1;
        return mNumberOfCoffees;
    }
}
