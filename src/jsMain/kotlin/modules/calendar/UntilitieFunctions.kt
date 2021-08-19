package modules.calendar

fun daysInMonth(month: Int, leapYear: Boolean): Int {
    var daysInMonth = 0
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        daysInMonth = 30
    } else if (month == 2) {
        if (leapYear) {
         daysInMonth=   29
        } else {
          daysInMonth=  28
        }
    } else {
        daysInMonth = 31
    }
    return daysInMonth
}