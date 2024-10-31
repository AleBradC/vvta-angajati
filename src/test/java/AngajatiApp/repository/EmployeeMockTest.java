package AngajatiApp.repository;

import AngajatiApp.controller.DidacticFunction;
import AngajatiApp.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMockTest {

    AngajatiApp.repository.EmployeeMock repoMock;

    @BeforeEach
    void setUp() {
        repoMock = new EmployeeMock();
    }

    @AfterEach
    void tearDown() {
    }

    // TEMA 2
    @Test
    void addEmployee_TC01() {
        Employee employee = new Employee("Brad", "Alexandra", "1234567890123", DidacticFunction.ASSISTENT, 3000.0);
        assertTrue(repoMock.addEmployee(employee));
    }

    @Test
    void addEmployee_TC02() {
        Employee employee = new Employee("Brad", "Alexandra", "123456789012", DidacticFunction.ASSISTENT, 3000.0);
        assertFalse(repoMock.addEmployee(employee));
    }

    @Test
    void addEmployee_TC03() {
        Employee employee = new Employee("Brad", "Alexandra", "1234567890123", null, 3000.0);
        assertThrows(NullPointerException.class, () -> repoMock.addEmployee(employee));
    }

    @Test
    void addEmployee_TC04() {
        Employee employee = new Employee("Brad", "Alexandra", "1234567890123", DidacticFunction.ASSISTENT, 0.0);
        assertFalse(repoMock.addEmployee(employee));
    }

    // TEMA 3
    @Test
    void modifyEmployee_TC01() {
        Employee mihaela = repoMock.getEmployeeList().stream()
                .filter(emp -> "Dodel".equals(emp.getFirstName()) && "Pacuraru".equals(emp.getLastName()))
                .findFirst()
                .orElse(null);

        assertNotNull(mihaela);
        repoMock.modifyEmployeeFunction(mihaela, DidacticFunction.LECTURER);
        assertEquals(DidacticFunction.LECTURER, mihaela.getFunction());
    }

    @Test
    void modifyEmployee_TC02() {
        List<DidacticFunction> originalFunctions = repoMock.getEmployeeList().stream()
                .map(Employee::getFunction)
                .collect(Collectors.toList());

        repoMock.modifyEmployeeFunction(null, DidacticFunction.LECTURER);
        for (int i = 0; i < repoMock.getEmployeeList().size(); i++) {
            assertEquals(originalFunctions.get(i), repoMock.getEmployeeList().get(i).getFunction());
        }
    }
}