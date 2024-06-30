package com.crio.xcompany.company;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Company {
    private String companyName;
    private Employee founder;
    private Map<String, Employee> employeeBook;

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new LinkedHashMap<>(); // Use LinkedHashMap to maintain insertion order
        employeeBook.put(founder.getName(), founder);
    }

    public static Company create(String companyName, Employee founder) {
        return new Company(companyName, founder);
    }

    public String getCompanyName() {
        return companyName;
    }

    // Method to register an employee
    public void registerEmployee(String employeeName, Gender gender) {
        Employee newEmployee = new Employee(employeeName, gender);
        employeeBook.put(employeeName, newEmployee);
        System.out.println("REGISTER_EMPLOYEE :>");
        System.out.println("EMPLOYEE_REGISTRATION_SUCCEEDED");
    }

    // Method to get an employee
    public Employee getEmployee(String employeeName) {
        return employeeBook.get(employeeName);
    }

    // Method to delete an employee
    public void deleteEmployee(String employeeName) {
        if (employeeBook.containsKey(employeeName)) {
            employeeBook.remove(employeeName);
            System.out.println("DELETE_EMPLOYEE :>");
            System.out.println("EMPLOYEE_DELETION_SUCCEEDED");
        } else {
            System.out.println("DELETE_EMPLOYEE :>");
            System.out.println("EMPLOYEE_NOT_FOUND");
        }
    }

    // Method to assign a manager to an employee
    public void assignManager(String employeeName, String managerName) {
        if (employeeBook.containsKey(employeeName) && employeeBook.containsKey(managerName)) {
            Employee employee = employeeBook.get(employeeName);
            Employee manager = employeeBook.get(managerName);
            employee.setManager(manager);
            System.out.println("ASSIGN_MANAGER :>");
            System.out.println("MANAGER_ASSIGNMENT_SUCCEEDED");
        } else {
            System.out.println("ASSIGN_MANAGER :>");
            System.out.println("EMPLOYEE_NOT_FOUND");
        }
    }

    // Method to get direct reports of a manager
    public List<Employee> getDirectReports(String managerName) {
        List<Employee> directReports = new ArrayList<>();
        if (employeeBook.containsKey(managerName)) {
            for (Employee employee : employeeBook.values()) {
                if (employee.getManager() != null && employee.getManager().getName().equals(managerName)) {
                    directReports.add(employee);
                }
            }
        } else {
            System.out.println("GET_DIRECT_REPORTS :>");
            System.out.println("EMPLOYEE_NOT_FOUND");
        }
        return directReports;
    }

    // Method to get team mates of an employee
public List<Employee> getTeamMates(String employeeName) {
    List<Employee> teamMates = new ArrayList<>();
    Employee employee = employeeBook.get(employeeName);
    if (employee != null && employee.getManager() != null) {
        Employee manager = employee.getManager();

        teamMates.add(manager);

        for (Employee e : employeeBook.values()) {
            if (e.getManager() != null && e.getManager().equals(manager)) {
                teamMates.add(e);
            }
        }
        // Include the manager as a team mate after all other employees managed by the same manager
        
    }
    return teamMates;
}





    // Method to get employee hierarchy under a manager
    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        List<List<Employee>> hierarchy = new ArrayList<>();
        List<Employee> level = new ArrayList<>();
        Employee manager = employeeBook.get(managerName);
        if (manager != null) {
            level.add(manager);
            hierarchy.add(level);
            int levelIndex = 0;
            while (level.size() > 0) {
                List<Employee> nextLevel = new ArrayList<>();
                for (Employee employee : level) {
                    List<Employee> reports = getDirectReports(employee.getName());
                    nextLevel.addAll(reports);
                }
                if (nextLevel.size() > 0) {
                    hierarchy.add(nextLevel);
                    level = nextLevel;
                } else {
                    break;
                }
                levelIndex++;
            }
        }
        return hierarchy;
    }
}