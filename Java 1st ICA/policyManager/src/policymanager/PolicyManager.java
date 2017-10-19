/*
 * Gadget Insurance Policy Prototype
 */
package policymanager;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 *
 * @author Heidi Portwine (s6110438)
 */

public class PolicyManager 
{
    // declared scanner for whole of code
    static Scanner keyb = new Scanner(System.in);
    
    public static void main(String[] args) 
    {
        displayMenu();
        main(null);
    }
    
    
    static void displayMenu()
    {
        String choice;
        // main menu. user chooses number
        System.out.println("1. Enter new policies ");
        System.out.println("2. Display summary of policies ");
        System.out.println("3. Display summary of policies for selected month ");
        System.out.println("4. Find and display policy ");
        System.out.println("0. Exit ");
        System.out.println("Please select an option from above: ");
        
        choice = keyb.nextLine(); 
        
        // check that user enters one of the choices
        while (!(choice.matches("[0-4]{1}")))
        {
            System.out.println("Please select an option from 0 - 4:  ");
            choice = keyb.nextLine();
        }
            switch (choice) 
            {
                case "1":
                    enterPolicy();
                    break;
                case "2":
                    summaryPolicies();
                    break;
                case "3":
                    selectedMonth();
                    break;
                case "4":
                    search();
                    break;
                case "0":
                    System.exit(0);   
            }
    }// end of display menu method

    // option 1 code
    static void enterPolicy()
    {
        Policy myPolicy = new Policy();
        myPolicy.setName(getName());
        myPolicy.setReferenceNumber(getReferenceNumber());
        myPolicy.setNumberOfItem(getNumberOfItems());
        myPolicy.setExcess(getExcess());
        myPolicy.setTerm(getTerm());
        myPolicy.setValue(getValue());
        myPolicy.setPremium();
        myPolicy.setDate();
        
        writeToFile(myPolicy.getDate(),myPolicy.getReferenceNumber(),myPolicy.getNumberOfItems(), myPolicy.getValue(),myPolicy.getExcess(),myPolicy.getPremium(),myPolicy.getTerm(), myPolicy.getName());
        
        output(myPolicy.getDate(),myPolicy.getReferenceNumber(),myPolicy.getNumberOfItems(), myPolicy.getValue(),myPolicy.getExcess(), myPolicy.getPremium(), myPolicy.getTerm(), myPolicy.getName());
    }
        
    // gets name from user and checks the length
    static String getName()
    {
        System.out.println("Please enter your name (e.g.J Smith): ");
        String name = keyb.nextLine();

        while (name.length()<1 || name.length()>20)
        {
            System.out.println("Your name must be between 1 and 20 characters");
            System.out.println("Please re enter your name: ");
            name = keyb.nextLine();
        }
        return name;
    } // end of name method
    
    
    static String getReferenceNumber()
    {   
        System.out.println("Please enter your reference number (e.g.RA345A):  ");
        String ref = keyb.nextLine().toUpperCase();
        
        boolean valid = false;
        
        while (!valid)
        {
            valid = true;
            if(ref.length()!=6)
            {
                System.out.println("Reference number should be 6 characters long (e.g.RA345A)");
                valid = false;
            }//checks that reference number is in correct format
            else if((((!Character.isLetter(ref.charAt(0)) || !Character.isLetter(ref.charAt(1))) || !Character.isDigit(ref.charAt(2))) || !Character.isDigit(ref.charAt(3))) || !Character.isDigit(ref.charAt(4)) || !Character.isLetter(ref.charAt(5)))
            {
                System.out.println("Reference number should be 2 letters, 3 digits followed by a letter (e.g.RA345A)");
                valid = false;
            } 
            if(!valid)
            {
                System.out.println("Please enter reference number: ");
                ref = keyb.nextLine();
            }   
        } 
        return ref;
    }//end of get reference number method
    
