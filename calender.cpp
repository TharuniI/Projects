/* Tharuni Iranjan
This program asks the user for a type of calender. Depending on
what they ask for a caledner for that specific month/year
will be printed out. The formula for first_day discovered with
the help of stack overflow*/

//include libraries
#include<iostream>
#include<cstdlib>
#include<iomanip>
#include<string>

//declaring variables
using namespace std;
string user_month;
int year;
int days_in_week = 0;
int fday= 0;
string space = "   ";

//decalring functions
void print_year(int year);
void print_month(string user_month, int year);

//calculates for the first day of the week
int start_of_month(int year){
  int first_day;
  first_day = (((year - 1)* 365)+((year-1)/4)-((year-1)/100) + ((year)/400)+ 1) % 7;
  return first_day;
}

int main(){ //main function
  //ask user if they want a calender for the month or the year
  string m_or_y;
  cout << "Would you like the calender of month or one year?" << endl;
  cout << "Enter m for month and y for year: ";
  cin >> m_or_y;

  //depending what the user inputs, a certain function would be called on
  if (m_or_y == "m"){
    print_month(user_month, year);
  } else if (m_or_y == "y"){
    print_year(year);
  }
  cout << endl;
}

//if user inputted y, the following function will run
void print_year(int year){
  //asks the user for the year
  cout << "Enter a year: ";
  cin >> year;

  //arrays are created to decalre each month and the number of days in each month
  string months[] = {"January", "February", "March", "April", "May",
                     "June", "July", "August", "September",
                     "October", "November", "December"};
  int days_in_month[] = {31,28,31,30,31,30,31,31,30,31,30,31};

  //if it is a leap year, an extra day is added to the month february
  if ((year%4 == 0 && year%100!=0) || year%400==0) {
    days_in_month[1] = 29;
  }

  fday =  start_of_month(year);
  //prints out all the months of a year
  for(int i = 0; i < 12; i++){
    char month = days_in_month[i] ;

    cout << endl;
    cout << "---------------"<<months[i]<<"-------------------" << endl;
    cout << "  Sun  Mon  Tue  Wed  Thurs  Fri  Sat" << endl;

    //sets the caldender to the first day of the week
    for(days_in_week=0;days_in_week < fday;days_in_week++){
      cout << "      ";
    }
    //prints out all the days of a month
    for(int days = 1; days <= month; days++){
      cout << space << days; //prints days in calender
      space = "    ";
      //new line for next set of numbers
      if(++days_in_week > 6){
        days_in_week = 0;
        cout << endl;
        space = " "; //space size set to 0 when its sunday
      }
      fday = days_in_week;
    }
  }
  cout << endl; //new line
}

//if the user inputted m, the following function will run
void print_month(string user_month, int year){
  //asks user to enter a year and month and are stored into seperate values
  cout << "Enter a year: ";
  cin >> year;
  cout << "Enter a month: ";
  cin >> user_month;
  cout << endl;

  //arrays are created to decalre each month and the number of days in each month
  string months[] = {"January", "February", "March", "April", "May",
                     "June", "July", "August", "September",
                     "October", "November", "December"};
  int days_in_month[] = {31,28,31,30,31,30,31,31,30,31,30,31};

  //if it is a leap year, an extra day is added to the month february
  if ((year%4 == 0 && year%100!=0) || year%400==0) {
    days_in_month[1] = 29;
  }

  fday =  start_of_month(year);
  //main for loop is used to print out all the months of the given year
  for(int i = 0;i < 12;i++){
    if (user_month != months[i]){
      continue;
    }
    //if the user inputted month matches the month in the array,
    //the caldender for that month will be printed out
    cout << "---------------"<<months[i]<<"-------------------" << endl;
    cout << "  Sun   Mon   Tue   Wed   Thurs   Fri   Sat" << endl;
    char month = days_in_month[i];

    //sets the caldender to the first day of the week
    for(int i = 0; i < fday; i++){
      cout << "      ";
    }
    space += "  "; //increases space size since the numbers are single digits
    //Prints out all the dates for each month
    for(int days = 1; days <= month; days++){
      cout << space << days;
      space = "    "; //space size reset to original
      //if statement allows the output to be printed in a calendar format
      if(++days_in_week>6){
        days_in_week = 0;
        cout << endl;
        space = " "; //retuens space to 0 when its sunday
      }
      fday = days_in_week;
    }
  }
  cout << endl; //new line
}
