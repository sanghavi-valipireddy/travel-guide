package com.ibtspl.foodmanagement;

/**
 * Created by Brunda on 7/6/2016.
 */
public class Break {

    private String item1;
    private String item2;
    public Break()
    {

    }
    public Break(String item1,String item2)
    {
        this.item1=item1;
        this.item2=item2;
    }
    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

}
