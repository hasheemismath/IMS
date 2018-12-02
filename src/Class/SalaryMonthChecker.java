/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vishwa
 */
public class SalaryMonthChecker {
    private Date date;
    
    public SalaryMonthChecker(){}
    
    public SalaryMonthChecker(Date date){
        this.date = date;
    }
    
    public boolean checkMonth(){
        Date cdate = java.sql.Date.valueOf(java.time.LocalDate.now());
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int paymentDate = cal.get(Calendar.MONTH);
        
        cal.setTime(cdate);
        int currentDate = cal.get(Calendar.MONTH);
        
        return (paymentDate == currentDate);
    }
    
    public Date newEmployee(){
        Date cdate = java.sql.Date.valueOf(java.time.LocalDate.now());
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(cdate);
        int fakeYear = cal.get(Calendar.YEAR);
        int fakeMonth = cal.get(Calendar.MONTH);
        
        if(cal.get(Calendar.MONTH) == 0){
            fakeYear--;
            fakeMonth = 12;
        }
        
        LocalDate fakeLdate = LocalDate.of(fakeYear, fakeMonth, cal.get(Calendar.DAY_OF_MONTH));
        
        return java.sql.Date.valueOf(fakeLdate);
    }
    
}
