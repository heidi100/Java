/*
 * Class to hold Policy info
 */
package policymanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author Heidi Portwine
 */
public class Policy 
{
        // values
        private String name;
        private String ref ;
        private int number;
        private String term;
        private double excess ;
        private double value;
        private double premium;
        private String date;
        
        Policy()
        {
            
        }
        
        Policy (String n, String r, int num, String t, double ex, double val, double pre, String d)
        {
            name=n; 
            ref=r;
            number=num;
            term=t;
            excess=ex;
            value=val;
            premium=pre;
            date=d;
        }
        //getters
        String getName()
        {
            return name;
        }
        String getReferenceNumber()
        {
            return ref;
        }
        int getNumberOfItems()
        {
            return number;
        }
        String getTerm()
        {
            return term;
        }
        double getExcess()
        {
            return excess;
        }
        double getValue()
        {
            return value;
        }
        double getPremium()
        {
            return premium;
        }
        String getDate()
        {
            return date;
        }
        
        //setters
        void setName(String n)
        {
            name = n;
        }
        void setReferenceNumber(String r)
        {
            ref = r;
        }
        void setNumberOfItem(int num)
        {
            number = num;
        }
        void setTerm(String t)
        {
            term = t;
        }
        void setExcess(double ex)
        {
            excess = ex;
        }
        void setValue(double val)
        {
            value = val;
        }
        void setPremium()
        {
            if (value <= 550)
        {    
            if(number == 1)
            {
                premium = 4.99;
            }
            else if (number == 2 || number == 3)
            {
                premium = 9.99;
            }
            else if (number == 4 || number == 5)
            {
                premium = 14.99;
            }
        }    
        else if(value >= 550 && value <= 800)
        {
            if (number == 1)
            {
                premium = 6.15;
            }
            else if (number == 2 || number == 3)
            {
                premium = 12.35;
            }
            else if (number == 4 || number == 5)
            {
                premium = 18.60;
            }
        }
        else if (value >= 800 && value <= 1000)
        {
            if (number == 1)
            {
                premium = 7.30;
            }
            else if (number == 2 || number == 3)
            {
                premium = 14.55;
            }
            else if (number == 4 || number == 5)
            {
                premium = 21.82;
            }
        }  
            
        // code to calculate the premium
            if (excess == 70)
        {
            premium = premium * 0.8;
        }
        else if (excess >= 60)
        {
            premium = premium * 0.85;
        }
        else if (excess >= 50)
        {
            premium = premium * 0.9;
        }
        else if (excess >= 40)
        {
            premium = premium * 0.95;
        }
        else if (excess >= 30)
        {
            premium = premium * 1.0;
        }
            
        // checks to see if user has selected annual
        // code to give user (annual) discount 
        if (term.equals("Annual"))
        {
            premium = premium * 0.9;
            premium = premium * 12;
        }
        }
        
        //to get date
        void setDate()
        {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        date= sdf.format(cal.getTime());
        }
        
        // to display like in archive file
        String stringConvert()
        {
            String s = "";
            s = s + date + "\t\t" + ref + "\t" + number + "\t" + value + "\t" + excess + "\t" + premium + "\t" + term + "\t" + name + "\t";
            return s;
        }
    
}
