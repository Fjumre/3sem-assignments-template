package app;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Time {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", LocalDate.of(1996, 5, 5)),
                new Employee("Jane", LocalDate.of(1986, 1, 4)),
                new Employee("Jack", LocalDate.of(1956, 11, 11)),
                new Employee("Joe", LocalDate.of(1978, 11, 23)),
                new Employee("Jill", LocalDate.of(1999, 12, 7))
        );

        int totalAge = 0;
        for (Employee employee : employees) {
            int age = Period.between(employee.getBirthday(), LocalDate.now()).getYears();
            totalAge += age;
            System.out.println(employee.getName() + " is " + age + " years old.");
        }

        int averageAge = totalAge / employees.size();
        System.out.println("Average Age: " + averageAge);

        // Filter employees whose birthday is on a specific month
    int month= 11;
        Predicate<Employee> isMonth= employee -> employee.getBirthday().getMonthValue()==month;
        employees.stream().
                filter(isMonth).
                forEach(System.out::println);

        System.out.println();
        byMonth(employees);
        System.out.println();
        birthMonthy(employees);
    }

    public static void byMonth(List<Employee> employees) {
        // Group employees by birth month
        Map<Month, Long> employeesByMonth = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getBirthday().getMonth(),
                        Collectors.counting() // Count employees in each group
                ));

        // Display the count of employees in each month
        employeesByMonth.forEach((month, count) -> System.out.println(month + ": " + count));
    }
    public static void birthMonthy(List<Employee> employees) {
        Month currentMonth = LocalDate.now().getMonth();

        // Filter employees whose birthday is in the current month
        List<Employee> employeesWithCurrentMonthBirthday = employees.stream()
                .filter(employee -> employee.getBirthday().getMonth() == currentMonth)
                .collect(Collectors.toList());

        // Display the employees
        System.out.println("Employees with birthdays in the current month (" + currentMonth + "):");
        employeesWithCurrentMonthBirthday.forEach(System.out::println);
    }
}