    static int getNumberOfItems()
    {
        System.out.println("Please enter number of items: ");
        String number = keyb.next();
        
        // checks that user enters a number
        while(!number.matches(".*[0-9].*"))
        {
            System.out.println("Please enter a number. Try again: ");
            number = keyb.next();
        }
        //checks user has entered a value above 0
        while (Integer.parseInt(number) <= 0)
        {
            System.out.println("Please enter a number above 0 to make a claim. Try again: ");
            number = keyb.next();
        }
        
        return Integer.parseInt(number);
    }// end of get number of items method
   
    static String getTerm()
    {
        String term = null;
        
        System.out.println("Please enter either 1 for Annual or 2 for Monthly: ");
        String method = keyb.next();
        
        //checks that a number has been entered and NOT a string
        while(!method.matches(".*[0-9].*")) 
        {
            System.out.println("Please enter a number. Try again: ");
            method = keyb.next();
        }
        
        int meth = Integer.parseInt(method);
        
        // checks that the user inputted 1 or 2
        while (meth != 1 && meth != 2)
        {
            System.out.println("Please enter either 1 for Annual or 2 for Monthly: ");
            meth = keyb.nextInt();
        }
        
        if(meth == 1)
        {
            term = "Annual";
        }
        else if (meth == 2)
        {
            term = "Monthly";
        }
        return term;
    }// end of getTerm method
    
    static double getExcess()
    {
        System.out.println("Please enter your excess (e.g. £30): ");
        String excess = keyb.next();
        
        // checks excess entered is not a string
        while(!excess.matches(".*[0-9].*")) //.* allows a wide range of values can be entered
        {
            System.out.println("Please enter a number. Try again: ");
            excess = keyb.next();
        }
        //checks that excess is between 30 and 70
        double ex = Double.parseDouble(excess);
        
        while(ex < 30 || ex > 70)
        {
            System.out.println("The excess needs to be between £30 and £70");
            ex = keyb.nextDouble();
        }      
        return ex;
    }// end of getExcess method
    
    static double getValue()
    {
        
        System.out.println("Please enter the value of the most expensive item: ");
        String value = keyb.next();
        
        // checks excess entered is not a string
        while(!value.matches(".*[0-9].*")) 
        {
            System.out.println("Please enter a number. Try again: ");
            value = keyb.next();
        }
        
        //check value entered is above 0
        double val = Double.parseDouble(value);
        
        while (val <= 0)
        {
            System.out.println("Value rejected. Please enter a value above 0: ");
            val = keyb.nextDouble();
        }
        return val;
    }// end of getValue method
   
    static void summaryPolicies() 
    {
        fileMenu(1);
    }
    
    static void selectedMonth()
    {
        fileMenu(2);
    }
    
    static void search()
    {
        fileMenu(3);
    }
    
    static void fileMenu(int option)
    {
        String choice;
        String fName;
        
        System.out.println("1. archive file ");
        System.out.println("2. policy file ");
        System.out.println("0. Exit ");
        System.out.println("Please select an option from above: ");
        
        choice = keyb.nextLine(); 
        
        // check that user enters one of the choices
        while (!(choice.matches("[0-2]{1}")))
        {
            System.out.println("Please select an option from 0 - 2:  ");
            choice = keyb.nextLine();
        }
        switch (choice) 
        {
            case "1":
                fName = "archive.txt";
                readFile(fName,option);
                break;
            case "2":
                fName = "policies.txt";
                readFile(fName,option);
                break;
            case "0":
                System.exit(0);   //pressing 0 will exit the whole system
        }
    }// end of filemenu method
    
