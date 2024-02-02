package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class FunctionalInterfaces {


    public void runExamples() {
        // Example 1: Filter numbers
        List<Integer> numbers = Arrays.asList(14, 21, 30, 45, 50, 63);
        Predicate<Integer> isDivisibleBy7 = n -> (n % 7) == 0;

        List<Integer> divisibleBy7 = numbers.stream().filter(isDivisibleBy7).collect(Collectors.toList());

        System.out.println("Numbers divisible by 7: " + divisibleBy7);


        // Example 2: Create and print employees
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");

        // Supplier for creating a list of employees from the names list
        Supplier<List<Employee>> employeeSupplier = () -> {
            List<Employee> employees = new ArrayList<>();
            names.forEach(name -> employees.add(new Employee(name)));
            return employees;
        };

        // Use the supplier to get the list of employee objects
        List<Employee> employees = employeeSupplier.get();

        // Printing the employees
        employees.forEach(employee -> System.out.println(employee + "\n"));



        // Example 3: Convert to names list
        employees = List.of(
                new Employee("John"),
                new Employee("Jane"),
                new Employee("Jack"),
                new Employee("Joe"),
                new Employee("Jill")
        );

        // Function to convert a list of Employee objects to a list of names
        Function<List<Employee>, List<String>> toNamesList = list -> list.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        List<String> employeeNames = toNamesList.apply(employees);
        System.out.println("Employee Names: ");
        employeeNames.forEach(System.out::println);


        // Example 4: Check if an employee is older than 18

        Predicate<Employee> isOlderThan18 = employee -> Period.between(employee.getBirthday(), LocalDate.now()).getYears() > 18;
        boolean isAdult = isOlderThan18.test(new Employee("John", LocalDate.of(1996, 5, 5)));
        System.out.println("Is adult: " + isAdult);
    }

    public static void main(String[] args) {
        new FunctionalInterfaces().runExamples();
    }
}