    static void writeToFile(String date, String ref,int numberOfItems, double value, double excess, double premium,String term, String name)
    {
        PrintWriter output = null;
        
        File policy = new File("policies.txt");

            try 
            {
                FileWriter FileW = new FileWriter(policy, true);
                output = new PrintWriter(FileW);	// create new stream, link newly created stream to  file
            } 
            catch (FileNotFoundException e) // problem with file !
            {
                System.out.println("There is a problem creating the file. Program closing");
                System.exit(0);  // this terminates the program - parameter 0 indicates normal termination
            }
            catch (IOException exc)
            {
                System.out.println("There is a problem creating the file. Program closing");
                System.exit(0);  // this terminates the program - parameter 0 indicates normal termination
            }
            
            // displays -1 and R if rejected
            if (numberOfItems > 5 || value > 1000)
            {
                premium = -1;
                term = "R";
            }
            else 
            {
                premium = premium *100; //changes premium to pence
            }
            
            output.print(date + "\t");
            output.print(ref + "\t");
            output.print(numberOfItems + "\t");
            output.print((int)value + "\t");
            output.print((int)excess + "\t");
            output.print((int)premium + "\t");
            output.print(term.charAt(0) + "\t");
            output.println(name);
        
            output.close();
    }// end of writetofile
    
    static void readFile(String fName, int option)
    {
        
        //initialise months
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may =0;
        int jun =0;
        int jul =0;
        int aug=0;
        int sep=0;
        int oct=0;
        int nov=0;
        int dec=0;

        //used for average monthly premium
        double mpre;
        int lnumber = 0;
        String month = "";
        String search = "";
        
        if(option ==2)
        {
            System.out.println("Please a month (e.g Jan) : ");
            month = keyb.nextLine().toLowerCase();
            //checks that one of the months has been entered
            while(!(month.equals("jan") ||month.equals("feb") || month.equals("mar") ||month.equals("apr") || month.equals("may") || month.equals("jun") || month.equals("jul") || month.equals("aug") || month.equals("sep") || month.equals("oct") || month.equals("nov") || month.equals("dec")))
            {
                System.out.println("ERROR!Please enter a month (e.g Jan) : ");
                month = keyb.nextLine().toLowerCase();
            }
        }
        else if(option ==3)
        {
            System.out.println("Please enter what you wish to search for: ");
            search = keyb.next().toLowerCase();
        }
        
        if(option ==1)
        {
            try
            {
                File file = new File(fName);
       
                if(file.exists())
                {
                    FileReader fr = new FileReader(file);
                    LineNumberReader lnr = new LineNumberReader(fr);

                    while(lnr.readLine() != null)
                    {
                        lnumber++;
                    }
                lnr.close();
                }
                else
                {
                    System.out.println("File doesnt exist");
                }
            }
            catch(IOException ex){}
        }
        
        // Scanner so compiler is happy
        Scanner x = null;    
        try 
        {
            x = new Scanner(new File(fName));
        } 
            catch (FileNotFoundException e) 
            {
                System.out.println("File doesn't exist");
                System.exit(1);
            }
            
            int accept =0;
            int number =0;
            double p =0;

            // reads each line from file
            while (x.hasNext()) 
            {
                String date = x.next();
                String ref = x.next();
                int items = x.nextInt();
                int value = x.nextInt();
                int excess = x.nextInt();
                int pre = x.nextInt();
                String term = x.next();
                String name = x.nextLine();
        
                if(option ==1)
                {
                    if(pre != -1)
                    {
                        accept++;
                        number = number + items;
                        //looks to see if its annual
                        if(term.equals("A"))
                        {
                            p= p + (pre/12);
                        }
                        else
                        {
                            p = p + pre;
                        }
                    }
                }
                else if(option==2)
                {

                    if(date.toLowerCase().contains(month))
                    {
                        lnumber++;
                
                        if(pre != -1)
                        {
                            accept++;
                            number = number + items;
                            //looks to see if its annual
                            if(term.equals("A"))
                            {
                                p= p + (pre/12);
                            }
                            else
                            {
                                p = p + pre;
                            }    
                        }
                    }
                }
                else if(option==3)
                {
                    if(ref.toLowerCase().contains(search) || name.toLowerCase().contains(search))
                    {
                        output(date,ref,items,value,excess,pre,term,name);
                    }
                }
                
                if(option ==1)
                {
                    //counters for months
                    if(date.contains("Jan"))
                    {
                        jan++; 
                    }
                    else if(date.contains("Feb"))
                    {
                        feb++;
                    }
                    else if (date.contains("Mar"))
                    {
                        mar++;
                    }
                    else if (date.contains("Apr"))
                    {
                        apr++;
                    }
                    else if(date.contains("May"))
                    {
                        may++;
                    }
                    else if(date.contains("Jun"))
                    {
                        jun++;
                    }
                    else if(date.contains("Jul"))
                    {
                        jul++;
                    }
                    else if(date.contains("Aug"))
                    {
                        aug++;
                    }
                    else if(date.contains("Sep"))
                    {
                        sep++;
                    }
                    else if(date.contains("Oct"))
                    {
                        oct++;
                    }
                    else if(date.contains("Nov"))
                    {
                        nov++;
                    }
                    else if(date.contains("Dec"))
                    {
                        dec++;
                    }
                }
            }
            
            if(option ==1 || option == 2)
            {
                mpre = ((p/accept)/100);// gets it back to pounds
                System.out.println("Total number of lines : "+ lnumber);
                System.out.println("Average number of items(accepted policies): " + number/accept );
                System.out.println("Average monthly premium; " + Math.round(mpre*100.0)/100.0); //rounds number to 2 d.p
            }
            
            if(option ==1)
            {
                System.out.println("Number of policies per month(inc. non-accepted) : ");
                System.out.println("");
                System.out.printf("%-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
                System.out.println("");
                System.out.printf("%-4d %-4d %-4d %-4d %-4d %-4d %-4d %-4d %-4d %-4d %-4d %-4d",jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec);
                System.out.println("");
            }

            x.close();        
    } // end of readfile
    
    static void output(String date, String ref,int numberOfItems, double value,double excess,double premium,String term,String name){
        
        int limit;
        // calculates limit
        if (value <= 1000 && value > 800)
        {
            limit = 1000;
        }
        else if (value <= 800 && value > 550)
        {
            limit = 800;
        }
        else if (value <= 550 && value > 0)
        {
            limit = 550;
        }
        else
        {
            limit = (int) value;
        }
        
        String numberWord;
        
        // formatting to convert int to string
        if (numberOfItems==1)
        {
            numberWord = "one";
        }     
        else if(numberOfItems==2)
        {
            numberWord = "Two";
        }
        else if(numberOfItems==3)
        {
            numberWord = "Three";
        }
        else if (numberOfItems==4)
        {
            numberWord = "Four";
        }
        else if (numberOfItems==5)
        {
            numberWord = "Five";
        }
        else
        {
            numberWord = Integer.toString(numberOfItems);
        }
        
        name = name.replaceAll("\t", "");// removes tab so it doesnt mess up format
        System.out.println("");
        System.out.println("+=============================================+");
        System.out.println("|                                             |");
        System.out.printf("|" +"%10s %-20s %14s %n", "Client:",name,""+ "|");
        System.out.println("|                                             |");
        System.out.printf("|" +"%10s %-20s %5s %-25s %n","Date:",date,"Ref:",ref +" |");
        System.out.printf("|"+"%10s %-19s %6s %-5s %2s %n","Terms:",term,"Items:",numberWord,"|");
        System.out.printf("|" + "%10s £%-20.2f %13s %n","Excess:",excess,"" +"|");
        
        System.out.println("|                                             |");
        
        // checks that the quantity of gadgets is less than 5
        if (numberOfItems > 5)
        {
            System.out.println("|   The quantity of gadgets is bigger than    |");
            System.out.println("|     5 therefore this claim is rejected      |");        
        }
        // checks that the most expensive item is less than 1000
        else if (value >1000)
        {
            System.out.println("|   The most expensive item is greater than   |");
            System.out.println("|   1000 therefore this has been rejected    |");
        }
        
        else
        {
            System.out.printf("|"+"%9s %-16s %-10s %8s %n",term,"","Limit per","|");
            System.out.printf("|"+"%10s £%-15.2f %1s %2s %8s %n","Premium:",premium,"","gadget:", limit +" |");
        }
            System.out.println("|                                             |");
            System.out.println("+=============================================+");
    }// end of output method    
}//end of policymangager class

        
   
